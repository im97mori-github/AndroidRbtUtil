package org.im97mori.rbt.sample;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.descriptor.ClientCharacteristicConfiguration;
import org.im97mori.ble.task.ConnectTask;
import org.im97mori.rbt.ble.RbtBLEConnection;
import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.characteristic.RequestMemoryIndex;

import java.util.LinkedList;
import java.util.List;

public class MemoryDataServiceSampleActivity extends BaseActivity implements View.OnClickListener, SampleCallback {

    private static class TestScanCallback extends ScanCallback {

        final MemoryDataServiceSampleActivity mActivity;
        final RbtAdvertisingDataParser mRbtAdvertisingDataParser;

        private TestScanCallback(MemoryDataServiceSampleActivity activity) {
            mActivity = activity;
            RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
            mRbtAdvertisingDataParser = builder.build();
        }

        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            parse(result);
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {

            for (ScanResult result : results) {
                parse(result);
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
        }

        private void parse(final ScanResult result) {
            ScanRecord record = result.getScanRecord();
            if (record != null) {
                byte[] data = record.getBytes();

                RbtAdvertisingDataParser.RbtAdvertisingDataParseResult rbtAdvertisingDataParseResult = mRbtAdvertisingDataParser.parse(data);
                if (rbtAdvertisingDataParseResult != null && rbtAdvertisingDataParseResult.isRbt()) {

                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mActivity.mRbtBLEConnection = new RbtBLEConnection(mActivity, result.getDevice(), mActivity.mRbtBleCallbackSample);
                                mActivity.mBluetoothLeScanner.stopScan(MemoryDataServiceSampleActivity.TestScanCallback.this);
                                mActivity.mTestScanCallback = null;

                                mActivity.mRbtBLEConnection.connect(ConnectTask.TIMEOUT_MILLIS);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothLeScanner;
    private TestScanCallback mTestScanCallback;
    private RbtBLEConnection mRbtBLEConnection;

    private ListView mListView;
    private Button mConnectDisconnectButton;

    private RbtCallbackSample mRbtBleCallbackSample;
    private ArrayAdapter<Pair<String, String>> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRbtBleCallbackSample = new RbtCallbackSample(this);

        mAdapter = new ArrayAdapter<Pair<String, String>>(this, R.layout.list_child, new LinkedList<Pair<String, String>>()) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View child = convertView;
                if (child == null) {
                    child = getLayoutInflater().inflate(R.layout.list_child, parent, false);
                }
                TextView textView = child.findViewById(R.id.time);
                textView.setText(getItem(position).first);
                textView = child.findViewById(R.id.body);
                textView.setText(getItem(position).second);
                return child;
            }
        };
        mListView = findViewById(android.R.id.list);
        mListView.setAdapter(mAdapter);

        mConnectDisconnectButton = findViewById(R.id.connectDisconnectButton);
        mConnectDisconnectButton.setOnClickListener(this);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null) {
            mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gatt_sample;
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateLayout();
    }

    private void updateLayout() {
        if (mBluetoothAdapter != null && !mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
        } else if (mBluetoothLeScanner == null) {
            mConnectDisconnectButton.setVisibility(View.GONE);
        } else {
            mConnectDisconnectButton.setVisibility(View.VISIBLE);

            if (mRbtBLEConnection == null) {
                mConnectDisconnectButton.setText(R.string.connect);
            } else {
                if (mRbtBLEConnection.isConnected()) {
                    mConnectDisconnectButton.setText(R.string.disconnect);
                } else {
                    mConnectDisconnectButton.setText(R.string.connect);
                }
            }
        }
        invalidateOptionsMenu();
    }

    @Override
    protected void onDestroy() {
        if (mBluetoothLeScanner != null && mTestScanCallback != null) {
            mBluetoothLeScanner.stopScan(mTestScanCallback);
            mTestScanCallback = null;
        }
        if (mRbtBLEConnection != null) {
            mRbtBLEConnection.quit();
            mRbtBLEConnection = null;
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memory_data_service, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setEnabled(mRbtBLEConnection != null && mRbtBLEConnection.isConnected());
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.read_memory_index_information == item.getItemId()) {
            mRbtBLEConnection.readMemoryIndexInformation();
        } else if (R.id.write_request_memory_index == item.getItemId()) {
            mRbtBLEConnection.writeRequestMemoryIndex(new RequestMemoryIndex(29836, 29837, RequestMemoryIndex.DATA_TYPE_SENSING_DATA));
        } else if (R.id.read_memory_status == item.getItemId()) {
            mRbtBLEConnection.readMemoryStatus();
        } else if (R.id.notification_memory_sensing_data == item.getItemId()) {
            mRbtBLEConnection.notificationSettingMemorySensingData(ClientCharacteristicConfiguration.CREATOR.createFromByteArray(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE));
        } else if (R.id.notification_memory_calculation_data == item.getItemId()) {
            mRbtBLEConnection.notificationSettingMemoryCalculationData(ClientCharacteristicConfiguration.CREATOR.createFromByteArray(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE));
        } else if (R.id.notification_memory_sensing_flag == item.getItemId()) {
            mRbtBLEConnection.notificationSettingMemorySensingFlag(ClientCharacteristicConfiguration.CREATOR.createFromByteArray(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE));
        } else if (R.id.notification_memory_calculation_flag == item.getItemId()) {
            mRbtBLEConnection.notificationSettingMemoryCalculationFlag(ClientCharacteristicConfiguration.CREATOR.createFromByteArray(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE));
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (R.id.connectDisconnectButton == v.getId()) {
            if (mRbtBLEConnection == null) {
                if (mBluetoothLeScanner != null) {
                    if (mTestScanCallback == null) {
                        if (hasPermission()) {
                            if (mRbtBLEConnection != null) {
                                mRbtBLEConnection.quit();
                                mRbtBLEConnection = null;
                            }
                            mTestScanCallback = new TestScanCallback(this);
                            mBluetoothLeScanner.startScan(mTestScanCallback);
                        }
                    } else {
                        mBluetoothLeScanner.stopScan(mTestScanCallback);
                        mTestScanCallback = null;
                    }
                }
            } else {
                if (mRbtBLEConnection.isConnected()) {
                    mRbtBLEConnection.quit();
                } else {
                    mRbtBLEConnection.connect(ConnectTask.TIMEOUT_MILLIS);
                }
            }

            updateLayout();
        } else {
            super.onClick(v);
        }
    }

    @Override
    public void onCallbacked(final Pair<String, String> log) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.add(log);
                mListView.smoothScrollToPosition(mAdapter.getCount());

                updateLayout();
            }
        });
    }

}
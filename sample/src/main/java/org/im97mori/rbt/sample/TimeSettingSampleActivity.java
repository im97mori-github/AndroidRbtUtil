package org.im97mori.rbt.sample;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
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

import org.im97mori.ble.BLEConnectionHolder;
import org.im97mori.ble.BLEConstants;
import org.im97mori.ble.task.ConnectTask;
import org.im97mori.rbt.ble.RbtBLEConnection;
import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.characteristic.MemoryStorageInterval;
import org.im97mori.rbt.ble.characteristic.TimeSetting;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class TimeSettingSampleActivity extends BaseActivity implements View.OnClickListener, SampleCallback{

    private static class TestScanCallback extends ScanCallback {

        final TimeSettingSampleActivity mActivity;
        final RbtAdvertisingDataParser mRbtAdvertisingDataParser;

        private TestScanCallback(TimeSettingSampleActivity activity) {
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
                                mActivity.mRbtBLEConnection = BLEConnectionHolder.getInstance(result.getDevice());
                                if (mActivity.mRbtBLEConnection == null) {
                                    mActivity.mRbtBLEConnection = new RbtBLEConnection(mActivity, result.getDevice());
                                    BLEConnectionHolder.addInstance(mActivity.mRbtBLEConnection, true);
                                }
                                mActivity.mRbtBLEConnection.attach(mActivity.mRbtBleCallbackSample);
                                mActivity.mBluetoothLeScanner.stopScan(TimeSettingSampleActivity.TestScanCallback.this);
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
                Pair<String, String> item = getItem(position);
                if (item != null) {
                    TextView textView = child.findViewById(R.id.time);
                    textView.setText(item.first);
                    textView = child.findViewById(R.id.body);
                    textView.setText(item.second);
                }
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
                    if (mRbtBLEConnection.isAttached(mRbtBleCallbackSample)) {
                        mConnectDisconnectButton.setText(R.string.disconnect);
                    } else {
                        mConnectDisconnectButton.setText(R.string.connect);
                    }
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
            mRbtBLEConnection.detach(mRbtBleCallbackSample);
            mRbtBLEConnection = null;
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.time_setting_service, menu);
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
        if (R.id.read_latest_time_counter == item.getItemId()) {
            mRbtBLEConnection.readLatestTimeCounter();
        } else if (R.id.read_time_setting == item.getItemId()) {
            mRbtBLEConnection.readTimeSetting();
        } else if (R.id.write_time_setting == item.getItemId()) {
            mRbtBLEConnection.writeTimeSetting(new TimeSetting(BigInteger.ONE));
        } else if (R.id.read_memory_storage_interval == item.getItemId()) {
            mRbtBLEConnection.readMemoryStorageInterval();
        } else if (R.id.write_memory_storage_interval == item.getItemId()) {
            mRbtBLEConnection.writeMemoryStorageInterval(new MemoryStorageInterval(1));
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (R.id.connectDisconnectButton == v.getId()) {
            if (mRbtBLEConnection == null) {
                BluetoothDevice target = findDevice();
                if (target == null) {
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
                    mRbtBLEConnection = BLEConnectionHolder.getInstance(target);
                    if (mRbtBLEConnection == null) {
                        mRbtBLEConnection = new RbtBLEConnection(this, target);
                        BLEConnectionHolder.addInstance(mRbtBLEConnection, true);
                    }
                    mRbtBLEConnection.attach(mRbtBleCallbackSample);
                    if (mRbtBLEConnection.isConnected()) {
                        mRbtBleCallbackSample.onBLEConnected(Integer.MIN_VALUE, target, null);
                    } else {
                        mRbtBLEConnection.connect(ConnectTask.TIMEOUT_MILLIS);
                    }
                }
            } else {
                if (mRbtBLEConnection.isConnected()) {
                    if (mRbtBLEConnection.isAttached(mRbtBleCallbackSample)) {
                        mRbtBLEConnection.detach(mRbtBleCallbackSample);
                        mRbtBleCallbackSample.onBLEDisconnected(Integer.MIN_VALUE, mRbtBLEConnection.getBluetoothDevice(), BLEConstants.ErrorCodes.UNKNOWN, null);
                    } else {
                        mRbtBLEConnection.attach(mRbtBleCallbackSample);
                        mRbtBleCallbackSample.onBLEConnected(Integer.MIN_VALUE, mRbtBLEConnection.getBluetoothDevice(), null);
                    }
                } else {
                    if (mRbtBLEConnection.isAttached(mRbtBleCallbackSample)) {
                        mRbtBLEConnection.detach(mRbtBleCallbackSample);
                    } else {
                        mRbtBLEConnection.attach(mRbtBleCallbackSample);
                        mRbtBLEConnection.connect(ConnectTask.TIMEOUT_MILLIS);
                    }
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

    private BluetoothDevice findDevice() {
        BluetoothDevice target = null;

        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        if (bluetoothManager != null) {
            List<BluetoothDevice> list = bluetoothManager.getConnectedDevices(BluetoothProfile.GATT);
            for (BluetoothDevice bluetoothDevice : list) {
                RbtBLEConnection rbtBLEConnection = BLEConnectionHolder.getInstance(bluetoothDevice);
                if (rbtBLEConnection != null) {
                    target = bluetoothDevice;
                    break;
                }
            }
        }
        return target;
    }

}

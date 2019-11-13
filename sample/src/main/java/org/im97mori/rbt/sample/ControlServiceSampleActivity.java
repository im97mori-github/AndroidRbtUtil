package org.im97mori.rbt.sample;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
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
import org.im97mori.ble.advertising.filter.AdvertisingDataFilter;
import org.im97mori.ble.task.ConnectTask;
import org.im97mori.rbt.ble.RbtBLEConnection;
import org.im97mori.rbt.ble.advertising.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.advertising.filter.FilteredRbtScanCallback;
import org.im97mori.rbt.ble.characteristic.cs.AccelerationLoggerControl;
import org.im97mori.rbt.ble.characteristic.cs.AdvertiseSetting;
import org.im97mori.rbt.ble.characteristic.cs.InstallationOffset;
import org.im97mori.rbt.ble.characteristic.cs.LedSettingEventState;
import org.im97mori.rbt.ble.characteristic.cs.LedSettingNormalState;
import org.im97mori.rbt.ble.characteristic.cs.LedStateOperation;
import org.im97mori.rbt.ble.characteristic.cs.MemoryReset;
import org.im97mori.rbt.ble.characteristic.cs.ModeChange;

import java.util.LinkedList;
import java.util.List;

public class ControlServiceSampleActivity extends BaseActivity implements View.OnClickListener, SampleCallback {

    @SuppressWarnings("unused")
    private static class TestScanCallback extends FilteredRbtScanCallback {

        private static class Builder extends FilteredRbtScanCallback.Builder {

            private final ControlServiceSampleActivity mActivity;

            Builder(@NonNull ControlServiceSampleActivity activity) {
                mActivity = activity;
            }

            @NonNull
            @Override
            public TestScanCallback build() {
                return new TestScanCallback(mFilterList, mRbtAdvertisingDataParser, mScanCallback, mActivity);
            }
        }

        final ControlServiceSampleActivity mActivity;

        private TestScanCallback(@NonNull List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> filterList, @Nullable RbtAdvertisingDataParser parser, @Nullable ScanCallback scanCallback, @NonNull ControlServiceSampleActivity activity) {
            super(filterList, parser, scanCallback);
            mActivity = activity;
        }

        @Override
        public void onFilteredScanResult(int callbackType, @NonNull ScanResult result, @NonNull RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult) {
            parse(result);
        }

        @Override
        public void onFilteredBatchScanResults(@NonNull List<ScanResult> results, @NonNull List<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> parseResults) {
            for (ScanResult result : results) {
                parse(result);
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
        }

        private void parse(final ScanResult result) {
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
                        mActivity.mBluetoothLeScanner.stopScan(TestScanCallback.this);
                        mActivity.mTestScanCallback = null;

                        mActivity.mRbtBLEConnection.connect(ConnectTask.TIMEOUT_MILLIS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
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
        getMenuInflater().inflate(R.menu.control_service, menu);
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
        if (R.id.read_led_setting_normal_state == item.getItemId()) {
            mRbtBLEConnection.readLedSettingNormalState();
        } else if (R.id.write_led_setting_normal_state == item.getItemId()) {
            mRbtBLEConnection.writeLedSettingNormalState(new LedSettingNormalState(LedSettingNormalState.DISPLAY_RULE_NORMALLY_OFF, 0, 0, 0));
        } else if (R.id.read_led_setting_event_state == item.getItemId()) {
            mRbtBLEConnection.readLedSettingEventState();
        } else if (R.id.write_led_setting_event_state == item.getItemId()) {
            mRbtBLEConnection.writeLedSettingEventState(new LedSettingEventState(LedSettingEventState.DISPLAY_RULE_SOUND_NOISE_EVENT_BIT, 0, 0, 0));
        } else if (R.id.read_led_state_operation == item.getItemId()) {
            mRbtBLEConnection.readLedStateOperation();
        } else if (R.id.write_led_state_operation == item.getItemId()) {
            mRbtBLEConnection.writeLedStateOperation(new LedStateOperation(LedStateOperation.START_UP_RAINBOW, LedStateOperation.ERROR_NONE, LedStateOperation.CONNECTION_NONE));
        } else if (R.id.read_installation_offset == item.getItemId()) {
            mRbtBLEConnection.readInstallationOffset();
        } else if (R.id.write_installation_offset == item.getItemId()) {
            mRbtBLEConnection.writeInstallationOffset(new InstallationOffset(0, 0x0000, 0x0000, 0x0000, 0x0000, 0x0000));
        } else if (R.id.read_advertising_setting == item.getItemId()) {
            mRbtBLEConnection.readAdvertisingSetting();
        } else if (R.id.write_advertising_setting == item.getItemId()) {
            mRbtBLEConnection.writeAdvertisingSetting(new AdvertiseSetting(0x00A0, AdvertiseSetting.ADVERTISING_MODE_SENSOR_DATA_AND_CALCULATION_DATA));
        } else if (R.id.write_memory_reset == item.getItemId()) {
            mRbtBLEConnection.writeMemoryReset(new MemoryReset(MemoryReset.MEMORY_RESET_SENSING_DATA_AREA));
            mRbtBLEConnection.writeMemoryReset(new MemoryReset(MemoryReset.MEMORY_RESET_ACCELERATION_DATA_AREA));
        } else if (R.id.read_mode_change == item.getItemId()) {
            mRbtBLEConnection.readModeChange();
        } else if (R.id.write_mode_change == item.getItemId()) {
            mRbtBLEConnection.writeModeChange(new ModeChange(ModeChange.MODE_CHANGE_NORMAL_MODE));
//            mRbtBLEConnection.writeModeChange(new ModeChange(ModeChange.MODE_CHANGE_ACCELERATION_LOGGER_MODE));
        } else if (R.id.write_acceleration_logger_control == item.getItemId()) {
            mRbtBLEConnection.writeAccelerationLoggerControl(new AccelerationLoggerControl(
                    AccelerationLoggerControl.LOG_START
                    , AccelerationLoggerControl.RANGE_OF_DETECTION_FIXED_VALUE
                    , AccelerationLoggerControl.ODR_400_HZ, 0x0001, 0x2800));
//            mRbtBLEConnection.writeAccelerationLoggerControl(new AccelerationLoggerControl(
//                    AccelerationLoggerControl.LOG_STOP
//                    , AccelerationLoggerControl.RANGE_OF_DETECTION_FIXED_VALUE
//                    , AccelerationLoggerControl.ODR_400_HZ, 0x0001, 0x2800));
        } else if (R.id.read_acceleration_logger_status == item.getItemId()) {
            mRbtBLEConnection.readAccelerationLoggerStatus();
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
                                mTestScanCallback = new TestScanCallback.Builder(this).build();
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
                    }
                    mRbtBLEConnection.attach(mRbtBleCallbackSample);
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

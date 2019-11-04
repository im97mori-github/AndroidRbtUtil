package org.im97mori.rbt.sample;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.im97mori.ble.advertising.filter.AdvertisingDataFilter;
import org.im97mori.rbt.ble.ad.CalculationData;
import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.ad.SensorData;
import org.im97mori.rbt.ble.ad.SensorDataAndCalculationData;
import org.im97mori.rbt.ble.ad.SensorFlagAndCalculationFlag;
import org.im97mori.rbt.ble.ad.SerialNumber;
import org.im97mori.rbt.ble.ad.filter.FilteredRbtScanCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class AdvertisingDataSampleActivity extends BaseActivity implements View.OnClickListener {

    @SuppressWarnings("unused")
    private static class TestScanCallback extends FilteredRbtScanCallback {

        private static class Builder extends FilteredRbtScanCallback.Builder {

            private final AdvertisingDataSampleActivity mActivity;

            Builder(@NonNull AdvertisingDataSampleActivity activity) {
                mActivity = activity;
            }

            @NonNull
            @Override
            public TestScanCallback build() {
                return new TestScanCallback(mFilterList, mRbtAdvertisingDataParser, mScanCallback, mActivity);
            }
        }

        final AdvertisingDataSampleActivity mActivity;

        private TestScanCallback(@NonNull List<AdvertisingDataFilter<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult>> filterList, @Nullable RbtAdvertisingDataParser parser, @Nullable ScanCallback scanCallback, @NonNull AdvertisingDataSampleActivity activity) {
            super(filterList, parser, scanCallback);
            mActivity = activity;
        }

        @Override
        public void onFilteredScanResult(int callbackType, @NonNull ScanResult result, @NonNull RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult) {
            parse(result, parseResult);
        }

        @Override
        public void onFilteredBatchScanResults(@NonNull List<ScanResult> results, @NonNull List<RbtAdvertisingDataParser.RbtAdvertisingDataParseResult> parseResults) {
            for (int i = 0; i < results.size(); i++) {
                parse(results.get(i), parseResults.get(i));
            }
        }

        private void parse(@NonNull ScanResult scanResult, @NonNull RbtAdvertisingDataParser.RbtAdvertisingDataParseResult parseResult) {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd HH:mm:ss", Locale.US);
            String now = format.format(new Date());

            StringBuilder sb = new StringBuilder();
            sb.append(scanResult.getDevice().getAddress());
            sb.append('\n');

            SensorData sensorData = parseResult.getSensorData();
            if (sensorData != null) {
                sb.append("Sensor data");
                sb.append("\nSequence number\n");
                sb.append(sensorData.getSequenceNumber());
                sb.append("\nTemperature(degC)\n");
                sb.append(sensorData.getTemperatureDegC());
                sb.append("\nRelative humidity(%RH)\n");
                sb.append(sensorData.getRelativeHumidityRh());
                sb.append("\nAmbient light(lx)\n");
                sb.append(sensorData.getAmbientLightLx());
                sb.append("\nBarometric pressure(hPa)\n");
                sb.append(sensorData.getBarometricPressureHpa());
                sb.append("\nSound noise(dB)\n");
                sb.append(sensorData.getSoundNoiseDb());
                sb.append("\neTVOC(ppb)\n");
                sb.append(sensorData.getEtvocPpb());
                sb.append("\neCO2(ppm)\n");
                sb.append(sensorData.getEco2Ppm());
            }

            CalculationData calculationData = parseResult.getCalculationData();
            if (calculationData != null) {
                sb.append("Calculation data");
                sb.append("\nSequence number\n");
                sb.append(calculationData.getSequenceNumber());
                sb.append("\nDiscomfort index(unit 0.01)\n");
                sb.append(calculationData.getDiscomfortIndexWithUnit());
                sb.append("\nHeat stroke(degC)\n");
                sb.append(calculationData.getHeatStrokeDegC());
                sb.append("\nVibration information\n");
                sb.append(calculationData.getVibrationInformation());
                sb.append("\nSI value(kine)\n");
                sb.append(calculationData.getSiValueKine());
                sb.append("\nPGA(gal)\n");
                sb.append(calculationData.getPgaGal());
                sb.append("\nSeismic intensity(unit 0.001)\n");
                sb.append(calculationData.getSeismicIntensityWithUnit());
                sb.append("\nAcceleration (X-axis)(gal)\n");
                sb.append(calculationData.getAccelerationXAxisGal());
                sb.append("\nAcceleration (Y-axis)(gal)\n");
                sb.append(calculationData.getAccelerationYAxisGal());
                sb.append("\nAcceleration (Z-axis)(gal)\n");
                sb.append(calculationData.getAccelerationZAxisGal());
            }

            SensorDataAndCalculationData sensorDataAndCalculationData = parseResult.getSensorDataAndCalculationData();
            if (sensorDataAndCalculationData != null) {
                sb.append("Sensor data & Calculation data (Scan rsp)");
                sb.append("\nSequence number\n");
                sb.append(sensorDataAndCalculationData.getSequenceNumber());
                sb.append("\nTemperature(degC)\n");
                sb.append(sensorDataAndCalculationData.getTemperatureDegC());
                sb.append("\nRelative humidity(%RH)\n");
                sb.append(sensorDataAndCalculationData.getRelativeHumidityRh());
                sb.append("\nAmbient light(lx)\n");
                sb.append(sensorDataAndCalculationData.getAmbientLightLx());
                sb.append("\nBarometric pressure(hPa)\n");
                sb.append(sensorDataAndCalculationData.getBarometricPressureHpa());
                sb.append("\nSound noise(dB)\n");
                sb.append(sensorDataAndCalculationData.getSoundNoiseDb());
                sb.append("\neTVOC(ppb)\n");
                sb.append(sensorDataAndCalculationData.getEtvocPpb());
                sb.append("\neCO2(ppm)\n");
                sb.append(sensorDataAndCalculationData.getEco2Ppm());

                sb.append("\nDiscomfort index(unit 0.01)\n");
                sb.append(sensorDataAndCalculationData.getDiscomfortIndexWithUnit());
                sb.append("\nHeat stroke(degC)\n");
                sb.append(sensorDataAndCalculationData.getHeatStrokeDegC());
                sb.append("\nVibration information\n");
                sb.append(sensorDataAndCalculationData.getVibrationInformation());
                sb.append("\nSI value(kine)\n");
                sb.append(sensorDataAndCalculationData.getSiValueKine());
                sb.append("\nPGA(gal)\n");
                sb.append(sensorDataAndCalculationData.getPgaGal());
                sb.append("\nSeismic intensity(unit 0.001)\n");
                sb.append(sensorDataAndCalculationData.getSeismicIntensityWithUnit());
                sb.append("\nAcceleration (X-axis)(gal)\n");
                sb.append(sensorDataAndCalculationData.getAccelerationXAxisGal());
                sb.append("\nAcceleration (Y-axis)(gal)\n");
                sb.append(sensorDataAndCalculationData.getAccelerationYAxisGal());
                sb.append("\nAcceleration (Z-axis)(gal)\n");
                sb.append(sensorDataAndCalculationData.getAccelerationZAxisGal());
            }

            SensorFlagAndCalculationFlag sensorFlagAndCalculationFlag = parseResult.getSensorFlagAndCalculationFlag();
            if (sensorFlagAndCalculationFlag != null) {
                sb.append("Sensor flag & Calculation flag (Scan rsp)");
                sb.append("\nSequence number\n");
                sb.append(Integer.toBinaryString(sensorFlagAndCalculationFlag.getSequenceNumber()));
                sb.append("\nTemperature flag\n");
                sb.append(Integer.toBinaryString(sensorFlagAndCalculationFlag.getTemperatureFlag()));
                sb.append("\nRelative humidity flag\n");
                sb.append(Integer.toBinaryString(sensorFlagAndCalculationFlag.getRelativeHumidityFlag()));
                sb.append("\nAmbient light flag\n");
                sb.append(Integer.toBinaryString(sensorFlagAndCalculationFlag.getAmbientLightFlag()));
                sb.append("\nBarometric pressure flag\n");
                sb.append(Integer.toBinaryString(sensorFlagAndCalculationFlag.getBarometricPressureFlag()));
                sb.append("\nSound noise flag\n");
                sb.append(Integer.toBinaryString(sensorFlagAndCalculationFlag.getSoundNoiseFlag()));
                sb.append("\neTVOC flag\n");
                sb.append(Integer.toBinaryString(sensorFlagAndCalculationFlag.getEtvocFlag()));
                sb.append("\neCO2 flag\n");
                sb.append(Integer.toBinaryString(sensorFlagAndCalculationFlag.getEco2Flag()));

                sb.append("\nDiscomfort index flag\n");
                sb.append(Integer.toBinaryString(sensorFlagAndCalculationFlag.getDiscomfortIndexFlag()));
                sb.append("\nHeat stroke flag\n");
                sb.append(Integer.toBinaryString(sensorFlagAndCalculationFlag.getHeatStrokeFlag()));
                sb.append("\nSI value flag\n");
                sb.append(Integer.toBinaryString(sensorFlagAndCalculationFlag.getSiValueFlag()));
                sb.append("\nPGA flag\n");
                sb.append(Integer.toBinaryString(sensorFlagAndCalculationFlag.getPgaFlag()));
                sb.append("\nSeismic intensity flag\n");
                sb.append(Integer.toBinaryString(sensorFlagAndCalculationFlag.getSeismicIntensityFlag()));
            }

            SerialNumber serialNumber = parseResult.getSerialNumber();
            if (serialNumber != null) {
                sb.append("Sensor data & Calculation data (Scan rsp)");
                sb.append("\nSerial number\n");
                sb.append(serialNumber.getSerialNumber());
                sb.append("\nMemory index (Latest)\n");
                sb.append(serialNumber.getMemoryIndex());
            }

            final Pair<String, String> log = Pair.create(now, sb.toString());

            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mActivity.mAdapter.add(log);
                        mActivity.mListView.smoothScrollToPosition(mActivity.mAdapter.getCount());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothLeScanner;
    private TestScanCallback mFilteredRbtScanCallback;

    private ListView mListView;
    private Button mConnectDisconnectButton;
    private ArrayAdapter<Pair<String, String>> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

            if (mBluetoothLeScanner == null) {
                mConnectDisconnectButton.setText(null);
            } else if (mFilteredRbtScanCallback == null) {
                mConnectDisconnectButton.setText(R.string.scan_start);
            } else {
                mConnectDisconnectButton.setText(R.string.scan_stop);
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (mBluetoothLeScanner != null && mFilteredRbtScanCallback != null) {
            mBluetoothLeScanner.stopScan(mFilteredRbtScanCallback);
            mFilteredRbtScanCallback = null;
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (R.id.connectDisconnectButton == v.getId()) {
            if (mBluetoothLeScanner != null) {
                if (mFilteredRbtScanCallback == null) {
                    if (hasPermission()) {
                        mFilteredRbtScanCallback = new TestScanCallback.Builder(this).build();
                        mBluetoothLeScanner.startScan(mFilteredRbtScanCallback);
                    }
                } else {
                    mBluetoothLeScanner.stopScan(mFilteredRbtScanCallback);
                    mFilteredRbtScanCallback = null;
                }
            }
            updateLayout();
        } else {
            super.onClick(v);
        }
    }
}

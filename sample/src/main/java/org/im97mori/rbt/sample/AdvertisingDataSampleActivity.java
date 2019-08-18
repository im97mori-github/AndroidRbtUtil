package org.im97mori.rbt.sample;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.im97mori.rbt.ble.ad.CalculationData;
import org.im97mori.rbt.ble.ad.RbtAdvertisingDataParser;
import org.im97mori.rbt.ble.ad.SensorData;
import org.im97mori.rbt.ble.ad.SensorDataAndCalculationData;
import org.im97mori.rbt.ble.ad.SensorFlagAndCalculationFlag;
import org.im97mori.rbt.ble.ad.SerialNumber;

import java.util.List;

public class AdvertisingDataSampleActivity extends BaseActivity implements View.OnClickListener {

    private static class TestScanCallback extends ScanCallback {

        final AdvertisingDataSampleActivity mActivity;
        final RbtAdvertisingDataParser mRbtAdvertisingDataParser;

        private TestScanCallback(AdvertisingDataSampleActivity activity) {
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

        private void parse(ScanResult result) {
            ScanRecord record = result.getScanRecord();
            if (record != null) {
                byte[] data = record.getBytes();

                RbtAdvertisingDataParser.RbtAdvertisingDataParseResult rbtAdvertisingDataParseResult = mRbtAdvertisingDataParser.parse(data);
                if (rbtAdvertisingDataParseResult != null && rbtAdvertisingDataParseResult.isRbt()) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(result.getDevice().getAddress());
                    sb.append('\n');

                    SensorData sensorData = rbtAdvertisingDataParseResult.getSensorData();
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

                    CalculationData calculationData = rbtAdvertisingDataParseResult.getCalculationData();
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

                    SensorDataAndCalculationData sensorDataAndCalculationData = rbtAdvertisingDataParseResult.getSensorDataAndCalculationData();
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

                    SensorFlagAndCalculationFlag sensorFlagAndCalculationFlag = rbtAdvertisingDataParseResult.getSensorFlagAndCalculationFlag();
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

                    SerialNumber serialNumber = rbtAdvertisingDataParseResult.getSerialNumber();
                    if (serialNumber != null) {
                        sb.append("Sensor data & Calculation data (Scan rsp)");
                        sb.append("\nSerial number\n");
                        sb.append(serialNumber.getSerialNumber());
                        sb.append("\nMemory index (Latest)\n");
                        sb.append(serialNumber.getMemoryIndex());
                    }

                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mActivity.mResultTextView.setText(sb);
                                mActivity.mBluetoothLeScanner.stopScan(TestScanCallback.this);
                                mActivity.mTestScanCallback = null;
                                mActivity.mStartStopButton.setText(R.string.scan_start);
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

    private Button mStartStopButton;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStartStopButton = findViewById(R.id.startStopButton);
        mResultTextView = findViewById(R.id.resultTextView);

        mStartStopButton.setOnClickListener(this);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null) {
            mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.advertising_data_sample;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mBluetoothAdapter != null && !mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.enable();
        } else if (mBluetoothLeScanner == null) {
            mResultTextView.setText(null);
        } else if (mTestScanCallback == null) {
            mStartStopButton.setText(R.string.scan_start);
        } else {
            mStartStopButton.setText(R.string.scan_stop);
        }
    }

    @Override
    protected void onDestroy() {
        if (mBluetoothLeScanner != null && mTestScanCallback != null) {
            mBluetoothLeScanner.stopScan(mTestScanCallback);
            mTestScanCallback = null;
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (R.id.startStopButton == v.getId()) {
            if (mBluetoothLeScanner != null) {
                if (mTestScanCallback == null) {
                    if (hasPermission()) {
                        mStartStopButton.setText(R.string.scan_stop);
                        mResultTextView.setText(null);
                        mTestScanCallback = new TestScanCallback(this);
                        mBluetoothLeScanner.startScan(mTestScanCallback);
                    }
                } else {
                    mStartStopButton.setText(R.string.scan_start);
                    mBluetoothLeScanner.stopScan(mTestScanCallback);
                    mTestScanCallback = null;
                }
            }
        } else {
            super.onClick(v);
        }
    }
}

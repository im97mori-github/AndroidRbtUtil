# OMRON 2JCIE-BU01 Data Parser

## Prerequire
minSdkVersion 19

## Download
project/build.gradle

    repositories {
        google()
        jcenter()
        maven { url "https://github.com/im97mori-github/maven/raw/master" }
    }

project/module/build.gradle

    dependencies {
        implementation 'org.im97mori:rbt:0.1.0'
    }

## How to use
    class TestScanCallback extends ScanCallback {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            ScanRecord scanRecord = result.getScanRecord();
            if (scanRecord != null) {
                RbtAdvertisingDataParser.Builder builder = new RbtAdvertisingDataParser.Builder(true);
                parser = builder.build();

                RbtAdvertisingDataParser.RbtAdvertisingDataParseResult rbtAdvertisingDataParseResult = parser.parse(scanRecord.getBytes());

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
                System.out.println(sb.toString());
            }
        }

## Note
Current version support only advertising data
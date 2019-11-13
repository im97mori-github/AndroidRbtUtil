package org.im97mori.rbt.ble.characteristic.is;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.im97mori.ble.ByteArrayCreater;
import org.im97mori.ble.ByteArrayInterface;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.im97mori.rbt.RbtConstants.CharacteristicUUID.ERROR_STATUS_CHARACTERISTIC;

/**
 * 2.7.1 Error status (Characteristics UUID: 0x5401)
 */
@SuppressWarnings("WeakerAccess")
public class ErrorStatus implements ByteArrayInterface, Parcelable {

    /**
     * Bit3: Initialization error
     */
    public static final int ERROR_STATUS_INITIALIZATION_ERROR = 0b00001000;

    /**
     * Bit2: Frozen output
     */
    public static final int ERROR_STATUS_FROZEN_OUTPUT = 0b00000100;

    /**
     * Bit1: Sensing data is out of range
     */
    public static final int ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE = 0b00000010;

    /**
     * Bit0: Communication error
     */
    public static final int ERROR_STATUS_COMMUNICATION_ERROR = 0b00000001;

    /**
     * Bit2: Reboot with watchdog
     */
    public static final int ERROR_STATUS_REBOOT_WITH_WATCHDOG = 0b00000100;

    /**
     * Bit1: FLASH memory erase error
     */
    public static final int ERROR_STATUS_FLASH_MEMORY_ERASE_ERROR = 0b00000010;

    /**
     * Bit0: FLASH memory initialization error
     */
    public static final int ERROR_STATUS_FLASH_MEMORY_INITIALIZATION_ERROR = 0b00000001;

    /**
     * @see ByteArrayCreater
     */
    public static final ByteArrayCreater<ErrorStatus> CREATOR = new ByteArrayCreater<ErrorStatus>() {

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public ErrorStatus createFromParcel(@NonNull Parcel in) {
            return new ErrorStatus(in);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public ErrorStatus[] newArray(int size) {
            return new ErrorStatus[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        @NonNull
        public ErrorStatus createFromByteArray(@NonNull byte[] values) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = new BluetoothGattCharacteristic(ERROR_STATUS_CHARACTERISTIC, 0, 0);
            bluetoothGattCharacteristic.setValue(values);
            return new ErrorStatus(bluetoothGattCharacteristic);
        }

    };

    /**
     * Temperature sensor error
     */
    private final int mTemperatureSensorError;

    /**
     * Relative humidity sensor error
     */
    private final int mRelativeHumiditySensorError;

    /**
     * Ambient light sensor error
     */
    private final int mAmbientLightSensorError;

    /**
     * Barometric pressure sensor error
     */
    private final int mBarometricPressureSensorError;

    /**
     * Sound noise sensor error
     */
    private final int mSoundNoiseSensorError;

    /**
     * Acceleration sensor error
     */
    private final int mAccelerationSensorError;

    /**
     * eTVOC sensor error
     */
    private final int mEtvocSensorError;

    /**
     * eCO2 sensor error
     */
    private final int mEco2SensorError;

    /**
     * CPU error
     */
    private final int mCpuError;

    /**
     * Constructor from {@link BluetoothGattCharacteristic}
     *
     * @param bluetoothGattCharacteristic Characteristics UUID: 0x5401
     */
    public ErrorStatus(@NonNull BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mTemperatureSensorError = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);
        mRelativeHumiditySensorError = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 1);
        mAmbientLightSensorError = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 2);
        mBarometricPressureSensorError = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 3);
        mSoundNoiseSensorError = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 4);
        mAccelerationSensorError = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 5);
        mEtvocSensorError = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 6);
        mEco2SensorError = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 7);
        mCpuError = bluetoothGattCharacteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 8);
    }

    /**
     * Constructor from {@link Parcel}
     *
     * @param in Parcel
     */
    private ErrorStatus(@NonNull Parcel in) {
        mTemperatureSensorError = in.readInt();
        mRelativeHumiditySensorError = in.readInt();
        mAmbientLightSensorError = in.readInt();
        mBarometricPressureSensorError = in.readInt();
        mSoundNoiseSensorError = in.readInt();
        mAccelerationSensorError = in.readInt();
        mEtvocSensorError = in.readInt();
        mEco2SensorError = in.readInt();
        mCpuError = in.readInt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(mTemperatureSensorError);
        dest.writeInt(mRelativeHumiditySensorError);
        dest.writeInt(mAmbientLightSensorError);
        dest.writeInt(mBarometricPressureSensorError);
        dest.writeInt(mSoundNoiseSensorError);
        dest.writeInt(mAccelerationSensorError);
        dest.writeInt(mEtvocSensorError);
        dest.writeInt(mEco2SensorError);
        dest.writeInt(mCpuError);
    }

    /**
     * @return Temperature sensor error
     */
    public int getTemperatureSensorError() {
        return mTemperatureSensorError;
    }

    /**
     * check Temperature Initialization error
     *
     * @return {@code true}:Temperature Initialization error bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureSensorInitializationError() {
        return isSensorInitializationError(mTemperatureSensorError);
    }

    /**
     * check Temperature Frozen output
     *
     * @return {@code true}:Temperature Frozen output bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureSensorFrozenOutputError() {
        return isSensorFrozenOutputError(mTemperatureSensorError);
    }

    /**
     * check Temperature Sensing data is out of range
     *
     * @return {@code true}:Temperature Sensing data is out of range bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureSensorSensingDataIsOutOfRangeError() {
        return isSensorSensingDataIsOutOfRangeError(mTemperatureSensorError);
    }

    /**
     * check Temperature Sensing data is out of range
     *
     * @return {@code true}:Temperature Sensing data is out of range bit is 1, {@code false}:bit is 0;
     */
    public boolean isTemperatureSensorCommunicationError() {
        return isSensorCommunicationError(mTemperatureSensorError);
    }

    /**
     * @return Relative humidity sensor error
     */
    public int getRelativeHumiditySensorError() {
        return mRelativeHumiditySensorError;
    }

    /**
     * check Relative humidity Initialization error
     *
     * @return {@code true}:Relative humidity Initialization error bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumiditySensorInitializationError() {
        return isSensorInitializationError(mRelativeHumiditySensorError);
    }

    /**
     * check Relative humidity Frozen output
     *
     * @return {@code true}:Relative humidity Frozen output bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumiditySensorFrozenOutputError() {
        return isSensorFrozenOutputError(mRelativeHumiditySensorError);
    }

    /**
     * check Relative humidity Sensing data is out of range
     *
     * @return {@code true}:Relative humidity Sensing data is out of range bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumiditySensorSensingDataIsOutOfRangeError() {
        return isSensorSensingDataIsOutOfRangeError(mRelativeHumiditySensorError);
    }

    /**
     * check Relative humidity Sensing data is out of range
     *
     * @return {@code true}:Relative humidity Sensing data is out of range bit is 1, {@code false}:bit is 0;
     */
    public boolean isRelativeHumiditySensorCommunicationError() {
        return isSensorCommunicationError(mRelativeHumiditySensorError);
    }

    /**
     * @return Ambient light sensor error
     */
    public int getAmbientLightSensorError() {
        return mAmbientLightSensorError;
    }

    /**
     * check Ambient light Initialization error
     *
     * @return {@code true}:Ambient light Initialization error bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightSensorInitializationError() {
        return isSensorInitializationError(mAmbientLightSensorError);
    }

    /**
     * check Ambient light Frozen output
     *
     * @return {@code true}:Ambient light Frozen output bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightSensorFrozenOutputError() {
        return isSensorFrozenOutputError(mAmbientLightSensorError);
    }

    /**
     * check Ambient light Sensing data is out of range
     *
     * @return {@code true}:Ambient light Sensing data is out of range bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightSensorSensingDataIsOutOfRangeError() {
        return isSensorSensingDataIsOutOfRangeError(mAmbientLightSensorError);
    }

    /**
     * check Ambient light Communication error
     *
     * @return {@code true}:Ambient light Communication error bit is 1, {@code false}:bit is 0;
     */
    public boolean isAmbientLightSensorCommunicationError() {
        return isSensorCommunicationError(mAmbientLightSensorError);
    }

    /**
     * @return Barometric pressure sensor error
     */
    public int getBarometricPressureSensorError() {
        return mBarometricPressureSensorError;
    }

    /**
     * check Barometric pressure Initialization error
     *
     * @return {@code true}:Barometric pressure Initialization error bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureSensorInitializationError() {
        return isSensorInitializationError(mBarometricPressureSensorError);
    }

    /**
     * check Barometric pressure Frozen output
     *
     * @return {@code true}:Barometric pressure Frozen output bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureSensorFrozenOutputError() {
        return isSensorFrozenOutputError(mBarometricPressureSensorError);
    }

    /**
     * check Barometric pressure Sensing data is out of range
     *
     * @return {@code true}:Barometric pressure Sensing data is out of range bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureSensorSensingDataIsOutOfRangeError() {
        return isSensorSensingDataIsOutOfRangeError(mBarometricPressureSensorError);
    }

    /**
     * check Barometric pressure Communication error
     *
     * @return {@code true}:Barometric pressure Communication error bit is 1, {@code false}:bit is 0;
     */
    public boolean isBarometricPressureSensorCommunicationError() {
        return isSensorCommunicationError(mBarometricPressureSensorError);
    }

    /**
     * @return Sound noise sensor error
     */
    public int getSoundNoiseSensorError() {
        return mSoundNoiseSensorError;
    }

    /**
     * check Sound noise Initialization error
     *
     * @return {@code true}:Sound noise Initialization error bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseSensorInitializationError() {
        return isSensorInitializationError(mSoundNoiseSensorError);
    }

    /**
     * check Sound noise Frozen output
     *
     * @return {@code true}:Sound noise Frozen output bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseSensorFrozenOutputError() {
        return isSensorFrozenOutputError(mSoundNoiseSensorError);
    }

    /**
     * check Sound noise Sensing data is out of range
     *
     * @return {@code true}:Sound noise Sensing data is out of range bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseSensorSensingDataIsOutOfRangeError() {
        return isSensorSensingDataIsOutOfRangeError(mSoundNoiseSensorError);
    }

    /**
     * check Sound noise Communication error
     *
     * @return {@code true}:Sound noise Communication error bit is 1, {@code false}:bit is 0;
     */
    public boolean isSoundNoiseSensorCommunicationError() {
        return isSensorCommunicationError(mSoundNoiseSensorError);
    }

    /**
     * @return Acceleration sensor error
     */
    public int getAccelerationSensorError() {
        return mAccelerationSensorError;
    }

    /**
     * check Acceleration Initialization error
     *
     * @return {@code true}:Acceleration Initialization error bit is 1, {@code false}:bit is 0;
     */
    public boolean isAccelerationSensorInitializationError() {
        return isSensorInitializationError(mAccelerationSensorError);
    }

    /**
     * check Acceleration Frozen output
     *
     * @return {@code true}:Acceleration Frozen output bit is 1, {@code false}:bit is 0;
     */
    public boolean isAccelerationSensorFrozenOutputError() {
        return isSensorFrozenOutputError(mAccelerationSensorError);
    }

    /**
     * check Acceleration Sensing data is out of range
     *
     * @return {@code true}:Acceleration Sensing data is out of range bit is 1, {@code false}:bit is 0;
     */
    public boolean isAccelerationSensorSensingDataIsOutOfRangeError() {
        return isSensorSensingDataIsOutOfRangeError(mAccelerationSensorError);
    }

    /**
     * check Acceleration Communication error
     *
     * @return {@code true}:Acceleration Communication error bit is 1, {@code false}:bit is 0;
     */
    public boolean isAccelerationSensorCommunicationError() {
        return isSensorCommunicationError(mAccelerationSensorError);
    }

    /**
     * @return eTVOC sensor error
     */
    public int getEtvocSensorError() {
        return mEtvocSensorError;
    }

    /**
     * check eTVOC Initialization error
     *
     * @return {@code true}:eCO2 Initialization error bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocSensorInitializationError() {
        return isSensorInitializationError(mEtvocSensorError);
    }

    /**
     * check eTVOC Frozen output
     *
     * @return {@code true}:eTVOC Frozen output bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocSensorFrozenOutputError() {
        return isSensorFrozenOutputError(mEtvocSensorError);
    }

    /**
     * check eTVOC Sensing data is out of range
     *
     * @return {@code true}:eTVOC Sensing data is out of range bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocSensorSensingDataIsOutOfRangeError() {
        return isSensorSensingDataIsOutOfRangeError(mEtvocSensorError);
    }

    /**
     * check eTVOC Communication error
     *
     * @return {@code true}:eTVOC Communication error bit is 1, {@code false}:bit is 0;
     */
    public boolean isEtvocSensorCommunicationError() {
        return isSensorCommunicationError(mEtvocSensorError);
    }

    /**
     * @return eCO2 sensor error
     */
    public int getEco2SensorError() {
        return mEco2SensorError;
    }

    /**
     * check eCO2 Initialization error
     *
     * @return {@code true}:eCO2 Initialization error bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2SensorInitializationError() {
        return isSensorInitializationError(mEco2SensorError);
    }

    /**
     * check eCO2 Frozen output
     *
     * @return {@code true}:eCO2 Frozen output bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2SensorFrozenOutputError() {
        return isSensorFrozenOutputError(mEco2SensorError);
    }

    /**
     * check eCO2 Sensing data is out of range
     *
     * @return {@code true}:eCO2 Sensing data is out of range bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2SensorSensingDataIsOutOfRangeError() {
        return isSensorSensingDataIsOutOfRangeError(mEco2SensorError);
    }

    /**
     * check eCO2 Communication error
     *
     * @return {@code true}:eCO2 Communication error bit is 1, {@code false}:bit is 0;
     */
    public boolean isEco2SensorCommunicationError() {
        return isSensorCommunicationError(mEco2SensorError);
    }

    /**
     * @return CPU error
     */
    public int getCpuError() {
        return mCpuError;
    }

    /**
     * check CPU Reboot with watchdog error
     *
     * @return {@code true}:CPU Reboot with watchdog error bit is 1, {@code false}:bit is 0;
     */
    public boolean isCpuRebootWithWatchdogError() {
        return (mCpuError & ERROR_STATUS_REBOOT_WITH_WATCHDOG) != 0;
    }

    /**
     * check CPU FLASH memory erase error
     *
     * @return {@code true}:CPU FLASH memory erase error bit is 1, {@code false}:bit is 0;
     */
    public boolean isCpuFlashMemmoryEraseError() {
        return (mCpuError & ERROR_STATUS_FLASH_MEMORY_ERASE_ERROR) != 0;
    }

    /**
     * check CPU FLASH memoryinitialization error
     *
     * @return {@code true}:CPU FLASH memoryinitialization error bit is 1, {@code false}:bit is 0;
     */
    public boolean isCpuFlashMemoryInitializationError() {
        return (mCpuError & ERROR_STATUS_FLASH_MEMORY_INITIALIZATION_ERROR) != 0;
    }

    /**
     * check Initialization error
     *
     * @param flag check target flag
     * @return {@code true}:target Initialization error bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorInitializationError(int flag) {
        return (flag & ERROR_STATUS_INITIALIZATION_ERROR) != 0;
    }

    /**
     * check Frozen output
     *
     * @param flag check target flag
     * @return {@code true}:target Frozen output error bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorFrozenOutputError(int flag) {
        return (flag & ERROR_STATUS_FROZEN_OUTPUT) != 0;
    }

    /**
     * check Sensing data is out of range
     *
     * @param flag check target flag
     * @return {@code true}:target Sensing data is out of range error bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorSensingDataIsOutOfRangeError(int flag) {
        return (flag & ERROR_STATUS_SENSING_DATA_IS_OUT_OF_RANGE) != 0;
    }

    /**
     * check Communication error
     *
     * @param flag check target flag
     * @return {@code true}:target Communication error bit is 1, {@code false}:bit is 0;
     */
    private boolean isSensorCommunicationError(int flag) {
        return (flag & ERROR_STATUS_COMMUNICATION_ERROR) != 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NonNull
    public byte[] getBytes() {
        byte[] data = new byte[11];
        ByteBuffer byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.put((byte) mTemperatureSensorError);
        byteBuffer.put((byte) mRelativeHumiditySensorError);
        byteBuffer.put((byte) mAmbientLightSensorError);
        byteBuffer.put((byte) mBarometricPressureSensorError);
        byteBuffer.put((byte) mSoundNoiseSensorError);
        byteBuffer.put((byte) mAccelerationSensorError);
        byteBuffer.put((byte) mEtvocSensorError);
        byteBuffer.put((byte) mEco2SensorError);
        byteBuffer.put((byte) mCpuError);
        byteBuffer.put((byte) 0xff);
        byteBuffer.put((byte) 0xff);
        return data;
    }

}

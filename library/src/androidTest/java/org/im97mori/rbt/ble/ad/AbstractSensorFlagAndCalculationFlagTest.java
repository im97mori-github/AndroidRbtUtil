package org.im97mori.rbt.ble.ad;

import org.im97mori.rbt.RbtConstants;

public abstract class AbstractSensorFlagAndCalculationFlagTest {

    static final int ALL_EVENT_FLAG_SENSOR =
            RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1
                    | RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2
                    | RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1
                    | RbtConstants.EventFlagSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2
                    | RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_1
                    | RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_RISE_2
                    | RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_1
                    | RbtConstants.EventFlagSensor.CHANGE_THRESHOLD_DECLINE_2
                    | RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_UPPER
                    | RbtConstants.EventFlagSensor.AVERAGE_VALUE_THRESHOLD_LOWER
                    | RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_UPPER
                    | RbtConstants.EventFlagSensor.PEAK_TO_PEAK_THRESHOLD_LOWER
                    | RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE
                    | RbtConstants.EventFlagSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE
                    | RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_UPPER
                    | RbtConstants.EventFlagSensor.BASE_DIFFERENCE_THRESHOLD_LOWER;

    static final int ALL_EVENT_FLAG_SENSOR_MSB = (ALL_EVENT_FLAG_SENSOR >> 8) & 0xff;
    static final int ALL_EVENT_FLAG_SENSOR_LSB = ALL_EVENT_FLAG_SENSOR & 0xff;

    static final int ALL_EVENT_FLAG_ACCELERATION =
            RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1
                    | RbtConstants.EventFlagAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2
                    | RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_1
                    | RbtConstants.EventFlagAcceleration.CHANGE_THRESHOLD_RISE_2;

}

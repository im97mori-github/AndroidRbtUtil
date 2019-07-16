package org.im97mori.rbt.ble.characteristic;

import org.im97mori.rbt.RbtConstants;

public abstract class AbstractEventEnableDisableTest {

    static final int ALL_EVENT_ENABLE_DISABLE_SENSOR =
            RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_1
                    | RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_UPPER_LIMIT_2
                    | RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_1
                    | RbtConstants.EventEnableDisableSensor.SIMPLE_THRESHOLD_LOWER_LIMIT_2
                    | RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_RISE_1
                    | RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_RISE_2
                    | RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_DECLINE_1
                    | RbtConstants.EventEnableDisableSensor.CHANGE_THRESHOLD_DECLINE_2
                    | RbtConstants.EventEnableDisableSensor.AVERAGE_VALUE_THRESHOLD_UPPER
                    | RbtConstants.EventEnableDisableSensor.AVERAGE_VALUE_THRESHOLD_LOWER
                    | RbtConstants.EventEnableDisableSensor.PEAK_TO_PEAK_THRESHOLD_UPPER
                    | RbtConstants.EventEnableDisableSensor.PEAK_TO_PEAK_THRESHOLD_LOWER
                    | RbtConstants.EventEnableDisableSensor.INTERVAL_DIFFERENCE_THRESHOLD_RISE
                    | RbtConstants.EventEnableDisableSensor.INTERVAL_DIFFERENCE_THRESHOLD_DECLINE
                    | RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_UPPER
                    | RbtConstants.EventEnableDisableSensor.BASE_DIFFERENCE_THRESHOLD_LOWER;

    static final int ALL_EVENT_ENABLE_DISABLE_SENSOR_MSB = (ALL_EVENT_ENABLE_DISABLE_SENSOR >> 8) & 0xff;
    static final int ALL_EVENT_ENABLE_DISABLE_SENSOR_LSB = ALL_EVENT_ENABLE_DISABLE_SENSOR & 0xff;

    static final int ALL_EVENT_ENABLE_DISABLE_ACCELERATION =
            RbtConstants.EventEnableDisableAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_1
                    | RbtConstants.EventEnableDisableAcceleration.SIMPLE_THRESHOLD_UPPER_LIMIT_2
                    | RbtConstants.EventEnableDisableAcceleration.CHANGE_THRESHOLD_RISE_1
                    | RbtConstants.EventEnableDisableAcceleration.CHANGE_THRESHOLD_RISE_2;

}

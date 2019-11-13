package org.im97mori.rbt.ble.task;

import org.im97mori.ble.task.AbstractBLETask;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractRbtTask extends AbstractBLETask {

    /**
     * PROGRESS:CHECK_REQUEST
     */
    public static final int PROGRESS_CHECK_REQUEST = PROGRESS_FIRST_USER;

    /**
     * PROGRESS:BATCH_NOTIFY
     */
    public static final int PROGRESS_BATCH_NOTIFY = PROGRESS_CHECK_REQUEST + 1;

}

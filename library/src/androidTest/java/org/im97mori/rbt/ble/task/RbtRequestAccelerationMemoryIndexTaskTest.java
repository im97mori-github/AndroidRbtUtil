package org.im97mori.rbt.ble.task;

import android.os.Bundle;
import android.os.Message;

import org.im97mori.ble.task.AbstractBLETask;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RbtRequestAccelerationMemoryIndexTaskTest {

    @Test
    public void test_createInitialMessage001() {
        RbtRequestAccelerationMemoryIndexTask task = new RbtRequestAccelerationMemoryIndexTask(null, null, null, null, null);
        Message message = task.createInitialMessage();

        assertNotNull(message);
        Bundle bundle = message.getData();
        assertNotNull(bundle);
        assertTrue(bundle.containsKey(AbstractBLETask.KEY_NEXT_PROGRESS));
        assertEquals(AbstractRbtTask.PROGRESS_CHECK_REQUEST, bundle.getInt(AbstractBLETask.KEY_NEXT_PROGRESS));
        assertEquals(task, message.obj);
    }


    @Test
    public void test_createBatchNotifyMessage001() {
        byte[] original = new byte[0];
        Message message = RbtRequestAccelerationMemoryIndexTask.createBatchNotifyMessage(original);

        assertNotNull(message);
        Bundle bundle = message.getData();
        assertNotNull(bundle);
        assertTrue(bundle.containsKey(AbstractBLETask.KEY_VALUES));
        assertArrayEquals(original, bundle.getByteArray(AbstractBLETask.KEY_VALUES));
        assertTrue(bundle.containsKey(AbstractBLETask.KEY_NEXT_PROGRESS));
        assertEquals(AbstractRbtTask.PROGRESS_BATCH_NOTIFY, bundle.getInt(AbstractBLETask.KEY_NEXT_PROGRESS));
    }

}

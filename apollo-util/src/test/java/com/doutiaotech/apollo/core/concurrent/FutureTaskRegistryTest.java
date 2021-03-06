package com.doutiaotech.apollo.core.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTaskRegistryTest {

    @Test
    public void test() {
        FutureTaskRegistry<Long> registry = new FutureTaskRegistry<>();
        registry.register(1L, new RunningFuture());
        registry.register(2L, new DoneFuture());
        Assert.assertTrue(registry.isRunning(1L));
        Assert.assertFalse(registry.isRunning(2L));
        List<Long> longs = registry.purgeDoneAndRejectRunning(Arrays.asList(1L, 2L, 3L));
        Assert.assertArrayEquals(longs.toArray(), Arrays.asList(2L, 3L).toArray());
        Assert.assertTrue(registry.isRunning(1L));
        Assert.assertFalse(registry.isRunning(2L));
        Assert.assertFalse(registry.isRunning(3L));
    }

    private class RunningFuture extends FutureAdapter {
        @Override
        public boolean isDone() {
            return false;
        }
    }

    private class DoneFuture extends FutureAdapter {

        @Override
        public boolean isDone() {
            return true;
        }
    }

    private abstract class FutureAdapter implements Future<Object> {

        @Override
        public boolean cancel(boolean b) {
            return false;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public Object get() throws InterruptedException, ExecutionException {
            return null;
        }

        @Override
        public Object get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            return null;
        }
    }


}

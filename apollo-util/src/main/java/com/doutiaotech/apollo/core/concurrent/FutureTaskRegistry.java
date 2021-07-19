package com.doutiaotech.apollo.core.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FutureTaskRegistry<K> {

    private ConcurrentMap<K, Future<?>> registry = new ConcurrentHashMap<>();

    public void register(K key, Future<?> futureTask) {
        registry.put(key, futureTask);
    }

    public List<K> purgeDoneAndRejectRunning(Collection<K> keys) {
        purgeDone();
        return keys.stream()
                .filter(((Predicate<K>) registry::containsKey).negate())
                .collect(Collectors.toList());
    }

    public boolean isRunning(K key) {
        Future<?> future = registry.get(key);
        return future != null && !future.isDone();
    }

    private void purgeDone() {
        registry.values().removeIf(Future::isDone);
    }

}

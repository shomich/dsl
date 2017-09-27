package ru.sbt.i9n.o11n.fluent.impl;

import ru.sbt.i9n.o11n.fluent.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by timmy on 12/09/17.
 */
public class MapContext implements Context {

    private final Map<String, Object> container = new HashMap<>();

    @Override
    public void put(String key, Object value) {
        this.container.put(key, value);
    }

    @Override
    public <T> T get(String key) {
        return (T) container.get(key);
    }
}

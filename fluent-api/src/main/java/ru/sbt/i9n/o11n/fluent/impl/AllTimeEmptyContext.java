package ru.sbt.i9n.o11n.fluent.impl;

import ru.sbt.i9n.o11n.fluent.Context;

/**
 * Created by SIGMA\sbt-galiullin-ts on 11.08.17.
 */
public class AllTimeEmptyContext implements Context {
    @Override
    public void put(String key, Object value) {

    }

    @Override
    public <T> T get(String key) {
        return null;
    }
}

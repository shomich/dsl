package ru.sbt.i9n.o11n.fluent;

/**
 * Created by SIGMA\sbt-galiullin-ts on 11.08.17.
 */
public interface Context {

    void put(String key, Object value);

    <T> T get(String key);

}

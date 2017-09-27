package ru.sbt.i9n.o11n.fluent.impl;

import ru.sbt.i9n.o11n.fluent.Map;
import ru.sbt.i9n.o11n.fluent.MessageAndContext;

/**
 * Created by SIGMA\sbt-galiullin-ts on 16.08.17.
 */
public class IdentityMap<T> implements Map<T, T> {

    @Override
    public MessageAndContext<T> map(MessageAndContext<T> in) {
        return in;
    }
}

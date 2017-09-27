package ru.sbt.i9n.o11n.fluent.impl.decider;

import ru.sbt.i9n.o11n.fluent.MessageAndContext;
import ru.sbt.i9n.o11n.fluent.StateDecider;

/**
 * Created by SIGMA\sbt-galiullin-ts on 11.08.17.
 */
public class DirectDecider implements StateDecider {

    private final String next;

    public DirectDecider(String next) {
        this.next = next;
    }

    @Override
    public String next(MessageAndContext messageAndContext) {
        return this.next;
    }
}

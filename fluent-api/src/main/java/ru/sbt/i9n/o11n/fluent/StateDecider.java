package ru.sbt.i9n.o11n.fluent;

/**
 * Created by SIGMA\sbt-galiullin-ts on 11.08.17.
 */
public interface StateDecider<T> extends Action {

    String next(MessageAndContext<T> messageAndContext);

}

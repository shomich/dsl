package ru.sbt.i9n.o11n.fluent;

/**
 * Created by SIGMA\sbt-galiullin-ts on 09.08.17.
 */
public interface Map<T, R> extends Action {

    MessageAndContext<R> map(MessageAndContext<T> in);

}

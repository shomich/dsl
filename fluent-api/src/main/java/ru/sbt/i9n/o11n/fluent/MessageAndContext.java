package ru.sbt.i9n.o11n.fluent;

/**
 * Created by timmy on 17/09/17.
 */
public class MessageAndContext<T> {

    private final T message;
    private final Context context;

    public MessageAndContext(T message, Context context) {
        this.message = message;
        this.context = context;
    }

    public T getMessage() {
        return message;
    }

    public Context getContext() {
        return context;
    }
}

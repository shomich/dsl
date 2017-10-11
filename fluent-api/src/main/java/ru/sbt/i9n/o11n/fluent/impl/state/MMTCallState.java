package ru.sbt.i9n.o11n.fluent.impl.state;

import ru.sbt.i9n.o11n.fluent.MessageAndContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by timmy on 12/10/17.
 */
public class MMTCallState<T, R> extends BaseState<T, R> {

    private final Class<T> api;
    private final String method;
    private final Object[] args;
    private final Class[] methodArgTypes;
    private R result;

    public MMTCallState(Class<T> api, String method, Object... args) {
        this.api = api;
        this.method = method;
        this.args = args;
        this.methodArgTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            methodArgTypes[i] = arg.getClass();
        }

    }

    @Override
    public void execute() {
        try {
            Method method = api.getMethod(this.method, methodArgTypes);
            result = (R) method.invoke(api.newInstance(), args);
            System.out.println("mmt call result " + result);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MessageAndContext<R> out() {
        return new MessageAndContext<R>(result, in().getContext());
    }
}

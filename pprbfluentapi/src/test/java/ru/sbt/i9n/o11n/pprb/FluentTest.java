package ru.sbt.i9n.o11n.pprb;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.i9n.o11n.fluent.Context;
import ru.sbt.i9n.o11n.fluent.FluentFSM;
import ru.sbt.i9n.o11n.fluent.MessageAndContext;
import ru.sbt.i9n.o11n.fluent.impl.MapContext;

/**
 * Created by timmy on 17/09/17.
 */
public class FluentTest {

    @Test
    public void contextDeciderTestLinear() {
        FluentFSM fsm = new PPRBFluentFSM.PPRBFluentFSMBuilder(new IntegerApiFactory())
                .start()
                .<String, String>map(in -> {
                    Context context = in.getContext();
                    context.put("next", "httpCall");

                    return new MessageAndContext<>("resp", context);
                })
                .label("httpCall")
                .httpCall("https://www.random.org/integers/?num=10&min=1&max=6&col=1&base=10&format=plain&rnd=new",
                        (code, response, in) -> {
                            System.out.println("code: " + code + " resp: " + response);
                            if (code == 200)
                                in.getContext().put("next", "lastMap");
                            return in;
                        })

                .mmtCall(Integer.class, (Integer s) -> s.intValue() )
                .route(messageAndContext -> messageAndContext.getContext().get("next"))
                .label("lastMap")
                .finish().build();
        MessageAndContext test = fsm.execute(new MessageAndContext<>("test", new MapContext()));
        Assert.assertEquals(0, test.getMessage());
    }

    private static class IntegerApiFactory extends MMTApiFactory {
        @Override
        public <T> T getApi(Class<T> api) {
            return (T)new Integer(0);
        }
    }
}

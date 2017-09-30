package ru.sbt.i9n.o11n.fluent;

import org.junit.Assert;
import org.junit.Test;
import ru.sbt.i9n.o11n.fluent.impl.MapContext;
import ru.sbt.i9n.o11n.fluent.impl.state.HttpGetState;

/**
 * Created by timmy on 17/09/17.
 */
public class FluentTest {

    @Test
    public void contextDeciderTest() {
        FluentFSM fsm = new FluentFSM.FSMBaseState()
                .start("map")
                .map("map", "http", new Map<String, String>() {
                    @Override
                    public MessageAndContext<String> map(MessageAndContext<String> in) {
                        Context context = in.getContext();
                        context.put("next", "finish");
                        return new MessageAndContext<>(in.getMessage(), context);
                    }
                })
                .httpCall("http", "route", "http://www.google.com/search?q=hui", new HttpGetState.CallbackHandler() {
                    @Override
                    public void onResponse(int code, String response) {
                        System.out.println("code: " + code + " resp: " + response);
                    }
                })
                .route("route", new StateDecider<String>() {
                    @Override
                    public String next(MessageAndContext<String> messageAndContext) {
                        return messageAndContext.getContext().get("next");
                    }
                })
                .finish().build();
        MessageAndContext test = fsm.execute(new MessageAndContext<>("test", new MapContext()));
        Assert.assertEquals("test", test.getMessage());
    }

    @Test
    public void contextDeciderTestLinear() {
        FluentFSMLinear fsm = new FluentFSMLinear.FSMBaseStateLinear()
                .start()
                .map("map", in -> {
                    Context context = in.getContext();
                    context.put("next", "httpCall");
                    return new MessageAndContext<>(in.getMessage(), context);
                })
                .label("httpCall")
                .httpCall("http",
                        "http://www.google.com/search?q=hui",
                        (code, response) -> System.out.println("code: " + code + " resp: " + response))
                .route("route", messageAndContext -> messageAndContext.getContext().get("next"))
                .finish().build();
        MessageAndContext test = fsm.execute(new MessageAndContext<>("test", new MapContext()));
        Assert.assertEquals("test", test.getMessage());
    }
}

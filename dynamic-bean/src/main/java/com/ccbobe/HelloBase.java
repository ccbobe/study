package com.ccbobe;

import org.springframework.stereotype.Component;

/**
 * @author ccbobe
 */
@RoutingClass(name = "helloBase",values = {""},version = "1")
@Component
public class HelloBase implements BaseRun,BaseBofore,BaseAfter {




    @Override
    public Context before(Context context) {
        System.out.println("before.....");
        context.setBefore("before");
        return context;
    }

    @Override
    public Context run(Context context) {
        System.out.println("afterRun.....");
        context.setResult("run");
        return context;
    }

    @Override
    public Context afterRun(Context context) {
        System.out.println("afterRun.....");
        context.setAfter("afterRun");
        return context;
    }


}

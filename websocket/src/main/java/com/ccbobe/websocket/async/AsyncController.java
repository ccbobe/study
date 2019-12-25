package com.ccbobe.websocket.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async/")
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("asyncPrimary")
    public String asyncPrimary(){
        asyncService.asyncPrimary();
        return "ok";
    }

    @RequestMapping("asyncOther")
    public String asyncOther(){
        asyncService.asyncPools();
        return "ok";
    }
}

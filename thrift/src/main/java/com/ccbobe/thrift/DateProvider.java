package com.ccbobe.thrift;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

import java.util.Date;
import java.util.UUID;

public class DateProvider  implements DateService.Iface,DateService.AsyncIface {

    @Override
    public Response getDate() throws TException {
        Response response = new Response();
        response.setCode("date");
        response.setMsgId(UUID.randomUUID().toString().replace("-","").toUpperCase());
        response.setData(new Date().toString());
        return response;
    }

    @Override
    public void getDate(AsyncMethodCallback<Response> resultHandler) throws TException {
        Response response = new Response();
        response.setCode("date");
        response.setMsgId(UUID.randomUUID().toString().replace("-","").toUpperCase());
        response.setData(new Date().toString());
        resultHandler.onComplete(response);
    }
}

package org.apache.catalina.controller;

import org.apache.coyote.http11.request.HttpRequest;
import org.apache.coyote.http11.response.HttpResponse;

public abstract class AbstractController implements Controller {

    @Override
    public void service(HttpRequest request, HttpResponse response) {

    }

    protected void doPost(HttpRequest request, HttpResponse response) throws Exception {

    }

    protected void doGet(HttpRequest request, HttpResponse response) throws Exception {

    }
}

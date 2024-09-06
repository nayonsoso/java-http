package org.apache.coyote.http11;

import java.util.Map;

public record HttpResponse(
        HttpResponseStatusLine statusLine,
        Map<String, String> headers,
        String body) {

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append(statusLine)
                .append(System.lineSeparator());

        headers.forEach((key, value) -> stringBuilder
                .append(key)
                .append(": ")
                .append(value)
                .append(System.lineSeparator()));

        stringBuilder
                .append(System.lineSeparator())
                .append(body);
        return stringBuilder.toString();
    }
}

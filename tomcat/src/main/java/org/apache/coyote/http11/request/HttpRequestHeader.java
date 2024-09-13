package org.apache.coyote.http11.request;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public record HttpRequestHeader(Map<String, String> headers) {

    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String COOKIE = "Cookie";
    public static final String JSESSION_ID = "JSESSIONID";

    public HttpRequestHeader(String request) {
        this(
                extractHeaders(request)
        );
    }

    private static Map<String, String> extractHeaders(String requestHeader) {
        String[] lines = requestHeader.split(System.lineSeparator());
        return Arrays.stream(lines)
                .skip(1)
                .map(line -> line.split(": "))
                .filter(parts -> parts.length == 2)
                .collect(Collectors.toMap(parts -> parts[0], parts -> parts[1]));
    }

    public boolean hasContentLength(){
        return headers.containsKey(CONTENT_LENGTH);
    }

    public Optional<String> getJSessionIdCookie() {
        String cookie = headers.get(COOKIE);
        if (cookie == null) {
            return Optional.empty();
        }

        return Arrays.stream(cookie.split(";"))
                .map(String::trim)
                .map(part -> part.split("="))
                .filter(parts -> parts.length == 2)
                .filter(parts -> parts[0].equals(JSESSION_ID))
                .map(parts -> parts[1])
                .findFirst();
    }

    public int getContentLength() {
        return Integer.parseInt(headers.get(CONTENT_LENGTH));
    }

    public String getContentType() {
        return headers.get(CONTENT_TYPE);
    }
}

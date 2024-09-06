package org.apache.coyote.session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager {

    private static final Map<String, Session> sessionStorage = new HashMap<>();

    private SessionManager() {}

    public static synchronized String add(Session session) {
        UUID uuid = UUID.randomUUID();
        sessionStorage.put(uuid.toString(), session);

        return uuid.toString();
    }

    public static Session findSession(String id) {
        return sessionStorage.get(id);
    }
}

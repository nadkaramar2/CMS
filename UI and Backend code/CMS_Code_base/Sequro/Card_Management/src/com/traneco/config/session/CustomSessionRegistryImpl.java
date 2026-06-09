package com.traneco.config.session;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Component;

@Component
public class CustomSessionRegistryImpl extends SessionRegistryImpl
{

    private final Map<String, SessionInformation> sessions = new ConcurrentHashMap<>();

    @Override
    public List<Object> getAllPrincipals() {
        return sessions.values().stream()
                .map(SessionInformation::getPrincipal)
                .collect(Collectors.toList());
    }

    @Override
    public List<SessionInformation> getAllSessions(Object principal, boolean includeExpiredSessions) {
        return sessions.values().stream()
                .filter(session -> session.getPrincipal().equals(principal))
                .filter(session -> includeExpiredSessions || !session.isExpired())
                .collect(Collectors.toList());
    }

    @Override
    public SessionInformation getSessionInformation(String sessionId) {
        return sessions.get(sessionId);
    }

    @Override
    public void refreshLastRequest(String sessionId) {
        SessionInformation session = sessions.get(sessionId);
        if (session != null) {
            session.refreshLastRequest();
        }
    }

    @Override
    public void registerNewSession(String sessionId, Object principal) {
        if (sessionId == null || principal == null) {
            throw new IllegalArgumentException("Session ID or principal cannot be null");
        }
        if (sessions.containsKey(sessionId)) {
            throw new IllegalArgumentException("Session ID already exists: " + sessionId);
        }
        sessions.put(sessionId, new SessionInformation(principal, sessionId, new Date()));
    }

    @Override
    public void removeSessionInformation(String sessionId) {
        sessions.remove(sessionId);
    }

    public void removeSessionInformation(Object principal) {
        List<SessionInformation> sessionsToRemove = getAllSessions(principal, true);
        for (SessionInformation session : sessionsToRemove) {
            sessions.remove(session.getSessionId());
        }
    }

}

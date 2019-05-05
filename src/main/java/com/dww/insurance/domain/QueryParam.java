package com.dww.insurance.domain;

public class QueryParam {
    private final String surname;
    private final String ownerId;
    private final Object bodyId;

    public QueryParam(String surname, String ownerId, Object bodyId) {

        this.surname = surname;
        this.ownerId = ownerId;
        this.bodyId = bodyId;
    }

    public String getSurname() {
        return surname;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Object getBodyId() {
        return bodyId;
    }
}

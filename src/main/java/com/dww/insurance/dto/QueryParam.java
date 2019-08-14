package com.dww.insurance.dto;

public class QueryParam {
    private final String surname;
    private final String ownerId;
    private final String bodyId;

    public QueryParam(String surname, String ownerId, String bodyId) {

        this.surname = surname;
        this.ownerId = ownerId;
        this.bodyId = bodyId;
    }

    public String getSurname() {
        return surname;
    }

    public int getOwnerId() {
        return ownerId == null || "".equals(ownerId) ? 0 : Integer.valueOf(ownerId);
    }

    public String getBodyId() {
        return bodyId;
    }
}

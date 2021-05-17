package com.example.clinic;

public class UrlMapping {
    public static final String API_PATH = "/api";
    public static final String PATIENTS = "/patients";
    public static final String CONSULTATIONS = "/consultations";
    public static final String DOCTORS = API_PATH + "/doctors";
    public static final String SECRETARIES = API_PATH + "/secretaries";

    public static final String ENTITY = "/{id}";

    public static final String FILTERED = "/filtered";

    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String USERS = API_PATH + "/users";
}

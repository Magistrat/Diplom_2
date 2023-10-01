package com.storage;

public interface SettingsInterface {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    public static final String REGISTER_USER_URL = "/api/auth/register";
    public static final String DELETE_USER_URL = "/api/auth/register";

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";

    public static final int SUCCESS_STATUS_CODE = 200;
    public static final int FORBIDDEN_STATUS_CODE = 403;
}

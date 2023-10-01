package com.storage;

public interface SettingsInterface {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    public static final String REGISTER_USER_URL = "/api/auth/register";
    public static final String DELETE_USER_URL = "/api/auth/register";
    public static final String LOGIN_USER_URL = "/api/auth/login";
    public static final String UPDATE_USER_URL = "/api/auth/user";
    public static final String ALL_INGREDIENTS_URL = "/api/ingredients";
    public static final String CREATE_ORDER_URL = "/api/orders";

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";

    public static final int SUCCESS_STATUS_CODE = 200;
    public static final int UNAUTHORIZED_STATUS_CODE = 401;
    public static final int FORBIDDEN_STATUS_CODE = 403;
}

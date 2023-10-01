package com.storage.pojo.login.positive;

import com.storage.pojo.login.positive.LoginPositiveResponseUserPojo;

public class LoginPositiveResponseAllPojo {
    // Pojo для позитивной авторизации пользователя (получение JSON)

    private boolean success;
    private String accessToken;
    private String refreshToken;
    private LoginPositiveResponseUserPojo user;

}

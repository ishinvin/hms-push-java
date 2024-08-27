/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2019-2024. All rights reserved.
 */

package io.github.ishinvin.push.util;

import io.github.ishinvin.push.messaging.HuaweiApp;
import io.github.ishinvin.push.messaging.HuaweiCredential;
import io.github.ishinvin.push.messaging.HuaweiOption;

public class InitAppUtils {

    // public static HuaweiApp initializeApp() {
    //     String appId = ResourceBundle.getBundle("url").getString("appid");
    //     String appSecret = ResourceBundle.getBundle("url").getString("appsecret");
    //     // Create HuaweiCredential
    //     // This appId and appSecret come from Huawei Developer Alliance
    //     return initializeApp(appId, appSecret);
    // }

    public static HuaweiApp initializeApp(String appId, String appSecret) {
        HuaweiCredential credential = HuaweiCredential.builder()
            .setAppId(appId)
            .setAppSecret(appSecret)
            .build();

        // Create HuaweiOption
        HuaweiOption option = HuaweiOption.builder()
            .setCredential(credential)
            .build();

        return HuaweiApp.getInstance(option);
    }
}

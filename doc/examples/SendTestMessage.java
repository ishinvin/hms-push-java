/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package io.github.ishinvin.push.examples;

import com.alibaba.fastjson.JSONObject;
import io.github.ishinvin.push.android.AndroidNotification;
import io.github.ishinvin.push.android.BadgeNotification;
import io.github.ishinvin.push.android.ClickAction;
import io.github.ishinvin.push.android.Color;
import io.github.ishinvin.push.android.LightSettings;
import io.github.ishinvin.push.exception.HuaweiMesssagingException;
import io.github.ishinvin.push.message.AndroidConfig;
import io.github.ishinvin.push.message.Message;
import io.github.ishinvin.push.message.Notification;
import io.github.ishinvin.push.messaging.HuaweiApp;
import io.github.ishinvin.push.messaging.HuaweiMessaging;
import io.github.ishinvin.push.model.Importance;
import io.github.ishinvin.push.model.Urgency;
import io.github.ishinvin.push.model.Visibility;
import io.github.ishinvin.push.reponse.SendResponse;
import io.github.ishinvin.push.util.InitAppUtils;

public class SendTestMessage {
    public void sendTestMessage() throws HuaweiMesssagingException {
        HuaweiApp app = InitAppUtils.initializeApp();
        HuaweiMessaging huaweiMessaging = HuaweiMessaging.getInstance(app);

        Notification notification = Notification.builder().setTitle("sample title")
            .setBody("sample message body")
            .build();
        
        JSONObject multiLangKey = new JSONObject();
        JSONObject titleKey = new JSONObject();
        titleKey.put("en", "好友请求");
        JSONObject bodyKey = new JSONObject();
        bodyKey.put("en", "My name is %s, I am from %s.");
        multiLangKey.put("key1", titleKey);
        multiLangKey.put("key2", bodyKey);

        LightSettings lightSettings = LightSettings.builder().setColor(Color.builder().setAlpha(0f).setRed(0f).setBlue(1f).setGreen(1f).build())
            .setLightOnDuration("3.5")
            .setLightOffDuration("5S")
            .build();

        AndroidNotification androidNotification = AndroidNotification.builder().setIcon("/raw/ic_launcher2")
            .setColor("#AACCDD")
            .setSound("/raw/shake")
            .setDefaultSound(true)
            .setTag("tagBoom")
            .setClickAction(ClickAction.builder().setType(2).setUrl("https://www.huawei.com").build())
            .setBodyLocKey("key2")
            .addBodyLocArgs("boy").addBodyLocArgs("dog")
            .setTitleLocKey("key1")
            .addTitleLocArgs("Girl").addTitleLocArgs("Cat")
            .setChannelId("Your Channel ID")
            .setNotifySummary("some summary")
            .setMultiLangkey(multiLangKey)
            .setStyle(1)
            .setBigTitle("Big Boom Title")
            .setBigBody("Big Boom Body")
            .setAutoClear(86400000)
            .setNotifyId(486)
            .setGroup("Group1")
            .setImportance(Importance.LOW.getValue())
            .setLightSettings(lightSettings)
            .setBadge(BadgeNotification.builder().setAddNum(1).setBadgeClass("Classic").build())
            .setVisibility(Visibility.PUBLIC.getValue())
            .setForegroundShow(true)
            .build();

        AndroidConfig androidConfig = AndroidConfig.builder().setCollapseKey(-1)
            .setUrgency(Urgency.HIGH.getValue())
            .setTtl("10000s")
            .setBiTag("the_sample_bi_tag_for_receipt_service")
            .setNotification(androidNotification)
            .build();

        Message message = Message.builder().setNotification(notification)
            .setAndroidConfig(androidConfig)
            .addToken(
                "AND8rUp4etqJvbakK7qQoCVgFHnROXzH8o7B8fTl9rMP5VRFN83zU3Nvmabm3xw7e3gZjyBbp_wfO1jP-UyDQcZN_CtjBpoa7nx1WaVFe_3mqXMJ6nXJNUZcDyO_-k3sSw")
            .build();

        SendResponse response = huaweiMessaging.sendMessage(message, true);
    }
}

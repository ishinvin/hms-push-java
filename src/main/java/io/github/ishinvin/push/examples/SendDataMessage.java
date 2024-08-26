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

import io.github.ishinvin.push.exception.HuaweiMesssagingException;
import io.github.ishinvin.push.message.AndroidConfig;
import io.github.ishinvin.push.message.Message;
import io.github.ishinvin.push.messaging.HuaweiApp;
import io.github.ishinvin.push.messaging.HuaweiMessaging;
import io.github.ishinvin.push.model.Urgency;
import io.github.ishinvin.push.reponse.SendResponse;
import io.github.ishinvin.push.util.InitAppUtils;

public class SendDataMessage {
    /**
     * send data message
     */
    public void sendTransparent() throws HuaweiMesssagingException {
        HuaweiApp app = InitAppUtils.initializeApp();
        HuaweiMessaging huaweiMessaging = HuaweiMessaging.getInstance(app);

        AndroidConfig androidConfig = AndroidConfig.builder().setCollapseKey(-1)
            .setUrgency(Urgency.HIGH.getValue())
            .setTtl("10000s")
            .setBiTag("the_sample_bi_tag_for_receipt_service")
            .build();

        String token =
            "AND8rUp4etqJvbakK7qQoCVgFHnROXzH8o7B8fTl9rMP5VRFN83zU3Nvmabm3xw7e3gZjyBbp_wfO1jP-UyDQcZN_CtjBpoa7nx1WaVFe_3mqXMJ6nXJNUZcDyO_-k3sSw";

        Message message = Message.builder()
            .setData("{'key1':'value1', 'key2':'value2'}")
            .setAndroidConfig(androidConfig)
            .addToken(token)
            .build();

        SendResponse response = huaweiMessaging.sendMessage(message);
    }
}

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

package io.github.ishinvin.push.apns;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.ishinvin.push.util.ValidatorUtils;

public class Aps {
    @JSONField(name = "alert")
    private final Object alert;

    @JSONField(name = "badge")
    private final Integer badge;

    @JSONField(name = "sound")
    private final String sound;

    @JSONField(name = "content-available")
    private final Integer contentAvailable;

    @JSONField(name = "category")
    private final String category;

    @JSONField(name = "thread-id")
    private final String threadId;

    private Aps(Builder builder) {
        this.alert = builder.alert;
        this.badge = builder.badge;
        this.sound = builder.sound;
        this.contentAvailable = builder.contentAvailable;
        this.category = builder.category;
        this.threadId = builder.threadId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Object getAlert() {
        return alert;
    }

    public Integer getBadge() {
        return badge;
    }

    public String getSound() {
        return sound;
    }

    public Integer getContentAvailable() {
        return contentAvailable;
    }

    public String getCategory() {
        return category;
    }

    public String getThreadId() {
        return threadId;
    }

    public void check() {
        if (this.alert != null) {
            if (this.alert instanceof Alert) {
                ((Alert) this.alert).check();
            } else {
                ValidatorUtils.checkArgument((this.alert instanceof String), "Alter should be Dictionary or String");
            }
        }
    }

    public static class Builder {
        private Object alert;
        private Integer badge;
        private String sound;
        private Integer contentAvailable;
        private String category;
        private String threadId;

        public Builder setAlert(Object alert) {
            this.alert = alert;
            return this;
        }

        public Builder setBadge(Integer badge) {
            this.badge = badge;
            return this;
        }

        public Builder setSound(String sound) {
            this.sound = sound;
            return this;
        }

        public Builder setContentAvailable(Integer contentAvailable) {
            this.contentAvailable = contentAvailable;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setThreadId(String threadId) {
            this.threadId = threadId;
            return this;
        }

        public Aps build() {
            return new Aps(this);
        }
    }
}

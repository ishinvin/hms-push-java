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

package io.github.ishinvin.push.webpush;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.ishinvin.push.util.ValidatorUtils;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;

public class WebHmsOptions {
    @JSONField(name = "link")
    private final String link;

    public WebHmsOptions(Builder builder) {
        this.link = builder.link;
    }

    /**
     * builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public String getLink() {
        return link;
    }

    public void check() {
        if (!StringUtils.isEmpty(this.link)) {
            try {
                new URL(link);
            } catch (MalformedURLException e) {
                ValidatorUtils.checkArgument(false, "Invalid link");
            }
        }
    }

    public static class Builder {
        private String link;

        public Builder setLink(String link) {
            this.link = link;
            return this;
        }

        public WebHmsOptions build() {
            return new WebHmsOptions(this);
        }
    }
}

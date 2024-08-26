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
import io.github.ishinvin.push.util.CollectionUtils;
import io.github.ishinvin.push.util.ValidatorUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Alert {
    @JSONField(name = "title")
    private final String title;

    @JSONField(name = "body")
    private final String body;

    @JSONField(name = "title-loc-key")
    private final String titleLocKey;
    @JSONField(name = "action-loc-key")
    private final String actionLocKey;
    @JSONField(name = "loc-key")
    private final String locKey;
    @JSONField(name = "launch-image")
    private final String launchImage;
    @JSONField(name = "title-loc-args")
    private List<String> titleLocArgs = new ArrayList<String>();
    @JSONField(name = "loc-args")
    private List<String> locArgs = new ArrayList<String>();

    private Alert(Builder builder) {
        this.title = builder.title;
        this.body = builder.body;
        this.titleLocKey = builder.titleLocKey;
        if (!CollectionUtils.isEmpty(builder.titleLocArgs)) {
            this.titleLocArgs.addAll(builder.titleLocArgs);
        } else {
            this.titleLocArgs = null;
        }
        this.actionLocKey = builder.actionLocKey;
        this.locKey = builder.locKey;
        if (!CollectionUtils.isEmpty(builder.locArgs)) {
            this.locArgs.addAll(builder.locArgs);
        } else {
            this.locArgs = null;
        }
        this.launchImage = builder.launchImage;
    }

    /**
     * builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getTitleLocKey() {
        return titleLocKey;
    }

    public List<String> getTitleLocArgs() {
        return titleLocArgs;
    }

    public String getActionLocKey() {
        return actionLocKey;
    }

    public String getLocKey() {
        return locKey;
    }

    public List<String> getLocArgs() {
        return locArgs;
    }

    public String getLaunchImage() {
        return launchImage;
    }

    public void check() {
        if (!CollectionUtils.isEmpty(this.locArgs)) {
            ValidatorUtils.checkArgument(StringUtils.isNotEmpty(this.locKey), "locKey is required when specifying locArgs");
        }
        if (!CollectionUtils.isEmpty(this.titleLocArgs)) {
            ValidatorUtils.checkArgument(StringUtils.isNotEmpty(this.titleLocKey), "titleLocKey is required when specifying titleLocArgs");
        }
    }

    public static class Builder {
        private final List<String> titleLocArgs = new ArrayList<String>();
        private final List<String> locArgs = new ArrayList<String>();
        private String title;
        private String body;
        private String titleLocKey;
        private String actionLocKey;
        private String locKey;
        private String launchImage;

        public Builder setTitle(String title) {
            this.title = title;
            return this;

        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setTitleLocKey(String titleLocKey) {
            this.titleLocKey = titleLocKey;
            return this;
        }

        public Builder setAddAllTitleLocArgs(List<String> titleLocArgs) {
            this.titleLocArgs.addAll(titleLocArgs);
            return this;
        }

        public Builder setAddTitleLocArg(String titleLocArg) {
            this.titleLocArgs.add(titleLocArg);
            return this;
        }

        public Builder setActionLocKey(String actionLocKey) {
            this.actionLocKey = actionLocKey;
            return this;
        }

        public Builder setLocKey(String locKey) {
            this.locKey = locKey;
            return this;
        }

        public Builder addAllLocArgs(List<String> locArgs) {
            this.locArgs.addAll(locArgs);
            return this;
        }

        public Builder addLocArg(String locArg) {
            this.locArgs.add(locArg);
            return this;
        }

        public Builder setLaunchImage(String launchImage) {
            this.launchImage = launchImage;
            return this;
        }

        public Alert build() {
            return new Alert(this);
        }
    }
}

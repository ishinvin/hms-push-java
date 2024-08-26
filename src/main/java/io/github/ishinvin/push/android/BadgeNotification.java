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

package io.github.ishinvin.push.android;

import com.alibaba.fastjson.annotation.JSONField;
import io.github.ishinvin.push.util.ValidatorUtils;

public class BadgeNotification {
    @JSONField(name = "add_num")
    private final Integer addNum;

    @JSONField(name = "class")
    private final String badgeClass;

    @JSONField(name = "set_num")
    private Integer setNum;

    public BadgeNotification(Integer addNum, String badgeClass) {
        this.addNum = addNum;
        this.badgeClass = badgeClass;
    }

    public BadgeNotification(Builder builder) {
        this.addNum = builder.addNum;
        this.badgeClass = builder.badgeClass;
        this.setNum = builder.setNum;
    }

    /**
     * builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public Integer getAddNum() {
        return addNum;
    }

    public String getBadgeClass() {
        return badgeClass;
    }

    public Integer getSetNum() {
        return setNum;
    }

    public void check() {
        if (this.addNum != null) {
            ValidatorUtils.checkArgument(this.addNum > 0 && this.addNum < 100, "add_num should locate between 0 and 100");
        }
        if (this.setNum != null) {
            ValidatorUtils.checkArgument(this.setNum >= 0 && this.setNum < 100, "set_num should locate between 0 and 100");
        }
    }

    public static class Builder {
        private Integer addNum;

        private String badgeClass;

        private Integer setNum;

        public Builder setAddNum(Integer addNum) {
            this.addNum = addNum;
            return this;
        }

        public Builder setSetNum(Integer setNum) {
            this.setNum = setNum;
            return this;
        }

        public Builder setBadgeClass(String badgeClass) {
            this.badgeClass = badgeClass;
            return this;
        }

        public BadgeNotification build() {
            return new BadgeNotification(this);
        }
    }


}

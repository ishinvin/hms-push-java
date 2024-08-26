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

public class Color {

    @JSONField(name = "alpha")
    private final Float alpha;

    @JSONField(name = "red")
    private final Float red;

    @JSONField(name = "green")
    private final Float green;

    @JSONField(name = "blue")
    private final Float blue;

    public Color(Builder builder) {
        this.alpha = builder.alpha;
        this.red = builder.red;
        this.green = builder.green;
        this.blue = builder.blue;
    }

    /**
     * builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public double getAlpha() {
        return alpha;
    }

    public Float getRed() {
        return red;
    }

    public Float getGreen() {
        return green;
    }

    public Float getBlue() {
        return blue;
    }

    public void check() {
        float zero = -0.000001f;
        float one = 1.000001f;
        ValidatorUtils.checkArgument(this.alpha > zero && this.alpha < one, "Alpha shoube locate between [0,1]");
        ValidatorUtils.checkArgument(this.red > zero && this.red < one, "Red shoube locate between [0,1]");
        ValidatorUtils.checkArgument(this.green > zero && this.green < one, "Green shoube locate between [0,1]");
        ValidatorUtils.checkArgument(this.blue > zero && this.blue < one, "Blue shoube locate between [0,1]");
    }

    public static class Builder {
        private Float alpha = 1.0f;
        private Float red = 0.0f;
        private Float green = 0.0f;
        private Float blue = 0.0f;

        public Builder setAlpha(Float alpha) {
            this.alpha = alpha;
            return this;
        }

        public Builder setRed(Float red) {
            this.red = red;
            return this;
        }

        public Builder setGreen(Float green) {
            this.green = green;
            return this;
        }

        public Builder setBlue(Float blue) {
            this.blue = blue;
            return this;
        }

        public Color build() {
            return new Color(this);
        }
    }
}

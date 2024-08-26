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

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.github.ishinvin.push.message.Notification;
import io.github.ishinvin.push.model.Importance;
import io.github.ishinvin.push.model.Visibility;
import io.github.ishinvin.push.util.CollectionUtils;
import io.github.ishinvin.push.util.ValidatorUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;


public class AndroidNotification {

    private static final String COLOR_PATTERN = "^#[0-9a-fA-F]{6}$";

    private static final String URL_PATTERN = "^https.*";

    private static final String VIBRATE_PATTERN = "[0-9]+|[0-9]+[sS]|[0-9]+[.][0-9]{1,9}|[0-9]+[.][0-9]{1,9}[sS]";

    @JSONField(name = "title")
    private final String title;

    @JSONField(name = "body")
    private final String body;

    @JSONField(name = "icon")
    private final String icon;

    @JSONField(name = "color")
    private final String color;

    @JSONField(name = "sound")
    private final String sound;

    @JSONField(name = "default_sound")
    private final boolean defaultSound;

    @JSONField(name = "tag")
    private final String tag;

    @JSONField(name = "click_action")
    private final ClickAction clickAction;

    @JSONField(name = "body_loc_key")
    private final String bodyLocKey;
    @JSONField(name = "title_loc_key")
    private final String titleLocKey;
    @JSONField(name = "multi_lang_key")
    private final JSONObject multiLangKey;
    @JSONField(name = "channel_id")
    private final String channelId;
    @JSONField(name = "notify_summary")
    private final String notifySummary;
    @JSONField(name = "image")
    private final String image;
    @JSONField(name = "style")
    private final Integer style;
    @JSONField(name = "big_title")
    private final String bigTitle;
    @JSONField(name = "big_body")
    private final String bigBody;
    @JSONField(name = "auto_clear")
    private final Integer autoClear;
    @JSONField(name = "notify_id")
    private final Integer notifyId;
    @JSONField(name = "group")
    private final String group;
    @JSONField(name = "badge")
    private final BadgeNotification badge;
    @JSONField(name = "ticker")
    private final String ticker;
    @JSONField(name = "auto_cancel")
    private final boolean autoCancel;
    @JSONField(name = "when")
    private final String when;
    @JSONField(name = "importance")
    private final String importance;
    @JSONField(name = "use_default_vibrate")
    private final boolean useDefaultVibrate;
    @JSONField(name = "use_default_light")
    private final boolean useDefaultLight;
    @JSONField(name = "visibility")
    private final String visibility;
    @JSONField(name = "light_settings")
    private final LightSettings lightSettings;
    @JSONField(name = "foreground_show")
    private final boolean foregroundShow;
    @JSONField(name = "inbox_content")
    private final List<String> inboxContent;
    @JSONField(name = "buttons")
    private final List<Button> buttons;
    @JSONField(name = "profile_id")
    private final String profileId;
    @JSONField(name = "body_loc_args")
    private List<String> bodyLocArgs = new ArrayList<>();
    @JSONField(name = "title_loc_args")
    private List<String> titleLocArgs = new ArrayList<>();
    @JSONField(name = "local_only")
    private Boolean localOnly;
    @JSONField(name = "vibrate_config")
    private List<String> vibrateConfig = new ArrayList<>();

    private AndroidNotification(Builder builder) {
        this.title = builder.title;
        this.body = builder.body;
        this.icon = builder.icon;
        this.color = builder.color;
        this.sound = builder.sound;
        this.defaultSound = builder.defaultSound;

        this.tag = builder.tag;
        this.clickAction = builder.clickAction;

        this.bodyLocKey = builder.bodyLocKey;
        if (!CollectionUtils.isEmpty(builder.bodyLocArgs)) {
            this.bodyLocArgs.addAll(builder.bodyLocArgs);
        } else {
            this.bodyLocArgs = null;
        }

        this.titleLocKey = builder.titleLocKey;
        if (!CollectionUtils.isEmpty(builder.titleLocArgs)) {
            this.titleLocArgs.addAll(builder.titleLocArgs);
        } else {
            this.titleLocArgs = null;
        }

        if (builder.multiLangkey != null) {
            this.multiLangKey = builder.multiLangkey;
        } else {
            this.multiLangKey = null;
        }

        this.channelId = builder.channelId;

        this.notifySummary = builder.notifySummary;
        this.image = builder.image;
        this.style = builder.style;
        this.bigTitle = builder.bigTitle;
        this.bigBody = builder.bigBody;
        this.autoClear = builder.autoClear;
        this.notifyId = builder.notifyId;
        this.group = builder.group;

        if (null != builder.badge) {
            this.badge = builder.badge;
        } else {
            this.badge = null;
        }

        this.ticker = builder.ticker;
        this.autoCancel = builder.autoCancel;
        this.when = builder.when;
        this.importance = builder.importance;
        this.useDefaultVibrate = builder.useDefaultVibrate;
        this.useDefaultLight = builder.useDefaultLight;
        if (!CollectionUtils.isEmpty(builder.vibrateConfig)) {
            this.vibrateConfig = builder.vibrateConfig;
        } else {
            this.vibrateConfig = null;
        }

        this.visibility = builder.visibility;
        this.lightSettings = builder.lightSettings;
        this.foregroundShow = builder.foregroundShow;

        if (!CollectionUtils.isEmpty(builder.inboxContent)) {
            this.inboxContent = builder.inboxContent;
        } else {
            this.inboxContent = null;
        }

        if (!CollectionUtils.isEmpty(builder.buttons)) {
            this.buttons = builder.buttons;
        } else {
            this.buttons = null;
        }

        this.profileId = builder.profileId;
    }

    /**
     * builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * check androidNotification's parameters
     *
     * @param notification which is in message
     */
    public void check(Notification notification) {
        if (null != notification) {
            ValidatorUtils.checkArgument(StringUtils.isNotEmpty(notification.getTitle()) || StringUtils.isNotEmpty(this.title),
                "title should be set");
            ValidatorUtils.checkArgument(StringUtils.isNotEmpty(notification.getBody()) || StringUtils.isNotEmpty(this.body), "body should be set");
        }

        if (StringUtils.isNotEmpty(this.color)) {
            ValidatorUtils.checkArgument(this.color.matches(AndroidNotification.COLOR_PATTERN),
                "Wrong color format, color must be in the form #RRGGBB");
        }

        if (this.clickAction != null) {
            this.clickAction.check();
        }

        if (!CollectionUtils.isEmpty(this.bodyLocArgs)) {
            ValidatorUtils.checkArgument(StringUtils.isNotEmpty(this.bodyLocKey), "bodyLocKey is required when specifying bodyLocArgs");
        }

        if (!CollectionUtils.isEmpty(this.titleLocArgs)) {
            ValidatorUtils.checkArgument(StringUtils.isNotEmpty(this.titleLocKey), "titleLocKey is required when specifying titleLocArgs");
        }

        if (StringUtils.isNotEmpty(this.image)) {
            ValidatorUtils.checkArgument(this.image.matches(URL_PATTERN), "notifyIcon must start with https");
        }

        //Style 0,1,2
        if (this.style != null) {
            boolean isTrue = this.style == 0 ||
                this.style == 1 ||
                this.style == 3;
            ValidatorUtils.checkArgument(isTrue, "style should be one of 0:default, 1: big text, 3: Inbox");

            if (this.style == 1) {
                ValidatorUtils.checkArgument(StringUtils.isNotEmpty(this.bigTitle) && StringUtils.isNotEmpty(this.bigBody),
                    "title and body are required when style = 1");
            } else if (this.style == 3) {
                ValidatorUtils.checkArgument(!CollectionUtils.isEmpty(this.inboxContent) && this.inboxContent.size() <= 5,
                    "inboxContent is required when style = 3 and at most 5 inbox content is needed");
            }
        }

        if (this.autoClear != null) {
            ValidatorUtils.checkArgument(this.autoClear > 0, "auto clear should positive value");
        }

        if (badge != null) {
            this.badge.check();
        }

        if (this.importance != null) {
            ValidatorUtils.checkArgument(StringUtils.equals(this.importance, Importance.LOW.getValue())
                    || StringUtils.equals(this.importance, Importance.NORMAL.getValue())
                    || StringUtils.equals(this.importance, Importance.HIGH.getValue()),
                "importance shouid be [HIGH, NORMAL, LOW]");
        }

        if (!CollectionUtils.isEmpty(this.vibrateConfig)) {
            ValidatorUtils.checkArgument(this.vibrateConfig.size() <= 10, "vibrate_config array size cannot be more than 10");
            for (String vibrateTiming : this.vibrateConfig) {
                ValidatorUtils.checkArgument(vibrateTiming.matches(AndroidNotification.VIBRATE_PATTERN), "Wrong vibrate timing format");
                long vibrateTimingValue = (long) (1000 * Double
                    .parseDouble(StringUtils.substringBefore(vibrateTiming.toLowerCase(Locale.getDefault()), "s")));
                ValidatorUtils.checkArgument(vibrateTimingValue > 0 && vibrateTimingValue < 60000,
                    "Vibrate timing duration must be greater than 0 and less than 60s");
            }
        }

        if (this.visibility != null) {
            ValidatorUtils.checkArgument(StringUtils.equals(this.visibility, Visibility.VISIBILITY_UNSPECIFIED.getValue())
                    || StringUtils.equals(this.visibility, Visibility.PRIVATE.getValue())
                    || StringUtils.equals(this.visibility, Visibility.PUBLIC.getValue())
                    || StringUtils.equals(this.visibility, Visibility.SECRET.getValue()),
                "visibility shouid be [VISIBILITY_UNSPECIFIED, PRIVATE, PUBLIC, SECRET]");
        }

        // multiLangKey
        if (null != this.multiLangKey) {
            Iterator<String> keyIterator = this.multiLangKey.keySet().iterator();
            while (keyIterator.hasNext()) {
                String key = keyIterator.next();
                ValidatorUtils.checkArgument((this.multiLangKey.get(key) instanceof JSONObject), "multiLangKey value should be JSONObject");
                JSONObject contentsObj = multiLangKey.getJSONObject(key);
                if (contentsObj != null) {
                    ValidatorUtils.checkArgument(contentsObj.keySet().size() <= 3, "Only three lang property can carry");
                }
            }
        }

        if (this.lightSettings != null) {
            this.lightSettings.check();
        }

        if (!CollectionUtils.isEmpty(this.buttons)) {
            ValidatorUtils.checkArgument(this.buttons.size() <= 3, "Only three buttons can carry");
            for (Button button : this.buttons) {
                button.check();
            }
        }

        if (this.profileId != null) {
            ValidatorUtils.checkArgument(this.profileId.length() > 64, "profileId length cannot exceed 64 characters");
        }
    }

    /**
     * getter
     */
    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getIcon() {
        return icon;
    }

    public String getColor() {
        return color;
    }

    public String getSound() {
        return sound;
    }

    public Boolean isDefaultSound() {
        return defaultSound;
    }

    public String getTag() {
        return tag;
    }

    public ClickAction getClickAction() {
        return clickAction;
    }

    public String getBodyLocKey() {
        return bodyLocKey;
    }

    public List<String> getBodyLocArgs() {
        return bodyLocArgs;
    }

    public String getTitleLocKey() {
        return titleLocKey;
    }

    public List<String> getTitleLocArgs() {
        return titleLocArgs;
    }

    public JSONObject getMultiLangKey() {
        return multiLangKey;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getNotifySummary() {
        return notifySummary;
    }

    public String getImage() {
        return image;
    }

    public Integer getStyle() {
        return style;
    }

    public String getBigTitle() {
        return bigTitle;
    }

    public String getBigBody() {
        return bigBody;
    }

    public Integer getAutoClear() {
        return autoClear;
    }

    public Integer getNotifyId() {
        return notifyId;
    }

    public String getGroup() {
        return group;
    }

    public BadgeNotification getBadge() {
        return badge;
    }

    public String getTicker() {
        return ticker;
    }

    public String getWhen() {
        return when;
    }

    public String getImportance() {
        return importance;
    }

    public List<String> getVibrateConfig() {
        return vibrateConfig;
    }

    public String getVisibility() {
        return visibility;
    }

    public LightSettings getLightSettings() {
        return lightSettings;
    }

    public boolean isAutoCancel() {
        return autoCancel;
    }

    public Boolean getLocalOnly() {
        return localOnly;
    }

    public boolean isUseDefaultVibrate() {
        return useDefaultVibrate;
    }

    public boolean isUseDefaultLight() {
        return useDefaultLight;
    }

    public boolean isForegroundShow() {
        return foregroundShow;
    }

    public List<String> getInboxContent() {
        return inboxContent;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public String getProfileId() {
        return profileId;
    }

    public static class Builder {

        private final List<String> bodyLocArgs = new ArrayList<>();
        private final List<String> titleLocArgs = new ArrayList<>();
        private final List<String> vibrateConfig = new ArrayList<>();
        private final List<String> inboxContent = new ArrayList<>();
        private final List<Button> buttons = new ArrayList<Button>();
        private String title;
        private String body;
        private String icon;
        private String color;
        private String sound;
        private boolean defaultSound;
        private String tag;
        private ClickAction clickAction;
        private String bodyLocKey;
        private String titleLocKey;
        private JSONObject multiLangkey;
        private String channelId;
        private String notifySummary;
        private String image;
        private Integer style;
        private String bigTitle;
        private String bigBody;
        private Integer autoClear;
        private Integer notifyId;
        private String group;
        private BadgeNotification badge;
        private String ticker;
        private boolean autoCancel = true;
        private String when;
        private String importance;
        private boolean useDefaultVibrate;
        private boolean useDefaultLight;
        private String visibility;
        private LightSettings lightSettings;
        private boolean foregroundShow;
        private String profileId;

        private Builder() {
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public Builder setIcon(String icon) {
            this.icon = icon;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setSound(String sound) {
            this.sound = sound;
            return this;
        }

        public Builder setDefaultSound(boolean defaultSound) {
            this.defaultSound = defaultSound;
            return this;
        }

        public Builder setTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder setClickAction(ClickAction clickAction) {
            this.clickAction = clickAction;
            return this;
        }

        public Builder setBodyLocKey(String bodyLocKey) {
            this.bodyLocKey = bodyLocKey;
            return this;
        }

        public Builder addBodyLocArgs(String arg) {
            this.bodyLocArgs.add(arg);
            return this;
        }

        public Builder addAllBodyLocArgs(List<String> args) {
            this.bodyLocArgs.addAll(args);
            return this;
        }

        public Builder setTitleLocKey(String titleLocKey) {
            this.titleLocKey = titleLocKey;
            return this;
        }

        public Builder addTitleLocArgs(String arg) {
            this.titleLocArgs.add(arg);
            return this;
        }

        public Builder addAllTitleLocArgs(List<String> args) {
            this.titleLocArgs.addAll(args);
            return this;
        }

        public Builder setMultiLangkey(JSONObject multiLangkey) {
            this.multiLangkey = multiLangkey;
            return this;
        }

        public Builder setChannelId(String channelId) {
            this.channelId = channelId;
            return this;
        }

        public Builder setNotifySummary(String notifySummary) {
            this.notifySummary = notifySummary;
            return this;
        }

        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public Builder setStyle(Integer style) {
            this.style = style;
            return this;
        }

        public Builder setBigTitle(String bigTitle) {
            this.bigTitle = bigTitle;
            return this;
        }

        public Builder setBigBody(String bigBody) {
            this.bigBody = bigBody;
            return this;
        }

        public Builder setAutoClear(Integer autoClear) {
            this.autoClear = autoClear;
            return this;
        }

        public Builder setNotifyId(Integer notifyId) {
            this.notifyId = notifyId;
            return this;
        }

        public Builder setGroup(String group) {
            this.group = group;
            return this;
        }

        public Builder setBadge(BadgeNotification badge) {
            this.badge = badge;
            return this;
        }

        public Builder setTicker(String ticker) {
            this.ticker = ticker;
            return this;
        }

        public Builder setAutoCancel(boolean autoCancel) {
            this.autoCancel = autoCancel;
            return this;
        }

        public Builder setWhen(String when) {
            this.when = when;
            return this;
        }

        public Builder setImportance(String importance) {
            this.importance = importance;
            return this;
        }

        public Builder setUseDefaultVibrate(boolean useDefaultVibrate) {
            this.useDefaultVibrate = useDefaultVibrate;
            return this;
        }

        public Builder setUseDefaultLight(boolean useDefaultLight) {
            this.useDefaultLight = useDefaultLight;
            return this;
        }

        public Builder addVibrateConfig(String vibrateTiming) {
            this.vibrateConfig.add(vibrateTiming);
            return this;
        }


        public Builder addAllVibrateConfig(List<String> vibrateTimings) {
            this.vibrateConfig.addAll(vibrateTimings);
            return this;
        }

        public Builder setVisibility(String visibility) {
            this.visibility = visibility;
            return this;
        }

        public Builder setLightSettings(LightSettings lightSettings) {
            this.lightSettings = lightSettings;
            return this;
        }

        public Builder setForegroundShow(boolean foregroundShow) {
            this.foregroundShow = foregroundShow;
            return this;
        }

        public Builder addInboxContent(String inboxContent) {
            this.inboxContent.add(inboxContent);
            return this;
        }

        public Builder addAllInboxContent(List<String> inboxContents) {
            this.inboxContent.addAll(inboxContents);
            return this;
        }

        public Builder addButton(Button button) {
            this.buttons.add(button);
            return this;
        }

        public Builder addAllButtons(List<Button> buttons) {
            this.buttons.addAll(buttons);
            return this;
        }

        public AndroidNotification build() {
            return new AndroidNotification(this);
        }

        public Builder setProfileId(String profileId) {
            this.profileId = profileId;
            return this;
        }
    }
}

package com.vorsk.minimalin.model;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.support.wearable.complications.ComplicationData;
import android.support.wearable.complications.SystemProviders;

import com.vorsk.minimalin.MaterialColors;
import com.vorsk.minimalin.R;
import com.vorsk.minimalin.config.ConfigActivity;
import com.vorsk.minimalin.config.ConfigRecyclerViewAdapter;
import com.vorsk.minimalin.config.color.ColorSelectionActivity;
import com.vorsk.minimalin.watchface.MinimalinWatchFaceService;

import java.util.ArrayList;

/**
 * Data represents different views for configuring the
 * {@link MinimalinWatchFaceService} watch face's appearance and complications
 * via {@link ConfigActivity}.
 */
public class ConfigData {

    // default setting for booleans
    public static final boolean DEFAULT_UNREAD_NOTIFICATION = true;
    public static final boolean DEFAULT_24_HOUR_TIME = false;
    public static final boolean DEFAULT_SECONDS_TICK_ENABLE = true;
    public static final boolean DEFAULT_NOTIFICATION_COMPLICATION = false;
    public static final boolean DEFAULT_HIDE_COMPLICATIONS_AMBIENT = false;
    public static final boolean DEFAULT_SHOW_HANDS = true;
    public static final String DEFAULT_BACKGROUND_COLOR = MaterialColors.Color.GREY.name();
    public static final String DEFAULT_PRIMARY_COLOR = MaterialColors.Color.BLUE.name();
    public static final String DEFAULT_SECONDARY_COLOR = MaterialColors.Color.PURPLE.name();
    // best to choose complications that do not require the RECEIVE_COMPLICATION_DATA permission so they render on first load
    // https://developer.android.com/reference/android/support/wearable/complications/SystemProviders
    public static final int[] DEFAULT_LEFT_COMPLICATION = {SystemProviders.WATCH_BATTERY,  ComplicationData.TYPE_RANGED_VALUE};
    public static final int[] DEFAULT_RIGHT_COMPLICATION = {SystemProviders.STEP_COUNT,  ComplicationData.TYPE_SHORT_TEXT};
    public static final int[] DEFAULT_TOP_COMPLICATION = {SystemProviders.DATE,  ComplicationData.TYPE_SHORT_TEXT};
    public static final int[] DEFAULT_BOTTOM_COMPLICATION = {SystemProviders.NEXT_EVENT,  ComplicationData.TYPE_LONG_TEXT};
    //public static final int[] DEFAULT_NOTIFICATION_COMPLICATION = {SystemProviders.UNREAD_NOTIFICATION_COUNT,  ComplicationData.TYPE_LONG_TEXT};

    /**
     * Returns Watch Face Service class associated with configuration Activity.
     */
    public static Class getWatchFaceServiceClass() {
        return MinimalinWatchFaceService.class;
    }

    /**
     * Includes all data to populate each of the 5 different custom
     * {@link ViewHolder} types in {@link ConfigRecyclerViewAdapter}.
     */
    public static ArrayList<ConfigItemType> getDataToPopulateAdapter(Context context) {

        ArrayList<ConfigItemType> settingsConfigData = new ArrayList<>();

        // Data for watch face complications UX in settings Activity.
        ConfigItemType complicationConfigItem =
                new ComplicationsConfigItem(R.drawable.add_complication, R.drawable.add_big_complication, R.drawable.added_complication, R.drawable.added_big_complication);
        settingsConfigData.add(complicationConfigItem);

        ConfigItemType primaryColorConfigItem =
                new ColorConfigItem(
                        context.getString(R.string.config_primary_color_label),
                        R.drawable.ic_color_lens,
                        context.getString(R.string.saved_primary_color)
                );
        settingsConfigData.add(primaryColorConfigItem);

        ConfigItemType accentColorConfigItem =
                new ColorConfigItem(
                        context.getString(R.string.config_secondary_color_label),
                        R.drawable.ic_color_lens,
                        context.getString(R.string.saved_secondary_color)
                );
        settingsConfigData.add(accentColorConfigItem);

        // Data for Background color UX in settings Activity.
        ConfigItemType backgroundColorConfigItem =
                new ColorConfigItem(
                        context.getString(R.string.config_background_color_label),
                        R.drawable.ic_color_lens,
                        context.getString(R.string.saved_background_color)
                );
        settingsConfigData.add(backgroundColorConfigItem);

        // Data notification complication
        ConfigItemType notificationComplicationToggle =
                new ComplicationSwitchConfigItem(
                        context.getString(R.string.config_notification_complication_label),
                        R.drawable.ic_notifications,
                        R.drawable.ic_notification_outline,
                        R.string.saved_notification_complication,
                        R.string.notification_complication_instruction_toast
                );
        settingsConfigData.add(notificationComplicationToggle);

        // Data for 'Unread Notifications' UX (toggle) in settings Activity.
        ConfigItemType unreadNotificationsConfigItem =
                new SwitchConfigItem(
                        context.getString(R.string.config_unread_notifications_label),
                        R.drawable.ic_notifications,
                        R.drawable.ic_notifications_off,
                        R.string.saved_unread_notifications_pref,
                        DEFAULT_UNREAD_NOTIFICATION);
        settingsConfigData.add(unreadNotificationsConfigItem);

        // Data for 24 hour mode UX (toggle) in settings Activity.
        ConfigItemType militaryTimeConfigItem =
                new SwitchConfigItem(
                        context.getString(R.string.config_24_hour_label),
                        R.drawable.time_24h,
                        R.drawable.time_12h,
                        R.string.saved_24h_pref,
                        DEFAULT_24_HOUR_TIME);
        settingsConfigData.add(militaryTimeConfigItem);

        // Option to hide complications in ambient mode
        ConfigItemType hideAmbientComplicationsConfigItem =
                new SwitchConfigItem(
                        context.getString(R.string.config_hide_ambient_complications_label),
                        R.drawable.added_complication,
                        R.drawable.add_complication,
                        R.string.saved_hide_ambient_complications,
                        DEFAULT_HIDE_COMPLICATIONS_AMBIENT);
        settingsConfigData.add(hideAmbientComplicationsConfigItem);

        // Show seconds tick UX (toggle) in settings Activity.
        ConfigItemType settingTickConfigItem =
                new SwitchConfigItem(
                        context.getString(R.string.config_enable_seconds_label),
                        R.drawable.clock_seconds,
                        R.drawable.clock,
                        R.string.saved_seconds_enable,
                        DEFAULT_SECONDS_TICK_ENABLE);
        settingsConfigData.add(settingTickConfigItem);

        // Show seconds tick UX (toggle) in settings Activity.
        ConfigItemType settingShowHandsItem =
                new SwitchConfigItem(
                        context.getString(R.string.config_show_hands_label),
                        R.drawable.clock,
                        R.drawable.clock_no_hands,
                        R.string.saved_show_watch_hands,
                        DEFAULT_SHOW_HANDS);
        settingsConfigData.add(settingShowHandsItem);

        // Show bigger digits UX (toggle) in settings Activity.
        ConfigItemType settingBiggerDigitsItem =
                new SwitchConfigItem(
                        context.getString(R.string.config_bigger_digits),
                        R.drawable.digits_big,
                        R.drawable.digits_small,
                        R.string.saved_bigger_digits,
                        context.getResources().getConfiguration().fontScale > 1.0);
        settingsConfigData.add(settingBiggerDigitsItem);

        return settingsConfigData;
    }

    /**
     * Interface all ConfigItems must implement so the {@link RecyclerView}'s Adapter associated
     * with the configuration activity knows what type of ViewHolder to inflate.
     */
    public interface ConfigItemType {
        int getConfigType();
    }

    /**
     * Data for Watch Face Complications Preview item in RecyclerView.
     */
    public static class ComplicationsConfigItem implements ConfigItemType {

        private final int defaultComplicationResourceId;
        private final int defaultComplicationLongResourceId;
        private final int defaultAddedComplicationResourceId;
        private final int defaultAddedComplicationLongResourceId;

        ComplicationsConfigItem(int defaultComplicationResourceId, int defaultComplicationLongResourceId,
                                int defaultAddedComplicationResourceId, int defaultAddedComplicationLongResourceId) {
            this.defaultComplicationResourceId = defaultComplicationResourceId;
            this.defaultComplicationLongResourceId = defaultComplicationLongResourceId;
            this.defaultAddedComplicationResourceId = defaultAddedComplicationResourceId;
            this.defaultAddedComplicationLongResourceId = defaultAddedComplicationLongResourceId;
        }

        public int getDefaultComplicationResourceId() {
            return defaultComplicationResourceId;
        }

        public int getDefaultComplicationLongResourceId() {
            return defaultComplicationLongResourceId;
        }

        public int getDefaultAddedComplicationResourceId() {
            return defaultAddedComplicationResourceId;
        }

        public int getDefaultAddedComplicationLongResourceId() {
            return defaultAddedComplicationLongResourceId;
        }

        @Override
        public int getConfigType() {
            return ConfigRecyclerViewAdapter.TYPE_COMPLICATIONS_CONFIG;
        }
    }

    /**
     * Data for color picker item in RecyclerView.
     */
    public static class ColorConfigItem implements ConfigItemType {

        private final String name;
        private final int iconResourceId;
        private final String sharedPrefString;
        private final Class<ColorSelectionActivity> activityToChoosePreference;

        ColorConfigItem(
                String name,
                int iconResourceId,
                String sharedPrefString) {
            this.name = name;
            this.iconResourceId = iconResourceId;
            this.sharedPrefString = sharedPrefString;
            this.activityToChoosePreference = ColorSelectionActivity.class;
        }

        public String getName() {
            return name;
        }

        public int getIconResourceId() {
            return iconResourceId;
        }

        public String getSharedPrefString() {
            return sharedPrefString;
        }

        public Class<ColorSelectionActivity> getActivityToChoosePreference() {
            return activityToChoosePreference;
        }

        @Override
        public int getConfigType() {
            return ConfigRecyclerViewAdapter.TYPE_COLOR_CONFIG;
        }
    }

    /**
     * Data for switch preference picker item in RecyclerView.
     */
    public static class SwitchConfigItem implements ConfigItemType {

        private final String name;
        private final int iconEnabledResourceId;
        private final int iconDisabledResourceId;
        private final int sharedPrefId;
        private final boolean switchDefault;

        SwitchConfigItem(
                String name,
                int iconEnabledResourceId,
                int iconDisabledResourceId,
                int sharedPrefId,
                boolean switchDefault) {
            this.name = name;
            this.iconEnabledResourceId = iconEnabledResourceId;
            this.iconDisabledResourceId = iconDisabledResourceId;
            this.sharedPrefId = sharedPrefId;
            this.switchDefault = switchDefault;
        }

        public String getName() {
            return name;
        }

        public boolean getDefault() {
            return switchDefault;
        }

        public int getIconEnabledResourceId() {
            return iconEnabledResourceId;
        }

        public int getIconDisabledResourceId() {
            return iconDisabledResourceId;
        }

        public int getSharedPrefId() {
            return sharedPrefId;
        }

        @Override
        public int getConfigType() {
            return ConfigRecyclerViewAdapter.TYPE_SWITCH_CONFIG;
        }
    }

    public static class ComplicationSwitchConfigItem extends SwitchConfigItem {

        private final ConfigRecyclerViewAdapter.ComplicationLocation complicationLocation;
        private final int instructionToastTextID;

        ComplicationSwitchConfigItem(
                String name,
                int iconEnabledResourceId,
                int iconDisabledResourceId,
                int sharedPrefId,
                int instructionToastTextID) {
            super(name, iconEnabledResourceId, iconDisabledResourceId, sharedPrefId, ConfigData.DEFAULT_NOTIFICATION_COMPLICATION);
            this.complicationLocation = ConfigRecyclerViewAdapter.ComplicationLocation.NOTIFICATION;
            this.instructionToastTextID = instructionToastTextID;
        }

        public ConfigRecyclerViewAdapter.ComplicationLocation getComplicationLocation() {
            return this.complicationLocation;
        }

        public int getInstructionToastTextID() {
            return instructionToastTextID;
        }

        @Override
        public int getConfigType() {
            return ConfigRecyclerViewAdapter.TYPE_COMPLICATION_SWITCH_CONFIG;
        }
    }
}

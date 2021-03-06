/**
 * Copyright (c) 2015, The MoKee Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mokee.app;

import android.annotation.SdkConstant;

/**
 * @hide
 * TODO: We need to somehow make these managers accessible via getSystemService
 */
public final class MKContextConstants {

    /**
     * @hide
     */
    private MKContextConstants() {
        // Empty constructor
    }

    /**
     * Use with {@link android.content.Context#getSystemService} to retrieve a
     * {@link mokee.app.MKStatusBarManager} for informing the user of
     * background events.
     *
     * @see android.content.Context#getSystemService
     * @see mokee.app.MKStatusBarManager
     */
    public static final String MK_STATUS_BAR_SERVICE = "mkstatusbar";

    /**
     * Use with {@link android.content.Context#getSystemService} to retrieve a
     * {@link mokee.app.ProfileManager} for informing the user of
     * background events.
     *
     * @see android.content.Context#getSystemService
     * @see mokee.app.ProfileManager
     *
     * @hide
     */
    public static final String MK_PROFILE_SERVICE = "profile";

    /**
     * Use with {@link android.content.Context#getSystemService} to retrieve a
     * {@link mokee.app.PartnerInterface} interact with system settings.
     *
     * @see android.content.Context#getSystemService
     * @see mokee.app.PartnerInterface
     *
     * @hide
     */
    public static final String MK_PARTNER_INTERFACE = "mkpartnerinterface";

    /**
     * Use with {@link android.content.Context#getSystemService} to retrieve a
     * {@link mokee.app.MKTelephonyManager} to manage the phone and
     * data connection.
     *
     * @see android.content.Context#getSystemService
     * @see mokee.app.MKTelephonyManager
     *
     * @hide
     */
    public static final String MK_TELEPHONY_MANAGER_SERVICE = "mktelephonymanager";

    /**
     * Use with {@link android.content.Context#getSystemService} to retrieve a
     * {@link mokee.hardware.MKHardwareManager} to manage the extended
     * hardware features of the device.
     *
     * @see android.content.Context#getSystemService
     * @see mokee.hardware.MKHardwareManager
     *
     * @hide
     */
    public static final String MK_HARDWARE_SERVICE = "mkhardware";

    /**
     * Control device power profile and characteristics.
     *
     * @hide
     */
    public static final String MK_PERFORMANCE_SERVICE = "mkperformance";

    /**
     * Controls changing and applying themes
     *
     * @hide
     */
    public static final String MK_THEME_SERVICE = "mkthemes";

    /**
     * Manages composed icons
     *
     * @hide
     */
    public static final String MK_ICON_CACHE_SERVICE = "mkiconcache";

    /**
     * @hide
     */
    public static final String MK_LIVE_LOCK_SCREEN_SERVICE = "mklivelockscreen";

    /**
     * Use with {@link android.content.Context#getSystemService} to retrieve a
     * {@link mokee.weather.MKWeatherManager} to manage the weather service
     * settings and request weather updates
     *
     * @see android.content.Context#getSystemService
     * @see mokee.weather.MKWeatherManager
     *
     * @hide
     */
    public static final String MK_WEATHER_SERVICE = "mkweather";

    /**
     * Manages display color adjustments
     *
     * @hide
     */
    public static final String MK_LIVEDISPLAY_SERVICE = "mklivedisplay";


    /**
     * Manages enhanced audio functionality
     *
     * @hide
     */
    public static final String MK_AUDIO_SERVICE = "mkaudio";

    /**
     * Features supported by the MKSDK.
     */
    public static class Features {
        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the hardware abstraction
         * framework service utilized by the mksdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String HARDWARE_ABSTRACTION = "org.mokee.hardware";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the mk status bar service
         * utilzed by the mksdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String STATUSBAR = "org.mokee.statusbar";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the mk profiles service
         * utilized by the mksdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String PROFILES = "org.mokee.profiles";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the mk telephony service
         * utilized by the mksdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String TELEPHONY = "org.mokee.telephony";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the mk theme service
         * utilized by the mksdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String THEMES = "org.mokee.theme";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the mk performance service
         * utilized by the mksdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String PERFORMANCE = "org.mokee.performance";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the mk partner service
         * utilized by the mksdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String PARTNER = "org.mokee.partner";

        /*
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the Live lock screen
         * feature.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String LIVE_LOCK_SCREEN = "org.mokee.livelockscreen";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the mk weather weather
         * service utilized by the mksdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String WEATHER_SERVICES = "org.mokee.weather";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the LiveDisplay service
         * utilized by the mksdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String LIVEDISPLAY = "org.mokee.livedisplay";

        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the MK audio extensions
         * utilized by the mksdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String AUDIO = "org.mokee.audio";
    }
}

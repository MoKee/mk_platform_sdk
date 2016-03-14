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
     * Features supported by the MKSDK.
     */
    public static class Features {
        /**
         * Feature for {@link PackageManager#getSystemAvailableFeatures} and
         * {@link PackageManager#hasSystemFeature}: The device includes the hardware abstraction
         * framework service utilized by the cmsdk.
         */
        @SdkConstant(SdkConstant.SdkConstantType.FEATURE)
        public static final String HARDWARE_ABSTRACTION = "org.mokee.hardware";
    }
}

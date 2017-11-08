/*
 * Copyright (C) 2017 The MoKee Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package mokee.fingerprint;

import android.content.Context;
import android.content.Intent;
import android.content.ComponentName;
import android.content.pm.LauncherApps;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import mokee.providers.MKSettings;

public class FingerprintShortcutManager {

    private static final String TAG = "FingerprintShortcutManager";

    private final Context mContext;
    private final LauncherApps mLauncherApps;

    public FingerprintShortcutManager(Context context) {
        this.mContext = context;
        this.mLauncherApps = (LauncherApps) context.getSystemService(
                Context.LAUNCHER_APPS_SERVICE);
    }

    public void startShortcutAsUser(int fingerId, UserHandle user) {
        final SparseArray<String> shortcuts = getShortcuts();
        final String target = shortcuts.get(fingerId);
        if (target == null) {
            return;
        }

        final String[] shortcut = target.split(":");
        final String[] cmp = shortcut[1].split("/");

        switch (shortcut[0]) {
            case "cmp":
                mLauncherApps.startMainActivity(
                        new ComponentName(cmp[0], cmp[1]),
                        user, null, null);
                break;
            case "shortcut":
                mLauncherApps.startShortcut(
                        cmp[0], cmp[1],
                        null, null, user);
                break;
        }
    }

    private SparseArray<String> getShortcuts() {
        final SparseArray<String> shortcuts = new SparseArray<>();

        final String shortcutMappings = MKSettings.System.getString(
                mContext.getContentResolver(),
                MKSettings.System.FINGERPRINT_SHORTCUTS);

        if (TextUtils.isEmpty(shortcutMappings)) {
            return shortcuts;
        }

        for (String line : shortcutMappings.split("\n")) {
            final String[] mapping = line.split(":");
            final int fingerId = Integer.parseInt(mapping[0]);
            shortcuts.put(fingerId, mapping[1] + ":" + mapping[2]);
        }

        return shortcuts;
    }

}

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

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.LauncherApps.ShortcutQuery;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import java.util.Collections;
import java.util.List;

import mokee.providers.MKSettings;

public class FingerprintShortcutManager {

    private static final String TAG = "FingerprintShortcutManager";

    private final Context mContext;
    private final PackageManager mPackageManager;
    private final LauncherApps mLauncherApps;

    public FingerprintShortcutManager(Context context) {
        this.mContext = context;
        this.mPackageManager = context.getPackageManager();
        this.mLauncherApps = (LauncherApps) context.getSystemService(
                Context.LAUNCHER_APPS_SERVICE);
    }

    public void startShortcutAsUser(int fingerId, UserHandle user) {
        final SparseArray<Target> shortcuts = getShortcuts();
        final Target target = shortcuts.get(fingerId);
        if (target != null) {
            target.startAsUser(user);
        }
    }

    public SparseArray<Target> getShortcuts() {
        final SparseArray<Target> shortcuts = new SparseArray<>();

        final String shortcutMappings = MKSettings.System.getString(
                mContext.getContentResolver(),
                MKSettings.System.FINGERPRINT_SHORTCUTS);

        if (TextUtils.isEmpty(shortcutMappings)) {
            return shortcuts;
        }

        for (String line : shortcutMappings.split("\n")) {
            final String[] mapping = line.split(":");
            final int fingerId = Integer.parseInt(mapping[0]);
            switch (mapping[1]) {
                case "cmp":
                    final String[] cmp = mapping[2].split("/");
                    shortcuts.put(fingerId, new ComponentTarget(cmp[0], cmp[1]));
                    break;
                case "shortcut":
                    final String[] shortcut = mapping[2].split("/");
                    shortcuts.put(fingerId, new ShortcutTarget(shortcut[0], shortcut[1]));
                    break;
            }
        }

        return shortcuts;
    }

    private CharSequence getActivityLabel(ComponentName component) {
        try {
            if (component == null) {
                return null;
            }

            final ActivityInfo activity = mPackageManager.getActivityInfo(
                    component, 0);
            if (activity == null) {
                return null;
            }

            return activity.loadLabel(mPackageManager);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    private CharSequence getShortcutLabel(String packageName, String shortcutId) {
        final ShortcutQuery query = new ShortcutQuery()
                .setPackage(packageName)
                .setShortcutIds(Collections.singletonList(shortcutId))
                .setQueryFlags(ShortcutQuery.FLAG_MATCH_DYNAMIC
                        | ShortcutQuery.FLAG_MATCH_PINNED
                        | ShortcutQuery.FLAG_MATCH_MANIFEST);

        final UserHandle userHandle = new UserHandle(ActivityManager.getCurrentUser());

        final List<ShortcutInfo> shortcuts =  mLauncherApps.getShortcuts(
                query, userHandle);
        if (shortcuts.isEmpty()) {
            return null;
        }

        final ShortcutInfo shortcut = shortcuts.get(0);

        final CharSequence activityLabel = getActivityLabel(shortcut.getActivity());
        if (activityLabel == null) {
            return null;
        }

        final CharSequence shortcutLabel = shortcut.getShortLabel();
        if (shortcutLabel == null) {
            return null;
        }

        return activityLabel + " " + shortcutLabel;
    }

    public abstract class Target {
        abstract void startAsUser(UserHandle user);
        public abstract CharSequence getLabel();
    }

    public class ComponentTarget extends Target {

        public final ComponentName component;

        private ComponentTarget(String packageName, String className) {
            this.component = new ComponentName(packageName, className);
        }

        @Override
        void startAsUser(UserHandle user) {
            mLauncherApps.startMainActivity(component,
                    user, null, null);
        }

        @Override
        public CharSequence getLabel() {
            return getActivityLabel(component);
        }

    }

    public class ShortcutTarget extends Target {

        public final String packageName;
        public final String shortcutId;

        private ShortcutTarget(String packageName, String shortcutId) {
            this.packageName = packageName;
            this.shortcutId = shortcutId;
        }

        @Override
        void startAsUser(UserHandle user) {
            mLauncherApps.startShortcut(packageName, shortcutId,
                    null, null, user);
        }

        @Override
        public CharSequence getLabel() {
            return getShortcutLabel(packageName, shortcutId);
        }

    }

}

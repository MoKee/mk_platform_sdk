/**
 * Copyright (C) 2016 The CyanogenMod Project
 * Copyright (C) 2016 The MoKee Open Source Project
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
 * limitations under the License.
 */
package org.mokee.platform.internal;

import org.mokee.platform.internal.common.BrokeredServiceConnection;

import android.annotation.NonNull;
import android.annotation.SdkConstant;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.util.Slog;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mokee.app.MKContextConstants;
import mokee.media.AudioSessionInfo;
import mokee.media.IMKAudioService;

public class MKAudioServiceBroker extends BrokerableMKSystemService<IMKAudioService> {

    private static final String TAG = "MKAudioServiceBroker";
    private static final boolean DEBUG = Log.isLoggable(TAG, Log.DEBUG);

    private final Context mContext;

    private static final ComponentName TARGET_IMPLEMENTATION_COMPONENT =
            new ComponentName("org.mokee.mkaudio.service",
                    "org.mokee.mkaudio.service.MKAudioService");

    public MKAudioServiceBroker(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public void onBootPhase(int phase) {
        super.onBootPhase(phase);
        if (phase == PHASE_THIRD_PARTY_APPS_CAN_START) {
            publishBinderService(MKContextConstants.MK_AUDIO_SERVICE, new BinderService());
        }
    }

    @Override
    public void onStart() {
        if (DEBUG) Slog.d(TAG, "service started");
    }

    @Override
    protected IMKAudioService getIBinderAsIInterface(@NonNull IBinder service) {
        return IMKAudioService.Stub.asInterface(service);
    }

    @Override
    protected IMKAudioService getDefaultImplementation() {
        return mServiceStubForFailure;
    }

    @Override
    protected ComponentName getServiceComponent() {
        return TARGET_IMPLEMENTATION_COMPONENT;
    }

    private void checkPermission() {
        mContext.enforceCallingOrSelfPermission(
                mokee.platform.Manifest.permission.OBSERVE_AUDIO_SESSIONS, null);
    }

    @Override
    public String getFeatureDeclaration() {
        return MKContextConstants.Features.AUDIO;
    }

    private final IMKAudioService mServiceStubForFailure = new IMKAudioService.Stub() {
        @Override
        public List<AudioSessionInfo> listAudioSessions(int streamType) throws RemoteException {
            checkPermission();
            return Collections.emptyList();
        }
    };

    private final class BinderService extends IMKAudioService.Stub {
        @Override
        public List<AudioSessionInfo> listAudioSessions(int streamType) throws RemoteException {
            checkPermission();
            return getBrokeredService().listAudioSessions(streamType);
        }

        @Override
        public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
            mContext.enforceCallingOrSelfPermission(android.Manifest.permission.DUMP, TAG);

            pw.println();
            pw.println("MKAudio Service State:");
            try {
                List<AudioSessionInfo> sessions = listAudioSessions(-1);
                if (sessions.size() > 0) {
                    pw.println("  Audio sessions:");
                    for (AudioSessionInfo info : sessions) {
                        pw.println("   " + info.toString());
                    }
                } else {
                    pw.println("  No active audio sessions");
                }
            } catch (RemoteException e) {
                // nothing
            }
        }
    }
}

/*
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

import android.annotation.NonNull;
import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import mokee.app.MKContextConstants;
import mokee.platform.Manifest;
import mokee.weather.IMKWeatherManager;
import mokee.weather.IWeatherServiceProviderChangeListener;
import mokee.weather.RequestInfo;

public class WeatherManagerServiceBroker extends BrokerableMKSystemService<IMKWeatherManager> {

    private Context mContext;

    private static final ComponentName TARGET_IMPLEMENTATION_COMPONENT =
            new ComponentName("org.mokee.weatherservice",
                    "org.mokee.weatherservice.WeatherManagerService");

    private void enforcePermission() {
        mContext.enforceCallingOrSelfPermission(
                Manifest.permission.ACCESS_WEATHER_MANAGER, null);
    }

    private final IBinder mService = new IMKWeatherManager.Stub() {

        @Override
        public void updateWeather(RequestInfo info) throws RemoteException {
            enforcePermission();
            getBrokeredService().updateWeather(info);
        }

        @Override
        public void lookupCity(RequestInfo info) throws RemoteException {
            enforcePermission();
            getBrokeredService().lookupCity(info);
        }

        @Override
        public void registerWeatherServiceProviderChangeListener(
                IWeatherServiceProviderChangeListener listener) throws RemoteException {
            enforcePermission();
            getBrokeredService().registerWeatherServiceProviderChangeListener(listener);
        }

        @Override
        public void unregisterWeatherServiceProviderChangeListener(
                IWeatherServiceProviderChangeListener listener) throws RemoteException {
            enforcePermission();
            getBrokeredService().unregisterWeatherServiceProviderChangeListener(listener);
        }

        @Override
        public String getActiveWeatherServiceProviderLabel() throws RemoteException {
            enforcePermission();
            return getBrokeredService().getActiveWeatherServiceProviderLabel();
        }

        @Override
        public void cancelRequest(int requestId) throws RemoteException {
            enforcePermission();
            getBrokeredService().cancelRequest(requestId);
        }
    };

    public WeatherManagerServiceBroker(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public String getFeatureDeclaration() {
        return MKContextConstants.Features.WEATHER_SERVICES;
    }

    @Override
    public void onStart() {
        publishBinderService(MKContextConstants.MK_WEATHER_SERVICE, mService);
    }

    @Override
    protected IMKWeatherManager getIBinderAsIInterface(@NonNull IBinder service) {
        return IMKWeatherManager.Stub.asInterface(service);
    }

    @Override
    protected IMKWeatherManager getDefaultImplementation() {
        return null;
    }

    @Override
    protected ComponentName getServiceComponent() {
        return TARGET_IMPLEMENTATION_COMPONENT;
    }

}

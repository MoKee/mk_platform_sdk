/*
 * Copyright (C) 2016 The CyanogenMod Project
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

package mokee.providers;

import android.net.Uri;

/**
 * The contract between the weather provider and applications.
 */
public class WeatherContract {

    /**
     * The authority of the weather content provider
     */
    public static final String AUTHORITY = "com.mokee.weather";

    /**
     * A content:// style uri to the authority for the weather provider
     */
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    public static class WeatherColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "weather");

        public static final Uri CURRENT_AND_FORECAST_WEATHER_URI
                = Uri.withAppendedPath(CONTENT_URI, "current_and_forecast");
        public static final Uri CURRENT_WEATHER_URI
                = Uri.withAppendedPath(CONTENT_URI, "current");
        public static final Uri FORECAST_WEATHER_URI
                = Uri.withAppendedPath(CONTENT_URI, "forecast");

        /**
         * The city name
         * <P>Type: TEXT</P>
         */
        public static final String CURRENT_CITY = "city";

        /**
         * A Valid {@link WeatherCode}
         * <P>Type: INTEGER</P>
         */
        public static final String CURRENT_CONDITION_CODE = "condition_code";


        /**
         * Air quality index
         * <P>Type: TEXT</P>
         */
        public static final String CURRENT_AQI = "aqi";

        /**
         * Uv index
         * <P>Type: TEXT</P>
         */
        public static final String CURRENT_UV = "uv";

        /**
         * A localized string mapped to the current weather condition code. Note that, if no
         * locale is found, the string will be in english
         * <P>Type: TEXT</P>
         */
        public static final String CURRENT_CONDITION = "condition";

        /**
         * The current weather temperature
         * <P>Type: DOUBLE</P>
         */
        public static final String CURRENT_TEMPERATURE = "temperature";

        /**
         * The unit in which current temperature is reported
         * <P>Type: INTEGER</P>
         * Can be one of the following:
         * <ul>
         * <li>{@link TempUnit#CELSIUS}</li>
         * <li>{@link TempUnit#FAHRENHEIT}</li>
         * </ul>
         */
        public static final String CURRENT_TEMPERATURE_UNIT = "temperature_unit";

        /**
         * The current weather humidity
         * <P>Type: DOUBLE</P>
         */
        public static final String CURRENT_HUMIDITY = "humidity";

        /**
         * The current wind direction (in degrees)
         * <P>Type: DOUBLE</P>
         */
        public static final String CURRENT_WIND_DIRECTION = "wind_direction";

        /**
         * The current wind speed
         * <P>Type: DOUBLE</P>
         */
        public static final String CURRENT_WIND_SPEED = "wind_speed";

        /**
         * The unit in which the wind speed is reported
         * <P>Type: INTEGER</P>
         * Can be one of the following:
         * <ul>
         * <li>{@link WindSpeedUnit#KPH}</li>
         * <li>{@link WindSpeedUnit#MPH}</li>
         * </ul>
         */
        public static final String CURRENT_WIND_SPEED_UNIT = "wind_speed_unit";

        /**
         * The timestamp when this weather was reported
         * <P>Type: LONG</P>
         */
        public static final String CURRENT_TIMESTAMP = "timestamp";

        /**
         * Today's high temperature.
         * <p>Type: DOUBLE</p>
         */
        public static final String TODAYS_HIGH_TEMPERATURE = "todays_high";

        /**
         * Today's low temperature.
         * <p>Type: DOUBLE</p>
         */
        public static final String TODAYS_LOW_TEMPERATURE = "todays_low";

        /**
         * The forecasted low temperature
         * <P>Type: DOUBLE</P>
         */
        public static final String FORECAST_LOW = "forecast_low";

        /**
         * The forecasted high temperature
         * <P>Type: DOUBLE</P>
         */
        public static final String FORECAST_HIGH = "forecast_high";

        /**
         * A localized string mapped to the forecasted weather condition code. Note that, if no
         * locale is found, the string will be in english
         * <P>Type: TEXT</P>
         */
        public static final String FORECAST_CONDITION = "forecast_condition";

        /**
         * The code identifying the forecasted weather condition.
         * @see #CURRENT_CONDITION_CODE
         */
        public static final String FORECAST_CONDITION_CODE = "forecast_condition_code";

        /**
         * Temperature units
         */
        public static final class TempUnit {
            private TempUnit() {}
            public final static int CELSIUS = 1;
            public final static int FAHRENHEIT = 2;
        }

        /**
         * Wind speed units
         */
        public static final class WindSpeedUnit {
            private WindSpeedUnit() {}
            /**
             * Kilometers per hour
             */
            public final static int KPH = 1;

            /**
             * Miles per hour
             */
            public final static int MPH = 2;
        }

        /**
         * Weather condition codes
         */
        public static final class WeatherCode {
            private WeatherCode() {}

            /**
             * @hide
             */
            public final static int WEATHER_CODE_MIN = 0;

            public final static int SUNNY = 100;
            public final static int CLOUDY = 101;
            public final static int FEW_CLOUDS = 102;
            public final static int PARTLY_CLOUDY = 103;
            public final static int OVERCAST = 104;
            public final static int WINDY = 200;
            public final static int CALM = 201;
            public final static int LIGHT_BREEZE = 202;
            public final static int MODERATE = 203;
            public final static int FRESH_BREEZE = 204;
            public final static int STRONG_BREEZE = 205;
            public final static int HIGH_WIND = 206;
            public final static int GALE = 207;
            public final static int STRONG_GALE = 208;
            public final static int STORM = 209;
            public final static int VIOLENT_STORM = 210;
            public final static int HURRICANE = 211;
            public final static int TORNADO = 212;
            public final static int TROPICAL_STORM = 213;
            public final static int SHOWER_RAIN = 300;
            public final static int HEAVY_SHOWER_RAIN = 301;
            public final static int THUNDERSHOWER = 302;
            public final static int HEAVY_THUNDERSTORM = 303;
            public final static int HAIL = 304;
            public final static int LIGHT_RAIN = 305;
            public final static int MODERATE_RAIN = 306;
            public final static int HEAVY_RAIN = 307;
            public final static int EXTREME_RAIN = 308;
            public final static int DRIZZLE_RAIN = 309;
            public final static int RAIN_STORM = 310;
            public final static int HEAVY_RAIN_STORM = 311;
            public final static int SEVERE_RAIN_STORM = 312;
            public final static int FREEZING_RAIN = 313;
            public final static int LIGHT_SNOW = 400;
            public final static int MODERATE_SNOW = 401;
            public final static int HEAVY_SNOW = 402;
            public final static int SNOWSTORM = 403;
            public final static int SLEET = 404;
            public final static int RAIN_WITH_SNOW = 405;
            public final static int SHOWER_SNOW = 406;
            public final static int SNOW_FLURRY = 407;
            public final static int MIST = 500;
            public final static int FOGGY = 501;
            public final static int HAZE = 502;
            public final static int SAND = 503;
            public final static int DUST = 504;
            public final static int VOLCANIC_ASH = 506;
            public final static int DUSTSTORM = 507;
            public final static int SANDSTORM = 508;
            public final static int HOT = 900;
            public final static int COLD = 901;

            /**
             * @hide
             */
            public final static int WEATHER_CODE_MAX = 51;

            public final static int NOT_AVAILABLE = 3200;
        }
    }
}
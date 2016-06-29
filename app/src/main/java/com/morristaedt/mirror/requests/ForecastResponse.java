package com.morristaedt.mirror.requests;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by HannahMitt on 8/23/15.
 */
public class ForecastResponse {

    float latitude;
    float longitude;

    public ForecastCurrently currently;
    public ForecastHourly hourly;
    public ForcastDaily daily;

    public class ForecastCurrently {
        public String summary;
        public float temperature;

        public String getDisplayTemperature() {
            return String.valueOf(Math.round(temperature)) + (char) 0x00B0;
        }
    }

    public class ForcastDaily {
        public ArrayList<Day> data;
        public String getFirstDailySummary() {
            return data.get(0).summary.toString();
        }
    }

    public class Day {
        public String summary;
    }

    public class ForecastHourly {
        String summary;
        public ArrayList<Hour> data;
    }

    public class Hour {
        public long time; // in seconds
        public String summary;
        public String precipType;
        public float precipProbability;
        public float temperature;

        public Calendar getCalendar() {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time * 1000);
            return calendar;
        }
    }
}

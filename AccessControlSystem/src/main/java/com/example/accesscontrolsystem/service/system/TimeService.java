package com.example.accesscontrolsystem.service.system;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service("TimeService")
public class TimeService {
    public long getTime() {
        return new Date().getTime();
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public String time2Day(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(time));
    }
    public String time2Minute(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        return sdf.format(time);
    }

    public long getTimeNDaysBefore(Integer n) {
        return getTime() - (long) n * 24 * 60 * 60 * 1000;
    }
}

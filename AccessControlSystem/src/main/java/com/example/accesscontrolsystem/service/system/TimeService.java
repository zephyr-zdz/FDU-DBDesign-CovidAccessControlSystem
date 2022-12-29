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
    public long day2Time(String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(day).getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    public long getTimeNDaysBefore(Integer n) {
        return getTime() - (long) n * 24 * 60 * 60 * 1000;
    }
}

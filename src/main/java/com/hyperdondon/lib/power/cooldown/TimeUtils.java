package com.hyperdondon.lib.power.cooldown;

public class TimeUtils {
    public static long fromMinutesAndSeconds(int min, int sec) {
        for (int i = 0; i < min; i++) sec += 60;
        return fromSeconds(sec);
    }

    public static long fromSeconds(int sec) {
        long milli = 0;
        for (int i = 0; i < sec; i++) milli += 1000;
        return milli;
    }

    public static long toSeconds(int milli) {
        var sec = 0;
        while (milli > 0) {
            milli -= 1000;
            sec++;
        }
        return sec;
    }


    public static long fromTicks(int ticks) {
        return ticks * 50L;
    }
}

package com.cmlteam.codevscovid19;

import java.util.Random;

public class Common {

    private static final Random R = new Random();

    public static double MAX_PEOPLE_PER_SQ_METER = 0.2;

    private Common() {
    }

    public static int getAppropriateDistance() {
        return R.nextInt(1500) + 500;
    }

}

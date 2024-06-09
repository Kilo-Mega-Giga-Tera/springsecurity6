package com.app.security.utils;

import java.util.Random;
import java.util.UUID;

public class CommonUtils {

    public static String getServiceReqNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(999999999 - 9999) + 9999;
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        return "SR" + randomNumber + "-" +uuid;
    }

}

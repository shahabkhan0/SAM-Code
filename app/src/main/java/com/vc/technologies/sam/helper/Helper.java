package com.vc.technologies.sam.helper;

public class Helper {

    public static boolean isEmailvalid(String trim) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(trim).matches();
    }

}

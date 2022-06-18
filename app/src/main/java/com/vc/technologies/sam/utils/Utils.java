package com.vc.technologies.sam.utils;

import android.app.Activity;
import android.app.Fragment;
import android.widget.FrameLayout;

public class Utils {
    public static void load_fragment(Activity activity, FrameLayout frameLayout, Fragment fragment) {
        activity.getFragmentManager()
                .beginTransaction()
                .replace(frameLayout.getId(), fragment).commit();
    }
}

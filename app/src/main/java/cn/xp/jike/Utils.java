package cn.xp.jike;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;


public class Utils {


    public static float dpToPixel(float dp) {
        return dp * (getDisplayMetrics().densityDpi / 160F);
    }

    public static float pixelsToDp(float f) {
        return f / (getDisplayMetrics().densityDpi / 160F);
    }

    public static DisplayMetrics getDisplayMetrics() {
        Context context = App.getContext();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay()
                .getMetrics(displaymetrics);
        return displaymetrics;
    }

}

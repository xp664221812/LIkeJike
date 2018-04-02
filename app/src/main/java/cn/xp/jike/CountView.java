package cn.xp.jike;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class CountView extends View {
    protected static final String TAG = CountView.class.getSimpleName();

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    boolean isUp;
    public int count;
    private int newCount;
    public int action = 0;

    public int changeNum = 0;

    public void setCount(int count) {
        this.count = count;
    }

    public void setAction(int action) {
        this.action = action;
        postInvalidate();
    }


    public CountView(Context context) {
        super(context);
    }

    public CountView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextSize(50);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        refreshView(canvas);
    }

    public void refreshView(Canvas canvas) {
        switch (action) {
            case 0:
                canvas.drawText(String.valueOf(count), 0, 120, paint);
                break;

            case 1:
                newCount = count + 1;
                action = 1;
                char[] newArray = String.valueOf(newCount).toCharArray();
                char[] oldArray = String.valueOf(count).toCharArray();
                int num = 0;
                if (newArray.length > oldArray.length) {
                    num = newArray.length;
                } else {
                    for (int i = 0; i < newArray.length; i++) {
                        if (newArray[i] != oldArray[i]) {
                            num++;
                        }
                    }

                }
                Log.d(TAG, "new count============" + newCount + ",length=========" + num);
                canvas.drawText(newArray, newArray.length - 1, num, 0, 120, paint);
                count = newCount;
                break;

            case 2:
                count = newCount;
                break;


        }



    }

}

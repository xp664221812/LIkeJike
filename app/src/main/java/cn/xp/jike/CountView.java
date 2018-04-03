package cn.xp.jike;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
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
//        invalidate();
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

            case 1: {
                newCount = count + 1;
//                action = 1;
                char[] newArray = String.valueOf(newCount).toCharArray();
                char[] oldArray = String.valueOf(count).toCharArray();

//                canvas.drawText(newArray, 0, newArray.length, 0, 120, paint);

                int num = 0;
                if (newArray.length > oldArray.length) {
                    num = newArray.length;
                    canvas.drawText(newArray, 0, num, 0, 120, paint);
                } else {
                    for (int i = 0; i < newArray.length; i++) {
                        if (newArray[i] != oldArray[i]) {
                            num++;
                        }
                    }
                    float startX = paint.measureText(oldArray, 0, oldArray.length - num);
                    Log.d(TAG, "new count============" + newCount + ",length=========" + num);
                    Log.d(TAG, "startX=============" + startX);
//                    canvas.drawText(newArray, 0, newArray.length - num, 0, 120, paint);
                    canvas.drawText(newArray, newArray.length - num, num, startX, 120, paint);

                }
                animate().translationY(-100);
                playPlusAnimation();

                count = newCount;

                break;
            }

            case 2:
                count = newCount;
                break;


        }


    }

    public void playPlusAnimation() {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("translationY", 0f, -10f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(this, "translationY", 0f, -10f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(this, "scaleX", 1f, 0f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(this, "scaleY", 1f, 0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator1).before(animator2).with(animator3);
        animatorSet.start();
//        ObjectAnimator.ofPropertyValuesHolder(this, holder1, holder2, holder3).start();
    }


}

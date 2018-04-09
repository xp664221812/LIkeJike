package cn.xp.jike;

import android.animation.ObjectAnimator;
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

    int transparentColor = 0xffffffff;
    int originColor = 0xff000000;

    boolean plus;
    public int count;
    private int newCount;
    public int action = 0;

    public int changeNum = 0;

    boolean isFirstInit = true;

    private Point[] points = new Point[4];

    public void setCount(int count) {
        this.count = count;
        isFirstInit = true;
        Log.d(TAG, "3333333333333333333");
//        initData();
//        postInvalidate();
    }

    public void setAction(int action) {
        this.action = action;

        postInvalidate();
    }


    public void setThumbNumber(float arg) {
        Log.d(TAG, "动画来了");

        postInvalidate();
    }


    public CountView(Context context) {
        super(context);
    }

    public CountView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        initData();
    }

    public CountView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextSize(50);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "count=================" + count);
        if (isFirstInit) {
            paint.setColor(originColor);
            canvas.drawText(String.valueOf(count), 0, 120, paint);
        } else {

        }
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
                    canvas.drawText(newArray, 0, newArray.length - num, 0, 120, paint);
                    canvas.drawText(newArray, newArray.length - num, num, startX, 120, paint);

                }
//                animate().translationY(-100);
                Log.d(TAG, "after y================" + getY());
                count = newCount;

                break;
            }

            case 2:
                count = newCount;
                break;


        }


    }

    public void playPlusAnimation(boolean thumbUp) {
        isFirstInit = false;
        plus = thumbUp;

//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(this, "translationY", 0, -50);
//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(this, "scaleX", 1f, 0f);
//        ObjectAnimator animator3 = ObjectAnimator.ofFloat(this, "scaleY", 1f, 0f);
//
//
//        ObjectAnimator animator4 = ObjectAnimator.ofFloat(this, "translationY", -50, 25);
//
//        ObjectAnimator animator5 = ObjectAnimator.ofFloat(this, "scaleX", 0f, 1f);
//        ObjectAnimator animator6 = ObjectAnimator.ofFloat(this, "scaleY", 0f, 1f);
//        ObjectAnimator animator7 = ObjectAnimator.ofFloat(this, "translationY", 25, 0);
//
//
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.setInterpolator(new LinearInterpolator());
//        animatorSet.setDuration(100);
//        animatorSet.play(animator1).before(animator2).with(animator3).before(animator4)
//                .before(animator5).with(animator6).before(animator7);
//        animatorSet.start();

        calculatePoints(true);
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "thumbNumber", 0f, 0.5f);
        animator.start();


    }


    private void initData() {
        Point point1 = new Point(0, 120);
        point1.content = String.valueOf(count);
        point1.color = originColor;
        points[0] = point1;
        Point point2 = new Point(0, 120);
        point1.content = String.valueOf(count);
        point1.color = originColor;
        points[0] = point1;
        Point point3 = new Point(0, 120);
        point1.content = String.valueOf(count);
        point1.color = originColor;
        points[0] = point1;
        Log.d(TAG, "length===============" + points.length);
    }


    private void calculatePoints(boolean plus) {
        newCount = plus ? count + 1 : count - 1;
        char[] newArray = String.valueOf(newCount).toCharArray();
        char[] oldArray = String.valueOf(count).toCharArray();
        int num = 0;
        if (newArray.length != oldArray.length) {
            num = newArray.length;
            Point point0 = new Point(0, 120, "", originColor);

            Point point1 = new Point(0, 120, String.valueOf(newCount), originColor);

            if (newCount > count) {
                Point point2 = new Point(0, 100, String.valueOf(count), transparentColor);

                Point point3 = new Point(0, 140, String.valueOf(newCount), transparentColor);
            } else {

            }

            points[0] = point0;


        } else {
            for (int i = 0; i < newArray.length; i++) {
                if (newArray[i] != oldArray[i]) {
                    num++;
                }
            }
            float startX = paint.measureText(oldArray, 0, oldArray.length - num);


        }

        Point point0 = new Point(0, 120, String.valueOf(newCount), originColor);
        points[0] = point0;

        Point point1 = new Point()
        Point point2 = new Point();


    }


}

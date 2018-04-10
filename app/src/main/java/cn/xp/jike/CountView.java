package cn.xp.jike;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;


public class CountView extends View {
    protected static final String TAG = CountView.class.getSimpleName();

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    int transparentColor = 0xffffffff;
    int originColor = 0xff000000;

    boolean plus;
    public int count;
    private int newCount;


    boolean isFirstInit = true;

    private Point[] points = new Point[4];

    private ColorObject mColorObject;

    public void setCount(int count) {
        this.count = count;
        isFirstInit = true;
        Log.d(TAG, "3333333333333333333");
//        initData();
//        postInvalidate();
    }


    public void setColor(ColorObject colorObject) {
        mColorObject = colorObject;
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
//            canvas.drawText(String.valueOf(count), 0, 80, paint);
//            canvas.drawText(String.valueOf(count), 0, 160, paint);
        } else {
            //不变部分
            paint.setColor(originColor);
            canvas.drawText(points[0].content, points[0].x, points[0].y, paint);
            //变化部分
            paint.setColor(mColorObject.color2);
            canvas.drawText(points[1].content, points[1].x, points[1].y, paint);

            //变化上部分
            paint.setColor(mColorObject.color1);
            canvas.drawText(points[2].content, points[2].x, points[2].y, paint);

            //变化下部分
            paint.setColor(mColorObject.color1);
            canvas.drawText(points[3].content, points[3].x, points[3].y, paint);
        }
    }

    /*   public void refreshView(Canvas canvas) {
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
   */
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


        ColorObject object1 = new ColorObject(originColor, transparentColor);
        ColorObject object2 = new ColorObject(transparentColor, originColor);

        calculatePoints(true);
        ObjectAnimator animator = ObjectAnimator.ofObject(this, "color", new HsvEvaluator(), object1, object2);
        animator.setDuration(350);
        animator.setInterpolator(new LinearInterpolator());
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
            Point point2;
            Point point3;
            if (newCount > count) {
                point2 = new Point(0, 80, String.valueOf(count), transparentColor);

                point3 = new Point(0, 160, String.valueOf(newCount), transparentColor);
            } else {
                point2 = new Point(0, 80, String.valueOf(newCount), transparentColor);

                point3 = new Point(0, 160, String.valueOf(count), transparentColor);
            }

            points[0] = point0;
            points[1] = point1;
            points[2] = point2;
            points[3] = point3;


        } else {
            for (int i = 0; i < newArray.length; i++) {
                if (newArray[i] != oldArray[i]) {
                    num++;
                }
            }
            float startX = paint.measureText(oldArray, 0, oldArray.length - num);

            Point point0 = new Point(0, 120, String.valueOf(newArray, 0, newArray.length - num), transparentColor);

            Point point1 = new Point(startX, 120, String.valueOf(newArray, newArray.length - num, num), transparentColor);
            Point point2;
            Point point3;

            if (newCount < count) {
                point2 = new Point(startX, 80, String.valueOf(newArray, newArray.length - num, num), transparentColor);
                point3 = new Point(startX, 80, String.valueOf(oldArray, oldArray.length - num, num), transparentColor);
            } else {
                point2 = new Point(startX, 80, String.valueOf(oldArray, oldArray.length - num, num), transparentColor);
                point3 = new Point(startX, 80, String.valueOf(newArray, newArray.length - num, num), transparentColor);
            }
            points[0] = point0;
            points[1] = point1;
            points[2] = point2;
            points[3] = point3;

        }
//
//        Point point0 = new Point(0, 120, String.valueOf(newCount), originColor);
//        points[0] = point0;
//
//        Point point1 = new Point();
//        Point point2 = new Point();


    }


    private class HsvEvaluator implements TypeEvaluator<ColorObject> {
        float[] startHsv1 = new float[3];
        float[] endHsv1 = new float[3];
        float[] outHsv1 = new float[3];

        float[] startHsv2 = new float[3];
        float[] endHsv2 = new float[3];
        float[] outHsv2 = new float[3];


        @Override
        public ColorObject evaluate(float fraction, ColorObject startValue, ColorObject endValue) {
            // 把 ARGB 转换成 HSV


            Color.colorToHSV(startValue.color1, startHsv1);
            Color.colorToHSV(endValue.color1, endHsv1);

            Color.colorToHSV(startValue.color2, startHsv2);
            Color.colorToHSV(endValue.color2, endHsv2);

            // 计算当前动画完成度（fraction）所对应的颜色值
            if (endHsv1[0] - startHsv1[0] > 180) {
                endHsv1[0] -= 360;
            } else if (endHsv1[0] - startHsv1[0] < -180) {
                endHsv1[0] += 360;
            }
            outHsv1[0] = startHsv1[0] + (endHsv1[0] - startHsv1[0]) * fraction;
            if (outHsv1[0] > 360) {
                outHsv1[0] -= 360;
            } else if (outHsv1[0] < 0) {
                outHsv1[0] += 360;
            }
            outHsv1[1] = startHsv1[1] + (endHsv1[1] - startHsv1[1]) * fraction;
            outHsv1[2] = startHsv1[2] + (endHsv1[2] - startHsv1[2]) * fraction;

            // 计算当前动画完成度（fraction）所对应的透明度
            int alpha1 = startValue.color1 >> 24 + (int) ((endValue.color1 >> 24 - startValue.color1 >> 24) * fraction);


            // 计算当前动画完成度（fraction）所对应的颜色值
            if (endHsv2[0] - startHsv2[0] > 180) {
                endHsv2[0] -= 360;
            } else if (endHsv2[0] - startHsv2[0] < -180) {
                endHsv2[0] += 360;
            }
            outHsv2[0] = startHsv2[0] + (endHsv2[0] - startHsv2[0]) * fraction;
            if (outHsv2[0] > 360) {
                outHsv2[0] -= 360;
            } else if (outHsv2[0] < 0) {
                outHsv2[0] += 360;
            }
            outHsv2[1] = startHsv2[1] + (endHsv2[1] - startHsv2[1]) * fraction;
            outHsv2[2] = startHsv2[2] + (endHsv2[2] - startHsv2[2]) * fraction;

            // 计算当前动画完成度（fraction）所对应的透明度
            int alpha2 = startValue.color2 >> 24 + (int) ((endValue.color2 >> 24 - startValue.color2 >> 24) * fraction);


            return new ColorObject(Color.HSVToColor(alpha1, outHsv1), Color.HSVToColor(alpha2, outHsv2));


        }
    }


}

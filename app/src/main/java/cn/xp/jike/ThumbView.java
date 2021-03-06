package cn.xp.jike;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class ThumbView extends View {


    protected static final String TAG = ThumbView.class.getSimpleName();
    private Bitmap unselected;
    private Bitmap selected;
    private Bitmap shining;

    private boolean isLike = false;

    private float mCenterX;
    private float mCenterY;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float FULL_SCALE = 1.0F;
    float HIDE_SCALE = 0F;

    float scale = 1.0F;

    boolean showShining = true;

    int selectedResId;
    int unSelectedResId;


    public void setLike(boolean isLike) {
        this.isLike = isLike;
    }


    public void startThumbUp() {
        isLike = !isLike;
        postInvalidate();
    }


    public ThumbView(Context context) {
        super(context);
    }

    public ThumbView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ThumbView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        unselected = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_messages_like_unselected);
        selected = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_messages_like_selected);
        shining = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_messages_like_selected_shining);

    }

    public void setThumbImage(int selectedResId, int unSelectedResId) {
        this.selectedResId = selectedResId;
        this.unSelectedResId = unSelectedResId;
        if (selectedResId != R.mipmap.ic_messages_like_selected) {
            showShining = false;
            selected = BitmapFactory.decodeResource(getResources(), selectedResId);
        }
        if (unSelectedResId != R.mipmap.ic_messages_like_unselected) {
            unselected = BitmapFactory.decodeResource(getResources(), unSelectedResId);
        }

        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        initBitmap();
        mCenterX = getWidth() / 2;
        mCenterY = getHeight() / 2;

        refreshLikeView(canvas);


    }

    public boolean isSelected() {
        return isLike;
    }


    private void refreshLikeView(Canvas canvas) {
        if (isLike) {
            canvas.save();
            canvas.scale(HIDE_SCALE, HIDE_SCALE);
            canvas.drawBitmap(unselected, mCenterX - unselected.getWidth() / 2, mCenterY - unselected.getHeight() / 2, paint);
            canvas.restore();


            canvas.save();
            canvas.scale(FULL_SCALE, FULL_SCALE);
            canvas.drawBitmap(selected, mCenterX - selected.getWidth() / 2, mCenterY - selected.getHeight() / 2, paint);
            canvas.restore();

            if (showShining) {

                canvas.save();
                canvas.scale(FULL_SCALE, FULL_SCALE);
                canvas.translate(0, -50);
                canvas.drawBitmap(shining, mCenterX - shining.getWidth() / 2, mCenterY - shining.getHeight() / 2, paint);
                canvas.restore();
            }
        } else {
            canvas.save();
            canvas.scale(FULL_SCALE, FULL_SCALE);
            canvas.drawBitmap(unselected, mCenterX - unselected.getWidth() / 2, mCenterY - unselected.getHeight() / 2, paint);
            canvas.restore();


            canvas.save();
            canvas.scale(HIDE_SCALE, HIDE_SCALE);
            canvas.drawBitmap(selected, mCenterX - selected.getWidth() / 2, mCenterY - selected.getHeight() / 2, paint);
            canvas.restore();

            if (showShining) {
                canvas.save();
                canvas.scale(HIDE_SCALE, HIDE_SCALE);
                canvas.translate(0, -50);
                canvas.drawBitmap(shining, mCenterX - shining.getWidth() / 2, mCenterY - shining.getHeight() / 2, paint);
                canvas.restore();
            }
        }
    }

    //放大动画
//    private void enlargementView() {
////        isLike = true;
//        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "scale", 0.1f, 1.0f);
//        animator.start();
//    }

    public void touchDownAnimation() {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.8f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.8f);
        ObjectAnimator.ofPropertyValuesHolder(this, holder1, holder2).start();
    }

    public void touchUpAnimation() {
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 0.8f, 1.0f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 0.8f, 1.0f);
        ObjectAnimator.ofPropertyValuesHolder(this, holder1, holder2).start();
    }


}

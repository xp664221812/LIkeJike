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
import android.view.View;


public class LikeView extends View {


    protected static final String TAG = LikeView.class.getSimpleName();
    private Bitmap unselected;
    private Bitmap selected;
    private Bitmap shining;

    private boolean isSelected = false;

    private float mCenterX;
    private float mCenterY;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    float FULL_SCALE = 1.0F;
    float HIDE_SCALE = 0F;

    float scale = 1.0F;

    public interface OnLikeListener {
        void onThumbUp();

        void onThumbDown();
    }

    private OnLikeListener likeListener;

    public void setLikeListener(OnLikeListener likeListener) {
        this.likeListener = likeListener;
        isSelected = !isSelected;
        postInvalidate();

    }


    public LikeView(Context context) {
        super(context);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        unselected = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_messages_like_unselected);
        selected = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_messages_like_selected);
        shining = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_messages_like_selected_shining);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        initBitmap();
        mCenterX = getWidth() / 2;
        mCenterY = getHeight() / 2;

        refreshLikeView(canvas);

//        refreshLikeView(canvas);

        if (isSelected) {
            //点赞

        } else {
            //取消点赞
        }

    }

    public float getScale() {
        return FULL_SCALE;
    }

    public void setScale(float scale) {
        this.scale = scale;
        postInvalidate();
    }




   /* private void initLocation(Canvas canvas) {
        mCenterX = getWidth() / 2;
        mCenterY = getHeight() / 2;

        refreshLikeView(canvas);

    }*/


    public void startAnimation() {
        isSelected = !isSelected;
        enlargementView();
        postInvalidate();

    }

    private void showLikeAnimation(Canvas canvas) {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "scale")
    }

    private void showUnlikeAnimation(Canvas canvas) {

    }

    private void refreshLikeView(Canvas canvas) {
        if (isSelected) {
            canvas.save();
            canvas.scale(HIDE_SCALE, HIDE_SCALE);
            canvas.drawBitmap(unselected, mCenterX - selected.getWidth() / 2, mCenterY - selected.getHeight() / 2, paint);
            canvas.restore();


            canvas.save();
            canvas.scale(FULL_SCALE, FULL_SCALE);
            canvas.drawBitmap(selected, mCenterX - selected.getWidth() / 2, mCenterY - selected.getHeight() / 2, paint);
            canvas.restore();


            canvas.save();
            canvas.scale(FULL_SCALE, FULL_SCALE);
            canvas.translate(0, -50);
            canvas.drawBitmap(shining, mCenterX - shining.getWidth() / 2, mCenterY - shining.getHeight() / 2, paint);
            canvas.restore();
        } else {
            canvas.save();
            canvas.scale(FULL_SCALE, FULL_SCALE);
            canvas.drawBitmap(unselected, mCenterX - selected.getWidth() / 2, mCenterY - selected.getHeight() / 2, paint);
            canvas.restore();


            canvas.save();
            canvas.scale(HIDE_SCALE, HIDE_SCALE);
            canvas.drawBitmap(selected, mCenterX - selected.getWidth() / 2, mCenterY - selected.getHeight() / 2, paint);
            canvas.restore();


            canvas.save();
            canvas.scale(HIDE_SCALE, HIDE_SCALE);
            canvas.translate(0, -50);
            canvas.drawBitmap(shining, mCenterX - shining.getWidth() / 2, mCenterY - shining.getHeight() / 2, paint);
            canvas.restore();
        }
    }

    //放大动画
    private void enlargementView() {
        isSelected = true;
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "scale", 0.1f, 1.0f);
        animator.start();
    }

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

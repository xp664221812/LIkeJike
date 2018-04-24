package cn.xp.jike;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


public class LikeView extends LinearLayout implements View.OnClickListener {


    private ThumbView thumbView;
    private CountView countView;
    protected static final String TAG = LikeView.class.getSimpleName();

    boolean isLike;

    int distance;
    int movingDistanceInY;

    float countTextSize;

    int selectedPictureResourceId;
    int unSelectedPictureResourceId;


    private onLikeListener likeListener;

    public interface onLikeListener {
        void onThumbUp();

        void onThumbDown();
    }


    public LikeView(Context context) {
        super(context);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LikeView);
        distance = array.getDimensionPixelSize(R.styleable.LikeView_distance_between_thumb_and_count, 0);
        movingDistanceInY = array.getDimensionPixelSize(R.styleable.LikeView_distance_move_in_y, 30);
        countTextSize = array.getDimension(R.styleable.LikeView_count_text_size, 14);
        selectedPictureResourceId = array.getResourceId(R.styleable.LikeView_like_selected_picture, R.mipmap.ic_messages_like_selected);
        unSelectedPictureResourceId = array.getResourceId(R.styleable.LikeView_like_unselected_picture, R.mipmap.ic_messages_like_unselected);
        array.recycle();
    }

    public LikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onAttachedToWindow() {
        Log.d(TAG,"11111111111111111111111");
        super.onAttachedToWindow();
        thumbView = findViewById(R.id.lv_start);
        countView = findViewById(R.id.cv_count);
        thumbView.setThumbImage(selectedPictureResourceId, unSelectedPictureResourceId);
        countView.setCount(1239);
        countView.setTextSize(TypedValue.COMPLEX_UNIT_PX, countTextSize);
        LinearLayout.LayoutParams params = (LayoutParams) countView.getLayoutParams();

        params.leftMargin = (int) Utils.dpToPixel(distance);

        countView.setLayoutParams(params);

        countView.setMoveDistanceInY((int) Utils.dpToPixel(movingDistanceInY));

        isLike = thumbView.isSelected();

        Drawable background = getBackground();
        if (background instanceof ColorDrawable) {
            int color = ((ColorDrawable) background).getColor();
            countView.setTransparentColor(color);
        }

        setOnClickListener(this);


    }

    public void setThumbUpAndDownImage(int selectedResId, int unSelectedResId) {
        Log.d(TAG,"2222222222222222222222222222222");
        thumbView.setThumbImage(selectedResId, unSelectedResId);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                thumbView.touchDownAnimation();
                break;
            case MotionEvent.ACTION_UP:
                thumbView.touchUpAnimation();
                break;
        }

        return super.onTouchEvent(event);

    }


    public int getLikeCount() {
        return countView.getCount();
    }


    public void setLikeCount(int count) {
        countView.setCount(count);
    }


    public void setLikeListener(onLikeListener listener) {
        this.likeListener = listener;
    }

    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }

    @Override
    public void onClick(View v) {
        setOnClickListener(null);

//        thumbView.setLike(isLike);

        thumbView.startThumbUp();

        countView.playPlusAnimation();

        if (isLike) {
            if (likeListener != null) {
                likeListener.onThumbDown();
            }
        } else {
            if (likeListener != null) {
                likeListener.onThumbUp();
            }
        }

        isLike = !isLike;

        setOnClickListener(LikeView.this);
//        likeView.startAnimation();
    }
}

package cn.xp.jike;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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

    int selectedPictureResourceId;


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
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LikeView);
        distance = a.getDimensionPixelSize(R.styleable.LikeView_distance_between_thumb_and_count, 0);
        movingDistanceInY = a.getDimensionPixelSize(R.styleable.LikeView_distance_move_in_y, 30);

        a.recycle();
    }

    public LikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        thumbView = findViewById(R.id.lv_start);
        countView = findViewById(R.id.cv_count);
        countView.setCount(999);

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
                likeListener.onThumbUp();
            }
        } else {
            if (likeListener != null) {
                likeListener.onThumbDown();
            }
        }

        isLike = !isLike;

        setOnClickListener(LikeView.this);
//        likeView.startAnimation();
    }
}

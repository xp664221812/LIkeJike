package cn.xp.jike;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


public class LikeViewLayout extends LinearLayout implements View.OnClickListener {


    private LikeView likeView;
    private CountView countView;
    protected static final String TAG = LikeViewLayout.class.getSimpleName();
    float left;
    float right;
    float top;
    float bottom;

    boolean isLike;

    public LikeViewLayout(Context context) {
        super(context);
    }

    public LikeViewLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LikeViewLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        likeView = findViewById(R.id.lv_start);
        countView = findViewById(R.id.cv_count);
        countView.setCount(999);
        isLike = likeView.isSelected();
        left = getLeft();
        right = getRight();
        top = getTop();
        bottom = getBottom();
//        Log.d(TAG, "left==" + left + ",right==" + right + ",top==" + top + ",bottom==" + bottom);
        setOnClickListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                likeView.touchDownAnimation();
                break;
            case MotionEvent.ACTION_UP:
                likeView.touchUpAnimation();
                break;
        }

        return super.onTouchEvent(event);

    }

    @Override
    public void onClick(View v) {
        setOnClickListener(null);
        likeView.setLikeListener(new LikeView.OnLikeListener() {
            @Override
            public void onThumbUp() {
                isLike=true;
                setOnClickListener(LikeViewLayout.this);
            }

            @Override
            public void onThumbDown() {
                isLike=false;
                setOnClickListener(LikeViewLayout.this);
            }
        });

        countView.playPlusAnimation(!isLike);
//        likeView.startAnimation();
    }
}

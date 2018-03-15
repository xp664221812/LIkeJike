package cn.xp.jike;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by xp on 18-2-1.
 */

public class LikeViewLayout extends LinearLayout implements View.OnClickListener{

    private LikeView likeView;


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
    public void onClick(View v) {
        likeView.
    }
}

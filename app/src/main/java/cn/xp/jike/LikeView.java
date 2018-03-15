package cn.xp.jike;

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

    OnLikeListener likeListener;


    public interface OnLikeListener {
        void onLike();
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

        initLocation(canvas);

    }

    public void setLikeListener(OnLikeListener likeListener) {
        this.likeListener = likeListener;
        this.likeListener.onLike();
    }


    private void initLocation(Canvas canvas) {
        mCenterX = getWidth() / 2;
        mCenterY = getHeight() / 2;

        canvas.save();
        canvas.drawBitmap(unselected, mCenterX - selected.getWidth() / 2, mCenterY - selected.getHeight() / 2, paint);
        canvas.restore();


        canvas.save();
        canvas.scale(0f, 0f);
        canvas.drawBitmap(selected, mCenterX - selected.getWidth() / 2, mCenterY - selected.getHeight() / 2, paint);
        canvas.restore();


        canvas.save();
        canvas.scale(0f, 0f);
        canvas.translate(0, -50);
        canvas.drawBitmap(shining, mCenterX - shining.getWidth() / 2, mCenterY - shining.getHeight() / 2, paint);
        canvas.restore();

    }


}

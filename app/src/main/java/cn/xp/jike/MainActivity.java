package cn.xp.jike;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    protected static final String TAG = MainActivity.class.getSimpleName();
    private LikeView likeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likeView = findViewById(R.id.ll_like);
        likeView.setLikeListener(new LikeView.onLikeListener() {
            @Override
            public void onThumbUp() {
                Log.d(TAG,"点赞了。。。。。。。。。count===="+likeView.getLikeCount());
            }

            @Override
            public void onThumbDown() {
                Log.d(TAG,"取消点赞了。。。。。。。。。count===="+likeView.getLikeCount());
            }
        });


    }
}

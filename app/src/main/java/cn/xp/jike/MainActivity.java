package cn.xp.jike;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    protected static final String TAG = MainActivity.class.getSimpleName();
    private LikeViewLayout likeViewLayout;
    private ThumbView thumbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* likeViewLayout = findViewById(R.id.ll_like);
        likeView = findViewById(R.id.lv_start);
        likeViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeView.startAnimation();
            }
        });*/
    }
}

# LikeView
## Summary
安卓点赞和取消点赞的效果，可以设置自己的点赞图片，也是我的第一个项目.
## Install
### gradle加载
    compile 'cn.xp.custom:LikeView:1.0.4'
### eclipse
    直接下载本项目就行
## Sample
### 基本使用
```java
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
### 设置自己的点赞图片和非点赞图
    

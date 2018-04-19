package cn.xp.jike;


public class Point {
    public float x;
    public float y;
    public String content;
    public int color;


    public Point() {

    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point(float x, float y, String content, int color) {
        this.x = x;
        this.y = y;
        this.content = content;
        this.color = color;
    }

}

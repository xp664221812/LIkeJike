package cn.xp.jike;

import java.io.Serializable;


public class ColorObject implements Serializable {

    int color1;
    int color2;

    float fraction;

    public ColorObject() {

    }

    public ColorObject(int color1, int color2) {
        this.color1 = color1;
        this.color2 = color2;
    }

    public ColorObject(int color1, int color2, float fraction) {
        this.color1 = color1;
        this.color2 = color2;
        this.fraction = fraction;
    }
}

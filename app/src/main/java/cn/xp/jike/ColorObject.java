package cn.xp.jike;

import java.io.Serializable;

/**
 * Created by xp on 18-4-10.
 */

public class ColorObject implements Serializable {

    int color1;
    int color2;

    public ColorObject() {

    }

    public ColorObject(int color1, int color2) {
        this.color1 = color1;
        this.color2 = color2;
    }
}

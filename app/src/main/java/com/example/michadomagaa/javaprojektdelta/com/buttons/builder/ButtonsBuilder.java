package com.example.michadomagaa.javaprojektdelta.com.buttons.builder;

/**
 * Created by macfr on 08.06.2017.
 */
        import android.content.Context;
        import android.widget.RelativeLayout;



public class ButtonsBuilder {

    private static int n = 4;

    public Bar initLeftBar(int w, int h, Context c, RelativeLayout rl){
        Bar leftBar = new Bar();
        for(int i = 0; i < n; i++){
            LeftButton btn = new LeftButton();
            btn.setRelativeView(w, h, c, rl, i);
            leftBar.addItem(btn);
        }
        return leftBar;
    }

    public Bar initRightBar(int w, int h, Context c, RelativeLayout rl){
        Bar rightBar = new Bar();
        for(int i = 0; i < n; i++){
            RightButton btn = new RightButton();
            btn.setRelativeView(w, h, c, rl, i);
            rightBar.addItem(btn);
        }
        return rightBar;
    }

    public Bar initBottomBar(int w, int h, Context c, RelativeLayout rl){
        Bar bottomBar = new Bar();

        return bottomBar;
    }

}
package com.example.michadomagaa.javaprojektdelta.com.buttons.decorator;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Created by macfr on 08.06.2017.
 */

public interface Item {
    void completeLayout();
    Context getContext();
    RelativeLayout getRelativeLayout();
    String getName();


}

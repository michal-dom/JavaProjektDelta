package com.example.michadomagaa.javaprojektdelta.com.buttons.decorator;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by macfr on 08.06.2017.
 */

public class OnClickItemDecorator extends ItemDecorator {

    Context context;
    RelativeLayout relativeLayout;

    String name;

    public OnClickItemDecorator(Item i) {
        super(i);
        this.context = i.getContext();
        this.relativeLayout = i.getRelativeLayout();
        this.name=i.getName();
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // cos do przyciskow
        }
    };

    public void completeLayout(){
        super.completeLayout();
        relativeLayout.setOnClickListener(listener);
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    @Override
    public String getName() {
        return name;
    }
}

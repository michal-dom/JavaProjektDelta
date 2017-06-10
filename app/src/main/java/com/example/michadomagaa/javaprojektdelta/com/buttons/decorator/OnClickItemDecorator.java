package com.example.michadomagaa.javaprojektdelta.com.buttons.decorator;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.michadomagaa.javaprojektdelta.R;

/**
 * Created by macfr on 08.06.2017.
 */

public class OnClickItemDecorator extends ItemDecorator {

    Context context;
    RelativeLayout relativeLayout;
    Activity activity;

    String name;

    private SeekBar seekBrightness, seekContrast, seekHue, seekSaturation;

    public OnClickItemDecorator(Item i) {
        super(i);
        this.context = i.getContext();
        this.relativeLayout = i.getRelativeLayout();
        this.name=i.getName();
        this.activity = i.getActivity();

        seekBrightness = (SeekBar) activity.findViewById(R.id.brightness_seekbar);
        seekContrast = (SeekBar) activity.findViewById(R.id.contrast_seekbar);
        seekHue = (SeekBar) activity.findViewById(R.id.hue_seekbar);
        seekSaturation = (SeekBar) activity.findViewById(R.id.saturation_seekbar);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (name){
                case "KONTRAST":
                    seekBrightness.setVisibility(View.INVISIBLE);
                    seekContrast.setVisibility(View.VISIBLE);
                    seekSaturation.setVisibility(View.INVISIBLE);
                    seekHue.setVisibility(View.INVISIBLE);
                    break;
                case "JASNOSC":
                    seekBrightness.setVisibility(View.VISIBLE);
                    seekContrast.setVisibility(View.INVISIBLE);
                    seekSaturation.setVisibility(View.INVISIBLE);
                    seekHue.setVisibility(View.INVISIBLE);
                    break;
                case "ODCIEN":
                    seekBrightness.setVisibility(View.INVISIBLE);
                    seekContrast.setVisibility(View.INVISIBLE);
                    seekSaturation.setVisibility(View.VISIBLE);
                    seekHue.setVisibility(View.INVISIBLE);
                    break;
                case "NASYCENIE":
                    seekBrightness.setVisibility(View.INVISIBLE);
                    seekContrast.setVisibility(View.INVISIBLE);
                    seekSaturation.setVisibility(View.INVISIBLE);
                    seekHue.setVisibility(View.VISIBLE);
                    break;
                default:
                    Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
                    break;
            }
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

    @Override
    public Activity getActivity() {
        return activity;
    }
}

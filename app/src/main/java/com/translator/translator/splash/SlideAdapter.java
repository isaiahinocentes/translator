package com.translator.translator.splash;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.translator.translator.R;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;

    //Images
    public int[] imageuno = {

            R.drawable.image_3
    };

    //Titles
    public String[] titleuno = {
    "LANGUAGE TRANSLATOR"
    };

    //Descriptions
    public String[] descriptionuno = {
            "Communication is simply the act of transferring information from one place to another\n",

    };

    //Background
    public int[] backgroundcoloruno = {
            R.drawable.bg
    };


    public SlideAdapter (Context context) {
        this.context = context;
    }



    @Override
    public int getCount() {
        return titleuno.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view==(RelativeLayout)o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        RelativeLayout layoutslide = (RelativeLayout) view.findViewById(R.id.slidelinearlayout);
        ImageView imgslide = (ImageView)  view.findViewById(R.id.slideimg);
        TextView txttitle= (TextView) view.findViewById(R.id.txttitle);
        TextView description = (TextView) view.findViewById(R.id.txtdescription);
        layoutslide.setBackgroundColor(backgroundcoloruno [position]);
        imgslide.setImageResource(imageuno[position]);
        txttitle.setText(titleuno[position]);
        description.setText(descriptionuno [position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}

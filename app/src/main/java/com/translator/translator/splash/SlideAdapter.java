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
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5
    };

    //Titles
    public String[] titleuno = {
            "COMMUNICATE",
            "SHARE IDEAS",
            "USER FRIENDLY",
            "TRAVEL WITH US",
            "HERMES TRANSLATOR"
    };

    //Descriptions
    public String[] descriptionuno = {
            "Communication is simply the act of transferring information from one place to another\n",
            "There are many benefits from sharing ideas at work. I thought it would be good to write this because I occasionally hear people talk about sharing creates stolen ideas and in return fighting over those stolen ideas.",
            "User-friendly describes a hardware device that is easy to use. It is \"friendly\" to the user, meaning it is not difficult to learn or understand.",
            "Travel with Apostolic Travel and Tours." + "APOSTOLIC  TRAVEL & TOURS  started out as a small ticketing outlet in Buendia offering domestic and international ticketing. It was registered on December 08, 2007 and accredited by The Department Of Foreign Affairs (DFA). ",
            "Welcome to Hermes Translator"
    };

    //Background
    public int[] backgroundcoloruno = {
            R.drawable.bg,
            R.drawable.bg,
            R.drawable.bg,
            R.drawable.bg,
            Color.rgb(255, 167, 170)
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

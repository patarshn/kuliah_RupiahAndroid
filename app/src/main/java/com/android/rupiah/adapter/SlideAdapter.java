package com.android.rupiah.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.android.rupiah.R;

import java.util.ArrayList;

public class SlideAdapter extends PagerAdapter {

    int [] images;
    LayoutInflater layoutInflater;
    Context context;

    public SlideAdapter(int [] images, Context context) {
        this.images = images;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount(){
        return images.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View imageLayout = layoutInflater.inflate(R.layout.slide_images,container,false);
        ImageView imageView = imageLayout.findViewById(R.id.image1);

        imageView.setImageDrawable(context.getDrawable(images[position]));
        container.addView(imageLayout);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view,@NonNull Object object){

        return view.equals(object);
    }
}

package com.eagle.cansacare.onboardingAndSplash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.eagle.cansacare.R;
import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;

    int images[] = {
            R.drawable.first_on_boarding,
            R.drawable.on_boarding_image_two,
            R.drawable.on_boarding_image_three,
    };

    int headings[] = {
            R.string.first_on_boarding_page,
            R.string.first_on_boarding_page,
            R.string.first_on_boarding_page,

    };

    int description[] = {
            R.string.first_on_boarding_description,
            R.string.second_on_boarding_description,
            R.string.third_on_boarding_description
    };

    public ViewPagerAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {

        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView slideTitleImage = (ImageView) view.findViewById(R.id.on_boarding_first_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.on_boarding_header);
        TextView slideDescription = (TextView) view.findViewById(R.id.on_boarding_description);

        slideTitleImage.setImageResource(images[position]);
        slideHeading.setText(headings[position]);
        slideDescription.setText(description[position]);

        container.addView(view);
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}

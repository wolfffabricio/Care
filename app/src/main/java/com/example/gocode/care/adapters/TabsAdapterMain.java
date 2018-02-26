package com.example.gocode.care.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.example.gocode.care.R;
import com.example.gocode.care.fragments.PerfilUserFragmentMain;
import com.example.gocode.care.fragments.PerfisFeedFragmentMain;

public class TabsAdapterMain extends FragmentPagerAdapter {

    private Context mcontext;
    private int[] icons = {R.drawable.ic_home_white_24dp, R.drawable.ic_account_white_24dp};
    private int heightIcon;

    public TabsAdapterMain(FragmentManager fm, Context context) {
        super(fm);
        this.mcontext = context;
        double scale = context.getResources().getDisplayMetrics().density;
        heightIcon = (int) (24 * scale + 0.5f);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        if (position == 0) {
            fragment = new PerfisFeedFragmentMain();
        } else {
            fragment = new PerfilUserFragmentMain();
        }

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable = mcontext.getResources().getDrawable(icons[position]);
        drawable.setBounds(0, 0, heightIcon, heightIcon);

        ImageSpan imageSpan = new ImageSpan(drawable);
        SpannableString sp = new SpannableString(" ");
        sp.setSpan(imageSpan, 0, sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sp;
    }
}

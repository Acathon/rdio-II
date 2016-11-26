package iram.radioplayer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import iram.radioplayer.Constant;
import iram.radioplayer.fragments.Radio_Fragment;

/**
 * Created by musta on 16/11/2016.
 */

public class Radio_PagerAdapter extends FragmentStatePagerAdapter {

    public static final int PAGE_COUNT = 23;

    public Radio_PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new Radio_Fragment().setFragmentPosition(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public void setPageCount(int position, int vibrantColor, int mutedColor) {
        Constant.vibrantColors[position] = vibrantColor;
        Constant.mutedColors[position] = mutedColor;
    }
}

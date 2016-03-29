package com.example.savi.atun.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.savi.atun.Fragments.TabOneFragment;
import com.example.savi.atun.Fragments.TabThreeFragment;
import com.example.savi.atun.Fragments.TabTwoFragment;

import java.util.ArrayList;

/**
 * Created by devuser on 07-03-2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int noOfTabs;
    ArrayList<Integer> prevList, currList,nextList ;

    public PagerAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);
        prevList = new ArrayList<>();
        currList = new ArrayList<>();
        nextList = new ArrayList<>();
        this.noOfTabs = noOfTabs ;
    }



    @Override
    public int getItemPosition(Object object) {
            return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                TabOneFragment firstFragment = new TabOneFragment();
                return  firstFragment ;

            case 1 :
                TabTwoFragment secondFragment = new TabTwoFragment();
                return  secondFragment ;

            case 2 :
                TabThreeFragment thirdFragment = new TabThreeFragment();
                /*Bundle bundleThird = new Bundle();
                bundleThird.putIntegerArrayList("numlist",nextList);
                thirdFragment.setArguments(bundleThird);*/
                return thirdFragment;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {

        return noOfTabs;
    }


}

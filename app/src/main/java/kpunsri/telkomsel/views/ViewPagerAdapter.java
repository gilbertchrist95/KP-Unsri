package kpunsri.telkomsel.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import kpunsri.telkomsel.R;
import kpunsri.telkomsel.activities.FormTab1;
import kpunsri.telkomsel.activities.FormTab2;
import kpunsri.telkomsel.activities.ActivityTab3;
import kpunsri.telkomsel.activities.ActivityTab4;
import kpunsri.telkomsel.activities.ActivityTab5;

/**
 * Created by hp1 on 21-01-2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    int icons[] = {R.drawable.tab1,R.drawable.tab2,R.drawable.icon3,R.drawable.icon4,R.drawable.icon5};


    CharSequence Titles[ ]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    private Context mContext;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb, Context context) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.mContext = context;
    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        if(position == 0) // if the position is 0 we are returning the First tab
        {
            FormTab1 formTab1 = new FormTab1();
            return formTab1;
        }
        else if(position == 1)            // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            FormTab2 formTab2 = new FormTab2();
            return formTab2;
        }
        else if(position ==2)             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            ActivityTab3 activityTab3 = new ActivityTab3();
            return activityTab3;

        }
        else if(position == 3)             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            ActivityTab4 activityTab4 = new ActivityTab4();
            return activityTab4;
        }
        else if(position == 4)             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            ActivityTab5 activityTab5 = new ActivityTab5();
            return activityTab5;
        }
        else
        {
            return null;
        }
    }

    // This method return the titles for the Tabs in the Tab Strip
    @Override
    public CharSequence getPageTitle(int position) {

//        Drawable drawable=getRescource().getDrawable(icons[position]);

//        Drawable drawable= ContextCompat.getDrawable(mContext,icons[position]);
//        drawable.setBounds(0,0,48,48);
//        ImageSpan imageSpan = new ImageSpan(drawable);
//        SpannableString spannableString = new SpannableString("");
//        spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return spannableString;

        Drawable image = mContext.getResources().getDrawable(icons[position]);
        image.setBounds(0, 10, 190, 110);
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
package com.example.cybercardv02;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * <h1>QR Code</h1>
 *
 * @author  Blane Staskiewicz
 * @version 2.0
 * @since   2021-07-28
 */
public class fragmentAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;
    
    /**
     * Constructor initializes adapter with number of tabs
     *
     * @param  fm         Fragment Manager
     * @param  NoofTabs   Number of Tabs 
     */
    public fragmentAdapter(@NonNull FragmentManager fm, int NoofTabs) {
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    
    /**
     * This method returns number of tabs
     *
     * @return  mNumOfTabs  Number of tabs
     */
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    
    /**
     * This method returns a Tab based off of position passed
     *
     * @param   position   Value (0-3) for each tab
     * @return  new Tab()  New Tab based on position value
     */
    @NonNull
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                return new Home();
            case 1:
                return new Contact();
            case 2:
                return new QrCode();
            default:
                return null;
        }
    }
}

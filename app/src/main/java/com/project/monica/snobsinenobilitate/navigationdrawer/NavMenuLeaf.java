package com.project.monica.snobsinenobilitate.navigationdrawer;

/**
 * Created by monica on 17/01/2015.
 */
public class NavMenuLeaf implements NavDrawerItemInterface {

    private String mLabel;
    private NavDrawerActivityConfiguration.MENU mId;
    private boolean isEnabled;

    public  NavMenuLeaf()
    {

    }
    public static NavMenuLeaf create(NavDrawerActivityConfiguration.MENU id, String label)
    {
        NavMenuLeaf nLeaf = new NavMenuLeaf();
        nLeaf.mId = id;
        nLeaf.mLabel = label;
        return nLeaf;
    }

    @Override
    public NavDrawerActivityConfiguration.MENU getNavDrawerId() {
        return mId;
    }

    @Override
    public String getNavDrawerLabel() {
        return mLabel;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}

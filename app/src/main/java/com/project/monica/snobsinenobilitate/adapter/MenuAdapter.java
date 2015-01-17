package com.project.monica.snobsinenobilitate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.project.monica.snobsinenobilitate.R;

/**
 * Created by monica on 17/01/2015.
 */
public class MenuAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;

    public MenuAdapter(Context context, int textViewResourceId, String[] objects) {
        super(context, textViewResourceId, objects);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
//        NavMenuLeaf menuItem = this.getItem(position);

        view = getItemView(convertView, parent, "textMock");

        return view;
    }

    public View getItemView(View convertView, ViewGroup parentView, String navDrawerItem) {

//        NavMenuLeaf menuItem = navDrawerItem;
        NavMenuItemHolder navMenuItemHolder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.navdrawer_item, parentView, false);
            TextView labelView = (TextView) convertView
                    .findViewById(R.id.navmenuitem_label);
            navMenuItemHolder = new NavMenuItemHolder();
            navMenuItemHolder.labelView = labelView;
            convertView.setTag(navMenuItemHolder);
        }

       else
        {
            navMenuItemHolder = (NavMenuItemHolder) convertView.getTag();
        }

        navMenuItemHolder.labelView.setText(navDrawerItem);

        return convertView;
    }

//    public View getSectionView(View convertView, ViewGroup parentView,
//                               NavMenuLeaf navDrawerItem) {
//
//        NavMenuSection menuSection = (NavMenuSection) navDrawerItem ;
//        NavMenuSectionHolder navMenuItemHolder = null;
//
//        if (convertView == null) {
//            convertView = inflater.inflate( R.layout.navdrawer_section, parentView, false);
//            TextView labelView = (TextView) convertView
//                    .findViewById( R.id.navmenusection_label );
//
//            navMenuItemHolder = new NavMenuSectionHolder();
//            navMenuItemHolder.labelView = labelView ;
//            convertView.setTag(navMenuItemHolder);
//        }
//
//        if ( navMenuItemHolder == null ) {
//            navMenuItemHolder = (NavMenuSectionHolder) convertView.getTag();
//        }
//
//        navMenuItemHolder.labelView.setText(menuSection.getLabel());
//
//        return convertView ;
//    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }


    private static class NavMenuItemHolder {
        private TextView labelView;
    }

//    private class NavMenuSectionHolder {
//        private TextView labelView;
//    }
}
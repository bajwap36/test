package com.example.briskdelivery;

import android.content.Context;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    List<String> list;
    List<Integer> pic;

    // Constructor
    public ImageAdapter(List<String> list, List<Integer> pic) {
        this.list = list;
        this.pic = pic;
        // sitesList = this.sitesList; do NOT set param = member value!
    }
    public int getCount() {
        return list.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // return null;
        if (view == null){
            //inflate the external layout and assign it to
            //the view object, here view corresponds to the linear (external) layout
            LayoutInflater layoutInflater
                    = LayoutInflater.from(viewGroup.getContext());
            view = layoutInflater.inflate(R.layout.listview_layout,
                    viewGroup,false);
        }
        //notice that findById is called on the view object (external layout view object)
        TextView txtViewSite = view.findViewById(R.id.tvRestList);

        txtViewSite.setText("          "+list.get(i));

        // txtViewSite.setTextColor(Color.RED); //just makes the text in the textview have red color

        //we can set drawable image on the left after
        Drawable img = viewGroup.getResources().getDrawable(pic.get(i));

        //img is always size 0 to begin with
        //set the image size/bounds
        img.setBounds(0,0,300,300); //this means the top, left 0,0 to bottom right 80,80 is the size of the drawable image

        //this means that the left drawable is the image, and top, right, bottom all have no images
        //in the textview
        txtViewSite.setCompoundDrawables(img,null,null,null);
        txtViewSite.setTextSize(25);

        //this is the spacing around drawable imgage
        txtViewSite.setCompoundDrawablePadding(7); //spacing around the drawable image


     //   txtViewSite.setGravity(Gravity.); //centers the text in the TextView

        return view;
        //return null; see what happens when you say return null
    }


}
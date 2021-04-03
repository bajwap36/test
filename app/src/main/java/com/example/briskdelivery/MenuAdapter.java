package com.example.briskdelivery;

import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuAdapter extends BaseAdapter {

    List<HashMap<String, String>> hmList = new ArrayList<HashMap<String, String>>();

    // Constructor
    public MenuAdapter( List<HashMap<String, String>> hmList) {
        this.hmList = hmList;
        // sitesList = this.sitesList; do NOT set param = member value!
    }
    public int getCount() {
        return hmList.size();
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
            view = layoutInflater.inflate(R.layout.listview_menu,
                    viewGroup,false);
        }
        //notice that findById is called on the view object (external layout view object)
        TextView txtItem = view.findViewById(R.id.txtItem);
        TextView txtSubItem1 = view.findViewById(R.id.txtSubItem1);

        TextView txtSubItem2 = view.findViewById(R.id.txtSubItem2);


        System.out.println(hmList.size());

        //System.out.println("why "+hmList.get(i).get("title"));

        txtItem.setText(hmList.get(i).get("title"));
         txtSubItem1.setText(hmList.get(i).get("desc"));

         txtSubItem2.setText(hmList.get(i).get("price"));


        // txtViewSite.setTextColor(Color.RED); //just makes the text in the textview have red color

        //we can set drawable image on the left after
       // Drawable img = viewGroup.getResources().getDrawable(pic.get(i));

        //img is always size 0 to begin with
        //set the image size/bounds
      //  img.setBounds(0,0,300,300); //this means the top, left 0,0 to bottom right 80,80 is the size of the drawable image

        //this means that the left drawable is the image, and top, right, bottom all have no images
        //in the textview

        txtItem.setGravity(Gravity.CENTER); //centers the text in the TextView
        txtSubItem1.setGravity(Gravity.CENTER); //centers the text in the TextView

        txtSubItem2.setGravity(Gravity.CENTER); //centers the text in the TextView


        return view;
        //return null; see what happens when you say return null
    }

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.jaipurindian,
            R.drawable.subway,
            R.drawable.aandw,
            R.drawable.zorbas,
            R.drawable.dairyqueen,
            R.drawable.seveneleven,
            R.drawable.unclefatih,
            R.drawable.donairdude,
            R.drawable.pizza85,
            R.drawable.iceandspice,
            R.drawable.mcdonald,
            R.drawable.chatime,
            R.drawable.freshslice,
            R.drawable.beercreek,
            R.drawable.pinchofspice




    };
}

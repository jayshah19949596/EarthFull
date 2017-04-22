package com.example.jaysh.earthfull.volunteer;

/**
 * Created by jaysh on 4/22/2017.
 */
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.example.jaysh.earthfull.R;
import com.example.jaysh.earthfull.data.*;

import java.util.ArrayList;


public class SearchAdapter extends CursorAdapter {

    DBMgr DBMgr;
    private Cursor mCursor;
    private Context mContext;
    public static int currentPosition;
    public SearchAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, ResourceCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mContext = context;
        mCursor = cursor;

        Log.d("SearchAdapter","In the constructor ");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("FoodItem","getView() - pos: " + position);
        if (!mCursor.moveToPosition(position)) {
            Log.d("FoodItem", "Can't move to position: " + position);
            return null;
        }

        if (convertView == null) {
            convertView = newView(mContext, mCursor, null);
        }
        bindView(convertView,mContext, mCursor);
        currentPosition = position;
        //convertView.setOnClickListener(new OnItemClickListener(position));
        return convertView;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View rowView = LayoutInflater.from(context).inflate(R.layout.search_list_view, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.descriptionValue = (TextView) rowView.findViewById(R.id.textEventDescValue);
        rowView.setTag(holder);
        Log.d("FoodItem","Description Value : /n"+cursor.getString(cursor.getColumnIndex(DBMgr.EVENT_NAME)));
        return rowView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        holder.descriptionValue.setText(cursor.getString(cursor.getColumnIndex(DBMgr.EVENT_DESCRIPTION)));

//        ArrayList<String> position_array_list = new ArrayList<String>();
//
//        Log.d("FoodItem","Value of Dishname : " +holder.dishName.getText());
//
//        Log.d("FoodItem","Value of Dishname : " +String.valueOf(MenuActivity.selectedDishes));
//
//
//
//        if(MenuActivity.selectedDishes.contains(holder.dishName.getText()))
//        {
//            Log.d("FoodItem","Dishname is present");
//
//            //position_array_list.add(currentPosition);
//            holder.dishCheckBox.setChecked(true);
//        }
//
//        else
//        {
//            Log.d("FoodItem","Dishname not present");
//
//            holder.dishCheckBox.setChecked(false);
//        }
//

    }


    @Override
    public int getCount() {
        Log.d("FoodItem","getCount(): " + (mCursor == null || mCursor.isClosed() ? 0 : mCursor.getCount()));
        return mCursor == null || mCursor.isClosed() ? 0 : mCursor.getCount();
    }

    class ViewHolder {
        TextView descriptionValue;
    }

}


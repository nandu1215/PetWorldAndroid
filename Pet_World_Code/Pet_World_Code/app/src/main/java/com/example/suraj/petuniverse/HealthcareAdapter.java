package com.example.suraj.petuniverse;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by suraj on 3/18/2016.
 */
public class HealthcareAdapter extends BaseExpandableListAdapter {
    private Context ctx;
    private HashMap<String, List<String>> Healthcare_Category;
    private List<String> Healthcare_List;

    public HealthcareAdapter(Context ctx,HashMap<String, List<String>> Healthcare_Category, List<String> Healthcare_List ){
        this.ctx=ctx;
        this.Healthcare_Category=Healthcare_Category;
        this.Healthcare_List=Healthcare_List;
    }

    @Override
    public int getGroupCount() {
        return Healthcare_List.size();
    }

    @Override
    public int getChildrenCount(int arg0) {
        return Healthcare_Category.get(Healthcare_List.get(arg0)).size();
//        return 0;
    }

    @Override
    public Object getGroup(int arg0) {

        return Healthcare_List.get(arg0);
    }

    @Override
    public Object getChild(int parent, int child) {
        return Healthcare_Category.get(Healthcare_List.get(parent)).get(child);
    }

    @Override
    public long getGroupId(int arg0) {
        return arg0;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertview, ViewGroup parentview) {
        String group_title = (String) getGroup(parent);
        if(convertview == null){
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflater.inflate(R.layout.content_parent_layout,parentview,false);
        }
        TextView parent_textview = (TextView) convertview.findViewById(R.id.parent_txt);
        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);
        return convertview;
    }

    @Override
    public View getChildView(int parent, int child, boolean lastChild, View convertview, ViewGroup parentview) {
        String child_title = (String) getChild(parent, child);
        if(convertview == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflater.inflate(R.layout.content_child_activity, parentview, false);
        }
        TextView child_textview = (TextView) convertview.findViewById(R.id.child_txt);
        child_textview.setText(child_title);
        return convertview;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}

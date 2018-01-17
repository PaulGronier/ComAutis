package com.example.paulg.comautis.ui.child;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.model.Child;

import java.util.List;

/**
 * Created by iem on 17/01/2018.
 */

public class AdapterListChild extends BaseAdapter {

    List<Child> mChild;
    Context context;
    TextView childName;
    ImageView childPic, removeButton, modifButton;

    public AdapterListChild(List<Child> mChild, Context context) {
        this.mChild = mChild;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mChild.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ConstraintLayout layout = getConstraintLayoutWithInitView(position, view);
        return layout;
    }

    @NonNull
    private ConstraintLayout getConstraintLayoutWithInitView(int position, View view) {
        ConstraintLayout layout;
        if (view == null){
            layout = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.activity_choose_child,null);
        }else {
            layout = (ConstraintLayout) view;
        }

        childName = layout.findViewById(R.id.child_name);
        childPic = layout.findViewById(R.id.child_pic);
        removeButton = layout.findViewById(R.id.remove_button);
        modifButton = layout.findViewById(R.id.modif_button);
        childPic.setImageResource(R.drawable.child_face);
        childName.setText(mChild.get(position).getName());
        removeButton.setImageResource(R.drawable.ic_action_name);
        modifButton.setImageResource(R.drawable.modif);
        return layout;
    }

}
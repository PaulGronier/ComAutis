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
import com.example.paulg.comautis.mvp.mvp.Model.Child;

import java.util.List;

/**
 * Created by iem on 17/01/2018.
 */

public class AdapterListChild extends BaseAdapter {

    List<Child> mChild;
    Context context;
    TextView childName;
    ImageView childPic, deleteButton, modifButton;

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
        ConstraintLayout layout;
        if( view == null) {
            layout = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.child_activity, null);
        }else {
            layout = (ConstraintLayout) view;
        }
            childName = layout.findViewById(R.id.tv_child_name);
            childPic = layout.findViewById(R.id.child_pic);
            modifButton = layout.findViewById(R.id.modif_button);
            deleteButton = layout.findViewById(R.id.deleteButton);

            childPic.setImageResource(R.drawable.child_face);
            childName.setText(mChild.get(position).getName());
            modifButton.setImageResource(R.drawable.modif);
            deleteButton.setImageResource(R.drawable.delete);
        return layout;
    }
}
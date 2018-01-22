package com.example.paulg.comautis.ui.child;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.Model.Child;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iem on 17/01/2018.
 */

public class AdapterListChild extends RecyclerView.Adapter<AdapterListChild.ChildHolder> {

    private final Context mContext;
    private final onClickListenner mListener;
    private final List<Child> mChild;

    public AdapterListChild(Context context, List<Child> child, onClickListenner listener) {
        mContext = context;
        mChild = child;
        mListener = listener;
    }

    @Override
    public ChildHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.child_activity, parent, false);
        return new ChildHolder(view);
    }

    @Override
    public void onBindViewHolder(ChildHolder holder, int position) {
        holder.bindButton(mChild.get(position));

    }

    @Override
    public int getItemCount() {
        return mChild.size();
    }


    public interface onClickListenner{

        void onRemoveClickItem(String childID);
        void onItemClick(String childID);


    }

    public class ChildHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.child_pic) ImageView childPic;
        @BindView(R.id.tv_child_name) TextView childName;
        @BindView(R.id.deleteButton) ImageView deleteButton;
        @BindView(R.id.modif_button) ImageView modifButton;
        View mView;

        public ChildHolder(View itemView) {
            super(itemView);
            mView = itemView;

            ButterKnife.bind(this, itemView);
        }
        public void bindButton(Child mChild){

            childPic.setImageResource(R.drawable.child_face);
            childName.setText(mChild.getName());
            //childName.setText(mChild.get(position).getName());
            deleteButton.setImageResource(R.drawable.delete);
            modifButton.setImageResource(R.drawable.modif);

            deleteButton.setOnClickListener(view -> mListener.onRemoveClickItem(mChild.getId()));
            childName.setOnClickListener(view -> mListener.onItemClick(mChild.getId()));
        }
    }
}

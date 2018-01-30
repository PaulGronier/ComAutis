package com.example.paulg.comautis.ui.picture;

/**
 * Created by paulg on 25/01/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.example.paulg.comautis.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import android.widget.TextView;

import com.example.paulg.comautis.mvp.Model.Picture;


/**
 * Created by paulg on 17/01/2018.
 */

public class RecyclerPictureAdapter extends RecyclerView.Adapter<RecyclerPictureAdapter.PictureHolder> implements Filterable {

    private final Context mContext;
    private final OnClickListener mListener;

    private final ArrayList<Picture> mPictures;
    private ArrayList<Picture> mPicturesFiltered;


    public interface OnClickListener {
        /*void onItemClick(String pageId, String name);
        void onModifClickItem();
        void onRemoveClickItem(String pageId);*/
    }

    public RecyclerPictureAdapter(Context context, ArrayList<Picture> pictures, OnClickListener listener) {
        mContext = context;
        mListener = listener;
        mPictures = pictures;
    }

    @Override
    public PictureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_picture, parent, false);

        return new PictureHolder(view);
    }

    @Override
    public void onBindViewHolder(PictureHolder holder, int position) {
        holder.bindButtons(mPictures.get(position));
    }

    @Override
    public int getItemCount() {
        return mPictures.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mPicturesFiltered = mPictures;
                } else {
                    ArrayList<Picture> filteredList = new ArrayList<>();
                    for (Picture picture : mPictures) {
                        if (picture.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(picture);
                        }
                    }
                    mPicturesFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mPicturesFiltered;
                return filterResults;
            }

                @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mPicturesFiltered = (ArrayList<Picture>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }



    public class PictureHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.picture_view) ImageView mPictureView;

        @BindView(R.id.picture_title) TextView mPictureTitleView;

        View mView;

        public PictureHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void bindButtons(Picture picture) {
            mPictureView.setImageBitmap(picture.getBitmap());
            mPictureTitleView.setText(picture.getName());
        }
    }
}


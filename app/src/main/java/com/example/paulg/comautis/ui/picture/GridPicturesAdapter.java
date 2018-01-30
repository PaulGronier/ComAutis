package com.example.paulg.comautis.ui.picture;

/**
 * Created by paulg on 24/01/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.Model.Picture;

import java.util.ArrayList;
import java.util.List;


public class GridPicturesAdapter extends BaseAdapter implements Filterable{

    private final List<Boolean> mIsSelected;
    private final List<Picture> mListPictures;
    private List<Boolean> mIsSeletedFiltered;
    private List<Picture> mPicturesFiltered;
    private final Context mContext;

    public GridPicturesAdapter(List<Picture> listPicture , List<Boolean> isSelected , Context context) {
        mListPictures = listPicture;
        mPicturesFiltered = listPicture;
        mIsSelected = isSelected;
        mIsSeletedFiltered = isSelected;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mListPictures.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layout;
        if (convertView == null){
            layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.grid_choose_picture_item , null);
        }else {
            layout = (RelativeLayout)convertView;
        }

        ImageView imageViewPicture = (ImageView) layout.findViewById(R.id.grid_item_image);
        ImageView imageSelected = (ImageView) layout.findViewById(R.id.selected_pictures);
        TextView textViewPictureName = (TextView) layout.findViewById(R.id.grid_item_name);

        imageViewPicture.setImageBitmap(mListPictures.get(position).getBitmap());
        if(mIsSelected.get(position))
            imageSelected.setVisibility(View.VISIBLE);
        textViewPictureName.setText(mListPictures.get(position).getName());

        return layout;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mPicturesFiltered = mListPictures;
                    mIsSeletedFiltered = mIsSelected;
                } else {
                    ArrayList<Picture> filteredList = new ArrayList<>();
                    for (Picture picture : mListPictures) {
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
}
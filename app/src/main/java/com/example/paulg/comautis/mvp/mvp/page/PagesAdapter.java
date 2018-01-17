package com.example.paulg.comautis.mvp.mvp.page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paulg.comautis.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paulg on 17/01/2018.
 */

public class PagesAdapter extends ArrayAdapter <Page> {



    public PagesAdapter(Context context, ArrayList<Page> pages) {
        super(context, 0, pages);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        Page page = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_page, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else { holder = (ViewHolder) convertView.getTag(); }

        holder.mPagePictureView.setImageResource(R.drawable.ic_page);
        holder.mPageTitleView.setText(page.getTitle());
        holder.mModifButtonView.setImageResource(R.drawable.modif);
        holder.mRemoveButtonView.setImageResource(R.drawable.ic_action_name);

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.page_pic) ImageView mPagePictureView;
        @BindView(R.id.page_title) TextView mPageTitleView;
        @BindView(R.id.modif_button) ImageView mModifButtonView;
        @BindView(R.id.remove_button) ImageView mRemoveButtonView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

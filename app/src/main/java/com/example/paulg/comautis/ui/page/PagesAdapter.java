package com.example.paulg.comautis.ui.page;

/**
 * Created by paulg on 25/01/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.Model.Page;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by paulg on 17/01/2018.
 */

public class PagesAdapter extends RecyclerView.Adapter<PagesAdapter.PagesHolder> {

    private final Context mContext;
    private final OnClickListener mListener;

    private final List<Page> mPages;


    public PagesAdapter(Context context, List<Page> pages, OnClickListener listener) {
        mContext = context;
        mListener = listener;
        mPages = pages;
    }

    @Override
    public PagesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_page, parent, false);

        return new PagesHolder(view);
    }

    @Override
    public void onBindViewHolder(PagesHolder holder, int position) {
        holder.bindButtons(mPages.get(position));
    }

    @Override
    public int getItemCount() {
        return mPages.size();
    }

    public interface OnClickListener {
        void onItemClick(String pageId, String name);
        void onRemoveClickItem(String pageId);
    }

    public class PagesHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.page_pic)
        ImageView mPagePictureView;
        @BindView(R.id.page_title)
        TextView mPageTitleView;

        @BindView(R.id.remove_button)
        ImageView mRemoveButtonView;

        View mView;

        public PagesHolder(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void bindButtons(Page page) {
            mPagePictureView.setImageResource(R.drawable.ic_page);
            mPageTitleView.setText(page.getName());
            mRemoveButtonView.setImageResource(R.drawable.delete);

            mView.setOnClickListener(v-> mListener.onItemClick(page.getId(), page.getName()));
            mRemoveButtonView.setOnClickListener(v -> mListener.onRemoveClickItem(page.getId()));
        }
    }
}


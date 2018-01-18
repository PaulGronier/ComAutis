package com.example.paulg.comautis.ui.page;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.model.page.Page;
import com.example.paulg.comautis.mvp.model.page.PagesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paulg on 16/01/2018.
 */

public class ChoosePageActivity extends AppCompatActivity implements PagesAdapter.OnClickListener {

    @BindView(R.id.lv_pages)
    RecyclerView mListPagesView;

    private List<Page> mListPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_pages);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void onModifClickItem() {
        Toast.makeText(this, "Modification", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRemoveClickItem() {
        Toast.makeText(this, "Suppression", Toast.LENGTH_SHORT).show();
    }

    private void init() {
        mListPages = new ArrayList<>();
        mListPages.add(new Page("Sortie à la plage"));

        PagesAdapter mPagesAdapter = new PagesAdapter(this, mListPages, this);
        mListPagesView.setAdapter(mPagesAdapter);
        mListPagesView.setLayoutManager(new LinearLayoutManager(this));
        mListPagesView.setVisibility(View.VISIBLE);
    }


}

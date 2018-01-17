package com.example.paulg.comautis.ui.page;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.model.page.Page;
import com.example.paulg.comautis.mvp.model.page.PagesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by paulg on 16/01/2018.
 */

public class ChoosePageActivity extends AppCompatActivity {

    @BindView(R.id.lv_pages) ListView mListPagesView;

    private List<Page> mListPages = new ArrayList<>();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_pages);
        ButterKnife.bind(this);
        init();


    }


    private void init () {
        ArrayList<Page> pages = new ArrayList<>();
        pages.add(new Page("Sortie à la plage"));
        PagesAdapter mPagesAdapter = new PagesAdapter(this, pages);

        mListPagesView.setAdapter(mPagesAdapter);
    }


}

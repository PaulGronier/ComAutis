package com.example.paulg.comautis.ui.page;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.Database.RequestCallback;
import com.example.paulg.comautis.mvp.Model.Child;
import com.example.paulg.comautis.mvp.Model.Model;
import com.example.paulg.comautis.mvp.Model.Page;
import com.example.paulg.comautis.ui.BaseActivity;
import com.example.paulg.comautis.ui.child.ChooseChildActivity;
import com.example.paulg.comautis.ui.picture.ChoosePictureActivity;
import com.example.paulg.comautis.ui.timer.ShowPicturesTimerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by paulg on 16/01/2018.
 */

public class ChoosePageActivity extends BaseActivity implements PagesAdapter.OnClickListener {

    public static final String EXTRA_PAGE_ID = "page_id";
    public static final String EXTRA_PAGE_NAME = "page_name";


    @BindView(R.id.lv_pages)
    RecyclerView mListPagesView;

    private FloatingActionButton mFloatingActionButton;

    EditText mEditDialogText;

    List<Child> mListChild;
    private List<Page> mListPages;
    private PagesAdapter mPagesAdapter;
    public String mChildId;
    public String mChildName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pages);
        mFloatingActionButton = findViewById(R.id.add_page_button);
        mListChild = new ArrayList<>();
        if(getIntent() != null){
            if(getIntent().getExtras() != null) {
                mChildId = getIntent().getExtras().getString(ChooseChildActivity.EXTRA_CHILD_ID);
                mChildName = getIntent().getExtras().getString(ChooseChildActivity.EXTRA_CHILD_NAME);
            }
        }
        ButterKnife.bind(this);
        init();
        addPageInRecyclerView();
        initAddPageButton();
        loadPages();
        getSupportActionBar().setTitle("Pages de " + mChildName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick(String pageId, String name) {
        Toast.makeText(this, "ItemClick", Toast.LENGTH_SHORT).show();
        Intent intentName = new Intent(getBaseContext(), ShowPicturesTimerActivity.class);
        intentName.putExtra(EXTRA_PAGE_ID, pageId);
        intentName.putExtra(EXTRA_PAGE_NAME, name);
        startActivity(intentName);
    }

    @Override
    public void onModifClickItem() {
        Toast.makeText(this, "Modification", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRemoveClickItem(String pageId) {
        LayoutInflater inflaterDeleteChild = getLayoutInflater();
        View dialogDeleteLayout = inflaterDeleteChild.inflate(R.layout.dialog_delete_page, null);
        AlertDialog.Builder mDeleteBuilder = new AlertDialog.Builder(ChoosePageActivity.this);
        mDeleteBuilder.setView(dialogDeleteLayout);
        mDeleteBuilder.setPositiveButton(R.string.btn_ad_positive, (dialog, which) -> {
            String idPageToDelete = pageId;
            mLocalDb.deletePageById(idPageToDelete, null);

            Toast.makeText(getApplicationContext(), "Page supprimée",
                    Toast.LENGTH_SHORT).show();
            addPageInRecyclerView();
        });
        mDeleteBuilder.setNegativeButton(R.string.btn_ad_negative, (dialog, which) -> Toast.makeText(getApplicationContext(), "Annulé", Toast.LENGTH_SHORT).show());

        mDeleteBuilder.show();
    }

    private void init() {
        mListPages = new ArrayList<>();
        mPagesAdapter = new PagesAdapter(this, mListPages, this);
        mListPagesView.setAdapter(mPagesAdapter);
        mListPagesView.setLayoutManager(new LinearLayoutManager(this));
        mListPagesView.setVisibility(View.VISIBLE);

    }


    private void addPageInRecyclerView(){
        mLocalDb.requestPageByChild(mChildId, new RequestCallback() {
            @Override
            public void onResult(List<? extends Model> entities) {
                mListPages.clear();
                for (int i = 0; i < entities.size(); i++) {
                    mListPages.add((Page) entities.get(i));
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        });

        mPagesAdapter.notifyDataSetChanged();
        //init list view with list child items
        mListPagesView.setAdapter(mPagesAdapter);
    }

    public void initAddPageButton() {
        mFloatingActionButton.setOnClickListener(view -> {
            LayoutInflater inflaterAddPage = getLayoutInflater();
            View dialogAddPage = inflaterAddPage.inflate(R.layout.dialog_add_page, null);
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(ChoosePageActivity.this);
            mBuilder.setView(dialogAddPage);
            mEditDialogText = dialogAddPage.findViewById(R.id.et_ad_page_title);
            mBuilder.setPositiveButton("Ok", (dialogInterface, i) -> {
                hideKeyboard(mEditDialogText);
                String pageTitle = mEditDialogText.getText().toString();
                if (pageTitle!= null && !pageTitle.isEmpty()) {
                    Page mPage = new Page(pageTitle);
                    mPage.setChildId(mChildId);
                    long id = mLocalDb.insertPage(mPage, null);
                    String pageId = Long.toString(id);
                    mPage.setId(pageId);
                    Toast.makeText(getApplicationContext(), "Page ajoutée à la base",
                            Toast.LENGTH_SHORT).show();
                    Intent intentName = new Intent(getBaseContext(), ChoosePictureActivity.class);
                    intentName.putExtra(EXTRA_PAGE_ID, pageId);
                    startActivity(intentName);
                    addPageInRecyclerView();
                } else {
                    Toast.makeText(getApplicationContext(), "Erreur d'ajout page", Toast.LENGTH_SHORT).show();
                }
            });
            //if the user want to cancel the process
            mBuilder.setNegativeButton("Annuler", (dialogInterface, i) -> {
                Toast.makeText(getApplicationContext(), "Ajout Annulé", Toast.LENGTH_SHORT).show();
                hideKeyboard(mEditDialogText);
            });
            mBuilder.show();
            openKeyboard();
        });
    }


    private void loadPages(){
        mLocalDb.requestPageByChild(mChildId,new RequestCallback() {
            @Override
            public void onResult(List<? extends Model> entities) {
                mListPages.clear();
                for (int i = 0; i < entities.size(); i++) {
                    mListPages.add((Page) entities.get(i));
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        });
        mPagesAdapter.notifyDataSetChanged();
        mListPagesView.setAdapter(mPagesAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        overridePendingTransition(R.anim.anim_slide_none, R.anim.anim_slide_down);
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.anim_slide_none, R.anim.anim_slide_down);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPages();
    }


}

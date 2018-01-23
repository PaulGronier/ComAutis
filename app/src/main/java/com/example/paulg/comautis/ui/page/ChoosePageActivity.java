package com.example.paulg.comautis.ui.page;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.Database.LocalDataBase;
import com.example.paulg.comautis.mvp.Database.RequestCallback;
import com.example.paulg.comautis.mvp.Database.SQLDataBase;
import com.example.paulg.comautis.mvp.Model.Child;
import com.example.paulg.comautis.mvp.Model.Model;
import com.example.paulg.comautis.mvp.page.Page;
import com.example.paulg.comautis.mvp.page.PagesAdapter;
import com.example.paulg.comautis.ui.child.ChooseChildActivity;

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

    @BindView(R.id.add_page_button)
    FloatingActionButton mFloatingActionButton;

    EditText mEditDialogText;

    List<Child> mListChild;
    private List<Page> mListPages;
    private PagesAdapter mPagesAdapter;
    public SQLDataBase myDB;
    public LocalDataBase mLocalDb;
    public String childId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pages);
        mListChild = new ArrayList<Child>();
        initDatabase();
        ButterKnife.bind(this);
        init();
        addPageInRecyclerView();
        addPage();

        getSupportActionBar().setTitle("Choisissez une page : ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick() {
        Toast.makeText(this, "ItemClick", Toast.LENGTH_SHORT).show();
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

        AlertDialog alertDialogDeleteChild = mDeleteBuilder.show();
    }

    private void init() {
        mListPages = new ArrayList<>();
        mPagesAdapter = new PagesAdapter(this, mListPages, this);
        mListPagesView.setAdapter(mPagesAdapter);
        mListPagesView.setLayoutManager(new LinearLayoutManager(this));
        mListPagesView.setVisibility(View.VISIBLE);

    }

    private void initDatabase() {
        SQLiteDatabase mComAutisDB = openOrCreateDatabase("ComAutisDB",MODE_PRIVATE,null);
        myDB = new SQLDataBase(getApplicationContext());
        myDB.onUpgrade(mComAutisDB, mComAutisDB.getVersion(),myDB.getVERSION());
        myDB.onCreate(mComAutisDB);
        mLocalDb = new LocalDataBase(mComAutisDB,null);
    }

    private void addPageInRecyclerView(){
        mLocalDb.requestPage(new RequestCallback() {
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

    private void addPage() {
        mFloatingActionButton.setOnClickListener(view -> {
            LayoutInflater inflaterAddChildren = getLayoutInflater();
            View dialogAddPage = inflaterAddChildren.inflate(R.layout.dialog_add_page, null);
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(ChoosePageActivity.this);
            mBuilder.setView(dialogAddPage);
            mEditDialogText = dialogAddPage.findViewById(R.id.et_ad_page_title);
            mBuilder.setPositiveButton("Ok", (dialogInterface, i) -> {

                String pageTitle = mEditDialogText.getText().toString();
                if (pageTitle != null && !pageTitle.isEmpty()){
                    Page mPage = new Page(pageTitle);
                    mPage.setChildId(childId);
                    long id = mLocalDb.insertPage(mPage, null);
                    mPage.setId(Long.toString(id));
                    Toast.makeText(getApplicationContext(), "Page ajoutée à la base",
                            Toast.LENGTH_SHORT).show();
                    addPageInRecyclerView();

                } else {
                    Toast.makeText(getApplicationContext(), "Erreur d'ajout page", Toast.LENGTH_SHORT).show();
                }
            });
            //if the user want to cancel the process
            mBuilder.setNegativeButton("Annuler", (dialogInterface, i) -> Toast.makeText(getApplicationContext(), "Ajout Annulé", Toast.LENGTH_SHORT).show());
            mBuilder.show();

        });
    }

    private void loadPages(){
        mLocalDb.requestPageByChild(childId,new RequestCallback() {
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

        //init list view with list pages items
        PagesAdapter listPageAdapter = new PagesAdapter(this, mListPages, null);
        mListPagesView.setAdapter(listPageAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intentName = new Intent(getBaseContext(), ChooseChildActivity.class);
        startActivity(intentName);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intentName = new Intent(getBaseContext(), ChooseChildActivity.class);
        startActivity(intentName);
    }
}

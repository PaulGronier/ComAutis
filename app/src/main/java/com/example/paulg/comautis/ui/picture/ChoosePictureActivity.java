package com.example.paulg.comautis.ui.picture;

/**
 * Created by paulg on 24/01/2018.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.Model.Picture;
import com.example.paulg.comautis.mvp.Model.PictureUtils;
import com.example.paulg.comautis.ui.BaseActivity;
import com.example.paulg.comautis.ui.page.ChoosePageActivity;
import com.example.paulg.comautis.ui.page.PageActivity;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;

public class ChoosePictureActivity extends BaseActivity {

    @BindView(R.id.search_bar) AutoCompleteTextView mSearchBarView;
    @BindView(R.id.delete_text) ImageView mDeleteTextButtonView;
    @BindView(R.id.search_text) ImageView mSearchButtonView;

    private SearchView mSearchView;

    private List<Picture> mlistPictures = new ArrayList<>();
    private String pageId;
    private GridView mGridPictures;
    private GridPicturesAdapter mGridPicturesAdapter;
    private GridPicturesAdapter mGridSearchedPicturesAdapter;
    private List<Picture> mSelectedBitmap = new ArrayList<>();
    private List<Boolean> mIsSeleted = new ArrayList<>();
    private List<Picture> mSearchedPictures = new ArrayList<>();
    private List<Boolean> mIsSearchedSeleted = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_picture);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Images de ");
        mSearchView = (SearchView) findViewById(R.id.search);

        if (getIntent()!=null){
            pageId = getIntent().getStringExtra(ChoosePageActivity.EXTRA_PAGE_ID);
        }

        mGridPictures = (GridView)findViewById(R.id.grid_choose_pictures);

        File files[] = PictureUtils.getPictures();
        int size = files.length;
        for(int i = 0 ; i < size ; i++){
            Bitmap bitmap = BitmapFactory.decodeFile(files[i].getAbsolutePath());
            bitmap = PictureUtils.getResizedBitmap(bitmap, 180, 180);
            Picture picture = new Picture();
            picture.setName(files[i].getName());
            picture.setBitmap(bitmap);
            picture.setmPicturePath(files[i].getAbsolutePath());
            mlistPictures.add(picture);
            mIsSeleted.add(i, false);
        }

        loadGridPictures();

        //long click
        mGridPictures.setOnItemClickListener((AdapterView.OnItemClickListener) (parent, view, position, id) -> {
            if (!mIsSeleted.get(position)) {
                mSelectedBitmap.add(mlistPictures.get(position));
                mIsSeleted.set(position, true);
                Picture picturePage = new Picture();
                picturePage.setName(mlistPictures.get(position).getName());
            } else {
                for (int i = 0; i < mSelectedBitmap.size(); i++) {
                    if (mlistPictures.get(position).getName().equals(mSelectedBitmap.get(i).getName())) {
                        mSelectedBitmap.remove(i);
                    }
                }
                mIsSeleted.set(position, false);
            }
            loadGridPictures();
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                mGridPicturesAdapter.getFilter().filter(query);
                mGridPicturesAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void loadGridPictures(){
        mGridPicturesAdapter = new GridPicturesAdapter(mlistPictures, mIsSeleted, getBaseContext());
        mGridPictures.setAdapter(mGridPicturesAdapter);
    }

    @OnClick(R.id.search_text)
    public void onSearchTextClick() {
        onActionDoneSearchStore(EditorInfo.IME_ACTION_DONE);
    }

    @OnClick(R.id.delete_text)
    public void onDeleteTextClick() {
        mSearchBarView.setText("");
        mGridPictures.setAdapter(mGridPicturesAdapter);
        Toast.makeText(getApplicationContext(), "Champs vidé", Toast.LENGTH_SHORT).show();

    }

    @OnTextChanged(R.id.search_bar)
    public void onSearchTextChanged() {
        if (isNullOrEmpty(mSearchBarView.getText().toString())) mDeleteTextButtonView.setVisibility(View.GONE);
        else mDeleteTextButtonView.setVisibility(View.VISIBLE);

        mGridPicturesAdapter.getFilter().filter(mSearchBarView.getText().toString());
        mGridPicturesAdapter.notifyDataSetChanged();
    }

    @OnEditorAction(R.id.search_bar)
    protected boolean onActionDoneSearchStore(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            hideKeyboard(mSearchBarView);
            /*mSearchedPictures.clear();
            for (Picture picture:mlistPictures) {
                if (picture.getName().contains(mSearchBarView.getText().toString())){
                    mSearchedPictures.add(picture);
                }
            }
            mGridSearchedPicturesAdapter = new GridPicturesAdapter(mSearchedPictures, mIsSeleted, getBaseContext());
            mGridPictures.setAdapter(mGridSearchedPicturesAdapter);*/
            mGridPicturesAdapter.getFilter().filter(mSearchBarView.getText());
            mGridPicturesAdapter.notifyDataSetChanged();

            return true;
        } else return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.for_choose_picture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_validate_picture){
            if(mSelectedBitmap.size() != 0) {
                for (int j = 0; j < mSelectedBitmap.size(); j++) {
                    mSelectedBitmap.get(j).setPageId(pageId);
                    mSelectedBitmap.get(j).setOrder(Integer.toString(j));
                    mLocalDb.insertPicture(mSelectedBitmap.get(j), null);
                }
                Intent intentPage = new Intent(getBaseContext(), PageActivity.class);
                intentPage.putExtra(ChoosePageActivity.EXTRA_PAGE_ID, pageId);
                startActivity(intentPage);
                finish();
            }
        }else if(item.getItemId() == R.id.home){
            onBackPressed();
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mLocalDb.deletePageById(pageId,null);
        finish();
    }
}

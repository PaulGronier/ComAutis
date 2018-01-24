package com.example.paulg.comautis.ui.child;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.example.paulg.comautis.mvp.Model.Page;
import com.example.paulg.comautis.ui.BaseActivity;
import com.example.paulg.comautis.ui.page.ChoosePageActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iem on 19/01/2018.
 */

public class ChooseChildActivity extends BaseActivity implements AdapterListChild.onClickListenner{

    public static final String EXTRA_CHILD_ID = "child_id";
    public static final String EXTRA_CHILD_NAME = "child_name";


    @BindView(R.id.rv_child) RecyclerView mChildRecyclerView;
    private List<Child> mListChild;
    AdapterListChild mAdapterListChild;
    EditText editNameChild;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_child_add);
        floatingActionButton = findViewById(R.id.add_child);

        ButterKnife.bind(this);

        init();
        addChildInListView();
        addChildren();
    }

    @Override
    public void onRemoveClickItem(String childID) {
        LayoutInflater inflaterDeleteChild = getLayoutInflater();
        View dialogDeletelayout = inflaterDeleteChild.inflate(R.layout.dialog_delete_child, null);
        AlertDialog.Builder mDeleteBuilder = new AlertDialog.Builder(ChooseChildActivity.this);
        mDeleteBuilder.setView(dialogDeletelayout);
        mDeleteBuilder.setPositiveButton(R.string.btn_ad_positive, (dialog, which) -> {
            String idChildToDelete = childID;
                mLocalDb.deleteChildById(idChildToDelete, null);
            Toast.makeText(getApplicationContext(), "Enfant supprimée",
                    Toast.LENGTH_SHORT).show();
            addChildInListView();
        });
        mDeleteBuilder.setNegativeButton(R.string.btn_ad_negative,
                (dialog, which) -> Toast.makeText(getApplicationContext(), "Annulé",
                        Toast.LENGTH_SHORT).show());
        mDeleteBuilder.show();
    }
    @Override
    public void onItemClick(String childID, String name) {
        Intent intentName = new Intent(getBaseContext(), ChoosePageActivity.class);
        intentName.putExtra(EXTRA_CHILD_ID, childID);
        intentName.putExtra(EXTRA_CHILD_NAME, name);
        startActivity(intentName);
    }

    private void addChildren() {
        floatingActionButton.setOnClickListener(view -> {
            LayoutInflater inflaterAddChildren = getLayoutInflater();
            View dialogAddChild = inflaterAddChildren.inflate(R.layout.dialog_add_child, null);
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(ChooseChildActivity.this);
            mBuilder.setView(dialogAddChild);
            editNameChild = dialogAddChild.findViewById(R.id.et_add_child_name);
            mBuilder.setPositiveButton("Ok", (dialogInterface, i) -> {
                String nameChild = editNameChild.getText().toString();
                if (nameChild != null && !nameChild.isEmpty()){
                    Child myChild = new Child();
                    myChild.setName(nameChild);
                    mLocalDb.insertChild(myChild, null);
                    Toast.makeText(getApplicationContext(), "Enfant ajouté à la base",
                            Toast.LENGTH_SHORT).show();
                    addChildInListView();
                } else {
                    Toast.makeText(getApplicationContext(), "Erreur", Toast.LENGTH_SHORT).show();
                }
            });
            //if the user want to cancel the process
            mBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getApplicationContext(), "Ajout Annulé", Toast.LENGTH_SHORT).show();
                }
            });
            mBuilder.show();
        });
    }

    private void init() {
        mListChild = new ArrayList<Child>();
        mAdapterListChild = new AdapterListChild(this, mListChild, this);
        mChildRecyclerView.setAdapter(mAdapterListChild);
        mChildRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mChildRecyclerView.setVisibility(View.VISIBLE);
    }


    private void addChildInListView(){
        mLocalDb.requestChild(new RequestCallback() {
            @Override
            public void onResult(List<? extends Model> entities) {
                mListChild.clear();
                for (int i = 0; i < entities.size(); i++) {
                    mListChild.add((Child) entities.get(i));
                }
            }

            @Override
            public void onError(Throwable error) {

            }
        });

        mAdapterListChild.notifyDataSetChanged();
        mChildRecyclerView.setAdapter(mAdapterListChild);
    }

}

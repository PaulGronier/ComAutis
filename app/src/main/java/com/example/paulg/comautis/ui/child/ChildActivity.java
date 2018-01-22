package com.example.paulg.comautis.ui.child;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.Database.LocalDataBase;
import com.example.paulg.comautis.mvp.Database.RequestCallback;
import com.example.paulg.comautis.mvp.Database.SQLDataBase;
import com.example.paulg.comautis.mvp.Model.Child;
import com.example.paulg.comautis.mvp.Model.Model;
import com.example.paulg.comautis.mvp.page.Page;
import com.example.paulg.comautis.ui.page.ChoosePageActivity;

import java.util.ArrayList;
import java.util.List;

public class ChildActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private EditText etNameChild;
    private ListView mChildListView;
    public static final String EXTRA_CHILD_ID = "child_id";
    private List<Child> mListChild = new ArrayList<Child>();
    public SQLDataBase myDB;
    public LocalDataBase mLocalDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_child_add);
        Database();
        initCompoment();
        addChildInListView();
        addChildren();
        clickOnAChild();
        deleteChild();
        getSupportActionBar().setTitle("Liste des enfants");
    }

    private void Database() {
        SQLiteDatabase mComAutisDB = openOrCreateDatabase("ComAutisDB",MODE_PRIVATE,null);
        myDB = new SQLDataBase(getApplicationContext());
        myDB.onUpgrade(mComAutisDB, mComAutisDB.getVersion(),myDB.getVERSION());
        myDB.onCreate(mComAutisDB);
        mLocalDb = new LocalDataBase(mComAutisDB,null);
    }


    //TODO FloatingButton for add Child
     private void addChildren() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflaterAddChildren = getLayoutInflater();
                View dialogAddChild = inflaterAddChildren.inflate(R.layout.dialog_add_child, null);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ChildActivity.this);
                mBuilder.setView(dialogAddChild);
                etNameChild = dialogAddChild.findViewById(R.id.et_ad_child_name);
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String nameChild = etNameChild.getText().toString();
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
            }
        });
    }

    private void clickOnAChild(){
        mChildListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), ChoosePageActivity.class);
                intent.putExtra(EXTRA_CHILD_ID, mListChild.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void deleteChild(){
        mChildListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                LayoutInflater inflaterDeleteChild = getLayoutInflater();
                View dialogDeleteLayout = inflaterDeleteChild.inflate(R.layout.dialog_delete_child, null);
                AlertDialog.Builder mDeleteBuilder = new AlertDialog.Builder(ChildActivity.this);
                mDeleteBuilder.setView(dialogDeleteLayout);
                mDeleteBuilder.setPositiveButton(R.string.btn_ad_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        String idChildTarget = mListChild.get(position).getId();
                        if (checkPageInChild(idChildTarget) != 0){
                            mLocalDb.deletePageByChild(idChildTarget, null);
                        }
                        mLocalDb.deleteChildById(idChildTarget, null);
                        Toast.makeText(getApplicationContext(), "Enfant supprimé",
                                Toast.LENGTH_SHORT).show();
                        addChildInListView();
                    }
                });
                mDeleteBuilder.setNegativeButton(R.string.btn_ad_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getApplicationContext(), "Annulé", Toast.LENGTH_SHORT).show();
                    }
                });
                mDeleteBuilder.show();
                return false;
            }
        });
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

        //init list view with list child items
        AdapterListChild listChildAdapter = new AdapterListChild(mListChild, getBaseContext());
        mChildListView.setAdapter(listChildAdapter);
    }

    private int checkPageInChild(String idChildTarget) {
        List<Page> listPageInChild = new ArrayList<>();
        mLocalDb.requestPageByChild(idChildTarget, new RequestCallback() {
            @Override
            public void onResult(List<? extends Model> entities) {
                List listPageInChild = (List<Page>) entities;
            }

            @Override
            public void onError(Throwable error) {

            }
        });
        return listPageInChild.size();
    }

    private void initCompoment(){
        floatingActionButton = findViewById(R.id.add_child);
        mChildListView = findViewById(R.id.lv_child);
        //init list view with list child items
        AdapterListChild listChildAdapter = new AdapterListChild(mListChild, getBaseContext());
        mChildListView.setAdapter(listChildAdapter);
    }

    @Override
    protected void onPause() {

        super.onPause();
        finish();
    }
}
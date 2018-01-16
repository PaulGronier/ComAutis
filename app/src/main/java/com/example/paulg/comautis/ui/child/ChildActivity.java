package com.example.paulg.comautis.ui.child;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.paulg.comautis.mvp.model.Child;
import com.example.paulg.comautis.ui.Page.PageActivity;

import java.util.ArrayList;
import java.util.List;

public class ChildActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private EditText etNameChild;
    private ListView mChildListView;
    public static final String EXTRA_CHILD_ID = "child_id";
    private List<Child> mListChild = new ArrayList<Child>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_child_add);

        initCompoment();
        addChildren();
        clickOnAChild();
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
                            /*
                                Child child = new Child();
                                child.setName(nameChild);
                                Insert le nom dans la base de donnée
                             */
                            Toast.makeText(getApplicationContext(),"Enfant ajouté dans la base", Toast.LENGTH_SHORT).show();
                            // Méthode qui add l'enfant
                                addChildInListView();

                        } else {
                            Toast.makeText(getApplicationContext(), "Erreur", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                //if the user want to cancel the processu
                mBuilder.setNegativeButton("Annuler", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Ajout Annulé", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = mBuilder.show();
            }
        });
    }

    private void clickOnAChild(){
        mChildListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), PageActivity.class);
                intent.putExtra(EXTRA_CHILD_ID, mListChild.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void addChildInListView(){}

    private void initCompoment(){
        floatingActionButton = findViewById(R.id.add_child);
        mChildListView = findViewById(R.id.lv_child);
    }

}

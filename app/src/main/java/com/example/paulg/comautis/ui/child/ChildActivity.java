package com.example.paulg.comautis.ui.child;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.paulg.comautis.R;

public class ChildActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButton;
    private EditText etNameChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_child_add);

        floatingActionButton = findViewById(R.id.add_child);
    }
     private void addChildren() {

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflaterAddChildren = getLayoutInflater();
                //View dialogAddChild = inflaterAddChildren.inflate(R.layout.dialog_add_child, root);
            }
        });
    }

}

package com.example.paulg.comautis.ui.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.paulg.comautis.R;
import com.example.paulg.comautis.mvp.presenter.home.HomePresenter;
import com.example.paulg.comautis.mvp.view.HomeView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements HomeView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void connected() {

    }
}

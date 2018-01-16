package com.example.paulg.comautis.mvp.presenter;

import com.example.paulg.comautis.mvp.view.View;

/**
 * Created by paulg on 15/01/2018.
 */

public interface Presenter<V extends View> {
    void onAttachView(View view);
    void onDettachView(View view);
}

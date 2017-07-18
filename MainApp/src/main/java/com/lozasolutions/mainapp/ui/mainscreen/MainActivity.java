package com.lozasolutions.mainapp.ui.mainscreen;

import android.os.Bundle;

import com.lozasolutions.mainapp.R;
import com.lozasolutions.mainapp.base.BaseActivity;
import com.lozasolutions.mainapp.utils.DialogFactory;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {


    @Inject
    MainPresenter mMainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainPresenter.attachView(this);

        if(savedInstanceState == null){
            mMainPresenter.check();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    /***** MVP View methods implementation *****/

    @Override
    public void showResultConversion(Float s) {

    }

    @Override
    public void showQuote(String quote) {

    }

    @Override
    public void showErrorGettingQuote() {

        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_quote))
                .show();
    }

    @Override
    public void showErrorGettingConversion() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_bank_info))
                .show();
    }
}
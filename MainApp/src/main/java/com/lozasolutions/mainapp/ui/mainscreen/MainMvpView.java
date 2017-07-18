package com.lozasolutions.mainapp.ui.mainscreen;

import com.lozasolutions.mainapp.base.MvpView;

public interface MainMvpView extends MvpView {

    void showResultConversion(Float s);

    void showQuote(String quote);

    void showErrorGettingQuote();

    void showErrorGettingConversion();

}
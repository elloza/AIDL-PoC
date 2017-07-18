package com.lozasolutions.mainapp.ui.mainscreen;

import android.util.Pair;

import com.google.gson.Gson;
import com.lozasolutions.bankapp.BankResult;
import com.lozasolutions.mainapp.base.BasePresenter;
import com.lozasolutions.mainapp.data.model.BankResultResponse;
import com.lozasolutions.mainapp.data.remote.BankRepository;
import com.lozasolutions.mainapp.data.remote.NamesRepository;
import com.lozasolutions.mainapp.injection.scopes.ConfigPersistent;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final BankRepository bankRepository;
    private final NamesRepository namesRepository;
    private Gson gson;

    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    //TODO change this to interactors
    @Inject
    public MainPresenter(BankRepository bankRepository, NamesRepository namesRepository, Gson gson) {
        this.bankRepository = bankRepository;
        this.namesRepository = namesRepository;
        this.gson = gson;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscriptions != null) mSubscriptions.clear();
    }


    public Observable<Pair<BankResult,String>> getInfo(){

        return namesRepository.getQuote(10).flatMap(new Func1<String, Observable<Pair<BankResult,String>>>() {
            @Override
            public Observable<Pair<BankResult,String>> call(final String s) {
                return bankRepository.getBankLatestRates("USD","EUR").flatMap(new Func1<BankResult, Observable<Pair<BankResult,String>>>() {
                    @Override
                    public Observable<Pair<BankResult,String>> call(final BankResult bankResult) {
                        Pair<BankResult,String> pair = new Pair(bankResult,s);
                        return Observable.just(pair);
                    }
                });
            }
        });

    }

    public void check() {

        mSubscriptions.add(getInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Pair<BankResult, String>>() {
            @Override
            public void call(Pair<BankResult, String> bankResultStringPair) {

                if(isViewAttached()){

                    BankResultResponse bankRes = gson.fromJson(bankResultStringPair.first.getJsonResponse(), BankResultResponse.class);

                    Float result = bankRes.getRates().get("USD") * 10;

                    getMvpView().showResultConversion(result);
                    getMvpView().showQuote(bankResultStringPair.second);
                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
                if(isViewAttached()){
                    getMvpView().showErrorGettingConversion();
                }
            }
        }));
        //RxUtil.unsubscribe(mSubscription);
    }

}
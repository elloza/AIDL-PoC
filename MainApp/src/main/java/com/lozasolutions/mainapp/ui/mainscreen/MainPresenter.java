package com.lozasolutions.mainapp.ui.mainscreen;

import android.util.Pair;

import com.google.gson.Gson;
import com.lozasolutions.bankapp.BankResult;
import com.lozasolutions.mainapp.base.BasePresenter;
import com.lozasolutions.mainapp.data.model.BankResultResponse;
import com.lozasolutions.mainapp.data.remote.BankRepository;
import com.lozasolutions.mainapp.data.remote.PrintRepository;
import com.lozasolutions.mainapp.injection.scopes.ConfigPersistent;
import com.lozasolutions.printerapp.BillInfo;
import com.lozasolutions.printerapp.PrintResult;

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
    private final PrintRepository printRepository;
    private Gson gson;

    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    //TODO change this to interactors
    @Inject
    public MainPresenter(BankRepository bankRepository, PrintRepository namesRepository, Gson gson) {
        this.bankRepository = bankRepository;
        this.printRepository = namesRepository;
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


    public Observable<Pair<BankResult,PrintResult>> getInfo(){

        return bankRepository.getBankLatestRates("USD","EUR").flatMap(new Func1<BankResult, Observable<Pair<BankResult,PrintResult>>>() {
            @Override
            public Observable<Pair<BankResult,PrintResult>> call(final BankResult bankResult) {

                return printRepository.print(new BillInfo("print")).flatMap(new Func1<PrintResult, Observable<Pair<BankResult,PrintResult>>>() {
                    @Override
                    public Observable<Pair<BankResult,PrintResult>> call(PrintResult printResult) {

                        Pair<BankResult,PrintResult> pair = new Pair(bankResult,printResult);
                        return Observable.just(pair);
                    }
                });
            }
        });

    }

    public void check() {

        mSubscriptions.add(getInfo().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Pair<BankResult, PrintResult>>() {
            @Override
            public void call(Pair<BankResult, PrintResult> bankResultStringPair) {

                if(isViewAttached()){

                    BankResultResponse bankRes = gson.fromJson(bankResultStringPair.first.getJsonResponse(), BankResultResponse.class);

                    Float result = bankRes.getRates().get("USD") * 10;

                    getMvpView().showResultConversion(result);
                    getMvpView().showQuote(bankResultStringPair.second.getQuote());
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
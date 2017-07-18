package com.lozasolutions.mainapp.data.remote;

import rx.Observable;

/**
 * Created by Loza on 17/07/2017.
 */

public interface NamesRepository {

    Observable<String> getQuote(Integer number);
}

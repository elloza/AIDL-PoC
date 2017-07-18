package com.lozasolutions.namesapp;
import com.lozasolutions.namesapp.NameQuote;

interface INamesService {
    NameQuote getQuote(int number);
    void exit();
}

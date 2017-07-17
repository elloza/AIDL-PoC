package com.lozasolutions.namesapp;
import com.lozasolutions.namesapp.NameQuote;

interface INamesService {
    NameQuote[] listFiles(String path);
    void exit();
}

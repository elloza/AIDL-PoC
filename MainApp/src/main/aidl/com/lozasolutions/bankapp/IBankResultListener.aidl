// IBankResultListener.aidl
package com.lozasolutions.bankapp;
import com.lozasolutions.bankapp.BankResult;


// Declare any non-default types here with import statements

// Declare the interface.
interface IBankResultListener {
	void sendResult(in BankResult result);
}
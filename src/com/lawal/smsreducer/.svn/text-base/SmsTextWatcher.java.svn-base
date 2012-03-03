package com.lawal.smsreducer;

import android.text.Editable;
import android.text.TextWatcher;

public class SmsTextWatcher implements TextWatcher
{

	private SMSMain mApp;

	public SmsTextWatcher(SMSMain app){
		mApp=app;
	}

	@Override
	public void afterTextChanged(Editable s) {
		mApp.updateStats();
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}
};
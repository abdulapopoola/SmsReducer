package com.lawal.smsreducer;

import com.lawal.data.AbbrType;
import com.lawal.data.SQLDbAdapter;
import com.lawal.logic.StringHelper;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LinkCreationActivity extends Activity implements OnClickListener {

	private EditText mPhraseText;
	private EditText mAbbrText;
	private static String tag = "linkcreation";

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.addlinklayout);
		mPhraseText = (EditText) findViewById(R.id.phrase);
		mAbbrText = (EditText) findViewById(R.id.abbr);

		Button saveBtn = (Button) findViewById(R.id.savebtn);
		Button cancelbtn = (Button) findViewById(R.id.cancelBtn);

		saveBtn.setOnClickListener(this);
		cancelbtn.setOnClickListener(this);

		Log.d(tag, "create");

	}
	
	private void saveLink(String phrase, String abbr) {
				
		SQLDbAdapter db = new SQLDbAdapter(this);
		db.insertWord(phrase, abbr, AbbrType.USER_DEFINED);		
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.savebtn) {
			String phrase = mPhraseText.getText().toString();
			String abbr = mAbbrText.getText().toString();
			
			if(StringHelper.isEmpty(phrase, abbr))
			{
				Toast.makeText(getBaseContext(), "Please enter the text and its abbreviation", Toast.LENGTH_SHORT).show();
			}
			else
			{
				saveLink(phrase, abbr);
				finish();
			}
		} else if (view.getId() == R.id.cancelBtn) {
			finish();
		}

	}
}

package com.lawal.smsreducer;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

public class Recipient{
	
	public String mName;
	public  List<RecipientNumber> mNumbers;
	
	private Recipient(){}
	
	
	public static class RecipientNumber{

		public RecipientNumber(String type, String number) {
			setType(type);
			setNumber(number);
		}
		private String mType;

		public String getType() {
			return mType;
		}

		public void setType(String value) {
			mType = value;
		}

		private String mNumber;

		public String getNumber() {
			return mNumber;
		}

		public void setNumber(String value) {
			mNumber = value;
		}
		
	}
	
	public static String typeToReadableName(String type) {
		int t = Integer.parseInt(type);
		switch (t) {
		case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
			return "Work";
		case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
			return "Home";
		case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
			return "Mobile";

		default:
			// XXX
			return type;
		}

	}

	public static Recipient createRecipient(Activity activity, Uri contactUri) {
		ArrayList<RecipientNumber> phones = new ArrayList<RecipientNumber>();

		String id = String.valueOf(ContentUris.parseId(contactUri));
		Cursor cur = activity.managedQuery(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID
				+ " = ?", new String[] { id }, null);
		while (cur.moveToNext()) {
			String numberType = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
			String number = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			phones.add(new RecipientNumber(numberType, number));
		}
//		String  name = cur.getString(cur.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
		//FIXME
		String name ="";
		Recipient r = new Recipient();
		r.mName=name;
		r.mNumbers=phones;
		
		cur.close();
		
		return r;
		

		
		
	}
	

}

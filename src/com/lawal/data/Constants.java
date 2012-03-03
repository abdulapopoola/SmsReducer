package com.lawal.data;

import android.provider.ContactsContract.Contacts;

public class Constants {
	
	public static final char SPACE = ' ';
	
	 private static final String[] PROJECTION = new String[]{
         Contacts._ID, // 0 
         Contacts.DISPLAY_NAME, //1
         Contacts.HAS_PHONE_NUMBER //2
 };


}

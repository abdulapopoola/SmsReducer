package com.lawal.logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.lawal.data.AbbrType;
import com.lawal.data.SQLDbAdapter;

public class DictParser {

	private SQLDbAdapter _db;

	public DictParser(SQLDbAdapter db, InputStream is){	
		_db =db;
		readDefaultList(is);
	}

	private void processLine(String str) {
		if(str.startsWith("#") || !str.contains("="))return;
		String[] strPair = str.split("=");
		if(strPair.length!=2)return;
		insertPair(strPair[0], strPair[1]);
	}

	protected void insertPair(String entity, String Abbr){
		if(entity!=null && Abbr!= null){
			_db.insertWord(entity.trim(), Abbr.trim(),AbbrType.EN_BASIC);
		}
	}

	private void readDefaultList(InputStream is) {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder contents = new StringBuilder();
		String line = null;
		try {
			while (( line = br.readLine()) != null){
				contents.append(line);
				processLine(contents.toString());
				contents.delete(0,contents.length());
				//contents.append(System.getProperty("line.separator"));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

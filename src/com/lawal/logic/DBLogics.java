package com.lawal.logic;

import com.lawal.data.SQLDbAdapter;

public class DBLogics {

	private SQLDbAdapter _db;

	public DBLogics(SQLDbAdapter db) {
		_db = db;

	}

	public String[] replaceWords(String[] sentences) {

		Transformer<String[], String[]> ReplacementTransformer = new Transformer<String[], String[]>() {

			@Override
			public String[] apply(String[] word) {
				String[] out = new String[word.length];
				
				for (int i = 0; i < word.length; i++) {
					out[i] = _db.getAbbrIfExist(word[i]);
				}
				return out;
			}
		};
		return ReplacementTransformer.apply(sentences);
		
		
	}

}

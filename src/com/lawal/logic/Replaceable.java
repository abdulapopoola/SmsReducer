package com.lawal.logic;

public class Replaceable {
	
	private String mToReplace;
	private String[] mReplaceAble;

	Replaceable(String toReplace, String[] replaceAble){
		setToReplace(toReplace);
		setReplaceAble(replaceAble);
	}

	public void setToReplace(String toReplace) {
		mToReplace = toReplace;
	}

	public String getToReplace() {
		return mToReplace;
	}

	public void setReplaceAble(String[] replaceAble) {
		mReplaceAble = replaceAble;
	}

	public String[] getReplaceAble() {
		return mReplaceAble;
	}

}

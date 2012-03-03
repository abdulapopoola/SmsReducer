package com.lawal.smsreducer;


public class Util {

	static long initMem;
	static long start;
	static Runtime r;

	public static String toTwoSF(double val){
		return String.format("%.2f",val);		
	}

	public static void startMeasurement() {
		r = Runtime.getRuntime();
		r.gc();
		initMem=  r.freeMemory();			
		start =System.currentTimeMillis();		
	}

	public static void getMeasurementResult() {
		System.out.println("");
		long sectaken = (System.currentTimeMillis()-start)/1000;
		if(sectaken<60){
			System.out.println("it took "+ sectaken+ " s");		
		}else{
			System.out.println("it took "+ sectaken/60+" min "+ sectaken%60+ " s");	
		}
		long memUsed = (r.freeMemory()-initMem);
		memUsed = Math.abs(memUsed);
		if(memUsed<1024){
			System.out.println("it took "+ memUsed+ " bytes");		
		}
		else if( (memUsed >= 1024) &&( memUsed <= (1024*1024))){
			System.out.println("it took "+ (double)memUsed/ (1024)+ " KB");		

		}
		else if( (memUsed >= (1024*1024) )&&( memUsed <= (1024*1024*1024))){
			System.out.println("it took "+ (double)memUsed/ (1024*1024)+ " MB");		
		}
		else {
			System.out.println("WHAO! it took "+ (double)memUsed/ (1024*1024*1024)+ " GB");			

		}

	}



}

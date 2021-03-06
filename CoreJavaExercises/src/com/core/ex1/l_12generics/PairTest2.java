/**
 * 
 */
package com.core.ex1.l_12generics;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author icastillejoss
 */
public class PairTest2 {
	
	public static void main(String[] args){
		GregorianCalendar[] birthdays ={
				new GregorianCalendar(1906, Calendar.DECEMBER, 9), //G. Hopper
				new GregorianCalendar(1815, Calendar.DECEMBER, 10),//A. Lovelace
				new GregorianCalendar(1903, Calendar.DECEMBER, 3), //J. von Neumann
				new GregorianCalendar(1910, Calendar.JUNE, 22),//K. Zuse
		};
		Pair<GregorianCalendar> mm = ArrayAlg2.minmax(birthdays);
		System.out.println("min = " + mm.getFirst().getTime());
		System.out.println("max = " + mm.getSecond().getTime());
	}
}

/**
 * @author icastillejos
 *
 */
class ArrayAlg2{
	
	/**
	 * @param array of objects of type T
	 * @return a pair with the min and max value
	 */
	public static<T extends Comparable> Pair<T> minmax(T[] a){
		if ( a == null || a.length == 0){
			return null;
		}
		T min = a[0];
		T max = a[0];
		for (int i =1; i < a.length; i++) {
			if (min.compareTo(a[i]) > 0) {
				min = a[i];
			}
			if (max.compareTo(a[i]) < 0) {
				max = a[i];
			}
		}
		return new Pair<T>(min, max);
	}
}
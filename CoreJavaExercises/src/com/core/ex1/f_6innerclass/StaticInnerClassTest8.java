/**
 * 
 */
package com.core.ex1.f_6innerclass;

/**
 * @author icastillejos
 * @version 1.0.0
 */
public class StaticInnerClassTest8 {
	public static void main(String[] args){
		double d[] = new double[20];
		for (int i = 0; i < d.length; i++){
			d[i] = 100 * Math.random();
		}
		ArrayAlg.Pair p = ArrayAlg.mimax(d);
		for (double x:d) System.out.println(x);
		System.out.println("min = " + p.getFirst());
		System.out.println("max = " + p.getSecond());
	}
}

class ArrayAlg{
	/**
	 * Pair of floating pint numbers
	 * */
	public static class Pair{
		private double first;
		private double second;
		
		public Pair(double f, double s){
			this.first = f;
			this.second = s;
		}

		public double getFirst() {
			return first;
		}

		public double getSecond() {
			return second;
		}
	}
	
	/**
	 * Calculate min and max in array
	 * */
	public static Pair mimax(double[] values){
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		
		for (double v : values){
			if ( min > v ) min = v;
			if ( max < v ) max = v;
		}		
		return new Pair(min, max);
	}
}

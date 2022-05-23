package model;

public class Operation {

	public static double sum(double a, double b) {
		return a + b;
	}
	
	public static double sub(double a, double b) {
		return a - b;
	}
	
	public static double div(double a, double b) throws Exception{
		try {
			return a / b;
		}catch (Exception e) {
			throw e;
		}
	}
	
	public static double mul(double a, double b) {
		return a * b;
	}
	
}

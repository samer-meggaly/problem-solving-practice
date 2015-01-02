package dynamic;

public class Fibonacci {
	
	int[] fibOf;
	int nthTterm;
	
	public Fibonacci(int n) {
		nthTterm = n;
		fibOf = new int [(n < 2)? 2:n];
		//The first two terms of the series is stored
		//F0 = 0
		//F1 = 1
		fibOf[0] = 0;
		fibOf[1] = 1;
		for (int i = 2; i < fibOf.length; i++) {
			fibOf[i] = -1;
		}
	}
	
	public int getValueTopDown() {
		return fibonacci(nthTterm);
	}

	public int getValueBottomUp() {
		return fibonacci();
	}

	//The core algorithm of calculation the n-th term of the series.
	//This a Dynamic Solution.
	//This is the Top-Down (Memoization) approach.
	private int fibonacci(int n) {
		if (n == 0 || n == 1) {
			return fibOf[n];
		} else {
			//if f(n-1) was calculated before, get it from the fibOf array.
			//else calculate it again.
			if (fibOf[n-1] == -1) {
				fibOf[n-1] = fibonacci(n-1);
			}
			//if f(n-2) was calculated before, get it from the fibOf array.
			//else calculate it again.
			if (fibOf[n-2] == -1) {
				fibOf[n-2] = fibonacci(n-2);
			}
			return fibOf[n-1] + fibOf[n-2];	//fn = fn-1 + fn-2;
		}
	}

	//The core algorithm of calculation the n-th term of the series.
	//This a Dynamic Solution.
	//This is the Bottom-Up approach. 
	private int fibonacci() {
		for (int i = 2; i < fibOf.length; i++) {
			fibOf[i] = fibOf[i-1] + fibOf[i-2];
		}
		return fibOf[nthTterm-1] + fibOf[nthTterm-2];
	}
	
	public static void main(String[] args) {
		Fibonacci fibCal = new Fibonacci(45);
		System.out.println(fibCal.getValueTopDown());
		System.out.println(fibCal.getValueBottomUp());
	}

}

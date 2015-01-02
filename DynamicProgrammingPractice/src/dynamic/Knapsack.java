package dynamic;

public class Knapsack {
	
	int[][] C;	//table of the optimal solutions
	int M;	//the max capacity of the knapsack
	int n;	//the number of items to choose from
	int[] p;	//array of the values of each item
	int[] w;	//array of weight of each item
	/*
	 * for every item i
	 * p[i] represents the value of i
	 * w[i] represents the weight of i	
	 */
	
	/**
	 * The constructor takes an array of Entry.
	 * Each of the entries is a pair of <value, weight> of every item to choose from
	 * @param items array of pairs of value, weight per row
	 * @param capacity the maximum weight
	 */
	public Knapsack(int[]valuesArrray, int[]weightsArray, int capacity) {
		p = valuesArrray.clone();
		w = weightsArray.clone();
		M = capacity;
		n = valuesArrray.length;
		C = new int [n+1][M+1];
	}
	
	public int getMaximumValue() {
		//achieve any max weight without taking any item results in a value of zero
		for (int i = 0; i <= M; i++)
			C[0][i] = 0;
		//achieve a zero max weight with taking any item results in a value of zero
		for (int i = 0; i <= n; i++)
			C[i][0] = 0;
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= M; j++) {
				if (w[i-1] > j) {
					C[i][j] = C[i-1][j];
				} else {
					if ((p[i-1] + C[i-1][j - w[i-1]]) > C[i-1][j]) {
						//take that element
						C[i][j] = p[i-1] + C[i-1][j - w[i-1]];
					} else {
						//no use to take it because it wont increase the value
						C[i][j] = C[i-1][j];
					}
				}
			}
		}
		
		return C[n][M];
	}
	
	public static void main(String[] args) {
		//Test case
		int[] p1 = {5, 4, 3, 2};
		int[] w1 = {6, 5, 4, 3};
		int m1 = 5;
		Knapsack knap1 = new Knapsack(p1, w1, m1);
		System.out.println(knap1.getMaximumValue());
		System.out.println();
	}

}

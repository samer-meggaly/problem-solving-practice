package leetcode.problems;

public class SingleNumber {

	public int singleNumber(int[] A) {
		
		if (A == null || A.length % 2 == 0)
			throw new IllegalArgumentException();
		
        return solveAttempt2(A);
    }
	
	private int solveAttempt2(int[] A) {
		int xoredSum = 0;
		for (int i : A) {
			xoredSum ^= i;
		}
		
		return xoredSum;
	}
	
	/* correction solution but gives time limit exception */
	@SuppressWarnings("unused")
	private int solveAttempt1(int[] A) {
		for (int i = 0; i < A.length; i++) {
			int j = 0;
			boolean unique = true;
			while (unique && j < A.length) {
				if (i != j)
					unique = A[i] != A[j];
				j++;
			}
			
			if (unique)
				return A[i];
		}
		
		throw new IllegalArgumentException();
	}
		
}

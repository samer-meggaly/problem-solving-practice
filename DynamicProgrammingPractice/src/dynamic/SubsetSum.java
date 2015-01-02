package dynamic;

import java.util.HashSet;
import java.util.Set;

public class SubsetSum {

	Integer[] setOfNumbers;
	int b;
	boolean[][] m;
	
	public SubsetSum(Set<Integer> set, int sum) {
		setOfNumbers = new Integer[set.size()];
		set.toArray(setOfNumbers);
		b = sum;
		m = new boolean[setOfNumbers.length][b + 1];
	}
	
	public boolean foundSubset() {
		
		for (int i = 0; i < (b + 1); i++) {
			m[0][i] = (setOfNumbers[0] == i);
		}
		
		for (int i = 1; i < setOfNumbers.length; i++) {
			for (int j = 0; j < (b + 1); j++) {
				if (setOfNumbers[i] > j) {
					m[i][j] = m[i - 1][j];
				} else {
					if (setOfNumbers[i] == j) {
						m[i][j] = true;
					} else {
						m[i][j] = (m[i - 1][j]) || (m[i - 1][j - setOfNumbers[i]]);
					}
				}
			}
		}
		return m[setOfNumbers.length - 1][b];
	}
	
	private static int getSumOfSet(Set<Integer> theSet) {
		Object[] array = theSet.toArray();
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += (Integer)array[i];
		}
		return sum;
	}
	
	public static void main(String[] args) {
		int[] list = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < list.length; i++) {
			set.add(list[i]);
		}
		PowerSet<Integer> allSubsets = new PowerSet<Integer>(set);
		System.out.println("Generating all the subsets and their sum:");
		System.out.println("=========================================");
		
		SubsetSum s;
		int sumOfSubset;
		boolean gotIt;

		for (Set<Integer> subSet : PowerSet.powerSet(allSubsets.originalSet)) {
			sumOfSubset = SubsetSum.getSumOfSet(subSet);
			System.out.println("The original set:  " + allSubsets.originalSet);
			System.out.println("Subset:\t" + subSet);
			System.out.println("The sum: = " + sumOfSubset);
			if (subSet.size() == 0)
				continue;
			s = new SubsetSum(allSubsets.originalSet, sumOfSubset);
			gotIt = s.foundSubset();
			System.out.println("Search result: " + gotIt);
			System.out.println("---------------------------------");
		}
	}
	
}

package leetcode.problems.tree;

import java.util.HashMap;
import java.util.Map;

public class UniqueBinarySearchTreesDP {

	public static void main(String[] args) {
		System.out.println(new UniqueBinarySearchTreesDP().numTrees(3));
	}

	private Map<Integer, Integer> nUniqueTrees = new HashMap<Integer, Integer>();

	public int numTrees(int n) {
		if (n <= 2)
			return n;
		
		int sumCounts = 0;
		for (int i = 1; i <= n; i++) {
			int leftCount, rightCount;
			if (nUniqueTrees.containsKey(i - 1)) {
				leftCount = nUniqueTrees.get(i - 1);
			} else {
				leftCount = numTrees(i - 1);
				nUniqueTrees.put(i - 1, leftCount);
			}
			
			if (nUniqueTrees.containsKey(n - i)) {
				rightCount = nUniqueTrees.get(n - i);
			} else {
				rightCount = numTrees(n - i);
				nUniqueTrees.put(n - i, rightCount);
			}
			
			if (i - 1 == 0)
				sumCounts += rightCount;
			else if (n - i == 0)
				sumCounts += leftCount;
			else
				sumCounts += leftCount * rightCount;
		}
		
		return sumCounts;
	}

}

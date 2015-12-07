package leetcode.problems.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueBinarySearchTreesDP {

	/*
	 * https://leetcode.com/problems/unique-binary-search-trees/
	 * https://leetcode.com/problems/unique-binary-search-trees-ii/
	 */

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

	public List<TreeNode> generateTrees(int n) {
		int[] nums = new int[n];
		for (int i = 0; i < n; i++)
			nums[i] = i + 1;
		return getTreesForRange(nums, 0, n - 1);
	}

	private List<TreeNode> getTreesForRange(int[] nums, int from, int to) {
		List<TreeNode> trees = new ArrayList<TreeNode>();
		if (from > to)
			return trees;
		for (int r = from; r <= to; r++) {
			List<TreeNode> leftSubtrees = getTreesForRange(nums, from, r - 1);
			List<TreeNode> rightSubtrees = getTreesForRange(nums, r + 1, to);
			if (!leftSubtrees.isEmpty() && !rightSubtrees.isEmpty()) {
				for (TreeNode left : leftSubtrees) {
					for (TreeNode right : rightSubtrees) {
						TreeNode temp = new TreeNode(nums[r]);
						temp.right = right;
						temp.left = left;
						trees.add(temp);
					}
				}
			} else if (!rightSubtrees.isEmpty()) {
				for (TreeNode right : rightSubtrees) {
					TreeNode temp = new TreeNode(nums[r]);
					temp.right = right;
					trees.add(temp);
				}
			} else if (!leftSubtrees.isEmpty()) {
				for (TreeNode left : leftSubtrees) {
					TreeNode temp = new TreeNode(nums[r]);
					temp.left = left;
					trees.add(temp);
				}
			} else {
				trees.add(new TreeNode(nums[r]));
			}
		}
		return trees;
	}

	public static void main(String[] args) {
		List<TreeNode> trees;
		trees = new UniqueBinarySearchTreesDP().generateTrees(1);
		for (TreeNode t : trees)
			System.out.println(t.toString());
		System.out.println("==============================================");
		trees = new UniqueBinarySearchTreesDP().generateTrees(2);
		for (TreeNode t : trees)
			System.out.println(t.toString());
		System.out.println("==============================================");
		trees = new UniqueBinarySearchTreesDP().generateTrees(3);
		for (TreeNode t : trees)
			System.out.println(t.toString());
		System.out.println("==============================================");
		trees = new UniqueBinarySearchTreesDP().generateTrees(4);
		for (TreeNode t : trees)
			System.out.println(t.toString());
		System.out.println("==============================================");

	}

}

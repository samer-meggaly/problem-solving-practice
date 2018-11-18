package leetcode.problems.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import leetcode.utils.LeetPrinter;

public class BTLevelTraversal {

	private List<List<Integer>> levels;

	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		// Recursive solution that visits every node only 1 time. This solution
		// is not my OWN thought.
		levels = new ArrayList<List<Integer>>();
		levelGuidedDFS(root, 0);
		List<List<Integer>> reversedLevels = new ArrayList<List<Integer>>(
				levels.size());
		for (int i = levels.size() - 1; i > -1; i--)
			reversedLevels.add(levels.get(i));
		return reversedLevels;
	}

	private void levelGuidedDFS(TreeNode root, int level) {
		if (root == null)
			return;
		if (level >= levels.size()) {
			levels.add(new ArrayList<Integer>());
		}

		levels.get(level).add(root.val);
		levelGuidedDFS(root.left, level + 1);
		levelGuidedDFS(root.right, level + 1);
	}

	public List<List<Integer>> levelOrderBottomRecursive(TreeNode root) {
		// passes on LeetCode, but slower than iterative solution.
		levels = new ArrayList<List<Integer>>();
		if (root != null)
			reverseBFS(Arrays.asList(root));
		return levels;
	}

	private void reverseBFS(List<TreeNode> level) {
		if (level.isEmpty())
			return;
		List<Integer> levelVals = new ArrayList<Integer>(level.size());
		List<TreeNode> nextLevel = new ArrayList<TreeNode>();
		for (TreeNode tn : level) {
			levelVals.add(tn.val);
			if (tn.left != null)
				nextLevel.add(tn.left);
			if (tn.right != null)
				nextLevel.add(tn.right);
		}
		reverseBFS(nextLevel);
		this.levels.add(levelVals);
	}

	public List<List<Integer>> levelOrderBottomIterative(TreeNode root) {
		// passes on LeetCode.
		if (root == null)
			return new ArrayList<List<Integer>>();
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.offer(root);
		TreeNode leftMostInLevel = null;
		Stack<List<Integer>> levels = new Stack<List<Integer>>();
		List<Integer> level = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if (node.equals(leftMostInLevel)) {
				levels.push(level);
				level = new ArrayList<Integer>();
				leftMostInLevel = null;
			}

			level.add(node.val);

			if (node.left != null) {
				queue.offer(node.left);
				if (leftMostInLevel == null)
					leftMostInLevel = node.left;
			}

			if (node.right != null) {
				queue.offer(node.right);
				if (leftMostInLevel == null)
					leftMostInLevel = node.right;
			}
		}
		// push the last level
		levels.push(level);

		List<List<Integer>> reversed = new ArrayList<List<Integer>>(
				levels.size());
		while (!levels.isEmpty()) {
			reversed.add(levels.pop());
		}

		return reversed;
	}

	public static void main(String[] args) {
		TreeNode root;
		List<List<Integer>> bottomLevels;

		root = TreeNode
				.buildTree(new Integer[] { 3, 9, 20, null, null, 15, 7 });
		bottomLevels = Arrays.asList(Arrays.asList(15, 7), Arrays.asList(9, 20),
				Arrays.asList(3));
		LeetPrinter.assertPrint(bottomLevels,
				new BTLevelTraversal().levelOrderBottom(root), "Case 1: ");

		root = TreeNode.buildTree(new Integer[] { 3, 9, 20, 1, 2, 15, 7 });
		bottomLevels = Arrays.asList(Arrays.asList(1, 2, 15, 7),
				Arrays.asList(9, 20), Arrays.asList(3));
		LeetPrinter.assertPrint(bottomLevels,
				new BTLevelTraversal().levelOrderBottom(root), "Case 2: ");

		root = TreeNode.buildTree(new Integer[] { 3 });
		bottomLevels = Arrays.asList(Arrays.asList(3));
		LeetPrinter.assertPrint(bottomLevels,
				new BTLevelTraversal().levelOrderBottom(root), "Case 3: ");

		root = TreeNode.buildTree(new Integer[] { 3, 9, 20 });
		bottomLevels = Arrays.asList(Arrays.asList(9, 20), Arrays.asList(3));
		LeetPrinter.assertPrint(bottomLevels,
				new BTLevelTraversal().levelOrderBottom(root), "Case 4: ");

		root = TreeNode.buildTree(new Integer[] { 3, 9, 20, 1, null, 15, 17,
				null, 13, 100, 101, 102, 103 });
		bottomLevels = Arrays.asList(Arrays.asList(13, 100, 101, 102, 103),
				Arrays.asList(1, 15, 17), Arrays.asList(9, 20),
				Arrays.asList(3));
		LeetPrinter.assertPrint(bottomLevels,
				new BTLevelTraversal().levelOrderBottom(root), "Case 5: ");

		root = TreeNode.buildTree(new Integer[] { 3, 9, 20, 1, null, 15, 17,
				null, null, null, null, 102, 103 });
		bottomLevels = Arrays.asList(Arrays.asList(102, 103),
				Arrays.asList(1, 15, 17), Arrays.asList(9, 20),
				Arrays.asList(3));
		LeetPrinter.assertPrint(bottomLevels,
				new BTLevelTraversal().levelOrderBottom(root), "Case 6: ");

		System.out.println("Done Successfully");
	}
}

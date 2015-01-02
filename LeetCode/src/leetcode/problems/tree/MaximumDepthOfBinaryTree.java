package leetcode.problems.tree;


public class MaximumDepthOfBinaryTree {

	public int maxDepth(TreeNode root) {
		return solveAttempt1(root);
	}

	private int solveAttempt1(TreeNode root) {
		if (root == null)
			return 0;
		return 1 + Math
				.max(solveAttempt1(root.left), solveAttempt1(root.right));
	}
	
}

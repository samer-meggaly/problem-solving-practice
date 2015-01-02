package leetcode.problems.tree;


public class SameTree {

	public boolean isSameTree(TreeNode p, TreeNode q) {
		return solveAttempt1(p, q);
	}

	private boolean solveAttempt1(TreeNode p, TreeNode q) {
		if (p != null && q != null) {
			return p.val == q.val && solveAttempt1(p.left, q.left)
					&& solveAttempt1(p.right, q.right);
		}

		return p == null && q == null;
	}
}

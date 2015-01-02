package leetcode.problems.tree;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int v) {
		val = v;
	}

	public String toString() {
		String leftString = (left == null) ? "" : left.toString();
		String rightString = (right == null) ? "" : right.toString();
		return String.format("{%s} <- (%d) -> {%s}", leftString, val,
				rightString);
	}
}

package base;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int v) {
		val = v;
	}

	public TreeNode(int v, TreeNode left, TreeNode right) {
		this.val = v;
		this.left = left;
		this.right = right;
	}

	public String toString() {
		String leftString = (left == null) ? "" : left.toString();
		String rightString = (right == null) ? "" : right.toString();
		return String.format("{%s} <- (%d) -> {%s}", leftString, val,
				rightString);
	}

	@Override
	protected TreeNode clone() {
		return new TreeNode(this.val, this.left.clone(), this.right.clone());
	}
}

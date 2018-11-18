package leetcode.problems.tree;

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
	public boolean equals(Object o) {
		if (o == null || !(o instanceof TreeNode))
			return false;
		else
			return new SameTree().isSameTree(this, (TreeNode) o);
	}

	@Override
	protected TreeNode clone() {
		return new TreeNode(this.val, this.left.clone(), this.right.clone());
	}

	/**
	 * Creates a tree node given level traversal of tree following
	 * <a href="https://leetcode.com/faq/#binary-tree">LeetCode convention</a>.
	 * 
	 * <code>
	 * </br>
	 * IN: [3]
	 * </br>
	 * OUT: () <- 3 -> ()
	 * </br></br>
	 * IN: [3, 9, 20, null, null, 15, 7]
	 * </br>
	 * OUT: {() <- 9 -> ()} <- 3 -> {{() <- 15 -> ()} <- 20 -> {() <- 13 -> ()}}
	 * </br>
	 * </code>
	 * 
	 * For more information check {@link leetcode.problems.tree.Codec}.
	 * 
	 * @param levels
	 *            Traversal of tree elements level by level.
	 * @return root.
	 */
	public static TreeNode buildTree(Integer[] levels) {
		return new Codec().deserialize(levels);
	}
}

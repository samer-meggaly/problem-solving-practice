package leetcode.problems.tree;

import java.util.HashMap;
import java.util.Map;

public class BTFromPrePostOrders {

	/*
	 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
	 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
	 */

	private int[] preorder = null;
	private Map<Integer, Integer> inorder = null;

	public TreeNode buildTreePreorder(int[] preorder, int[] inorder) {
		this.preorder = preorder;

		// Caching instead of searching through inorder array searching for val.
		this.inorder = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++)
			this.inorder.put(inorder[i], i);
		return constructTreePreorder(0, 0, inorder.length - 1);
	}

	private TreeNode constructTreePreorder(int rootIdx, int inorderFrom,
			int inorderTo) {
		if (rootIdx >= preorder.length)
			return null;

		int val = preorder[rootIdx];
		int rootInorderIdx = this.inorder.get(val);

		int leftSubtreeSize = rootInorderIdx - inorderFrom;
		int rightSubtreeSize = inorderTo - rootInorderIdx;
		TreeNode root = new TreeNode(val);
		if (leftSubtreeSize > 0)
			root.left = constructTreePreorder(rootIdx + 1, inorderFrom,
					rootInorderIdx - 1);
		if (rightSubtreeSize > 0)
			root.right = constructTreePreorder(rootIdx + leftSubtreeSize + 1,
					rootInorderIdx + 1, inorderTo);
		return root;
	}

	private int[] postorder;

	public TreeNode buildTreePostorder(int[] inorder, int[] postorder) {
		this.postorder = postorder;
		this.inorder = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++)
			this.inorder.put(inorder[i], i);
		return constructTreePostorder(postorder.length - 1, 0,
				inorder.length - 1);
	}

	private TreeNode constructTreePostorder(int rootIdx, int inorderFrom,
			int inorderTo) {
		if (rootIdx < 0)
			return null;

		int val = postorder[rootIdx];
		int rootInorderIdx = inorder.get(val);
		int leftSubtreeSize = rootInorderIdx - inorderFrom;
		int rightSubtreeSize = inorderTo - rootInorderIdx;
		TreeNode root = new TreeNode(val);
		if (leftSubtreeSize > 0)
			root.left = constructTreePostorder(rootIdx - rightSubtreeSize - 1,
					inorderFrom, rootInorderIdx - 1);
		if (rightSubtreeSize > 0)
			root.right = constructTreePostorder(rootIdx - 1, rootInorderIdx + 1,
					inorderTo);
		return root;
	}

	public static void main(String[] args) {
		int[] pre, in;
		pre = new int[] { 15, 6, 4, 5, 7, 23, 71, 50 };
		in = new int[] { 4, 5, 6, 7, 15, 23, 50, 71 };
		System.out
				.println(new BTFromPrePostOrders().buildTreePreorder(pre, in));
	}
}

package leetcode.problems.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLCA {
	private boolean search(TreeNode root, TreeNode n, List<TreeNode> nPath) {
		if (root == null)
			return false;
		if (root == n) {
			nPath.add(root);
			return true;
		} else {
			boolean rootIsAncestor = search(root.left, n, nPath)
					|| search(root.right, n, nPath);
			if (rootIsAncestor) {
				nPath.add(root);
				return true;
			}
		}
		return false;
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p,
			TreeNode q) {
		List<TreeNode> pPath = new ArrayList<TreeNode>();
		search(root, p, pPath);
		TreeNode prev = null;
		for (int i = 0; i < pPath.size(); i++) {
			TreeNode curr = pPath.get(i);
			if (curr == q)
				return curr;
			boolean found = false;
			if (i == 0 || prev == curr.left)
				found |= search(curr.right, q, new ArrayList<TreeNode>());
			if (i == 0 || prev == curr.right)
				found |= search(curr.left, q, new ArrayList<TreeNode>());
			if (found)
				return curr;
			prev = curr;
		}

		return null;
	}
	
	public static void main(String[] args) {
		TreeNode root, p, q, lca;
		Integer[] tree;

		tree = new Integer[] { 2, null, 1 };
		root = TreeNode.buildTree(tree);
		p = getTopLeftMostWithVal(root, 2);
		q = getTopLeftMostWithVal(root, 1);
		lca = new BinaryTreeLCA().lowestCommonAncestor(root, p, q);
		System.out.println((lca == null) ? "null" : lca.val);
		System.out.println("Done");
	}

	private static TreeNode getTopLeftMostWithVal(TreeNode root, int val) {
		if (root == null)
			return null;
		else if (root.val == val)
			return root;
		else {
			TreeNode searchLeft = getTopLeftMostWithVal(root.left, val);
			if (searchLeft == null)
				return getTopLeftMostWithVal(root.right, val);
			else
				return searchLeft;
		}
	}
}

package leetcode.problems.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeIterativePostorder {

	// https://leetcode.com/problems/binary-tree-postorder-traversal/

	private static class VisitedNode {
		boolean visited = false;
		TreeNode node;

		public VisitedNode(TreeNode node) {
			this.node = node;
		}
	}

	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> postorder = new ArrayList<Integer>();
		if (root == null)
			return postorder;
		Stack<VisitedNode> stack = new Stack<VisitedNode>();
		stack.push(new VisitedNode(root));
		while (!stack.isEmpty()) {
			VisitedNode node = stack.pop();
			if (!node.visited) {
				node.visited = true;
				stack.push(node);
				if (node.node.right != null)
					stack.push(new VisitedNode(node.node.right));
				if (node.node.left != null)
					stack.push(new VisitedNode(node.node.left));
			} else
				postorder.add(node.node.val);
		}
		return postorder;
	}
}

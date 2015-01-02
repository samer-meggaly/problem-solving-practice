package leetcode.problems.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LinkTreeNodeNext {
	public class Solution {
		public void connect(TreeLinkNode root) {
			solve3(root);
		}

		public void solve3(TreeLinkNode root) {
			Stack<TreeLinkNode> stack = new Stack<TreeLinkNode>();
			stack.push(root);
			while (!stack.isEmpty()) {
				TreeLinkNode curr = stack.pop();
				if (curr == null)
					continue;

				if (curr.left != null) {
					if (curr.right != null)
						curr.left.next = curr.right;
					else
						curr.left.next = getFirstNonNullRight(curr.next);
				}

				if (curr.right != null) {
					curr.right.next = getFirstNonNullRight(curr.next);
				}

				stack.push(curr.left);
				stack.push(curr.right);
			}
		}

		public void solve2(TreeLinkNode root) {
			if (root == null)
				return;

			if (root.left != null) {
				if (root.right != null)
					root.left.next = root.right;
				else
					root.left.next = getFirstNonNullRight(root.next);
			}

			if (root.right != null) {
				root.right.next = getFirstNonNullRight(root.next);
			}

			solve2(root.right);
			solve2(root.left);
		}

		private TreeLinkNode getFirstNonNullRight(TreeLinkNode parent) {
			TreeLinkNode firstNonNullRight = null;
			while (parent != null && firstNonNullRight == null) {
				if (parent.left != null)
					firstNonNullRight = parent.left;
				else if (parent.right != null)
					firstNonNullRight = parent.right;
				else
					parent = parent.next;
			}
			return firstNonNullRight;
		}

		public void solve1(TreeLinkNode root) {
			Queue<TreeLinkNode> q0 = new LinkedList<TreeLinkNode>();
			Queue<TreeLinkNode> q1 = new LinkedList<TreeLinkNode>();
			q0.add(root);
			boolean done = false;
			while (!done) {
				if (!q0.isEmpty()) {
					fillOther(q0, q1);
				} else if (!q1.isEmpty()) {
					fillOther(q1, q0);
				} else {
					done = true;
				}
			}
		}

		private void fillOther(Queue<TreeLinkNode> queue,
				Queue<TreeLinkNode> other) {
			while (!queue.isEmpty()) {
				TreeLinkNode n = queue.poll();
				if (n != null) {
					n.next = queue.peek();
					if (n.left != null)
						other.offer(n.left);
					if (n.right != null)
						other.offer(n.right);
				}
			}
		}
	}

	public static void main(String[] args) {
		Solution solver = new LinkTreeNodeNext().new Solution();
		case1(solver);
		case2(solver);
	}

	private static void case1(Solution solver) {
		TreeLinkNode node1 = new TreeLinkNode(1);
		TreeLinkNode node2 = new TreeLinkNode(2);
		TreeLinkNode node3 = new TreeLinkNode(3);
		TreeLinkNode node4 = new TreeLinkNode(4);
		TreeLinkNode node5 = new TreeLinkNode(5);
		TreeLinkNode node6 = new TreeLinkNode(6);
		TreeLinkNode node7 = new TreeLinkNode(7);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		node3.right = node7;
		solver.connect(node1);
		System.out.println();
	}

	private static void case2(Solution solver) {
		TreeLinkNode node1 = new TreeLinkNode(1);
		TreeLinkNode node2 = new TreeLinkNode(2);
		TreeLinkNode node3 = new TreeLinkNode(3);
		TreeLinkNode node4 = new TreeLinkNode(4);
		TreeLinkNode node7 = new TreeLinkNode(7);
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		// node2.right = node5;
		// node3.left = node6;
		node3.right = node7;
		solver.connect(node1);
		System.out.println();
	}
}

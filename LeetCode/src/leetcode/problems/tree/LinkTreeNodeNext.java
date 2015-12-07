package leetcode.problems.tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class LinkTreeNodeNext {

	/*
	 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
	 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
	 */

	public void connectPerfectBT(TreeLinkNode root) {
		// Passes on LeetCode for Perfect Binary Trees.
		int counter = 1;
		TreeLinkNode ptr = root;
		TreeLinkNode firstInNextLevel = null;
		while (ptr != null && ptr.left != null && ptr.right != null) {
			if ((counter & (counter - 1)) == 0) {
				firstInNextLevel = ptr.left;
			}
			counter++;

			ptr.left.next = ptr.right;
			if (ptr.next != null) {
				ptr.right.next = ptr.next.left;
				ptr = ptr.next;
			} else {
				ptr = firstInNextLevel;
			}
		}
	}

	public void connectUsingTwoQueues(TreeLinkNode root) {
		// Passes on LeetCode, but uses O(N) space.
		if (root == null)
			return;
		Queue<TreeLinkNode> q0 = new ArrayDeque<TreeLinkNode>();
		q0.add(root);
		while (!q0.isEmpty()) {
			Queue<TreeLinkNode> q1 = new ArrayDeque<TreeLinkNode>();
			while (!q0.isEmpty()) {
				TreeLinkNode n = q0.poll();
				n.next = q0.peek();
				if (n.left != null)
					q1.offer(n.left);
				if (n.right != null)
					q1.offer(n.right);
			}
			q0 = q1;
		}
	}

	public void connectNonPerfectBTWithNoSpace(TreeLinkNode root) {
		// Passes on LeetCode for non-Perfect Binary Trees. Uses O(1) space.
		// Time complexity is >= N2, I think O(N2 * LgN).
		TreeLinkNode ptr = root;
		TreeLinkNode firstInNextLevel = null;
		while (ptr != null) {
			if (firstInNextLevel == null) {
				firstInNextLevel = (ptr.left != null) ? ptr.left : ptr.right;
			}

			TreeLinkNode nextInNextLevel = getLeftMostChildOfParent(ptr.next);
			if (ptr.right != null) {
				ptr.right.next = nextInNextLevel;
				if (ptr.left != null)
					ptr.left.next = ptr.right;
			} else if (ptr.left != null) {
				ptr.left.next = nextInNextLevel;
			}

			if (ptr.next != null) {
				ptr = ptr.next;
			} else {
				ptr = firstInNextLevel;
				firstInNextLevel = null;
			}
		}
	}

	private TreeLinkNode getLeftMostChildOfParent(TreeLinkNode parent) {
		while (parent != null) {
			if (parent.left != null)
				return parent.left;
			if (parent.right != null)
				return parent.right;
			parent = parent.next;
		}
		return null;
	}

	public void connect(TreeLinkNode root) {
		// NOT my solution. Just implemented it to keep the optimal solution.
		// O(1) Space and O(N) Time complexities.
		TreeLinkNode ptr = root;
		while (ptr != null) {

			// process the current level's children nodes.
			TreeLinkNode firstInNextLevel = null;

			/*
			 * The following pointer will always hold the "previous" to the
			 * child node that is being processes. It is always null at the
			 * beginning of processing of the level, which makes sense (The
			 * leftmost child will have NO previous node).
			 */
			TreeLinkNode mostRightInNextLevel = null;
			while (ptr != null) {
				if (ptr.left != null) {
					if (mostRightInNextLevel == null)
						firstInNextLevel = ptr.left;
					else
						mostRightInNextLevel.next = ptr.left;
					mostRightInNextLevel = ptr.left;
				}

				if (ptr.right != null) {
					if (mostRightInNextLevel == null)
						firstInNextLevel = ptr.right;
					else
						mostRightInNextLevel.next = ptr.right;
					mostRightInNextLevel = ptr.right;
				}

				ptr = ptr.next;
			}

			ptr = firstInNextLevel;
		}
	}
}

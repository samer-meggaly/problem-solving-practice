package leetcode.problems.tree;

public class TreeLinkNode {
	// 1 -> NULL
	// / \
	// 2 -> 3 -> NULL
	// / \ / \
	// 4->5->6->7 -> NULL

	int val;
	TreeLinkNode left, right, next;

	public TreeLinkNode(int val) {
		this.val = val;
	}
	
	@Override
	public String toString() {
		return "" + val;
	}
}

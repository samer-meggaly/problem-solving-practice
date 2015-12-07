package leetcode.utils;

import leetcode.problems.tree.TreeLinkNode;
import leetcode.problems.tree.TreeNode;

public class LeetPrinter {

	public static void assertPrint(Object expected, Object actual,
			String messagePrefix) {
		if (!expected.equals(actual)) {
			System.err.println(messagePrefix + actual.toString());
			System.err.flush();
		}
	}

	public static TreeNode toTree(Integer[] nums) {
		return constructTree(nums, 1);
	}

	private static TreeNode constructTree(Integer[] nums, int i) {
		if (i > nums.length || nums[i - 1] == null)
			return null;
		TreeNode node = new TreeNode(nums[i - 1]);
		node.left = constructTree(nums, 2 * i);
		node.right = constructTree(nums, 2 * i + 1);
		return node;
	}
	
	public void connect(TreeLinkNode root) {
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

	public static void main(String[] args) {
		TreeNode tree = toTree(
				new Integer[] { 1, 2, 3, 4, 5, null, 7, null, null, 10, 11 });
		System.out.println(tree);
	}
}

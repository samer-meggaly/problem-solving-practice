package leetcode.problems.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import leetcode.problems.tree.TreeNode;

public class SortedListToBST {
	public class Solution {

		private ListNode head;

		public TreeNode sortedListToBST(ListNode head) {
			this.head = head;
			return solve2();
		}

		public TreeNode solve2() {
			int len = 0;
			ListNode ptr = head;
			while (ptr != null) {
				ptr = ptr.next;
				len++;
			}

			return buildBottomUp(0, len - 1);
		}

		private TreeNode buildBottomUp(int from, int to) {
			if (from > to)
				return null;

			int middle = (from + to) >> 1;
			TreeNode left = buildBottomUp(from, middle - 1);
			TreeNode root = new TreeNode(head.val);
			head = head.next;
			TreeNode right = buildBottomUp(middle + 1, to);
			root.left = left;
			root.right = right;
			return root;
		}

		public TreeNode solve1() {
			int[] arr = toArray(head);
			return buildTopDownFromArray(arr);
		}

		private TreeNode buildTopDownFromArray(int[] arr) {
			if (arr.length == 0)
				return null;
			if (arr.length == 1)
				return new TreeNode(arr[0]);

			int middle = arr.length >> 1;
			TreeNode root = new TreeNode(arr[middle]);
			TreeNode left = buildTopDownFromArray(Arrays.copyOfRange(arr, 0,
					middle));
			TreeNode right = buildTopDownFromArray(Arrays.copyOfRange(arr,
					middle + 1, arr.length));
			root.left = left;
			root.right = right;
			return root;
		}

		private int[] toArray(ListNode head) {
			ListNode ptr = head;
			List<Integer> list = new ArrayList<Integer>();
			while (ptr != null) {
				list.add(ptr.val);
				ptr = ptr.next;
			}

			int[] vals = new int[list.size()];
			for (int i = 0; i < vals.length; i++) {
				vals[i] = list.get(i);
			}

			return vals;
		}
	}

	public static void main(String[] args) {
		Solution solver = new SortedListToBST().new Solution();
		ListNode aList = null;
		aList = ListNode.buildList(new int[] { 1 });
		System.out.println("aList:");
		System.out.println(String.format("[ %s ]", aList));
		System.out.println("Tree: " + solver.sortedListToBST(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2 });
		System.out.println("aList:");
		System.out.println(String.format("[ %s ]", aList));
		System.out.println("Tree: " + solver.sortedListToBST(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2, 3 });
		System.out.println("aList:");
		System.out.println(String.format("[ %s ]", aList));
		System.out.println("Tree: " + solver.sortedListToBST(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2, 3, 5, 6, 7 });
		System.out.println("aList:");
		System.out.println(String.format("[ %s ]", aList));
		System.out.println("Tree: " + solver.sortedListToBST(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 5, 6, 7, 8, 9 });
		System.out.println("aList:");
		System.out.println(String.format("[ %s ]", aList));
		System.out.println("Tree: " + solver.sortedListToBST(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 0, 1, 2, 3, 4 });
		System.out.println("aList:");
		System.out.println(String.format("[ %s ]", aList));
		System.out.println("Tree: " + solver.sortedListToBST(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { -4, -3, -2, -1 });
		System.out.println("aList:");
		System.out.println(String.format("[ %s ]", aList));
		System.out.println("Tree: " + solver.sortedListToBST(aList));
		System.out.println();

	}
}

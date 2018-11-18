package leetcode.problems.linkedlist;

public class ReorderList {
	// https://leetcode.com/problems/reorder-list/
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 * int val;
	 * ListNode next;
	 * ListNode(int x) { val = x; }
	 * }
	 */
	public class Solution {
		private ListNode reverse(ListNode head) {
			ListNode prev = null;
			ListNode curr = head;
			while (curr != null) {
				ListNode next = curr.next;
				curr.next = prev;
				prev = curr;
				curr = next;
			}
			return prev;
		}

		private ListNode getFirstHalfTail(ListNode head) {
			ListNode fast = head;
			ListNode slow = head;
			ListNode firstHalfTail = null;
			while (fast != null && fast.next != null) {
				fast = fast.next.next;
				firstHalfTail = slow;
				slow = slow.next;
			}
			return firstHalfTail;
		}

		public void reorderList(ListNode head) {
			if (head == null || head.next == null || head.next.next == null)
				return;
			ListNode firstHalfTail = getFirstHalfTail(head);
			ListNode ptr1 = head;
			ListNode ptr2 = reverse(firstHalfTail.next);
			firstHalfTail.next = null;
			ListNode mergeTail = null;
			while (ptr1 != null && ptr2 != null) {
				if (mergeTail == null) {
					mergeTail = ptr1;
				} else {
					mergeTail.next = ptr1;
					mergeTail = mergeTail.next;
				}
				ptr1 = ptr1.next;
				mergeTail.next = ptr2;
				mergeTail = mergeTail.next;
				ptr2 = ptr2.next;
			}
		}
	}

	public static void main(String[] args) {
		Solution solver = new ReorderList().new Solution();
		ListNode aList = null;
		aList = ListNode.buildList(new int[] {});
		System.out.println("   A List: " + aList);
		solver.reorderList(aList);
		System.out.println("Reordered: " + aList);
		System.out.println();

		aList = ListNode.buildList(new int[] { 1 });
		System.out.println("   A List: " + aList);
		solver.reorderList(aList);
		System.out.println("Reordered: " + aList);
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2 });
		System.out.println("   A List: " + aList);
		solver.reorderList(aList);
		System.out.println("Reordered: " + aList);
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2, 3 });
		System.out.println("   A List: " + aList);
		solver.reorderList(aList);
		System.out.println("Reordered: " + aList);
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2, 3, 4 });
		System.out.println("   A List: " + aList);
		solver.reorderList(aList);
		System.out.println("Reordered: " + aList);
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2, 3, 4, 5 });
		System.out.println("   A List: " + aList);
		solver.reorderList(aList);
		System.out.println("Reordered: " + aList);
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2, 3, 4, 5, 6 });
		System.out.println("   A List: " + aList);
		solver.reorderList(aList);
		System.out.println("Reordered: " + aList);
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2, 3, 4, 5, 6, 7 });
		System.out.println("   A List: " + aList);
		solver.reorderList(aList);
		System.out.println("Reordered: " + aList);
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
		System.out.println("   A List: " + aList);
		solver.reorderList(aList);
		// [ 1, 10, 2, 9, 3, 8, 4, 7, 5, 6 ]
		System.out.println("Reordered: " + aList);
		System.out.println();
	}
}

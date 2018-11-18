package leetcode.problems.linkedlist;

public class OddEvenList {
	// https://leetcode.com/problems/odd-even-linked-list/
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 * int val;
	 * ListNode next;
	 * ListNode(int x) { val = x; }
	 * }
	 */
	public class Solution {
		public ListNode oddEvenList(ListNode head) {
			ListNode oddTail = head;
			if (oddTail == null || oddTail.next == null)
				return oddTail;
			ListNode evenTail = oddTail.next;
			ListNode evenHead = evenTail;
			ListNode ptr = evenTail.next;
			boolean toOdd = true;
			while (ptr != null) {
				if (toOdd) {
					oddTail.next = ptr;
					oddTail = oddTail.next;
				} else {
					evenTail.next = ptr;
					evenTail = evenTail.next;
				}
				ptr = ptr.next;
				toOdd = !toOdd;
			}
			oddTail.next = evenHead;
			evenTail.next = null;
			return head;
		}
	}

	public static void main(String[] args) {
		Solution solver = new OddEvenList().new Solution();
		ListNode aList = null;
		aList = ListNode.buildList(new int[] {});
		System.out.println(" A List: " + aList);
		System.out.println("OddEven: " + solver.oddEvenList(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 1 });
		System.out.println(" A List: " + aList);
		System.out.println("OddEven: " + solver.oddEvenList(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2 });
		System.out.println(" A List: " + aList);
		System.out.println("OddEven: " + solver.oddEvenList(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2, 3 });
		System.out.println(" A List: " + aList);
		System.out.println("OddEven: " + solver.oddEvenList(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2, 3, 4 });
		System.out.println(" A List: " + aList);
		System.out.println("OddEven: " + solver.oddEvenList(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2, 3, 4, 5 });
		System.out.println(" A List: " + aList);
		System.out.println("OddEven: " + solver.oddEvenList(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });
		System.out.println(" A List: " + aList);
		System.out.println("OddEven: " + solver.oddEvenList(aList));
		System.out.println();
	}
}

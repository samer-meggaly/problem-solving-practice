package leetcode.problems.linkedlist;

public class SortList {

	public class Solution {
		public ListNode sortList(ListNode head) {
			return mergeSort(head);
		}

		public ListNode mergeSort(ListNode head) {
			if (head == null || head.next == null)
				return head;

			int len = 0;
			ListNode ptr = head;
			while (ptr != null) {
				ptr = ptr.next;
				len++;
			}
			len >>= 1;
			ptr = head;
			while (len > 1) {
				ptr = ptr.next;
				len--;
			}

			ListNode leftList = head;
			ListNode rightList = ptr.next;
			ptr.next = null;
			leftList = mergeSort(leftList);
			rightList = mergeSort(rightList);

			return new MergeSortedList().new Solution().mergeTwoLists(
					leftList, rightList);
		}
	}

	public static void main(String[] args) {
		Solution solver = new SortList().new Solution();
		ListNode aList = null;
		aList = ListNode.buildList(new int[] { 1 });
		System.out.println("aList:");
		System.out.println(String.format("[ %s ]", aList));
		System.out.println("Sorted: " + solver.sortList(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 2, 1 });
		System.out.println("aList:");
		System.out.println(String.format("[ %s ]", aList));
		System.out.println("Sorted: " + solver.sortList(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 3, 2, 1 });
		System.out.println("aList:");
		System.out.println(String.format("[ %s ]", aList));
		System.out.println("Sorted: " + solver.sortList(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 3, 2, 5, 1, 6, 7 });
		System.out.println("aList:");
		System.out.println(String.format("[ %s ]", aList));
		System.out.println("Sorted: " + solver.sortList(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 5, 6, 7, 8, 9 });
		System.out.println("aList:");
		System.out.println(String.format("[ %s ]", aList));
		System.out.println("Sorted: " + solver.sortList(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { 1, 2, 3, 4, 0 });
		System.out.println("aList:");
		System.out.println(String.format("[ %s ]", aList));
		System.out.println("Sorted: " + solver.sortList(aList));
		System.out.println();

		aList = ListNode.buildList(new int[] { -1, -2, -3, -4 });
		System.out.println("aList:");
		System.out.println(String.format("[ %s ]", aList));
		System.out.println("Sorted: " + solver.sortList(aList));
		System.out.println();

	}
}

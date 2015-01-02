package leetcode.problems.linkedlist;

public class MergeSortedList {

	public class Solution {
		public ListNode mergeTwoLists(ListNode headA, ListNode headB) {
			if (headA == null)
				return headB;
			if (headB == null)
				return headA;

			ListNode head;
			if (headA.val < headB.val) {
				head = headA;
				headA = headA.next;
			} else {
				head = headB;
				headB = headB.next;
			}

			ListNode mergePtr = head;
			while (true) {
				if (headA == null) {
					mergePtr.next = headB;
					break;
				}

				if (headB == null) {
					mergePtr.next = headA;
					break;
				}

				if (headA.val < headB.val) {
					mergePtr.next = headA;
					headA = headA.next;
				} else {
					mergePtr.next = headB;
					headB = headB.next;
				}

				mergePtr = mergePtr.next;
			}

			return head;
		}
	}

	public static void main(String[] args) {
		Solution solver = new MergeSortedList().new Solution();
		ListNode list1, list2;
		list1 = ListNode.buildList(new int[] { 1, 2, 3 });
		list2 = ListNode.buildList(new int[] { 1, 2, 3 });
		System.out.println("List1 : " + list1);
		System.out.println("List2 : " + list2);
		System.out.println("Merged: " + solver.mergeTwoLists(list1, list2));
		System.out.println();

		list1 = ListNode.buildList(new int[] { 1, 3, 5 });
		list2 = ListNode.buildList(new int[] { 2, 4, 6 });
		System.out.println("List1 : " + list1);
		System.out.println("List2 : " + list2);
		System.out.println("Merged: " + solver.mergeTwoLists(list1, list2));
		System.out.println();

		list1 = ListNode.buildList(new int[] { 2 });
		list2 = ListNode.buildList(new int[] { 1, 1, 3 });
		System.out.println("List1 : " + list1);
		System.out.println("List2 : " + list2);
		System.out.println("Merged: " + solver.mergeTwoLists(list1, list2));
		System.out.println();

		list1 = ListNode.buildList(new int[] { 1 });
		list2 = ListNode.buildList(new int[] { 1 });
		System.out.println("List1 : " + list1);
		System.out.println("List2 : " + list2);
		System.out.println("Merged: " + solver.mergeTwoLists(list1, list2));
		System.out.println();

		list1 = ListNode.buildList(new int[] { -2, -1, 1, 2 });
		list2 = ListNode.buildList(new int[] { 0 });
		System.out.println("List1 : " + list1);
		System.out.println("List2 : " + list2);
		System.out.println("Merged: " + solver.mergeTwoLists(list1, list2));
		System.out.println();

	}
}

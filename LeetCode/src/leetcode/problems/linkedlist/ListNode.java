package leetcode.problems.linkedlist;

/** Definition for singly-linked list. */
public class ListNode {
	public int val;
	public ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}

	void append(ListNode listToAppend) {
		if (next == null)
			next = listToAppend;
		else
			next.append(listToAppend);
	}

	public String toString() {
		return val + ((next == null) ? "" : ", " + next);
	}

	public static ListNode buildList(int[] elems) {
		ListNode head = null;
		for (int i = elems.length - 1; i >= 0; i--) {
			ListNode preLast = new ListNode(elems[i]);
			preLast.next = head;
			head = preLast;
		}
		return head;
	}

	public static ListNode deleteDuplicates(ListNode sortedListHead) {
		if (sortedListHead == null)
			return sortedListHead;

		ListNode ptr = sortedListHead;
		while (ptr.next != null) {
			if (ptr.val == ptr.next.val)
				ptr.next = ptr.next.next;
			else
				ptr = ptr.next;
		}

		return sortedListHead;
	}

	public static void main(String[] args) {
		System.out.println(deleteDuplicates(buildList(new int[] { 1, 2, 2, 2,
				3, 4, 4, 5 })));
		System.out
				.println(deleteDuplicates(buildList(new int[] { 1, 2, 3, 4, 5 })));
		System.out.println(deleteDuplicates(buildList(new int[] { 1 })));
		System.out.println(deleteDuplicates(buildList(new int[] { 1, 2 })));
		System.out
				.println(deleteDuplicates(buildList(new int[] { 2, 2, 2, 2 })));
	}
}
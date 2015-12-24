package leetcode.problems.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import leetcode.utils.LeetPrinter;

public class MergeKSortedLists {

	/*
	 * https://leetcode.com/problems/merge-k-sorted-lists/
	 * 
	 * Both MinHeapMerging and BottomUPBinaryTreeMerging are O(KN Lg(K)), where
	 * K is the number of Lists to merge and N is the average length of a list.
	 * However, the 2 ListNode merging function in MergeSortedList class skips
	 * traversing the rest of one list if reached the end of the other list,
	 * hence it is faster in random cases unlike the heap solution which is
	 * Omega(KN Lg(K)).
	 */

	public ListNode mergeKListsWithMinHeap(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;
		Queue<ListNode> pqueue = new PriorityQueue<ListNode>(lists.length,
				new Comparator<ListNode>() {
					public int compare(ListNode n1, ListNode n2) {
						return n1.val - n2.val;
					}
				});
		for (ListNode head : lists)
			if (head != null)
				pqueue.offer(head);
		ListNode mergedHead = null;
		ListNode mergedTail = null;
		while (!pqueue.isEmpty()) {
			ListNode minOfListsHeads = pqueue.poll();
			if (mergedTail == null) {
				mergedHead = minOfListsHeads;
				mergedTail = minOfListsHeads;
			} else {
				mergedTail.next = minOfListsHeads;
				mergedTail = mergedTail.next;
			}

			if (mergedTail.next != null) {
				pqueue.offer(mergedTail.next);
				mergedTail.next = null;
			}
		}

		return mergedHead;
	}

	private ListNode[] lists = null;

	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;
		this.lists = lists;
		return mergeListsInRange(0, lists.length);
	}

	private ListNode mergeListsInRange(int from, int to) {
		if (to - from <= 0)
			return null;
		if (to - from == 1)
			return lists[from];
		if (to - from == 2)
			return new MergeSortedList().new Solution()
					.mergeTwoLists(lists[from], lists[from + 1]);

		int middle = ((to - from) >> 1) + from;
		ListNode leftMerged = mergeListsInRange(from, middle);
		ListNode rightMerged = mergeListsInRange(middle, to);
		return new MergeSortedList().new Solution().mergeTwoLists(leftMerged,
				rightMerged);
	}

	private static ListNode[] toArrayOfLists(int[][] lists) {
		ListNode[] converted = new ListNode[lists.length];
		for (int i = 0; i < converted.length; i++)
			converted[i] = ListNode.buildList(lists[i]);
		return converted;
	}

	public static void main(String[] args) {
		ListNode[] lists;
		ListNode merged, mergedGT;
		lists = toArrayOfLists(new int[][] { { 1, 3, 5 }, { 2, 4, 6 },
				{ 1, 9, 11, 13 }, { 8 } });
		mergedGT = ListNode
				.buildList(new int[] { 1, 1, 2, 3, 4, 5, 6, 8, 9, 11, 13 });
		merged = new MergeKSortedLists().mergeKLists(lists);
		LeetPrinter.assertPrint(mergedGT, merged,
				"Case 1:\nExp: " + mergedGT + "\nAct: ");
		lists = toArrayOfLists(new int[][] { { -1, -1, 0, 3, 5, 5, 5, 5 },
				{ -3, 2, 4, 6 }, { 1, 9, 11, 13 }, {}, { -100, -99 } });
		mergedGT = ListNode.buildList(new int[] { -100, -99, -3, -1, -1, 0, 1,
				2, 3, 4, 5, 5, 5, 5, 6, 9, 11, 13 });
		merged = new MergeKSortedLists().mergeKLists(lists);
		LeetPrinter.assertPrint(mergedGT, merged,
				"Case 2:\nExp: " + mergedGT + "\nAct: ");
		System.out.println("Done Successfully");
	}
}

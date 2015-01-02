package leetcode.problems.linkedlist;

import java.util.Stack;

public class LinkedListIntersection {

	public class Solution {
		public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
			return solver2(headA, headB);
		}
		
		public ListNode solver3(ListNode headA, ListNode headB) {
			if (headA == null || headB == null)
				return null;

			ListNode ptrA = headA;
			ListNode ptrB = headB;

			while (ptrA.next != null && ptrB.next != null) {
				ptrA = ptrA.next;
				ptrB = ptrB.next;
			}

			if (ptrA.next == null) {
				ptrA = headB;
				ptrB = ptrB.next;
				if (ptrB == null)
					ptrB = headA;
				while (ptrA.next != null && !ptrA.equals(ptrB)) {
					ptrA = ptrA.next;
					ptrB = ptrB.next;
					if (ptrB == null)
						ptrB = headA;
				}
			} else {
				ptrB = headA;
				ptrA = ptrA.next;
				if (ptrA == null)
					ptrA = headB;
				while (ptrB.next != null && !ptrB.equals(ptrA)) {
					ptrB = ptrB.next;
					ptrA = ptrA.next;
					if (ptrA == null)
						ptrA = headB;
				}
			}

			return (ptrA.equals(ptrB) ? ptrA : null);
		}
		
		public ListNode solver2(ListNode headA, ListNode headB) {
			if (headA == null || headB == null)
				return null;
			ListNode ptrA = headA;
			int lenA = 0;
			while (ptrA != null) {
				lenA++;
				ptrA = ptrA.next;
			}
			ListNode ptrB = headB;
			int lenB = 0;
			while (ptrB != null) {
				lenB++;
				ptrB = ptrB.next;
			}
			
			ptrA = headA;
			while (lenA > lenB) {
				ptrA = ptrA.next;
				lenA--;
			}
			ptrB = headB;
			while (lenB > lenA) {
				ptrB = ptrB.next;
				lenB--;
			}
			
			while (ptrA != null && !ptrA.equals(ptrB)) {
				ptrA = ptrA.next;
				ptrB = ptrB.next;
			}
			
			return ptrA;
		}

		public ListNode solver1(ListNode headA, ListNode headB) {
			if (headA == null || headB == null)
				return null;
			ListNode ptr = headA;
			Stack<ListNode> stackA = new Stack<ListNode>();
			stackA.push(ptr);
			while (ptr.next != null) {
				stackA.push(ptr.next);
				ptr = ptr.next;
			}
			ptr = headB;
			Stack<ListNode> stackB = new Stack<ListNode>();
			stackB.push(ptr);
			while (ptr.next != null) {
				stackB.push(ptr.next);
				ptr = ptr.next;
			}

			ptr = null;
			while (!stackA.isEmpty() && !stackB.isEmpty()
					&& stackA.peek().equals(stackB.peek())) {
				ptr = stackA.pop();
				stackB.pop();
			}
			return ptr;
		}
	}

	public static void main(String[] args) {
		Solution solver = new LinkedListIntersection().new Solution();
		ListNode list1 = ListNode.buildList(new int[] { 1, 2, 3, 4 });
		System.out.println("List1:");
		System.out.println(String.format("[ %s ]", list1));
		System.out.println("List1.List1: " + solver.getIntersectionNode(list1, list1).val);
		ListNode list2 = ListNode.buildList(new int[] { 5, 6, 7 });
		System.out.println("List2:");
		System.out.println(String.format("[ %s ]", list2));
		System.out.println("List2.List2: " + solver.getIntersectionNode(list2, list2).val);
		ListNode list3 = ListNode.buildList(new int[] { 8, 9, 10, 11 });
		System.out.println("List3:");
		System.out.println(String.format("[ %s ]", list3));
		System.out.println("List3.List3: " + solver.getIntersectionNode(list3, list3).val);
		System.out.println();
		
		System.out.println("List1.List2: " + solver.getIntersectionNode(list1, list2));
		System.out.println("List1.List3: " + solver.getIntersectionNode(list1, list3));
		System.out.println("List3.List2: " + solver.getIntersectionNode(list3, list2));
		System.out.println();
		
		list1.append(list3);
		System.out.println("List1:");
		System.out.println(String.format("[ %s ]", list1));
		list2.append(list3);
		System.out.println("List2:");
		System.out.println(String.format("[ %s ]", list2));
		System.out.println("List1.List2: " + solver.getIntersectionNode(list1, list2).val);
		
	}

}

package base.chapters;

import java.util.HashSet;
import java.util.Set;

import base.ListNode;

public class LinkedLists {

	static class LinkedList {
		ListNode head;
		ListNode tail;

		LinkedList add(int i) {
			ListNode n = new ListNode(i);
			if (head == null) {
				head = n;
				tail = n;
			} else {
				tail.next = n;
				tail = n;
			}

			return this;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[ ");
			ListNode ptr = head;
			while (ptr != null) {
				sb.append(ptr.data);
				if (ptr.next != null)
					sb.append(", ");
				ptr = ptr.next;
			}
			sb.append(" ]");
			return sb.toString();
		}
	}

	static void removeDuplicates1(LinkedList list) {
		if (list.head == null)
			return;
		Set<Integer> marked = new HashSet<Integer>();
		marked.add(list.head.data);
		ListNode ptr = list.head;
		while (ptr != null && ptr.next != null) {
			if (marked.contains(ptr.next.data)) {
				// delete next of the ptr
				ptr.next = ptr.next.next;
			} else {
				// add to the marked list
				marked.add(ptr.next.data);
				ptr = ptr.next;
			}
		}
	}

	static void removeDuplicates(LinkedList list) {
		if (list.head == null)
			return;
		ListNode uniTail = list.head;
		while (uniTail.next != null) {
			boolean unique = true;
			for (ListNode runner = list.head; runner != uniTail.next
					&& unique; runner = runner.next) {
				unique = runner.data != uniTail.next.data;
			}

			if (unique) {
				uniTail = uniTail.next;
			} else {
				ListNode uniTailNext = uniTail.next;
				uniTail.next = uniTailNext.next;
				uniTailNext.next = null;
			}
		}
	}

	static class ListPartition {
		private ListNode lessThan;
		private ListNode greaterEqThan;

		public ListPartition(ListNode head, int x) {
			ListNode ptr1 = null;
			ListNode ptr2 = null;
			while (head != null) {
				if (head.data < x) {
					if (ptr1 == null)
						lessThan = ptr1 = head;
					else {
						ptr1.next = head;
						ptr1 = ptr1.next;
					}
				} else {
					if (ptr2 == null)
						greaterEqThan = ptr2 = head;
					else {
						ptr2.next = head;
						ptr2 = ptr2.next;
					}
				}
				head = head.next;
			}
			
			if (ptr1 != null)
				ptr1.next = null;
			if (ptr2 != null)
				ptr2.next = null;
		}
		
		public ListNode getLessThan() {
			return lessThan;
		}
		
		public ListNode getGreaterEqThan() {
			return greaterEqThan;
		}
	}

	public static void main(String[] args) {
		LinkedList ll = new LinkedList().add(1).add(2);
		removeDuplicates(ll);
		System.out.println(ll); // [ 1, 2 ]
		ll = new LinkedList().add(1).add(1);
		removeDuplicates(ll);
		System.out.println(ll); // [ 1 ]
		ll = new LinkedList().add(1).add(1).add(1);
		removeDuplicates(ll);
		System.out.println(ll); // [ 1 ]
		ll = new LinkedList().add(1).add(2).add(3).add(1);
		removeDuplicates(ll);
		System.out.println(ll); // [ 1, 2, 3 ]
		ll = new LinkedList().add(1).add(2).add(2).add(2).add(5).add(3).add(5);
		removeDuplicates(ll);
		System.out.println(ll); // [ 1, 2, 5, 3 ]
		ll = new LinkedList().add(1).add(1).add(2).add(2).add(2).add(5).add(3)
				.add(5).add(1);
		removeDuplicates(ll);
		System.out.println(ll); // [ 1, 2, 5, 3 ]
		
		System.out.println("==========================================================");
		
		ListPartition partitioner;
		ll = new LinkedList().add(1).add(4).add(2).add(2).add(5).add(3).add(5);
		partitioner = new ListPartition(ll.head, 4);
		System.out.println(partitioner.getLessThan()); // [ 1, 2, 2, 3]
		System.out.println(partitioner.getGreaterEqThan()); // [ 4, 5, 5 ]
		ll = new LinkedList().add(1).add(4).add(2).add(2).add(5).add(3).add(5);
		partitioner = new ListPartition(ll.head, 0);
		System.out.println(partitioner.getLessThan()); // null
		System.out.println(partitioner.getGreaterEqThan()); // [ 1, 4, 2, 2, 5, 3, 5 ]
		ll = new LinkedList().add(1).add(4).add(2).add(2).add(5).add(3).add(5);
		partitioner = new ListPartition(ll.head, 10);
		System.out.println(partitioner.getLessThan()); // [ 1, 4, 2, 2, 5, 3, 5 ]
		System.out.println(partitioner.getGreaterEqThan()); // null
	}

}

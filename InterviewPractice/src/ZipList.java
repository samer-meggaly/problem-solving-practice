public class ZipList {

	public static ListNode zipList1(ListNode head) {
		// O(N * N)
		ListNode ptr = head;
		while (ptr != null && ptr.next != null) {
			// find the last node and set next of pre-last to null
			ListNode preLast = ptr;
			while (preLast.next.next != null) {
				preLast = preLast.next;
			}
			ListNode last = preLast.next;
			preLast.next = null;

			// sift last to be next of current ptr
			ListNode nextPtr = ptr.next;
			ptr.next = last;
			last.next = nextPtr;
			ptr = nextPtr;
		}

		return head;
	}

	public static ListNode zipList2(ListNode head) {
		// O(N) but also O(N) Stack Calls!
		int length = len(head);
		if (length % 2 == 0)
			zip(head, 0, length / 2 - 1, true);
		else
			zip(head, 0, length / 2, false);
		return head;
	}

	private static int len(ListNode head) {
		ListNode ptr = head;
		int count = 0;
		while (ptr != null) {
			count++;
			ptr = ptr.next;
		}
		return count;
	}

	private static ListNode zip(ListNode curr, int call, int maxCall,
			boolean evenLengthed) {

		if (call == maxCall) {
			ListNode tmp;
			if (evenLengthed) {
				tmp = curr.next.next;
				curr.next.next = null;
			} else {
				tmp = curr.next;
				curr.next = null;
			}
			return tmp;
		}

		ListNode zipNext = zip(curr.next, call + 1, maxCall, evenLengthed);
		ListNode zipNextNext = zipNext.next;
		zipNext.next = curr.next;
		curr.next = zipNext;
		return zipNextNext;
	}

	public static void main(String[] args) {
		System.out.println("Testing ZipList1:");
		testZipList1();
		System.out.println("=================");
		System.out.println("Testing ZipList2:");
		testZipList2();
		System.out.println("=================");
	}

	private static void testZipList1() {
		ListNode head = null;
		head = ListNode.buildList(new int[] { 1, 2, 3 });
		System.out.println("Before  : " + head);
		System.out.println("Expected: "
				+ ListNode.buildList(new int[] { 1, 3, 2 }));
		System.out.println("Zipped  : " + zipList1(head));
		System.out.println();

		head = ListNode.buildList(new int[] { 1, 2, 3, 4, 5 });
		System.out.println("Before  : " + head);
		System.out.println("Expected: "
				+ ListNode.buildList(new int[] { 1, 5, 2, 4, 3 }));
		System.out.println("Zipped  : " + zipList1(head));
		System.out.println();

		head = ListNode.buildList(new int[] { 1, 2, 3, 4 });
		System.out.println("Before  : " + head);
		System.out.println("Expected: "
				+ ListNode.buildList(new int[] { 1, 4, 2, 3 }));
		System.out.println("Zipped  : " + zipList1(head));
		System.out.println();

		head = ListNode.buildList(new int[] { 1, 2, 3, 4, 5, 6 });
		System.out.println("Before  : " + head);
		System.out.println("Expected: "
				+ ListNode.buildList(new int[] { 1, 6, 2, 5, 3, 4 }));
		System.out.println("Zipped  : " + zipList1(head));
		System.out.println();

		head = ListNode.buildList(new int[] { 1, 2 });
		System.out.println("Before  : " + head);
		System.out.println("Expected: "
				+ ListNode.buildList(new int[] { 1, 2 }));
		System.out.println("Zipped  : " + zipList1(head));
		System.out.println();

		head = ListNode.buildList(new int[] { 1, 2, 3, 4, 5, 6, 7 });
		System.out.println("Before  : " + head);
		System.out.println("Expected: "
				+ ListNode.buildList(new int[] { 1, 7, 2, 6, 3, 5, 4 }));
		System.out.println("Zipped  : " + zipList1(head));
		System.out.println();

	}
	
	private static void testZipList2() {
		ListNode head = null;
		head = ListNode.buildList(new int[] { 1, 2, 3 });
		System.out.println("Before  : " + head);
		System.out.println("Expected: "
				+ ListNode.buildList(new int[] { 1, 3, 2 }));
		System.out.println("Zipped  : " + zipList2(head));
		System.out.println();

		head = ListNode.buildList(new int[] { 1, 2, 3, 4, 5 });
		System.out.println("Before  : " + head);
		System.out.println("Expected: "
				+ ListNode.buildList(new int[] { 1, 5, 2, 4, 3 }));
		System.out.println("Zipped  : " + zipList2(head));
		System.out.println();

		head = ListNode.buildList(new int[] { 1, 2, 3, 4 });
		System.out.println("Before  : " + head);
		System.out.println("Expected: "
				+ ListNode.buildList(new int[] { 1, 4, 2, 3 }));
		System.out.println("Zipped  : " + zipList2(head));
		System.out.println();

		head = ListNode.buildList(new int[] { 1, 2, 3, 4, 5, 6 });
		System.out.println("Before  : " + head);
		System.out.println("Expected: "
				+ ListNode.buildList(new int[] { 1, 6, 2, 5, 3, 4 }));
		System.out.println("Zipped  : " + zipList2(head));
		System.out.println();

		head = ListNode.buildList(new int[] { 1, 2 });
		System.out.println("Before  : " + head);
		System.out.println("Expected: "
				+ ListNode.buildList(new int[] { 1, 2 }));
		System.out.println("Zipped  : " + zipList2(head));
		System.out.println();

		head = ListNode.buildList(new int[] { 1, 2, 3, 4, 5, 6, 7 });
		System.out.println("Before  : " + head);
		System.out.println("Expected: "
				+ ListNode.buildList(new int[] { 1, 7, 2, 6, 3, 5, 4 }));
		System.out.println("Zipped  : " + zipList2(head));
		System.out.println();

	}
}

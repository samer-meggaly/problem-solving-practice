public class MedianOfCircularList {

	public static CircularListNode findMedian(CircularListNode head) {
		if (head == null || head.next == head)
			return head;

		// find the first node of the list, the smallest one
		CircularListNode ptr = head.next;
		boolean foundFirst = head.val > ptr.val;
		while (!foundFirst && ptr != head) {
			if (ptr.val > ptr.next.val)
				foundFirst = true;
			ptr = ptr.next;
		}

		// search for the median element
		CircularListNode fastPtr = ptr;
		CircularListNode slowPtr = ptr;
		while (fastPtr.next != ptr && fastPtr.next.next != ptr) {
			fastPtr = fastPtr.next.next;
			slowPtr = slowPtr.next;
		}

		return slowPtr;
	}

	public static void main(String[] args) {
		CircularListNode list;
		int[] elems;
		int medianIdx;
		elems = new int[] { 1, 2, 3, 4, 5 };
		medianIdx = ((elems.length + 1) >> 1) - 1;
		list = buildCircularList(elems);
		System.out.println("Elements list is  : " + toString(elems));
		System.out.println("Random head set to: " + list.val);
		System.out.println("Median expected is: " + elems[medianIdx]);
		System.out.println("Median returned is: " + findMedian(list));
		System.out.println();

		elems = new int[] { 1, 2, 3, 4, 5, 6 };
		medianIdx = ((elems.length + 1) >> 1) - 1;
		list = buildCircularList(elems);
		System.out.println("Elements list is  : " + toString(elems));
		System.out.println("Random head set to: " + list.val);
		System.out.println("Median expected is: " + elems[medianIdx]);
		System.out.println("Median returned is: " + findMedian(list));
		System.out.println();

		elems = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		medianIdx = ((elems.length + 1) >> 1) - 1;
		list = buildCircularList(elems);
		System.out.println("Elements list is  : " + toString(elems));
		System.out.println("Random head set to: " + list.val);
		System.out.println("Median expected is: " + elems[medianIdx]);
		System.out.println("Median returned is: " + findMedian(list));
		System.out.println();

		elems = new int[] { 1, 2, 3, 4 };
		medianIdx = ((elems.length + 1) >> 1) - 1;
		list = buildCircularList(elems);
		System.out.println("Elements list is  : " + toString(elems));
		System.out.println("Random head set to: " + list.val);
		System.out.println("Median expected is: " + elems[medianIdx]);
		System.out.println("Median returned is: " + findMedian(list));
		System.out.println();

		elems = new int[] { 1, 2, 3 };
		medianIdx = ((elems.length + 1) >> 1) - 1;
		list = buildCircularList(elems);
		System.out.println("Elements list is  : " + toString(elems));
		System.out.println("Random head set to: " + list.val);
		System.out.println("Median expected is: " + elems[medianIdx]);
		System.out.println("Median returned is: " + findMedian(list));
		System.out.println();

		elems = new int[] { 1, 2 };
		medianIdx = ((elems.length + 1) >> 1) - 1;
		list = buildCircularList(elems);
		System.out.println("Elements list is  : " + toString(elems));
		System.out.println("Random head set to: " + list);
		System.out.println("Median expected is: " + elems[medianIdx]);
		System.out.println("Median returned is: " + findMedian(list));
		System.out.println();

		elems = new int[] { 1 };
		medianIdx = ((elems.length + 1) >> 1) - 1;
		list = buildCircularList(elems);
		System.out.println("Elements list is  : " + toString(elems));
		System.out.println("Random head set to: " + list);
		System.out.println("Median expected is: " + elems[medianIdx]);
		System.out.println("Median returned is: " + findMedian(list));
		System.out.println();

		elems = new int[] { 1, 2, 2, 2, 3 };
		medianIdx = ((elems.length + 1) >> 1) - 1;
		list = buildCircularList(elems);
		System.out.println("Elements list is  : " + toString(elems));
		System.out.println("Random head set to: " + list);
		System.out.println("Median expected is: " + elems[medianIdx]);
		System.out.println("Median returned is: " + findMedian(list));
		System.out.println();

		elems = new int[] { 2, 2, 2, 2 };
		medianIdx = ((elems.length + 1) >> 1) - 1;
		list = buildCircularList(elems);
		System.out.println("Elements list is  : " + toString(elems));
		System.out.println("Random head set to: " + list);
		System.out.println("Median expected is: " + elems[medianIdx]);
		System.out.println("Median returned is: " + findMedian(list));
		System.out.println();

		elems = new int[] { 1, 1, 1, 2, 2 };
		medianIdx = ((elems.length + 1) >> 1) - 1;
		list = buildCircularList(elems);
		System.out.println("Elements list is  : " + toString(elems));
		System.out.println("Random head set to: " + list);
		System.out.println("Median expected is: " + elems[medianIdx]);
		System.out.println("Median returned is: " + findMedian(list));
		System.out.println();

		elems = new int[] { 1, 1, 3, 3, 3 };
		medianIdx = ((elems.length + 1) >> 1) - 1;
		list = buildCircularList(elems);
		System.out.println("Elements list is  : " + toString(elems));
		System.out.println("Random head set to: " + list);
		System.out.println("Median expected is: " + elems[medianIdx]);
		System.out.println("Median returned is: " + findMedian(list));
		System.out.println();

	}

	private static CircularListNode buildCircularList(int[] elems) {
		CircularListNode head = null;
		CircularListNode last = null;
		for (int i = elems.length - 1; i >= 0; i--) {
			CircularListNode preLast = new CircularListNode(i, elems[i]);
			if (i == elems.length - 1)
				last = preLast;
			preLast.next = head;
			head = preLast;
		}

		// make it circular
		last.next = head;

		// now return a random ptr to the list
		CircularListNode ptr = head;
		for (int i = 0; i < (Math.random() * elems.length); i++) {
			ptr = ptr.next;
		}
		return ptr;
	}

	private static class CircularListNode {
		int idx;
		int val;
		CircularListNode next;

		public CircularListNode(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}

		@Override
		public String toString() {
			return "Val=" + val + ", Idx=" + idx;
		}
	}

	private static String toString(int[] Arr) {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (int i = 0; i < Arr.length; i++) {
			sb.append(Arr[i]);
			if (i < Arr.length - 1)
				sb.append(", ");
		}
		sb.append(" ]");
		return sb.toString();
	}
}

public class DutchFlagPartition {

	public static void quick3WayPartition(int[] A, int i) {

		if (A == null || i < 0 || i >= A.length)
			throw new IllegalArgumentException();

		// bring the pivot to the beginning
		int pivot = A[i];
		A[i] = A[0];
		A[0] = pivot;

		int le = 0;
		int gt = A.length - 1;
		int j = 1;
		while (j <= gt) {
			if (A[j] < pivot)
				swap(A, le++, j++);
			else if (A[j] > pivot)
				swap(A, j, gt--);
			else
				j++;
		}
	}

	private static void swap(int[] A, int a, int b) {
		int tmp = A[b];
		A[b] = A[a];
		A[a] = tmp;
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

	public static void main(String[] args) {
		int[] A;
		int i;
		A = new int[] { 3, 1, 2, 2, 1, 3, 2, 1 };
		i = (int) (Math.random() * A.length);
		System.out.println("Partition on: A[" + i + "]");
		System.out.println("Before      : " + toString(A));
		quick3WayPartition(A, i);
		System.out.println("After       : " + toString(A));

		A = new int[] { 1, 3, 5, 2, 6, 7, 8, 9, 5 };
		i = (int) (Math.random() * A.length);
		System.out.println("Partition on: A[" + i + "]");
		System.out.println("Before      : " + toString(A));
		quick3WayPartition(A, i);
		System.out.println("After       : " + toString(A));

		A = new int[] { 1, 1, 1, 1, 1, 1, 2, 0 };
		i = (int) (Math.random() * A.length);
		System.out.println("Partition on: A[" + i + "]");
		System.out.println("Before      : " + toString(A));
		quick3WayPartition(A, i);
		System.out.println("After       : " + toString(A));

		A = new int[] { 3, 1 };
		i = (int) (Math.random() * A.length);
		System.out.println("Partition on: A[" + i + "]");
		System.out.println("Before      : " + toString(A));
		quick3WayPartition(A, i);
		System.out.println("After       : " + toString(A));

		A = new int[] { 3 };
		i = (int) (Math.random() * A.length);
		System.out.println("Partition on: A[" + i + "]");
		System.out.println("Before      : " + toString(A));
		quick3WayPartition(A, i);
		System.out.println("After       : " + toString(A));

	}
}

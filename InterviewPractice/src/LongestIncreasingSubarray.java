public class LongestIncreasingSubarray {

	public static int[] lis1(int[] A) {
		if (A == null || A.length == 0)
			throw new IllegalArgumentException();

		int longest = 1;
		int[] indices = new int[2];
		indices[0] = 0;
		indices[1] = 0;
		int i = 0;
		int count = 1;
		while (i < A.length - 1) {
			if (A[i] < A[i + 1]) {
				count++;
			} else {
				if (count > longest) {
					longest = count;
					indices[0] = i - count + 1;
					indices[1] = i;
				}
				count = 1;
			}
			i++;
		}

		// following is to handle the whole array being the sub-array
		if (count > longest) {
			longest = count;
			indices[0] = i - count + 1;
			indices[1] = i;
		}
		return indices;
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
		A = new int[] { 2, 3, 1, 2, 3, 4, 2, 5, 1 };
		System.out.println("LIS for: " + toString(A));
		System.out.println("is     : [ " + 2 + ", " + 5 + " ]");
		System.out.println("Bounds : " + toString(lis1(A)));
		System.out.println();

		A = new int[] { 1, 2, 3, 4, 5, 6, 1 };
		System.out.println("LIS for: " + toString(A));
		System.out.println("is     : [ " + 0 + ", " + 5 + " ]");
		System.out.println("Bounds : " + toString(lis1(A)));
		System.out.println();

		A = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		System.out.println("LIS for: " + toString(A));
		System.out.println("is     : [ " + 0 + ", " + 6 + " ]");
		System.out.println("Bounds : " + toString(lis1(A)));
		System.out.println();

		A = new int[] { 3, 2, 1 };
		System.out.println("LIS for: " + toString(A));
		System.out.println("is     : [ " + 0 + ", " + 0 + " ]");
		System.out.println("Bounds : " + toString(lis1(A)));
		System.out.println();

		A = new int[] { 1, 1, 1, 2, 3, 4, 5, 6, 6 };
		System.out.println("LIS for: " + toString(A));
		System.out.println("is     : [ " + 2 + ", " + 7 + " ]");
		System.out.println("Bounds : " + toString(lis1(A)));
		System.out.println();

		A = new int[] { 2, 2, 2, 2 };
		System.out.println("LIS for: " + toString(A));
		System.out.println("is     : [ " + 0 + ", " + 0 + " ]");
		System.out.println("Bounds : " + toString(lis1(A)));
		System.out.println();

	}
}

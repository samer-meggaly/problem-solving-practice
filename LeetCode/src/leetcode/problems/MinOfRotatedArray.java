package leetcode.problems;

import java.util.Arrays;

public class MinOfRotatedArray {
	public class Solution {
		public int findMin(Integer[] num) {
			return solve2(num);
		}

		public int solve2(Integer[] num) {
			return solve2Aux(num, 0, num.length - 1);
		}

		private int solve2Aux(Integer[] arr, int lb, int ub) {
			if (lb >= ub || arr[lb] < arr[ub])
				return arr[lb];

			// int middle = (lb + ub) >> 1; will cause overflow if length is
			// MAX_INT, better is the following
			int middle = ((ub - lb) >> 1) + lb;

			// Check if element (mid+1) is minimum element. Consider
			// the cases like {1, 1, 0, 1}
			if (middle + 1 <= ub && arr[middle + 1] < arr[middle])
				return arr[middle + 1];

			// Check if mid itself is minimum element
			// the cases like {1, 1, 0, 1, 1}
			if (middle - 1 >= lb && arr[middle] < arr[middle - 1])
				return arr[middle];

			// This case causes O(n) time
			// the cases like {7, 7, 1, 7}
			if (arr[lb] == arr[middle] && arr[ub] == arr[middle])
				return Math.min(solve2Aux(arr, lb, middle - 1),
						solve2Aux(arr, middle + 1, ub));

			// Decide whether we need to go to left half or right half
			if (arr[middle] >= arr[lb])
				return solve2Aux(arr, middle + 1, ub);
			else
				return solve2Aux(arr, lb, middle);
		}

		// no duplicates support
		public int solve1(Integer[] num) {
			int lb = 0;
			int ub = num.length - 1;
			int middle = -1;
			while (lb <= ub) {
				// middle = (ub + lb) >> 1; will cause overflow if length is
				// MAX_INT, better is the following
				middle = ((ub - lb) >> 1) + lb;
				if (num[lb] < num[ub]) {
					middle = lb;
					break;
				} else if (num[middle] >= num[lb]) {
					lb = middle + 1;
				} else {
					ub = middle;
				}
			}
			return num[middle];
		}
	}

	public static void main(String[] args) {
		Solution solver = new MinOfRotatedArray().new Solution();
		Integer[] arr;
		arr = new Integer[] { 4, 5, 6, 7, 1, 2, 3 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 4, 5, 6, 1, 2, 3 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 4, 5, 1, 2, 3 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 7, 1, 2, 3 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 2, 3, 4, 5, 6, 7, 1 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 7, 1 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 1, 1, 1, 1, 1, 1 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 1, 2, 3, 4 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 7, 1, 7 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 7, 1, 1, 1, 1, 1 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 7, 1, 1 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 7, 1, 7, 7 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 7, 1, 7, 7, 7 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 7, 1, 2, 2, 2, 7 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 7, 1, 2, 2, 7, 7, 7 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 7, 8, 8, 8, 8, 8, 8, 1, 7, 7, 7 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 1, 2, 1 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 2, 2, 3, 1, 2, 2 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 5, 6, 1, 2, 3, 4 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 2, 2, 1, 2 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 1, 1, 2, 2, 3 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 1, 1, 1, 4, 4, 4, 4, 5, 1, 1 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 3, 3, 3, 3, 3, 3, 3, 3, 1, 2, 2, 3 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

		arr = new Integer[] { 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2 };
		System.out.println("List: " + Arrays.asList(arr));
		System.out.println("Min : " + solver.findMin(arr));
		System.out.println();

	}
}

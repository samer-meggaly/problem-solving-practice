package leetcode.problems;

import java.util.Arrays;

import leetcode.utils.LeetPrinter;

public class MedianOfTwoSortedArrays {

	public double findMedianSortedArraysNaive(int[] nums1, int[] nums2) {
		// O(N) naive solution.
		int sumLens = nums1.length + nums2.length;
		int m = sumLens >> 1;
		double median = new KthOrderStatistic().kthOrderStatisticNaive(nums1,
				nums2, m + 1); // Kth is 1-based not 0-based.
		if (sumLens % 2 == 0) {
			median += new KthOrderStatistic().kthOrderStatisticNaive(nums1,
					nums2, m); // Kth is 1-based not 0-based.
			median /= 2.0;
		}
		return median;
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		if (nums1.length == 0)
			return nums2[nums2.length >> 1];
		if (nums2.length == 0)
			return nums1[nums1.length >> 1];

		int med1 = nums1[nums1.length >> 1];
		int med2 = nums2[nums2.length >> 1];

		if (med1 < med2) {
			return findMedianSortedArrays(
					Arrays.copyOfRange(nums1, nums1.length / 2 + 1,
							nums1.length),
					Arrays.copyOf(nums2, nums2.length / 2 + 1));
		} else if (med2 < med1) {
			return findMedianSortedArrays(
					Arrays.copyOf(nums1, nums1.length / 2 + 1),
					Arrays.copyOfRange(nums2, nums2.length / 2 + 1,
							nums2.length));
		} else {
			return med2;
		}
	}

	public static void main(String[] args) {
		int[] nums1, nums2;
		double expected;
		nums1 = new int[] { 1, 3, 5 };
		nums2 = new int[] { 0, 2, 4, 6 };
		expected = 3;
		LeetPrinter.assertPrint(expected,
				new MedianOfTwoSortedArrays().findMedianSortedArrays(nums1,
						nums2),
				String.format("Case#1 >>> Expected: %.1f, but Found: ",
						expected));

		nums1 = new int[] { 1, 3, 5, 7 };
		nums2 = new int[] { 0, 2, 4, 6 };
		expected = 3.5;
		LeetPrinter.assertPrint(expected,
				new MedianOfTwoSortedArrays().findMedianSortedArrays(nums1,
						nums2),
				String.format("Case#2 >>> Expected: %.1f, but Found: ",
						expected));

		nums1 = new int[] { 1 };
		nums2 = new int[] { 0, 2, 4, 6 };
		expected = 2;
		LeetPrinter.assertPrint(expected,
				new MedianOfTwoSortedArrays().findMedianSortedArrays(nums1,
						nums2),
				String.format("Case#3 >>> Expected: %.1f, but Found: ",
						expected));

		nums1 = new int[] { 1 };
		nums2 = new int[] { 2 };
		expected = 1.5;
		LeetPrinter.assertPrint(expected,
				new MedianOfTwoSortedArrays().findMedianSortedArrays(nums1,
						nums2),
				String.format("Case#4 >>> Expected: %.1f, but Found: ",
						expected));

		nums1 = new int[] { 1, 5, 7 };
		nums2 = new int[] { 0, 2, 4 };
		expected = 3;
		LeetPrinter.assertPrint(expected,
				new MedianOfTwoSortedArrays().findMedianSortedArrays(nums1,
						nums2),
				String.format("Case#5 >>> Expected: %.1f, but Found: ",
						expected));

		nums1 = new int[] { 5, 5, 5 };
		nums2 = new int[] { 5, 5, 5, 5 };
		expected = 5;
		LeetPrinter.assertPrint(expected,
				new MedianOfTwoSortedArrays().findMedianSortedArrays(nums1,
						nums2),
				String.format("Case#6 >>> Expected: %.1f, but Found: ",
						expected));

		nums1 = new int[] { 3, 3, 3, 3 };
		nums2 = new int[] { 5, 5, 5, 5 };
		expected = 4;
		LeetPrinter.assertPrint(expected,
				new MedianOfTwoSortedArrays().findMedianSortedArrays(nums1,
						nums2),
				String.format("Case#7 >>> Expected: %.1f, but Found: ",
						expected));

		nums1 = new int[] { 1, 3, 5, 7, 9 };
		nums2 = new int[] { 2, 4, 6, 8, 10, 11 };
		expected = 5;
		LeetPrinter.assertPrint(expected,
				new MedianOfTwoSortedArrays().findMedianSortedArrays(nums1,
						nums2),
				String.format("Case#8 >>> Expected: %.1f, but Found: ",
						expected));

		System.out.println("Done Successfully");
	}

}

package leetcode.problems;

import leetcode.utils.LeetPrinter;

public class KthOrderStatistic {
	
	public int kthOrderStatisticNaive(int[] sorted1, int[] sorted2, int k) {
		if (k < 1 || k > sorted1.length + sorted2.length)
			throw new IndexOutOfBoundsException("Invalid k: " + k);

		int nums1Ptr = 0, nums2Ptr = 0, consumed = 0;
		int kthOrderStat = Integer.MAX_VALUE;
		while (nums1Ptr < sorted1.length && nums2Ptr < sorted2.length
				&& consumed < k) {

			consumed++;
			if (consumed == k)
				kthOrderStat = Math.min(sorted1[nums1Ptr], sorted2[nums2Ptr]);

			if (sorted1[nums1Ptr] < sorted2[nums2Ptr]) {
				nums1Ptr++;
			} else {
				nums2Ptr++;
			}
		}

		if (consumed < k) {
			if (nums1Ptr < sorted1.length)
				kthOrderStat = sorted1[nums1Ptr + k - consumed - 1];
			else // (nums2Ptr < sorted2.length)
				kthOrderStat = sorted2[nums2Ptr + k - consumed - 1];
		}

		return kthOrderStat;
	}

	public static void main(String[] args) {
		int[] nums1, nums2, merged;
		nums1 = new int[] { 1, 3, 5 };
		nums2 = new int[] { 0, 2, 4, 6 };
		merged = merge(nums1, nums2);
		for (int i = 0; i < merged.length; i++) {
			LeetPrinter.assertPrint(merged[i],
					new KthOrderStatistic().kthOrderStatisticNaive(nums1, nums2,
							i + 1),
					String.format("Case#1 >>> Expected: %d, but Found: ",
							merged[i]));
		}

		nums1 = new int[] { 1, 3, 5, 7 };
		nums2 = new int[] { 0, 2, 4, 6 };
		merged = merge(nums1, nums2);
		for (int i = 0; i < merged.length; i++) {
			LeetPrinter.assertPrint(merged[i],
					new KthOrderStatistic().kthOrderStatisticNaive(nums1, nums2,
							i + 1),
					String.format("Case#2 >>> Expected: %d, but Found: ",
							merged[i]));
		}

		nums1 = new int[] { 1 };
		nums2 = new int[] { 0, 2, 4, 6 };
		merged = merge(nums1, nums2);
		for (int i = 0; i < merged.length; i++) {
			LeetPrinter.assertPrint(merged[i],
					new KthOrderStatistic().kthOrderStatisticNaive(nums1, nums2,
							i + 1),
					String.format("Case#3 >>> Expected: %d, but Found: ",
							merged[i]));
		}

		nums1 = new int[] { 1 };
		nums2 = new int[] { 2 };
		merged = merge(nums1, nums2);
		for (int i = 0; i < merged.length; i++) {
			LeetPrinter.assertPrint(merged[i],
					new KthOrderStatistic().kthOrderStatisticNaive(nums1, nums2,
							i + 1),
					String.format("Case#4 >>> Expected: %d, but Found: ",
							merged[i]));
		}

		nums1 = new int[] { 1, 5, 7 };
		nums2 = new int[] { 0, 2, 3, 4, 4 };
		merged = merge(nums1, nums2);
		for (int i = 0; i < merged.length; i++) {
			LeetPrinter.assertPrint(merged[i],
					new KthOrderStatistic().kthOrderStatisticNaive(nums1, nums2,
							i + 1),
					String.format("Case#5 >>> Expected: %d, but Found: ",
							merged[i]));
		}

		nums1 = new int[] { 5, 5, 5 };
		nums2 = new int[] { 5, 5, 5, 5 };
		merged = merge(nums1, nums2);
		for (int i = 0; i < merged.length; i++) {
			LeetPrinter.assertPrint(merged[i],
					new KthOrderStatistic().kthOrderStatisticNaive(nums1, nums2,
							i + 1),
					String.format("Case#6 >>> Expected: %d, but Found: ",
							merged[i]));
		}

		nums1 = new int[] { 3, 3, 3, 3 };
		nums2 = new int[] { 5, 5, 5, 5 };
		merged = merge(nums1, nums2);
		for (int i = 0; i < merged.length; i++) {
			LeetPrinter.assertPrint(merged[i],
					new KthOrderStatistic().kthOrderStatisticNaive(nums1, nums2,
							i + 1),
					String.format("Case#7 >>> Expected: %d, but Found: ",
							merged[i]));
		}

		System.out.println("Done Successfully");
	}

	private static int[] merge(int[] nums1, int[] nums2) {
		int[] merged = new int[nums1.length + nums2.length];
		int nums1Ptr = 0, nums2Ptr = 0, mergePtr = 0;
		while (nums1Ptr < nums1.length && nums2Ptr < nums2.length) {
			if (nums1[nums1Ptr] < nums2[nums2Ptr]) {
				merged[mergePtr++] = nums1[nums1Ptr++];
			} else {
				merged[mergePtr++] = nums2[nums2Ptr++];
			}
		}

		while (nums1Ptr < nums1.length) {
			merged[mergePtr++] = nums1[nums1Ptr++];
		}
		while (nums2Ptr < nums2.length) {
			merged[mergePtr++] = nums2[nums2Ptr++];
		}

		return merged;
	}
}

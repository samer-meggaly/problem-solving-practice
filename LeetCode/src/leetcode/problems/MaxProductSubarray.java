package leetcode.problems;

import leetcode.utils.LeetPrinter;

public class MaxProductSubarray {

	// https://leetcode.com/problems/maximum-product-subarray/

	public int maxProductQuadratic(int[] nums) {
		// O(N^2) solution that gives TLE on LeetCode. This solution
		// brute-forces through all possible sub-arrays.
		int maxProduct = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			int accProduct = 1;
			for (int j = i; j < nums.length; j++) {
				accProduct *= nums[j];
				maxProduct = Math.max(maxProduct, accProduct);
			}
		}
		return maxProduct;
	}

	/*
	 * If all the elements are positives, then the max product of sub-array is
	 * the product of ALL of the elements in the original array.
	 * 
	 * If all the elements are negatives, then the max product of sub-array is
	 * also the product of ALL of the elements in the original array IF THERE
	 * COUNT IS EVEN (the more couples of negative numbers you add, the greater
	 * the product that you will get). If you have an ODD number of negative
	 * integers, then you will need to drop either the Left-Most number OR the
	 * Right-Most number from the product of all the elements.
	 * 
	 * Mix; adding positives between the negatives will only increase the
	 * overall product of, but will never break, the continuous sub-array.
	 * 
	 * Having Zeros 0 anywhere in the array, just splits the problem, because 0
	 * breaks any continuous sequence that you can get. So solve the problem
	 * before the zero, solve separately after the zero, then take
	 * MAX(MAX_BEFORE_ZERO, MAX_AFTER_ZERO, ZERO_IFSELF).
	 * 
	 * The solution to this problem is by scanning the array and accumulating
	 * the product of the elements from both left-to-right and right-to-left.
	 * The max product of a sub-array is the largest value that you will ever
	 * encounter during both scans.
	 * 
	 */
	public int maxProductTwoIterations(int[] nums) {
		// O(N) solution that passes on LeetCode.
		int maxProduct = Integer.MIN_VALUE;
		int prodAcc = 1;
		// Left to right pass
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				maxProduct = Math.max(maxProduct, 0);
				prodAcc = 1;
			} else {
				prodAcc *= nums[i];
				maxProduct = Math.max(maxProduct, prodAcc);
			}
		}
		prodAcc = 1;
		// Right to left pass
		for (int i = nums.length - 1; i > -1; i--) {
			if (nums[i] == 0) {
				maxProduct = Math.max(maxProduct, 0);
				prodAcc = 1;
			} else {
				prodAcc *= nums[i];
				maxProduct = Math.max(maxProduct, prodAcc);
			}
		}
		return maxProduct;
	}

	public int maxProduct(int[] nums) {
		// O(N) solution that passes on LeetCode. Doing both LTR and RTL passes
		// in a single for-loop.
		int maxProduct = Integer.MIN_VALUE;
		int leftProdAcc = 1;
		int rightProdAcc = 1;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				maxProduct = Math.max(maxProduct, 0);
				leftProdAcc = 1;
			} else {
				leftProdAcc *= nums[i];
				maxProduct = Math.max(maxProduct, leftProdAcc);
			}

			int rightI = nums.length - i - 1;
			if (nums[rightI] == 0) {
				maxProduct = Math.max(maxProduct, 0);
				rightProdAcc = 1;
			} else {
				rightProdAcc *= nums[rightI];
				maxProduct = Math.max(maxProduct, rightProdAcc);
			}
		}
		return maxProduct;
	}

	public static void main(String[] args) {
		int[] nums;
		int maxProduct;

		nums = new int[] { -2 };
		maxProduct = -2;
		LeetPrinter.assertPrint(maxProduct,
				new MaxProductSubarray().maxProduct(nums),
				"Case#1 >>> Expected: " + maxProduct + ", but found: ");
		nums = new int[] { -2, -1, 1, 1, -3 };
		maxProduct = 3;
		LeetPrinter.assertPrint(maxProduct,
				new MaxProductSubarray().maxProduct(nums),
				"Case#2 >>> Expected: " + maxProduct + ", but found: ");
		nums = new int[] { 5, 3, -1, 4 };
		maxProduct = 15;
		LeetPrinter.assertPrint(maxProduct,
				new MaxProductSubarray().maxProduct(nums),
				"Case#3 >>> Expected: " + maxProduct + ", but found: ");
		nums = new int[] { 3, 3, -1, 4, 0, 10 };
		maxProduct = 10;
		LeetPrinter.assertPrint(maxProduct,
				new MaxProductSubarray().maxProduct(nums),
				"Case#4 >>> Expected: " + maxProduct + ", but found: ");
		nums = new int[] { 6, 3, 1, 1, 1, 0, 10 };
		maxProduct = 18;
		LeetPrinter.assertPrint(maxProduct,
				new MaxProductSubarray().maxProduct(nums),
				"Case#5 >>> Expected: " + maxProduct + ", but found: ");
		nums = new int[] { -1, 0, -1 };
		maxProduct = 0;
		LeetPrinter.assertPrint(maxProduct,
				new MaxProductSubarray().maxProduct(nums),
				"Case#6 >>> Expected: " + maxProduct + ", but found: ");
		nums = new int[] { -2, 6, 1, -3, 1, -1, 1, 0, 10 };
		maxProduct = 36;
		LeetPrinter.assertPrint(maxProduct,
				new MaxProductSubarray().maxProduct(nums),
				"Case#7 >>> Expected: " + maxProduct + ", but found: ");
		System.out.println("Done Successfully");
	}

}

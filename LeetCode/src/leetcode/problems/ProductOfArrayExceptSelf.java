package leetcode.problems;

import java.util.Arrays;

import leetcode.utils.LeetPrinter;

public class ProductOfArrayExceptSelf {

	// https://leetcode.com/problems/product-of-array-except-self/

	public int[] productExceptSelf(int[] nums) {
		// 2ms on LeetCode.
		if (nums == null || nums.length < 2)
			return nums;

		int[] finalProds = new int[nums.length];
		// first pass from left to right
		finalProds[0] = 1;
		for (int i = 1; i < nums.length; i++)
			finalProds[i] = nums[i - 1] * finalProds[i - 1];
		// second pass from right to left
		int prodFromRight = nums[nums.length - 1];
		for (int i = nums.length - 2; i > -1; i--) {
			finalProds[i] *= prodFromRight;
			prodFromRight *= nums[i];
		}

		return finalProds;
	}

	public int[] productExceptSelfWithDivision(int[] nums) {
		// 2ms on LeetCode.
		if (nums == null || nums.length < 2)
			return nums;

		int allProducts = 1;
		int countZeros = 0;
		for (int i : nums) {
			if (i == 0)
				countZeros++;
			else
				allProducts *= i;
		}

		if (countZeros > 1)
			return new int[nums.length];
		else if (countZeros == 1) {
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == 0)
					nums[i] = allProducts;
				else
					nums[i] = 0;
			}
		} else {
			for (int i = 0; i < nums.length; i++) {
				nums[i] = allProducts / nums[i];
			}
		}

		return nums;
	}

	public int[] productExceptSelf_1NSpace(int[] nums) {
		// 5ms on LeetCode.
		if (nums == null || nums.length < 2)
			return nums;

		int[] prodBefore = new int[nums.length];
		int[] prodAfter = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			if (i == 0) {
				prodBefore[i] = nums[i];
				prodAfter[nums.length - i - 1] = nums[nums.length - i - 1];
			} else {
				prodBefore[i] = nums[i] * prodBefore[i - 1];
				prodAfter[nums.length - i - 1] = nums[nums.length - i - 1]
						* prodAfter[nums.length - i];
			}
		}

		for (int i = 1; i < nums.length - 1; i++) {
			nums[i] = prodBefore[i - 1] * prodAfter[i + 1];
		}

		nums[0] = prodAfter[1];
		nums[nums.length - 1] = prodBefore[nums.length - 2];
		return nums;
	}

	public static void main(String[] args) {
		int[] nums, expected;
		nums = new int[] {};
		expected = new int[] {};
		LeetPrinter.assertArraysPrint(expected,
				new ProductOfArrayExceptSelf().productExceptSelf(nums),
				String.format("Expected: %s\nFound   : ",
						Arrays.toString(expected)));

		nums = new int[] { 0, 0 };
		expected = new int[] { 0, 0 };
		LeetPrinter.assertArraysPrint(expected,
				new ProductOfArrayExceptSelf().productExceptSelf(nums),
				String.format("Expected: %s\nFound   : ",
						Arrays.toString(expected)));

		nums = new int[] { 2, 3 };
		expected = new int[] { 3, 2 };
		LeetPrinter.assertArraysPrint(expected,
				new ProductOfArrayExceptSelf().productExceptSelf(nums),
				String.format("Expected: %s\nFound   : ",
						Arrays.toString(expected)));

		nums = new int[] { 1, 2, 3, 4 };
		expected = new int[] { 24, 12, 8, 6 };
		LeetPrinter.assertArraysPrint(expected,
				new ProductOfArrayExceptSelf().productExceptSelf(nums),
				String.format("Expected: %s\nFound   : ",
						Arrays.toString(expected)));

		nums = new int[] { 1, 2, 0, 3, 4 };
		expected = new int[] { 0, 0, 24, 0, 0 };
		LeetPrinter.assertArraysPrint(expected,
				new ProductOfArrayExceptSelf().productExceptSelf(nums),
				String.format("Expected: %s\nFound   : ",
						Arrays.toString(expected)));

		nums = new int[] { 0, 2, 3, 0 };
		expected = new int[] { 0, 0, 0, 0 };
		LeetPrinter.assertArraysPrint(expected,
				new ProductOfArrayExceptSelf().productExceptSelf(nums),
				String.format("Expected: %s\nFound   : ",
						Arrays.toString(expected)));
		System.out.println("Done Successfully");
	}

}

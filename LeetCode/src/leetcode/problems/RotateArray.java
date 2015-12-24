package leetcode.problems;

import leetcode.utils.LeetPrinter;

public class RotateArray {
	
	// https://leetcode.com/problems/rotate-array/

	public void rotateNotInPlace(Integer[] nums, int k) {
		k = k % nums.length;
		int[] temp = new int[nums.length];
		for (int i = 0; i < nums.length; i++)
			temp[(i + k) % nums.length] = nums[i];
		for (int i = 0; i < nums.length; i++)
			nums[i] = temp[i];
	}

	public void rotate(Integer[] nums, int k) {
		k = k % nums.length;
		if (nums == null || nums.length == 0 || k == 0)
			return;
		int startI = 0;
		int startIVal = nums[startI];
		int i = startI;
		for (int j = 0; j < nums.length; j++) {
			int nextI = (i - k + nums.length) % nums.length;
			if (nextI == startI) {
				nums[i] = startIVal;
				startI++;
				startIVal = nums[startI];
				i = startI;
			} else {
				nums[i] = nums[nextI];
				i = nextI;
			}
		}
	}

	public static void main(String[] args) {
		Integer[] nums;
		int k;

		k = 0;
		nums = new Integer[] { 1 };
		new RotateArray().rotate(nums, k);
		LeetPrinter.assertArraysPrint(new Integer[] { 1 }, nums, "Case 1: ");
		k = 3;
		nums = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
		new RotateArray().rotate(nums, k);
		LeetPrinter.assertArraysPrint(new Integer[] { 5, 6, 7, 1, 2, 3, 4 },
				nums, "Case 2: ");
		k = 1;
		nums = new Integer[] { 1, 2, 3 };
		new RotateArray().rotate(nums, k);
		LeetPrinter.assertArraysPrint(new Integer[] { 3, 1, 2 }, nums,
				"Case 3: ");
		k = 3;
		nums = new Integer[] { 1, 2, 3, 4, 5, 6 };
		new RotateArray().rotate(nums, k);
		LeetPrinter.assertArraysPrint(new Integer[] { 4, 5, 6, 1, 2, 3 }, nums,
				"Case 4: ");
		k = 3;
		nums = new Integer[] { 1, 2 };
		new RotateArray().rotate(nums, k);
		LeetPrinter.assertArraysPrint(new Integer[] { 2, 1 }, nums, "Case 5: ");
		System.out.println("Done Successfully");
	}
}

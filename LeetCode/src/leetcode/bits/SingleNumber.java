package leetcode.bits;

import java.util.Arrays;

public class SingleNumber {
	/*
	 * https://leetcode.com/problems/single-number/
	 * https://leetcode.com/problems/single-number-ii/
	 * https://leetcode.com/problems/single-number-iii/
	 */

	public int singleNumber(int[] nums) {
		int unique = 0;
		for (int i = 0; i < nums.length; i++)
			unique ^= nums[i];
		return unique;
	}

	public int singleNumberII(int[] nums) {
		int ones = 0;
		int twos = 0;
		for (int n : nums) {
			twos |= (ones & n);
			ones ^= n;
			int commonBits = ones & twos;
			ones &= (~commonBits);
			twos &= (~commonBits);
		}

		return ones;
	}

	public int singleNumberII_V1(int[] nums) {
		int single = 0;
		for (int shift = 0; shift < 32; shift++) {
			int bitCount = 0;
			int pot = (1 << shift);
			for (int i = 0; i < nums.length; i++) {
				if ((nums[i] & pot) != 0)
					bitCount++;
			}
			if (bitCount % 3 == 1)
				single |= pot;
		}

		return single;
	}

	public int[] singleNumberIII(int[] nums) {
		int allXor = 0;
		for (int n : nums)
			allXor ^= n;

		int smallestPot = (allXor & -allXor);
		int hasSmallestPotXor = 0;
		for (int n : nums) {
			if ((n & smallestPot) != 0)
				hasSmallestPotXor ^= n;
		}

		return new int[] { hasSmallestPotXor, hasSmallestPotXor ^ allXor };
	}

	public static void main(String[] args) {
		System.out.println(new SingleNumber() // 5
				.singleNumber(new int[] { 1, 4, 5, 1, 2, 4, 2 }));
		System.out.println(new SingleNumber() // 5
				.singleNumberII(new int[] { 1, 4, 5, 1, 1, 4, 4 }));
		System.out.println(Arrays.toString(new SingleNumber() // [3,5] or [5,3]
				.singleNumberIII(new int[] { 1, 4, 5, 1, 4, 3 })));
	}
}

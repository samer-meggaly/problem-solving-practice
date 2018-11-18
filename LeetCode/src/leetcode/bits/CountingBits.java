package leetcode.bits;

import java.util.Arrays;

public class CountingBits {

	public int[] countBits_V1(int num) {
		// pass in 18 ms.
		int[] countBits = new int[num + 1];
		for (int i = 1; i <= num; i++) {
			int mostSignificantBit = (int) Math
					.floor(Math.log10(i) / Math.log10(2));
			int mostSignificantPOT = 1 << mostSignificantBit;
			countBits[i] = 1 + countBits[i & (~mostSignificantPOT)];
		}
		return countBits;
	}

	public int[] countBits(int num) {
		// pass in 3 ms.
		int[] countBits = new int[num + 1];
		int pot = 1;
		for (int i = 1; i <= num; i++) {
			if (pot << 1 == i)
				pot <<= 1;
			countBits[i] = 1 + countBits[i & (~pot)];
		}
		return countBits;
	}

	public static void main(String[] args) {
		// [0]
		System.out.println(Arrays.toString(new CountingBits().countBits(0)));
		// [0,1]
		System.out.println(Arrays.toString(new CountingBits().countBits(1)));
		// [0,1,1]
		System.out.println(Arrays.toString(new CountingBits().countBits(2)));
		// [0,1,1,2]
		System.out.println(Arrays.toString(new CountingBits().countBits(3)));
		// [0,1,1,2,1,2]
		System.out.println(Arrays.toString(new CountingBits().countBits(5)));
		// [0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1]
		System.out.println(Arrays.toString(new CountingBits().countBits(16)));
	}
}

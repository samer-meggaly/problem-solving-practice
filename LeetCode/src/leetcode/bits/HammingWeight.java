package leetcode.bits;

public class HammingWeight {

	// https://leetcode.com/problems/number-of-1-bits/

	public int hammingWeight_V1(int n) {
		int count = 0;
		for (int i = 0; i < 32; i++)
			count += (n & (1 << i)) >>> i;
		return count;
	}

	public int hammingWeight_V2(int n) {
		int count = 0;
		for (; n != 0; n >>>= 1)
			count += n & 1;
		return count;
	}

	public int hammingWeight_V3(int n) {
		int count = 0;
		for (; n != 0; n -= (n & -n))
			count++;
		return count;
	}

	public int hammingWeight(int n) {
		int count = 0;
		for (; n != 0; n = (n & (n - 1)))
			count++;
		return count;
	}

	public static void main(String[] args) {
		System.out.println(
				new HammingWeight().hammingWeight_V1(Integer.MIN_VALUE));
		System.out.println(
				new HammingWeight().hammingWeight_V1(Integer.MAX_VALUE));
		System.out.println(
				new HammingWeight().hammingWeight_V2(Integer.MIN_VALUE));
		System.out.println(
				new HammingWeight().hammingWeight_V2(Integer.MAX_VALUE));
		System.out.println(
				new HammingWeight().hammingWeight_V3(Integer.MIN_VALUE));
		System.out.println(
				new HammingWeight().hammingWeight_V3(Integer.MAX_VALUE));
		System.out
				.println(new HammingWeight().hammingWeight(Integer.MIN_VALUE));
		System.out
				.println(new HammingWeight().hammingWeight(Integer.MAX_VALUE));
	}
}

package leetcode.bits;

public class PowerOf {

	/*
	 * https://leetcode.com/problems/power-of-two/
	 * https://leetcode.com/problems/power-of-three/
	 * https://leetcode.com/problems/power-of-four/
	 * https://leetcode.com/problems/reverse-bits/
	 */

	public boolean isPowerOfTwo(int n) {
		return n > 0 && ((n & (n - 1)) == 0);
	}

	public boolean isPowerOfThree(int n) {
		double pow = Math.log10(n) / Math.log10(3);
		return pow == ((int) pow) && n == ((int) Math.pow(3, (int) pow));
	}

	public boolean isPowerOfThree_V1(int n) {
		for (; n > 1; n /= 3.0) {
			if (n % 3 != 0)
				return false;
		}

		return n == 1;
	}

	public boolean isPowerOfFour(int num) {
		double sqrtNum = Math.sqrt(num);
		return isPowerOfTwo(num) && (sqrtNum % 1 == 0)
				&& isPowerOfTwo((int) sqrtNum);
	}

	public int reverseBits(int n) {
		int reverse = 0;
		for (int i = 0; i < 32; i++) {
			reverse <<= 1;
			reverse |= (n & 1);
			n >>>= 1;
		}

		return reverse;
	}

	public static void main(String[] args) {
		System.out.println(new PowerOf().isPowerOfThree(27));
		System.out.println(new PowerOf().reverseBits(43261596));
		System.out.println(new PowerOf().reverseBits(0));
		System.out.println(new PowerOf().reverseBits(Integer.MIN_VALUE));
	}
}

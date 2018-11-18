import java.util.Scanner;

public class ExtremelyDangerousVirus {

	private static final int MOD = (int) (1E9 + 7);

	private static long modPow(long a, long x, long p) {
		// return a^x mod p
		long res = 1L;
		while (x > 0) {
			if ((x & 1) != 0)
				res = (res * a) % p;
			a = (a * a) % p;
			x >>>= 1;
		}

		return res;
	}

	private static long modInverse(long a, long p) {
		// return modular multiplicative inverse of : a mod p, assuming p is
		// prime
		return modPow(a, p - 2, p);
	}

	private static long modC(long m, long k) {
		// return C(n,k) mod p, assuming p is prime
		long numerator = 1L; // n*(n-1)* ... * (n-k+1)
		for (long i = 0L; i < k; i++)
			numerator = (numerator * ((m - i) % MOD)) % MOD;

		long denominator = 1; // k!
		for (long i = 1L; i <= k; i++)
			denominator = (denominator * (i % MOD)) % MOD;

		long denomModInv = modInverse(denominator, MOD);
		return (numerator * denomModInv) % MOD;
	}

	@SuppressWarnings("unused")
	private static long expectedCells_V1(long a, long b, long t) {
		// TLE
		long expectedCells = 0L;
		long asInc = 1L;
		long bsDec = modPow(b, t, MOD);
		long bInv = modInverse(b, MOD);
		for (long k = 0L; k <= t; k++) {
			long combination = modC(t, k);
			combination *= asInc;
			combination %= MOD;
			combination *= bsDec;
			combination %= MOD;
			expectedCells += combination;
			expectedCells %= MOD;
			asInc = (asInc * a) % MOD;
			bsDec = (bsDec * bInv) % MOD;
		}
		
		long _2Tot = modPow(2, t, MOD);
		expectedCells *= modInverse(_2Tot, MOD);

		return expectedCells % MOD;
	}
	
	private static long expectedCells(long a, long b, long t) {
		long half = (a + b) % MOD;
		half >>>= 1;
		return modPow(half, t, MOD);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(expectedCells(scanner.nextLong(), scanner.nextLong(),
				scanner.nextLong()));
		scanner.close();
	}
}

package hackerrank.dp;

import java.util.Arrays;
import java.util.Scanner;

public class GridWalking {

	// Correct solution that passes on HackerRank.
	// 2 DP problems + 1 Combinatorial problem with hint of Number Theory :)))

	private static int N = -1;
	private static int[] Ds = null;
	private static final int MOD = (int) (1E9 + 7);
	private static final int MAX_M = 300;
	private static final long[] MOD_FACT = new long[MAX_M + 1];

	private static void fillModFactorial() {
		MOD_FACT[0] = 1;
		for (int m = 1; m <= MAX_M; m++) {
			MOD_FACT[m] = (m * MOD_FACT[m - 1]) % MOD;
		}
	}

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
		// return modular multiplicative inverse of : a mod p, assuming p is prime
		return modPow(a, p - 2, p);
	}

	private static long modC(int m, int k, int p) {
		long kFactmkFact = (MOD_FACT[k] * MOD_FACT[m - k]) % MOD;
		long inv = modInverse(kFactmkFact, MOD);
		return (MOD_FACT[m] * inv) % MOD;
	}

	private static long[] dWalks(int M, int D, int s) {
		long[][] dWalks = new long[M + 1][D + 1];
		for (int x = 1; x <= D; x++)
			dWalks[0][x] = 1; // with 0 steps you cannot go anywhere so you have
								// only 1 unique move (stand still).

		// Next compute unique walks using <m> steps starting at location <x>.
		/*
		 * walks(given-steps= m, starting-at= x) =
		 * walks(given-steps= m-1, starting-at= x-1) +
		 * walks(given-steps= m-1, starting-at= x+1)
		 */
		for (int m = 1; m <= M; m++) {
			for (int x = 1; x <= D; x++) {
				if (x - 1 > 0)
					dWalks[m][x] += dWalks[m - 1][x - 1];
				if (x + 1 <= D)
					dWalks[m][x] += dWalks[m - 1][x + 1];
				dWalks[m][x] %= MOD;
			}
		}
		/*
		 * Now dWalks array holds the total number of unique walks that you can
		 * perform using <m> steps and starting at location <x> for all <m> in
		 * [0, M] and for all <x> along the dimension in [1, D].
		 */

		long[] sDWalks = new long[M + 1];
		for (int m = 0; m <= M; m++) {
			sDWalks[m] = dWalks[m][s];
		}
		/*
		 * Now we return the number of unique walks for all <m> in [0, M]
		 * starting from the specific start location <s>.
		 */
		return sDWalks;
	}

	private static long[][] memo = null;

	private static long combineWalks(int steps, int d) {
		if (steps == 0)
			return 1L;
		if (d == N - 1)
			return DsWalks[d][steps];

		if (memo[d][steps] == -1) {
			long combineWalks = 0L;
			for (int k = 0; k <= steps; k++) {
				long kWalks = (modC(steps, k, MOD) * DsWalks[d][k]) % MOD;
				kWalks *= combineWalks(steps - k, d + 1) % MOD;
				combineWalks += (kWalks % MOD);
				combineWalks %= MOD;
			}
			memo[d][steps] = combineWalks;
		}
		return memo[d][steps];
	}

	private static long[][] DsWalks = null;

	private static long countWalks(int M, int[] initLoc) {
		DsWalks = new long[N][M + 1];
		memo = new long[N][M + 1];
		for (int d = 0; d < N; d++) {
			DsWalks[d] = dWalks(M, Ds[d], initLoc[d]);
			Arrays.fill(memo[d], -1);
		}
		return combineWalks(M, 0);
	}

	public static void main(String[] args) {
		fillModFactorial();
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		for (int t = 0; t < T; t++) {
			N = scanner.nextInt();
			int M = scanner.nextInt();
			int[] initLoc = new int[N];
			for (int d = 0; d < N; d++)
				initLoc[d] = scanner.nextInt();
			Ds = new int[N];
			for (int d = 0; d < N; d++)
				Ds[d] = scanner.nextInt();

			System.out.println(countWalks(M, initLoc));
		}
		scanner.close();
	}
}
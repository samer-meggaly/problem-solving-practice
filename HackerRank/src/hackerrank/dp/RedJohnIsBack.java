package hackerrank.dp;

import java.util.Scanner;

public class RedJohnIsBack {

	private static final int MAX_N = 40;
	private static final int[][] N_CHOOSE_K = new int[MAX_N + 1][MAX_N + 1];

	private static void computeCombinations() {
		for (int n = 0; n <= MAX_N; n++)
			N_CHOOSE_K[n][0] = 1;
		for (int n = 1; n <= MAX_N; n++) {
			for (int k = 1; k <= n; k++) {
				N_CHOOSE_K[n][k] = N_CHOOSE_K[n - 1][k]
						+ N_CHOOSE_K[n - 1][k - 1];
			}
		}
	}

	private static final int[] COUNT_PRIMES = new int[1 << 18];

	private static void countPrimes() {
		boolean[] isNotPrime = new boolean[COUNT_PRIMES.length];
		isNotPrime[0] = true;
		isNotPrime[1] = true;
		for (int n = 2; n < isNotPrime.length; n++) {
			COUNT_PRIMES[n] = COUNT_PRIMES[n - 1];

			if (!isNotPrime[n]) {
				// <n> is indeed a prime.
				COUNT_PRIMES[n]++;
				int nn = n * 2;
				while (nn < isNotPrime.length) {
					isNotPrime[nn] = true;
					nn += n;
				}
			}
		}
	}

	private static int computeP(int N) {
		int max44Blocks = N / 4;
		int countConfigsM = 0;
		for (int k = 0; k <= max44Blocks; k++) {
			int remainingN = N - (k * 3);
			countConfigsM += N_CHOOSE_K[remainingN][k];
		}

		return COUNT_PRIMES[countConfigsM];
	}

	public static void main(String[] args) {
		computeCombinations();
		countPrimes();
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		for (int t = 0; t < T; t++) {
			System.out.println(computeP(scanner.nextInt()));
		}
		scanner.close();
	}
}
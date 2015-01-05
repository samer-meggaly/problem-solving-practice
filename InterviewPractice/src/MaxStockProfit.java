import java.util.Arrays;

public class MaxStockProfit {

	public static double maxProfit1(double[] S) {
		// O(N * N)
		if (S == null || S.length < 2)
			throw new IllegalArgumentException("Invalid S content size");

		double maxProfit = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < S.length; i++) {
			for (int j = i + 1; j < S.length; j++)
				maxProfit = Math.max(maxProfit, S[j] - S[i]);
		}

		return maxProfit;
	}

	public static double maxProfit2(double[] S) {
		// O(N lg N)
		if (S == null || S.length < 2)
			throw new IllegalArgumentException("Invalid S content size");

		return maxProfit2Aux(S, 0, S.length - 1);
	}

	private static double maxProfit2Aux(double[] S, int lo, int hi) {
		if (hi - lo < 1)
			throw new IllegalArgumentException("Invalid boundries: " + lo
					+ " & " + hi);
		if (hi - lo == 1)
			return S[hi] - S[lo];

		int mid = ((hi - lo) / 2) + lo;
		double maxProfitLeft = Double.NEGATIVE_INFINITY;
		double maxProfitRight = Double.NEGATIVE_INFINITY;
		if (mid - lo >= 2)
			maxProfitLeft = maxProfit2Aux(S, lo, mid);
		if (hi - mid - 1 >= 2)
			maxProfitRight = maxProfit2Aux(S, mid + 1, hi);

		double minLeft = Double.POSITIVE_INFINITY;
		for (int i = lo; i <= mid; i++)
			minLeft = Math.min(minLeft, S[i]);
		double maxRight = Double.NEGATIVE_INFINITY;
		for (int i = mid + 1; i <= hi; i++)
			maxRight = Math.max(maxRight, S[i]);

		return Math.max(maxRight - minLeft,
				Math.max(maxProfitLeft, maxProfitRight));
	}

	public static double maxProfit3(double[] S) {
		// O(N)
		if (S == null || S.length < 2)
			throw new IllegalArgumentException("Invalid S content size");

		double maxProfitSoFar = Double.NEGATIVE_INFINITY;
		double minSoFar = S[0];
		for (int d = 1; d < S.length; d++) {
			maxProfitSoFar = Math.max(maxProfitSoFar, S[d] - minSoFar);
			minSoFar = Math.min(minSoFar, S[d]);
		}

		return maxProfitSoFar;
	}

	public static void main(String[] args) {
		double[] S = null;
		S = new double[] { 5, 4, 3, 2, 1 };
		System.out.println("Expected: " + -1.0);
		System.out.println("Method 1: "
				+ maxProfit1(Arrays.copyOf(S, S.length)));
		System.out.println("Method 2: "
				+ maxProfit2(Arrays.copyOf(S, S.length)));
		System.out.println("Method 3: "
				+ maxProfit3(Arrays.copyOf(S, S.length)));
		System.out.println();

		S = new double[] { 1, 2, 3, 4, 5 };
		System.out.println("Expected: " + 4.0);
		System.out.println("Method 1: "
				+ maxProfit1(Arrays.copyOf(S, S.length)));
		System.out.println("Method 2: "
				+ maxProfit2(Arrays.copyOf(S, S.length)));
		System.out.println("Method 3: "
				+ maxProfit3(Arrays.copyOf(S, S.length)));
		System.out.println();

		S = new double[] { 1, 101 };
		System.out.println("Expected: " + 100.0);
		System.out.println("Method 1: "
				+ maxProfit1(Arrays.copyOf(S, S.length)));
		System.out.println("Method 2: "
				+ maxProfit2(Arrays.copyOf(S, S.length)));
		System.out.println("Method 3: "
				+ maxProfit3(Arrays.copyOf(S, S.length)));
		System.out.println();

	}
}

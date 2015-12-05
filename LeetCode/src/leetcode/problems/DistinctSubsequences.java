package leetcode.problems;

import static leetcode.utils.LeetPrinter.assertPrint;

import java.util.Arrays;

public class DistinctSubsequences {

	// https://leetcode.com/problems/distinct-subsequences/

	private int[][] memo;

	public int numDistinctrecursive(String s, String t) {
		// StackOverflowError on LeetCode.
		memo = new int[s.length()][t.length()];
		for (int i = 0; i < memo.length; i++)
			Arrays.fill(memo[i], -1);
		return recurse(s, 0, t, 0);
	}

	private int recurse(String s, int sPtr, String t, int tPtr) {
		if (s.length() - sPtr < t.length() - tPtr)
			return 0;

		if (tPtr == t.length())
			return 1;

		if (memo[sPtr][tPtr] == -1) {
			int seqs = recurse(s, sPtr + 1, t, tPtr);
			if (s.charAt(sPtr) == t.charAt(tPtr)) {
				seqs += recurse(s, sPtr + 1, t, tPtr + 1);
			}

			memo[sPtr][tPtr] = seqs;
		}

		return memo[sPtr][tPtr];
	}

	public int numDistinctIter(String s, String t) {
		// Tabular solution that implements the same recursive formula above.
		// passes on LeetCode.
		int[][] tabular = new int[s.length() + 1][t.length() + 1];
		for (int r = 0; r < tabular.length; r++)
			tabular[r][0] = 1;

		for (int r = 1; r < tabular.length; r++) {
			for (int c = 1; c < tabular[0].length; c++) {
				tabular[r][c] = tabular[r - 1][c];
				if (s.charAt(r - 1) == t.charAt(c - 1))
					tabular[r][c] += tabular[r - 1][c - 1];
			}
		}

		return tabular[s.length()][t.length()];
	}

	public int numDistinct(String s, String t) {
		// Tabular solution that implements the same recursive formula above.
		// passes on LeetCode.
		// Some space optimization.
		int[] tabular = new int[t.length() + 1];
		tabular[0] = 1;

		for (int r = 1; r <= s.length(); r++) {
			int[] nextTabular = new int[tabular.length];
			nextTabular[0] = 1; // EMPTY-STRING t matches 1 sequence in s.
			for (int c = 1; c <= t.length(); c++) {
				nextTabular[c] = tabular[c];
				if (s.charAt(r - 1) == t.charAt(c - 1))
					nextTabular[c] += tabular[c - 1];
			}
			tabular = nextTabular;
		}

		return tabular[t.length()];
	}

	public static void main(String[] args) {
		String s, t;

		s = "babgbag";
		t = "bag";
		assertPrint(5, new DistinctSubsequences().numDistinct(s, t),
				String.format("S=[%s], T=[%s]: ", s, t));
		s = "rabbbit";
		t = "rabbit";
		assertPrint(3, new DistinctSubsequences().numDistinct(s, t),
				String.format("S=[%s], T=[%s]: ", s, t));
		s = "abcd";
		t = "abcd";
		assertPrint(1, new DistinctSubsequences().numDistinct(s, t),
				String.format("S=[%s], T=[%s]: ", s, t));
		s = "bab";
		t = "bag";
		assertPrint(0, new DistinctSubsequences().numDistinct(s, t),
				String.format("S=[%s], T=[%s]: ", s, t));
		s = "ab";
		t = "abc";
		assertPrint(0, new DistinctSubsequences().numDistinct(s, t),
				String.format("S=[%s], T=[%s]: ", s, t));
		s = "";
		t = "";
		assertPrint(1, new DistinctSubsequences().numDistinct(s, t),
				String.format("S=[%s], T=[%s]: ", s, t));
		s = "";
		t = "bag";
		assertPrint(0, new DistinctSubsequences().numDistinct(s, t),
				String.format("S=[%s], T=[%s]: ", s, t));
		s = "bab";
		t = "";
		assertPrint(1, new DistinctSubsequences().numDistinct(s, t),
				String.format("S=[%s], T=[%s]: ", s, t));
		System.out.println("Done Successfully");
	}

}

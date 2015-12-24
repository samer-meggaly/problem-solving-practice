package leetcode.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import leetcode.utils.LeetPrinter;

public class PalindromePartitioning {

	/*
	 * https://leetcode.com/problems/palindrome-partitioning/
	 * https://leetcode.com/problems/palindrome-partitioning-ii/
	 */

	private char[] sChars = null;
	private int[][] memo;

	public int minCutRecursive(String s) {
		// TLE on LeetCode. Solves TLE case in ~2s. O(N^3) by avoiding
		// re-computation of optimally solved sub-problems.
		this.sChars = s.toCharArray();
		this.memo = new int[s.length()][s.length()];
		return minPalindrome(0, s.length() - 1) - 1;
	}

	private int minPalindrome(int fromIdx, int toIdx) {
		if (toIdx - fromIdx <= 0) {
			return 1;
		}

		if (memo[fromIdx][toIdx] != 0) {
			return memo[fromIdx][toIdx];
		}

		if (isPalindrome(fromIdx, toIdx)) {
			memo[fromIdx][toIdx] = 1;
			return 1;
		}

		int minPalindromes = toIdx - fromIdx + 1;
		for (int j = fromIdx; j < toIdx; j++) {
			int minLeftSubstring = minPalindrome(fromIdx, j);
			int minRightSubstring = minPalindrome(j + 1, toIdx);
			minPalindromes = Math.min(minPalindromes,
					minLeftSubstring + minRightSubstring);
		}

		memo[fromIdx][toIdx] = minPalindromes;

		return minPalindromes;
	}

	public int minCutIterative(String s) {
		// TLE on LeetCode. Solves TLE cases in ~1s. O(N^3)
		if (s.isEmpty())
			return 0;
		this.sChars = s.toCharArray();
		this.memo = new int[s.length()][s.length()];
		for (int len = 1; len <= sChars.length; len++) {
			for (int fromIdx = 0; fromIdx < sChars.length - len
					+ 1; fromIdx++) {
				int toIdx = fromIdx + len - 1;
				if (isPalindrome(fromIdx, toIdx)) {
					memo[fromIdx][toIdx] = 1;
				} else {
					int minPalindromes = toIdx - fromIdx + 1;
					for (int j = fromIdx; j < toIdx; j++) {
						int minLeftSubstring = memo[fromIdx][j];
						int minRightSubstring = memo[j + 1][toIdx];
						minPalindromes = Math.min(minPalindromes,
								minLeftSubstring + minRightSubstring);
					}

					memo[fromIdx][toIdx] = minPalindromes;
				}
			}
		}

		return this.memo[0][s.length() - 1] - 1;
	}

	private boolean isPalindrome(int fromIdx, int toIdx) {
		int half = (toIdx - fromIdx + 1) >> 1;
		for (int i = 0; i < half; i++) {
			if (this.sChars[fromIdx + i] != this.sChars[toIdx - i]) {
				return false;
			}
		}
		return true;
	}

	public int minCut(String s) {
		// O(N^2) of Space and Time complexities.
		// The following is NOT my-own-thought solution.
		if (s.isEmpty())
			return 0;
		this.sChars = s.toCharArray();
		// For i<=j<, mark whether substring s[i...j] is a Palindrome or not.
		boolean[][] palinSubstr = new boolean[s.length()][s.length()];

		// Each substring of length 1 (single char) is Palindrome.
		for (int i = 0; i < s.length(); i++)
			palinSubstr[i][i] = true;

		for (int len = 2; len <= s.length(); len++) {
			for (int from = 0; from < s.length() - len + 1; from++) {
				int to = from + len - 1;
				palinSubstr[from][to] = (this.sChars[from] == this.sChars[to])
						&& (len == 2 || palinSubstr[from + 1][to - 1]);
			}
		}

		// Minimum Palindrome partitions for substring s[0...i].
		int[] minPalin = new int[s.length()];

		for (int i = 0; i < s.length(); i++) {
			// Substring s[0...i] could have at maximum of i+1 partitions.
			int minPalinToI = i + 1;

			// First check if entire substring s[0...i] is a palindrome.
			if (palinSubstr[0][i])
				minPalinToI = 1;

			// Try to minimize the number of partitions by using smaller
			// palindrome substrings within the substring s[0...i].
			// Minimum over all j (0 <= j < i): Sum of
			// minimum palindrome partitions of substring s[0...j] +
			// 1 (for the palindrome partition of substring s[j+1...i])
			for (int j = 0; j < i && minPalinToI > 1; j++) {
				if (palinSubstr[j + 1][i]) {
					minPalinToI = Math.min(minPalinToI, minPalin[j] + 1);
				}
			}
			minPalin[i] = minPalinToI;
		}

		return minPalin[minPalin.length - 1] - 1;
	}

	private Object[][] substringPartitions;

	public List<List<String>> partitionRecursive(String s) {
		// TLE on LeetCode. O(N^3)
		this.sChars = s.toCharArray();
		substringPartitions = new Object[s.length()][s.length()];
		return new ArrayList<List<String>>(allPartitions(0, s.length() - 1));
	}

	@SuppressWarnings("unchecked")
	private Set<List<String>> allPartitions(int fromIdx, int toIdx) {
		if (substringPartitions[fromIdx][toIdx] == null) {
			Set<List<String>> partitions = new HashSet<List<String>>();
			if (fromIdx == toIdx) {
				partitions.add(
						Arrays.asList(String.valueOf(this.sChars[fromIdx])));
			} else {
				if (this.isPalindrome(fromIdx, toIdx)) {
					partitions.add(Arrays.asList(new String[] {
							new String(Arrays.copyOfRange(this.sChars, fromIdx,
									toIdx + 1)) }));
				}
				for (int j = fromIdx; j < toIdx; j++) {
					Set<List<String>> leftParts = allPartitions(fromIdx, j);
					Set<List<String>> rightParts = allPartitions(j + 1, toIdx);
					for (List<String> left : leftParts) {
						for (List<String> right : rightParts) {
							List<String> merged = new ArrayList<String>();
							merged.addAll(left);
							merged.addAll(right);
							partitions.add(merged);
						}
					}
				}
			}
			substringPartitions[fromIdx][toIdx] = partitions;
		}

		return (Set<List<String>>) substringPartitions[fromIdx][toIdx];
	}

	public List<List<String>> partitionIterative(String s) {
		// O(N^2) Space and Time complexities, passes on LeetCode.
		List<List<List<String>>> substringPartitions = new ArrayList<List<List<String>>>(
				s.length());
		// For i<=j<, mark whether substring s[i...j] is a Palindrome or not.
		boolean[][] palinSubstr = new boolean[s.length()][s.length()];

		// Each substring of length 1 (single char) is Palindrome.
		for (int i = 0; i < s.length(); i++) {
			palinSubstr[i][i] = true;
		}

		for (int len = 2; len <= s.length(); len++) {
			for (int from = 0; from < s.length() - len + 1; from++) {
				int to = from + len - 1;
				palinSubstr[from][to] = (s.charAt(from) == s.charAt(to))
						&& (len == 2 || palinSubstr[from + 1][to - 1]);
			}
		}

		for (int i = 0; i < s.length(); i++) {
			List<List<String>> partitions = new ArrayList<List<String>>();
			if (palinSubstr[0][i]) {
				partitions.add(
						Arrays.asList(new String[] { s.substring(0, i + 1) }));
			}

			for (int j = 0; j < i; j++) {
				List<List<String>> leftParts;
				if (palinSubstr[j + 1][i]) {
					String rightSubstr = s.substring(j + 1, i + 1);
					leftParts = substringPartitions.get(j);
					for (List<String> left : leftParts) {
						List<String> merged = new ArrayList<String>();
						merged.addAll(left);
						merged.add(rightSubstr);
						partitions.add(merged);
					}
				}
			}
			substringPartitions.add(i, partitions);
		}

		return substringPartitions.get(s.length() - 1);
	}

	public List<List<String>> partition(String s) {
		// O(N^2) Space and time complexities.
		// More optimized by avoiding pre-computation of isPalindrome for
		// substrings beforehand, it is computed on the fly.
		List<List<List<String>>> substringPartitions = new ArrayList<List<List<String>>>(
				s.length());
		for (int i = 0; i < s.length(); i++)
			substringPartitions.add(new ArrayList<List<String>>());
		// For i<=j<, mark whether substring s[i...j] is a Palindrome or not.
		boolean[][] palinSubstr = new boolean[s.length()][s.length()];

		// Each substring of length 1 (single char) is Palindrome.
		for (int i = 0; i < s.length(); i++) {
			palinSubstr[i][i] = true;
		}

		int lastCharIdx = s.length() - 1;
		for (int i = lastCharIdx; i > -1; i--) {
			List<List<String>> partitions = substringPartitions.get(i);
			if ((s.charAt(i) == s.charAt(lastCharIdx)) && (lastCharIdx - i <= 1
					|| palinSubstr[i + 1][lastCharIdx - 1])) {
				palinSubstr[i][lastCharIdx] = true;
				partitions.add(Arrays.asList(new String[] { s.substring(i) }));
			}

			// Set parts of s[i...] =
			// SUM_(if s[i...j-1] is Palindrome) {append s[i...j-1] to PARTS[j]}
			for (int j = i + 1; j < s.length(); j++) {
				List<List<String>> rightParts;
				if ((s.charAt(i) == s.charAt(j - 1))
						&& ((j - 1) - i <= 1 || palinSubstr[i + 1][j - 2])) {
					palinSubstr[i][j - 1] = true;
					String leftSubstr = s.substring(i, j); // s[i...j-1]
					rightParts = substringPartitions.get(j); // s[j...]
					for (List<String> right : rightParts) {
						List<String> merged = new ArrayList<String>();
						merged.add(leftSubstr);
						merged.addAll(right);
						partitions.add(merged);
					}
				}
			}
		}

		return substringPartitions.get(0);
	}

	public static void main(String[] args) {
		String string;
		int expected;
		string = "";
		expected = 0;
		LeetPrinter.assertPrint(expected,
				new PalindromePartitioning().minCut(string),
				string + " >>> Expected: " + expected + ", but found: ");
		string = "aaaaaa";
		expected = 0;
		LeetPrinter.assertPrint(expected,
				new PalindromePartitioning().minCut(string),
				string + " >>> Expected: " + expected + ", but found: ");
		System.out // 32
				.println(new PalindromePartitioning().partition(string).size());
		string = "abadd";
		expected = 1;
		LeetPrinter.assertPrint(expected,
				new PalindromePartitioning().minCut(string),
				string + " >>> Expected: " + expected + ", but found: ");
		System.out.println(new PalindromePartitioning().partition(string));
		string = "aab";
		expected = 1;
		LeetPrinter.assertPrint(expected,
				new PalindromePartitioning().minCut(string),
				string + " >>> Expected: " + expected + ", but found: ");
		System.out.println(new PalindromePartitioning().partition(string));
		string = "abcd";
		expected = 3;
		LeetPrinter.assertPrint(expected,
				new PalindromePartitioning().minCut(string),
				string + " >>> Expected: " + expected + ", but found: ");
		System.out.println(new PalindromePartitioning().partition(string));
		string = "aaaabbaaaaaaa";
		expected = 1;
		LeetPrinter.assertPrint(expected,
				new PalindromePartitioning().minCut(string),
				string + " >>> Expected: " + expected + ", but found: ");
		System.out // 1196
				.println(new PalindromePartitioning().partition(string).size());
		// TLE case
		long start = System.currentTimeMillis();
		string = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaa";
		expected = 1;
		LeetPrinter.assertPrint(expected,
				new PalindromePartitioning().minCut(string),
				"AA...BB...AA >>> Expected: " + expected + ", but found: ");
		System.out.println("MinCuts TLE case took "
				+ ((System.currentTimeMillis() - start) / ((int) 1E0))
				+ " millisecs");
		string = "kwtbjmsjvbrwriqwxadwnufplszhqccayvdhhvscxjaqsrmrrqngmuvxnugdzjfxeihogz"
				+ "sdjtvdmkudckjoggltcuybddbjoizu";
		start = System.currentTimeMillis();
		new PalindromePartitioning().partition(string);
		System.out.println("AllParts TLE case took "
				+ ((System.currentTimeMillis() - start) / ((int) 1E0))
				+ " millisecs");
		System.out // 144
				.println(new PalindromePartitioning().partition(string).size());
		System.out.println("Done Successfully");
	}

}

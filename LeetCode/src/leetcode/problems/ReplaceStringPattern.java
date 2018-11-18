package leetcode.problems;

import java.util.Arrays;

import leetcode.utils.LeetPrinter;

public class ReplaceStringPattern {

	public String replacePatterByX(String string, String pattern) {
		if (string.length() < pattern.length())
			return string;

		char[] sChars = string.toCharArray();
		int scanPtr = 0;
		int lastCopyPtr = -1;
		while (scanPtr < sChars.length) {
			boolean matching = sChars[scanPtr] == pattern.charAt(0);
			int sMatchPtr = scanPtr + 1;
			int pMatchPtr = 1;
			while (matching && pMatchPtr < pattern.length()) {
				matching = sMatchPtr < sChars.length
						&& sChars[sMatchPtr] == pattern.charAt(pMatchPtr);
				sMatchPtr++;
				pMatchPtr++;
			}
			
			if (matching) {
				if (lastCopyPtr == -1 || sChars[lastCopyPtr] != 'X')
					sChars[++lastCopyPtr] = 'X';
				scanPtr = sMatchPtr;
			} else {
				sChars[++lastCopyPtr] = sChars[scanPtr];
				scanPtr++;
			}
		}

		return new String(Arrays.copyOf(sChars, lastCopyPtr + 1));
	}

	public static void main(String[] args) {
		String[] strings = new String[] { "a", "aa", "aa", "aa", "abc",
				"abcabc", "abcabcabc", "abcaabcaabc", "abcaaabcaaabca",
				"abcabcabababcabc", "abcabcabababcabcab", "aabbaabbaaabbbaabb",
				"aabbaabbaaabbbaabb", "aabbaabbaaabbbaaabb",
				"aabbaabbaaabbbaaabc", "abcdeffdfegabcabc", "abcdeffdfegabcabc",
				"abcdeffdfegabcabc", "abcdeffdfegabcab", "abcdeffdfegabcabcab",
				"abcdeffdfegabcaabcab", "abcdeffdfegabcaaaabcab", "aaaaaa",
				"aaaaaa", "aaaaaa", "aaaaaa", "aabaababaaab", "aabaababaaa",
				"aaaab", "baaa", "aabaaabaab", "aabaaabaab", "aabaaabaa" };

		String[] patterns = new String[] { "a", "aa", "a", "aaa", "abc", "abc",
				"abc", "abc", "abc", "abc", "abc", "aabb", "aaabb", "aaabb",
				"aaabb", "abc", "ab", "a", "abc", "abc", "abc", "abc", "a",
				"aa", "aaaaaa", "aaaaaaa", "a", "a", "a", "a", "aaa", "aa",
				"aa" };

		String[] expected = new String[] { "X", "X", "X", "aa", "X", "X", "X",
				"XaXaX", "XaaXaaXa", "XababX", "XababXab", "XaXbX",
				"aabbaabbXbaabb", "aabbaabbXbX", "aabbaabbXbaaabc",
				"XdeffdfegX", "XcdeffdfegXcXc", "XbcdeffdfegXbcXbc",
				"XdeffdfegXab", "XdeffdfegXab", "XdeffdfegXaXab",
				"XdeffdfegXaaaXab", "X", "X", "X", "aaaaaa", "XbXbXbXb",
				"XbXbXbX", "Xb", "bX", "aabXbaab", "XbXabXb", "XbXabX" };

		assert patterns.length == strings.length
				&& expected.length == strings.length;
		int N = strings.length;
		for (int c = 0; c < N; c++) {
			String s = strings[c];
			String p = patterns[c];
			String e = expected[c];
			String actual = new ReplaceStringPattern().replacePatterByX(s, p);
			LeetPrinter.assertPrint(e, actual,
					String.format(
							"Case# %02d: Failed for String: %s and Pattern: %s. Expected: %s, but Found: ",
							c + 1, s, p, e));
		}
		System.out.println("Done");
	}
}

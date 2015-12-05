package leetcode.problems;

import java.util.HashMap;
import java.util.Map;

import leetcode.utils.LeetPrinter;

public class ScrambleString {

	// https://leetcode.com/problems/scramble-string/

	byte[][][] memo;

	public boolean isScramble(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;
		memo = new byte[s1.length()][s1.length()][s1.length()];
		return recurse(s1.toCharArray(), s2.toCharArray(), 0, 0, s1.length());
	}

	private boolean recurse(char[] s1Chars, char[] s2Chars, int s1From,
			int s2From, int len) {
		if (len == 1) {
			return s1Chars[s1From] == s2Chars[s2From];
		}

		if (memo[s1From][s2From][len - 1] == 0) {
			boolean scramble = false;
			for (int i = 1; i < len && !scramble; i++) {

				// try matching splits before swapping left and right subtrees.
				if (recurse(s1Chars, s2Chars, s1From, s2From, i)) {
					scramble = recurse(s1Chars, s2Chars, s1From + i, s2From + i,
							len - i);
				}

				// try matching splits after swapping left and right subtrees.
				if (!scramble && recurse(s1Chars, s2Chars, s1From,
						s2From + len - i, i)) {
					scramble = recurse(s1Chars, s2Chars, s1From + i, s2From,
							len - i);
				}
			}
			memo[s1From][s2From][len - 1] = (byte) (scramble ? 1 : 2);
		}

		return memo[s1From][s2From][len - 1] == 1;

	}

	@SuppressWarnings("unused")
	private boolean isAnagram(String s1, String s2) {
		/*
		 * All Scrambles are Anagrams, but not all Anagrams are Scrambles.
		 * Example: abcd, bcad or
		 */
		if (s1.length() != s2.length())
			return false;
		Map<Character, Integer> counts = new HashMap<Character, Integer>();
		for (int i = 0; i < s1.length(); i++) {
			char c = s1.charAt(i);
			counts.put(c, counts.getOrDefault(c, 0) + 1);
		}

		for (int i = 0; i < s2.length(); i++) {
			char c = s2.charAt(i);
			if (counts.containsKey(c)) {
				int countC = counts.get(c);
				if (countC == 1)
					counts.remove(c);
				else
					counts.put(c, countC - 1);
			} else {
				return false;
			}
		}

		return counts.isEmpty();
	}

	public static void main(String[] args) {
		String s1, s2;
		s1 = "aa";
		s2 = "aa";
		LeetPrinter.assertPrint(true, new ScrambleString().isScramble(s1, s2),
				s1 + " & " + s2 + " >> ");
		s1 = "abcd";
		s2 = "bdac";
		LeetPrinter.assertPrint(false, new ScrambleString().isScramble(s1, s2),
				s1 + " & " + s2 + " >> ");
		s1 = "abcd";
		s2 = "cadb";
		LeetPrinter.assertPrint(false, new ScrambleString().isScramble(s1, s2),
				s1 + " & " + s2 + " >> ");
		for (String ss : new String[] { "abcd", "abdc", "acbd", "acdb", "adbc",
				"adcb", "bacd", "badc", "bcad", "bcda", "bdca", "cabd", "cbad",
				"cbda", "cdab", "cdba", "dacb", "dabc", "dbac", "dbca", "dcab",
				"dcba" }) {
			s1 = "abcd";
			if (ss == "acdb")
				System.out.print("");
			LeetPrinter.assertPrint(true,
					new ScrambleString().isScramble(s1, ss),
					s1 + " & " + ss + " >> ");
		}
		s1 = "ccabcbabcbabbbbcbb";
		s2 = "bbbbabccccbbbabcba";
		LeetPrinter.assertPrint(false, new ScrambleString().isScramble(s1, s2),
				s1 + " & " + s2 + " >> ");
		System.out.println("Done Successfully");

	}
}

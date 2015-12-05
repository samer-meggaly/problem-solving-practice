package leetcode.problems;

public class WildCard {

	// https://leetcode.com/problems/wildcard-matching/

	private byte[][] memo;

	public boolean isMatchRecursive(String s, String p) {
		// 0:Unknown - 1:True - 2:False
		memo = new byte[s.length()][p.length()];
		return dfs(s, 0, p, 0);
	}

	private boolean dfs(String s, int sPtr, String p, int pPtr) {
		if (s.length() == sPtr && p.length() == pPtr)
			return true;
		if (s.length() == sPtr) {
			// consume all *s at the end of the pattern
			while (pPtr < p.length() && p.charAt(pPtr) == '*')
				pPtr++;
			return pPtr == p.length();
		}
		if (p.length() == pPtr)
			return false; // p is finished before consuming all s

		if (memo[sPtr][pPtr] == 0) {
			boolean match = false;
			if (p.charAt(pPtr) == '*') { // 0-more sequence of chars
				if (!dfs(s, sPtr + 1, p, pPtr)) // * consumes 1 extra char
					match = dfs(s, sPtr, p, pPtr + 1); // * maps EMPTY-STRING
				else
					match = true;
			} else {
				if (s.charAt(sPtr) == p.charAt(pPtr) || p.charAt(pPtr) == '?')
					match = dfs(s, sPtr + 1, p, pPtr + 1);
			}
			memo[sPtr][pPtr] = (byte) (match ? 1 : 2);
		}

		return memo[sPtr][pPtr] == 1;
	}

	public boolean isMatchIter(String s, String p) {
		/*
		 * table to represent matching between characters the string s and the
		 * pattern p. The +1 is to account for the empty string in both the
		 * string and the pattern. The rows represent the character of the
		 * string s. The columns represent the characters of the pattern p.
		 */
		boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
		match[0][0] = true; // both empty strings are a match
		// patterns that match empty string
		for (int i = 1; i < match[0].length; i++)
			match[0][i] = p.charAt(i - 1) == '*' && match[0][i - 1];

		for (int r = 1; r < match.length; r++) {
			char sChar = s.charAt(r - 1);
			for (int c = 1; c < match[0].length; c++) {
				char pChar = p.charAt(c - 1);
				if (pChar == '*') {
					match[r][c] = match[r][c - 1] // ignore * for EMPTY-STRING
							|| match[r - 1][c]; // consume sChar into *
				} else if (pChar == '?') {
					match[r][c] = match[r - 1][c - 1];
				} else {
					match[r][c] = match[r - 1][c - 1] && pChar == sChar;
				}
			}
		}
		return match[s.length()][p.length()];
	}

	public boolean isMatch(String s, String p) {
		/*
		 * table to represent matching between characters the string s and the
		 * pattern p. The +1 is to account for the empty string in both the
		 * string and the pattern. The rows represent the character of the
		 * string s. The columns represent the characters of the pattern p.
		 * 
		 * This implementation optimizes space complexity.
		 */
		boolean[] match = new boolean[p.length() + 1];
		match[0] = true; // both empty strings are a match
		// patterns that match empty string
		for (int i = 1; i < match.length; i++)
			match[i] = p.charAt(i - 1) == '*' && match[i - 1];

		for (int r = 0; r < s.length(); r++) {
			char sChar = s.charAt(r);
			boolean[] nextMatch = new boolean[p.length() + 1];
			for (int c = 1; c < p.length() + 1; c++) {
				char pChar = p.charAt(c - 1);
				if (pChar == '*') {
					nextMatch[c] = nextMatch[c - 1] // ignore * for EMPTY-STRING
							|| match[c]; // consume sChar into *
				} else if (pChar == '?') {
					nextMatch[c] = match[c - 1];
				} else {
					nextMatch[c] = match[c - 1] && pChar == sChar;
				}
			}
			match = nextMatch;
		}
		return match[p.length()];
	}

	public static void main(String[] args) {
		assert new WildCard().isMatch("aa", "a") == false;
		assert new WildCard().isMatch("aa", "aa") == true;
		assert new WildCard().isMatch("aaa", "aa") == false;
		assert new WildCard().isMatch("aa", "*") == true;
		assert new WildCard().isMatch("aa", "a*") == true;
		assert new WildCard().isMatch("abb", "?*") == true;
		assert new WildCard().isMatch("aab", "c*a*b") == false;
		assert new WildCard().isMatch("cabab", "c*ab") == true;
		assert new WildCard().isMatch("ab", "***ab") == true;
		assert new WildCard().isMatch("sdfghjklab", "*ab") == true;
		assert new WildCard().isMatch("cab", "c*ab") == true;
		assert new WildCard().isMatch(
				"aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba",
				"a*******b") == false; // weird pattern, test case on LeetCode!
		assert new WildCard().isMatch("abc", "abc**") == true;
		assert new WildCard().isMatch("abc", "abc?") == false;
		assert new WildCard().isMatch("abce", "abc?") == true;
		assert new WildCard().isMatch("", "*") == true;
		assert new WildCard().isMatch("", "?") == false;
		assert new WildCard().isMatch("", "?*") == false;
		assert new WildCard().isMatch("a", "?") == true;
		assert new WildCard().isMatch("", "") == true;
		assert new WildCard().isMatch("*", "") == false;
		assert new WildCard().isMatch("", "****") == true;
		assert new WildCard().isMatch("", "a") == false;
		assert new WildCard().isMatch("", "ab") == false;
		assert new WildCard().isMatch("", "*a") == false;
		assert new WildCard().isMatch("", "*a*") == false;
		assert new WildCard().isMatch("bbbabbb", "*a*") == true;
		System.out.println("Done Successfully");
	}
}

package leetcode.problems;

import leetcode.utils.LeetPrinter;

public class EditDistance {
	
	// https://leetcode.com/problems/edit-distance/
	
	public int minDistance(String word1, String word2) {
		int[][] tabular = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 1; i < tabular.length; i++)
			tabular[i][0] = i;
		for (int i = 1; i < tabular[0].length; i++)
			tabular[0][i] = i;

		for (int r = 1; r < tabular.length; r++) {
			for (int c = 1; c < tabular[0].length; c++) {
				int replace = tabular[r - 1][c - 1]
						+ ((word1.charAt(r - 1) == word2.charAt(c - 1)) ? 0
								: 1);
				tabular[r][c] = Math.min(replace,
						Math.min(tabular[r - 1][c], tabular[r][c - 1]) + 1);
			}
		}

		return tabular[word1.length()][word2.length()];
	}

	public static void main(String[] args) {
		String w1, w2;
		int d;
		w1 = "";
		w2 = "";
		d = 0;
		LeetPrinter.assertPrint(d, new EditDistance().minDistance(w1, w2),
				w1 + " -> " + w2 + " in " + d + " not ");
		w1 = "abc";
		w2 = "";
		d = 3;
		LeetPrinter.assertPrint(d, new EditDistance().minDistance(w1, w2),
				w1 + " -> " + w2 + " in " + d + " not ");
		w1 = "";
		w2 = "abc";
		d = 3;
		LeetPrinter.assertPrint(d, new EditDistance().minDistance(w1, w2),
				w1 + " -> " + w2 + " in " + d + " not ");
		w1 = "abc";
		w2 = "abd";
		d = 1;
		LeetPrinter.assertPrint(d, new EditDistance().minDistance(w1, w2),
				w1 + " -> " + w2 + " in " + d + " not ");
		w1 = "abc";
		w2 = "aabc";
		d = 1;
		LeetPrinter.assertPrint(d, new EditDistance().minDistance(w1, w2),
				w1 + " -> " + w2 + " in " + d + " not ");
		w1 = "zoologicoarchaeologist";
		w2 = "zoogeologist";
		d = 10;
		LeetPrinter.assertPrint(d, new EditDistance().minDistance(w1, w2),
				w1 + " -> " + w2 + " in " + d + " not ");
		System.out.println("Done Successfully");
	}
}

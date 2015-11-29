package leetcode.problems;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class WordSearch {
	// https://leetcode.com/problems/word-search/

	public boolean exist(char[][] board, String word) {
		if (word.length() == 0)
			return true;
		if (board.length == 0 || board[0].length == 0)
			return false;

		boolean exists = false;
		/*
		 * Using Queue (BFS) instead of Stack (DFS) will cause TLE of some test
		 * cases on Leetcode. To use Queue, try moving the entire While-loop
		 * inside the two nested For-loops.
		 */
		Stack<SearchNode> queue = new Stack<SearchNode>();
		for (int r = 0; r < board.length && !exists; r++) {
			for (int c = 0; c < board[0].length && !exists; c++) {
				if (board[r][c] == word.charAt(0)) {
					SearchNode start = new SearchNode(r, c, 1,
							new HashSet<Integer>());
					start.visited.add(r * board[0].length + c);
					queue.push(start);
					exists = word.length() == 1;
				}
			}
		}

		int[][] displacements = new int[][] { { 0, -1 }, { 0, 1 }, { -1, 0 },
				{ 1, 0 } };
		while (!queue.isEmpty() && !exists) {
			SearchNode curr = queue.pop();
			for (int[] disp : displacements) {
				int newR = curr.row + disp[0];
				int newC = curr.col + disp[1];
				int cell = newR * board[0].length + newC;
				if (newR > -1 && newR < board.length && newC > -1
						&& newC < board[0].length
						&& !curr.visited.contains(cell)) {
					if (board[newR][newC] == word.charAt(curr.idx)) {
						exists = curr.idx + 1 == word.length();
						if (!exists) {
							SearchNode newNode = new SearchNode(newR, newC,
									curr.idx + 1, curr.visited);
							newNode.visited.add(cell);
							queue.push(newNode);
						} else {
							break;
						}
					}
				}
			}
		}

		return exists;
	}

	private static class SearchNode {
		int row;
		int col;
		int idx;
		Set<Integer> visited;

		public SearchNode(int row, int col, int idx, Set<Integer> visited) {
			this.row = row;
			this.col = col;
			this.idx = idx;
			this.visited = new HashSet<Integer>(visited);
		}
	}

	public static void main(String[] args) {
		char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' },
				{ 'A', 'D', 'E', 'E' } };
		System.out.println(new WordSearch().exist(board, "ABCCED"));
		System.out.println(new WordSearch().exist(board, "SEE"));
		System.out.println(new WordSearch().exist(board, "ABCB"));
		board = new char[][] { { 'A', 'A' } };
		System.out.println(new WordSearch().exist(board, "AAA"));
	}
}

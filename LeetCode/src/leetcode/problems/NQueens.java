package leetcode.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

	/*
	 * https://leetcode.com/problems/n-queens/
	 * https://leetcode.com/problems/n-queens-ii/
	 */

	private List<List<String>> solutions = new ArrayList<List<String>>();

	public List<List<String>> solveNQueens(int n) {
		recurseAndFill(new int[n], 0);
		return solutions;
	}

	private void recurseAndFill(int[] rowColumns, int row) {
		if (row == rowColumns.length) {
			List<String> board = toBoard(rowColumns);
			solutions.add(board);
			return;
		}

		for (int c = 0; c < rowColumns.length; c++) {
			boolean validColumn = true;
			for (int r = 0; r < row && validColumn; r++) {
				boolean sameColumn = rowColumns[r] == c;
				boolean sameDiagonal = Math.abs(r - row) == Math
						.abs(rowColumns[r] - c);
				validColumn = !(sameColumn || sameDiagonal);
			}
			if (validColumn) {
				rowColumns[row] = c;
				recurseAndFill(rowColumns, row + 1);
			}
		}
	}

	private List<String> toBoard(int[] rowColumns) {
		List<String> board = new ArrayList<String>(rowColumns.length);
		char[] row = new char[rowColumns.length];
		Arrays.fill(row, '.');
		for (int c : rowColumns) {
			row[c] = 'Q';
			board.add(new String(row));
			row[c] = '.';
		}

		return board;
	}

	private int solutionsCount = 0;

	public int totalNQueens(int n) {
		recurseAndFillCounting(new int[n], 0);
		return solutionsCount;
	}

	private void recurseAndFillCounting(int[] rowColumns, int row) {
		if (row == rowColumns.length) {
			solutionsCount++;
			return;
		}

		for (int c = 0; c < rowColumns.length; c++) {
			boolean validColumn = true;
			for (int r = 0; r < row && validColumn; r++) {
				boolean sameColumn = rowColumns[r] == c;
				boolean sameDiagonal = Math.abs(r - row) == Math
						.abs(rowColumns[r] - c);
				validColumn = !(sameColumn || sameDiagonal);
			}
			if (validColumn) {
				rowColumns[row] = c;
				recurseAndFillCounting(rowColumns, row + 1);
			}
		}
	}

	public static void main(String[] args) {
		List<List<String>> boards;
		boards = new NQueens().solveNQueens(8);
		for (List<String> board : boards)
			System.out.println(board);
		System.out.println(new NQueens().totalNQueens(8));
		System.out.println(new NQueens().totalNQueens(10));
		System.out.println("Done Successfully");
	}
}

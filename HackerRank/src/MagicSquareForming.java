import java.util.Scanner;

public class MagicSquareForming {

	private static final int[] COUNTS = new int[10];
	private static final int[][] BOARD = new int[3][3];
	private static final int[][] IDX_TO_ROW_COL = new int[][] {
			new int[] { 0, 0 },
			new int[] { 0, 1 },
			new int[] { 0, 2 },
			new int[] { 1, 0 },
			new int[] { 1, 1 },
			new int[] { 1, 2 },
			new int[] { 2, 0 },
			new int[] { 2, 1 },
			new int[] { 2, 2 },
	};
	private static int minCost = Integer.MAX_VALUE;

	private static boolean isMagic() {
		for (int ci = 1; ci < COUNTS.length; ci++)
			if (COUNTS[ci] != 1)
				return false;
		for (int fix = 0; fix < 3; fix++) {
			int sumRow = 0;
			int sumCol = 0;
			for (int move = 0; move < 3; move++) {
				sumRow += BOARD[fix][move];
				sumCol += BOARD[move][fix];
			}
			if (sumRow != 15 || sumCol != 15)
				return false;
		}

		return (BOARD[0][0] + BOARD[1][1] + BOARD[2][2] == 15)
				&& (BOARD[0][2] + BOARD[1][1] + BOARD[2][0] == 15);
	}
	
	private static void tryMagic(int idx, int costSoFar) {
		if (costSoFar >= minCost)
			return;
		if (isMagic()) {
			minCost = Math.min(minCost, costSoFar);
			return;
		}		
		if (idx == 9)
			return;
		
		int idxRow = IDX_TO_ROW_COL[idx][0];
		int idxCol = IDX_TO_ROW_COL[idx][1];
		int vOld = BOARD[idxRow][idxCol];
		COUNTS[vOld]--;
		for (int v = 1; v < 10; v++) {
			BOARD[idxRow][idxCol] = v;			
			COUNTS[v]++;
			tryMagic(idx + 1, costSoFar + Math.abs(vOld - v));			
			COUNTS[v]--;
		}
		BOARD[idxRow][idxCol] = vOld;
		COUNTS[vOld]++;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		for (int r = 0; r < 3; r++)
			for (int c = 0; c < 3; c++)
				COUNTS[BOARD[r][c] = scanner.nextInt()]++;
		scanner.close();
		tryMagic(0, 0);
		System.out.println(minCost);
	}
}
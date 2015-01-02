package world2010.round1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Rotate {

	static char RED = 'R';
	static char BLUE = 'B';
	static char DOT = '.';
		
	public static String checkBoard(char[][] board, int K) {
		
		boolean blueWon = false;
		boolean redWon = false;
		boolean go = true;
		
		for (int i = 0; i < board.length && go; i++) {
			for (int j = 0; j < board[i].length && go; j++) {
				char curr = board[i][j];
				if ((!blueWon && curr == BLUE) || (!redWon && curr == RED)) {
					// check down
					int d = 1;
					for (; d < K && (i + d < board.length); d++) {
						if (board[i + d][j] != curr)
							break;
					}
					if (d == K && curr == BLUE) blueWon = true;
					if (d == K && curr == RED) redWon = true;
					
					// check right
					d = 1;
					for (; d < K && (j + d < board[i].length); d++) {
						if (board[i][j + d] != curr)
							break;
					}
					if (d == K && curr == BLUE) blueWon = true;
					if (d == K && curr == RED) redWon = true;
					
					// check diagonal
					d = 1;
					for (; d < K && (i + d < board.length)
							&& (j + d < board[i].length); d++) {
						if (board[i + d][j + d] != curr)
							break;
					}
					if (d == K && curr == BLUE) blueWon = true;
					if (d == K && curr == RED) redWon = true;
					
					// check anti-diagonal
					d = 1;
					for (; d < K && (i + d < board.length)
							&& (j - d >= 0); d++) {
						if (board[i + d][j - d] != curr)
							break;
					}
					if (d == K && curr == BLUE) blueWon = true;
					if (d == K && curr == RED) redWon = true;
				}
				
				go = !(blueWon == true && redWon == true);
			}
		}
		
		if (blueWon && redWon) return "Both";
		else if (blueWon) return "Blue";
		else if (redWon) return "Red";
		else return "Neither";
	}
	
	static PrintWriter out;
	
	public static void parseInput() throws Exception {
		String file = "world2010/1A/A-large-practice.in";
//		String file = "input.in";
		Scanner scanner = new Scanner(new File(file));
		out = new PrintWriter(new FileWriter((file.replaceAll(".in", ""))));
		
		int T = Integer.parseInt(scanner.nextLine());
		
		for (int i = 0; i < T; i++) {
			String[] NK = scanner.nextLine().split("\\s");
			int N = Integer.parseInt(NK[0]);
			int K = Integer.parseInt(NK[1]);
			char[][] board = new char[N][N];
			
			for (int n = 0; n < N; n++) {
				String row = scanner.nextLine();
				char[] rowChar = new char[N];
				Arrays.fill(rowChar, DOT);
				int charIdx = N - 1;
				for (int idx = N - 1; idx >= 0; idx--)
					if (row.charAt(idx) != DOT)
						rowChar[charIdx--] = row.charAt(idx);
				for (int j = 0; j < N; j++)
					board[j][N - n - 1] = rowChar[j];
			}
			
			for (char[] cs : board) {
				System.out.println(new String(cs));
			}
			
			String r = checkBoard(board, K);
			out.println("Case #"+(i+1)+": "+r+"");
		}
	}
	
	public static void main(String[] args) throws Exception {
		parseInput();
		out.close();
		System.out.println("Done!");
	}

}

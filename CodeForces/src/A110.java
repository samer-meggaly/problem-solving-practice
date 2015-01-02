import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


public class A110 {
	
	public void solve(int[][] board) {
		int n = board.length;
		int[] rowSum = new int[n];
		
		for (int row = 0; row < n; row++) {
			rowSum[row] = sum(board[row]);
		}
		
		int counter = 0;
		for (int col = 0; col < n; col++) {
			int colSum = 0;
			for (int row = 0; row < n; row++)
				colSum += board[row][col];
			
			
			for (int row = 0; row < n; row++)
				if (colSum > rowSum[row])
					counter++;
		}
		
		System.out.println(counter);
	}
	
	private int sum(int[] cells) {
		int sum = 0;
		for (int i : cells)
			sum += i;
		return sum;
	}

	public static void main(String[] args) {
		
		A110 a = new A110();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner s = new Scanner(br);
		int n = s.nextInt();
		int[][] board = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				board[i][j] = s.nextInt();
		a.solve(board);
		
	}
}

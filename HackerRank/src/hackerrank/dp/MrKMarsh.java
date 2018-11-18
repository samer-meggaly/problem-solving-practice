package hackerrank.dp;

import java.util.Scanner;

public class MrKMarsh {
    
    private static final int[][] ROW_CLEAN_CELLS_TO_LEFT = new int[500][500];
    private static final int[][] ROW_CLEAN_CELLS_TO_UP = new int[500][500];
    
    private static void accX(char[][] land, int m, int n) {
    	// O(N^2) dominated by maxLand computation.
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (land[r][c] == 'x')
                    ROW_CLEAN_CELLS_TO_UP[r][c] = ROW_CLEAN_CELLS_TO_LEFT[r][c] = -1;
                else {
                    ROW_CLEAN_CELLS_TO_LEFT[r][c] = (c > 0) ? ROW_CLEAN_CELLS_TO_LEFT[r][c - 1] + 1 : 0;
                    ROW_CLEAN_CELLS_TO_UP[r][c] = (r > 0) ? ROW_CLEAN_CELLS_TO_UP[r - 1][c] + 1 : 0;
                }
            }
        }
    }
    
    private static int maxLand(int m, int n) {
    	// O(N^3) solution that passes on HackerRank.
        int maxLand = -1;
        for (int r1 = 0; r1 < m; r1++) {
            for (int r2 = r1 + 1; r2 < m; r2++) {
                for (int c2 = n - 1; c2 > 0; c2--) {
                	if (ROW_CLEAN_CELLS_TO_UP[r2][c2] < r2 - r1)
                		continue;
                    int maxWidth = Math.min(
                        ROW_CLEAN_CELLS_TO_LEFT[r1][c2], 
                        ROW_CLEAN_CELLS_TO_LEFT[r2][c2]);
                    if (maxWidth < 1)
                    	continue;
                    for (int c1 = c2 - maxWidth; c1 < c2; c1++) {
                        if (ROW_CLEAN_CELLS_TO_UP[r2][c1] >= r2 - r1) {
                            maxLand = Math.max(maxLand, 2 * (r2 + c2 - r1 - c1));
                        }	
                    }
                    c2 -= maxWidth;
                }
            }
        }
        return maxLand;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        char[][] land = new char[m][n];
        for (int r = 0; r < m; r++)
            land[r] = scanner.next().toCharArray();       
        scanner.close();
        accX(land, m, n);
        int maxLand = maxLand(m, n);
        System.out.println((maxLand > 0) ? maxLand : "impossible");
    }
}
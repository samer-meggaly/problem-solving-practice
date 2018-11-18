import java.util.Arrays;
import java.util.Scanner;

public class CuttingBoards {
    
    private static class Cut implements Comparable<Cut> {
        private static final boolean VERTICAL = true;
        private static final boolean HORIZONTAL = !VERTICAL;
        
        private int c;
        private boolean o;
        public Cut(int c, boolean o) {
            this.c = c;
            this.o = o;
        }
        
        public int compareTo(Cut other) {
            return -(this.c - other.c);
        }
    }
    
    private static int minCost(Cut[] cuts) {
        final int MOD = (int) (1E9 + 7);
        Arrays.sort(cuts);
        int verticalSegments = 1;
        int horizontalSegments = 1;
        int cost = 0;
        for (Cut cut : cuts) {
            long cc = cut.c;
            if (cut.o == Cut.VERTICAL) {
                cc *= horizontalSegments;
                verticalSegments++;
            } else {
                cc *= verticalSegments;
                horizontalSegments++;
            }
            cost += (int) (cc % MOD);
            cost %= MOD;
        }
        return cost;        
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int t = 0; t < T; t++) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            Cut[] cuts = new Cut[m + n - 2];
            int c = 0;
            for (int i = 0; i < m - 1; i++)
                cuts[c++] = new Cut(scanner.nextInt(), Cut.HORIZONTAL);
            for (int i = 0; i < n - 1; i++)
                cuts[c++] = new Cut(scanner.nextInt(), Cut.VERTICAL);
            
            System.out.println(minCost(cuts));
        }
        scanner.close();
    }
}
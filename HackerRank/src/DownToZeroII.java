import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class DownToZeroII {
    
	private static int reduce(int N) {
		// BFS
		// Solves HackerRank test cases in ~1.3 seconds
        Queue<Integer> queue = new ArrayDeque<Integer>(N);
        int[] dist = new int[N + 1];
        queue.offer(N);
        while (!queue.isEmpty()) {
            N = queue.poll();
            if (N == 0)
                break;
            int sqrtN = (int) Math.ceil(Math.sqrt(N));
            for (int a = sqrtN; a > 1; a--) {
                if (N % a == 0 && (N / a) != 1) {
                    int nextN = Math.max(a, N / a);
                    if (dist[nextN] == 0) {
                        dist[nextN] = 1 + dist[N];
                        queue.offer(nextN);
                    }
                }                    
            }
            if (dist[N - 1] == 0) {
                dist[N - 1] = 1 + dist[N];
                queue.offer(N - 1);
            }
        }
        return dist[0];
    }
	
	private static int[] MIN_STEPS = new int[1000001]; 
	static {
		Arrays.fill(MIN_STEPS, -1);
        MIN_STEPS[0] = 0;
	}
	@SuppressWarnings("unused")
	private static int reduceDP(int N) {
    	// Dynamic Programming
    	// Solves HackerRank test cases in ~3.2 seconds
        if (MIN_STEPS[N] != -1)
            return MIN_STEPS[N];
        
        int sqrtN = (int) Math.ceil(Math.sqrt(N));
        MIN_STEPS[N] = 1 + reduceDP(N - 1);
        for (int a = sqrtN; a > 1; a--) {
            if (N % a == 0 && (N / a) != 1)
                MIN_STEPS[N] = Math.min(MIN_STEPS[N], 1 + reduceDP(Math.max(a, N / a)));
        }
        return MIN_STEPS[N];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int Q = scanner.nextInt();
        for (int q = 0; q < Q; q++) {
            System.out.println(reduce(scanner.nextInt()));
        }
        scanner.close();
    }
}
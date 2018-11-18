package hackerrank.dp;
import java.util.Scanner;

public class MaximumSubarray {
    
    private static int maxSumSubsequence(int[] nums) {
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			max = Math.max(max, Math.max(max + nums[i], nums[i]));
		}
		return max;
	}

	@SuppressWarnings("unused")
	private static int maxSumSubarray_V1(int[] nums) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			int sumFromI = nums[i];
			max = Math.max(max, sumFromI);
			for (int j = i + 1; j < nums.length; j++)
				max = Math.max(max, sumFromI += nums[j]);
		}
		return max;
	}
	
	private static int maxSumSubarray(int[] nums) {
		int max = nums[0];
		int sum = nums[0];
		for (int i = 1; i < nums.length; i++) {
			sum += nums[i];
			max = Math.max(max, Math.max(sum, nums[i]));
			if (sum < 0)
				sum = 0;
		}
		return max;
	}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt();
            int[] nums = new int[N];
            for (int i = 0; i < N; i++)
                nums[i] = scanner.nextInt();
            System.out.println(maxSumSubarray(nums) + " " + maxSumSubsequence(nums));
        }
        scanner.close();
    }
}
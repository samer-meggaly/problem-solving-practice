import java.util.Arrays;
import java.util.Scanner;

public class AlmostSorted {
    
    private static boolean sorted(int[] nums, int l, int r) {
        boolean sorted = true;
        for (int i = l; i < r && sorted; i++)
            sorted &= nums[i] < nums[i + 1];
        return sorted;
    }
    
    private static boolean reversed(int[] nums, int l, int r) {
        boolean reversed = true;
        for (int i = l; i < r && reversed; i++)
            reversed &= nums[i] > nums[i + 1];
        return reversed;
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    @SuppressWarnings("unused")
	private static void trySort_V1(int[] nums) {
    	// pass all HackerRank test cases
        int l = 1;
        for (; l < nums.length && nums[l] > nums[l - 1]; l++);
        if (l == nums.length) {
            System.out.println("yes");
            return;
        }
        l--;
        
        int r = nums.length - 1;
        for (; r > l && nums[r] > nums[r - 1] && nums[r] > nums[l]; r--);
        
        if (nums[r] > nums[l])
        	System.out.println("no");
        else if (sorted(nums, l + 1, r - 1)) {
            swap(nums, l, r);
            if (nums[r] > nums[r - 1] && nums[l] < nums[l + 1] && (l - 1 < 0 || nums[l - 1] < nums[l]))
                System.out.println(String.format("yes\nswap %d %d", l + 1, r + 1)); // 1-based indexing
            else
                System.out.println("no");
        } else if (reversed(nums, l, r) && (l - 1 < 0 || nums[l - 1] < nums[r])) {
            System.out.println(String.format("yes\nreverse %d %d", l + 1, r + 1)); // 1-based indexing
        } else
            System.out.println("no");
    }
    
    private static void trySort(int[] nums) {
    	// pass all HackerRank test cases, almost same time as trySort_V1
        int[] clone = Arrays.copyOf(nums, nums.length);
        Arrays.sort(clone);
        
        int l = 0;
        for (; l < nums.length && nums[l] == clone[l]; l++);
        int r = nums.length - 1;
        for (; r > -1 && nums[r] == clone[r]; r--);
        
        if (nums[l] == clone[r] && nums[r] == clone[l]) {
            if (sorted(nums, l + 1, r - 1))
                System.out.println(String.format("yes\nswap %d %d", l + 1, r + 1)); // 1-based indexing
            else if (reversed(nums, l, r))
                System.out.println(String.format("yes\nreverse %d %d", l + 1, r + 1)); // 1-based indexing
            else
                System.out.println("no");    
        } else
            System.out.println("no");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = scanner.nextInt();
        trySort(nums);
        scanner.close();
    }
}
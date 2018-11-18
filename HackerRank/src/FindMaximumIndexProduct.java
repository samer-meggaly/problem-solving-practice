import java.util.Stack;

public class FindMaximumIndexProduct {

	private static long maxIndexProduct(int[] nums) {
		int[] left = new int[nums.length];
		int[] right = new int[nums.length];
		Stack<Integer> stack = new Stack<Integer>();
		int i = 1;
		while (i < nums.length) {
			if (stack.isEmpty())
				stack.push(i++);
			else if (nums[stack.peek()] > nums[i]) {
				left[i] = stack.peek();
				stack.push(i++);
			} else if (nums[stack.peek()] < nums[i]) {
				do {
					right[stack.pop()] = i;
				} while (!stack.isEmpty() && nums[stack.peek()] < nums[i]);
			} else {
				left[i] = left[stack.peek()];
				stack.push(i++);
			}
		}

		long maxIdxProd = Long.MIN_VALUE;
		for (int j = 1; j < nums.length; j++)
			maxIdxProd = Math.max(maxIdxProd, ((long) left[j]) * right[j]);

		return maxIdxProd;
	}

	private static long maxIndexProductV2(int[] nums) {
		int[] left = new int[nums.length];
		int[] right = new int[nums.length];
		Stack<Integer> stack = new Stack<Integer>();
		int i = 1;
		while (i < nums.length) {
			// pop and set Right index of all elements that are < nums[i]
			while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
				right[stack.pop()] = i;

			// now any remaining elements on the stack are >= nums[i]
			if (!stack.isEmpty()) {
				// set the Left index of i
				left[i] = (nums[stack.peek()] == nums[i]) ? left[stack.peek()]
						: stack.peek();
			}

			// now put i on the stack to wait for an element greater than itself
			// to set its (i's) Right index, if any.
			stack.push(i++);
		}

		long maxIdxProd = Long.MIN_VALUE;
		for (int j = 1; j < nums.length; j++)
			maxIdxProd = Math.max(maxIdxProd, (long) left[j] * (long) right[j]);

		return maxIdxProd;
	}

	public static void main(String[] args) {
		int[] nums = null;
		nums = new int[] { 0, 5, 4, 3, 4, 5 };
		System.out.println(maxIndexProduct(nums));
		nums = new int[] { 0, 5, 4, 3, 4, 5 };
		System.out.println(maxIndexProductV2(nums));
	}

}

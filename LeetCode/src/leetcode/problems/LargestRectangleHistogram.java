package leetcode.problems;

import java.util.ArrayDeque;
import java.util.Deque;

import leetcode.utils.LeetPrinter;

public class LargestRectangleHistogram {
	
	// https://leetcode.com/problems/largest-rectangle-in-histogram/

	public int largestRectangleArea(int[] heights) {
		// using 2N space and 3N time, passes on LeetCode in 4 ms.
		int n = heights.length;
		if (heights == null || n < 1)
			return 0;
		int[] leftMostIdx = new int[n];
		leftMostIdx[0] = -1;
		for (int i = 1; i < n; i++) {
			int leftOfI = i - 1;
			while (leftOfI > -1 && heights[leftOfI] >= heights[i])
				leftOfI = leftMostIdx[leftOfI];
			leftMostIdx[i] = leftOfI;
		}

		int[] rightMostIdx = new int[n];
		rightMostIdx[n - 1] = n;
		for (int i = n - 2; i > -1; i--) {
			int rightOfI = i + 1;
			while (rightOfI < n && heights[rightOfI] >= heights[i])
				rightOfI = rightMostIdx[rightOfI];
			rightMostIdx[i] = rightOfI;
		}

		int maxArea = 0;
		for (int i = 0; i < n; i++) {
			maxArea = Math.max(maxArea,
					heights[i] * (rightMostIdx[i] - leftMostIdx[i] - 1));
		}
		return maxArea;
	}

	public int largestRectangleArea_V1(int[] heights) {
		// using N space for stack and 2N time, passes on LeetCode in 24 ms.
		int n = heights.length;
		if (heights == null || n < 1)
			return 0;
		Deque<Integer> stack = new ArrayDeque<Integer>(n);
		int maxArea = 0;
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
				int h = heights[stack.pop()];
				int left = stack.isEmpty() ? -1 : stack.peek();
				maxArea = Math.max(maxArea, h * (i - left - 1));
			}
			stack.push(i);
		}

		while (!stack.isEmpty()) {
			int h = heights[stack.pop()];
			int left = stack.isEmpty() ? -1 : stack.peek();
			maxArea = Math.max(maxArea, h * (n - left - 1));
		}

		return maxArea;
	}

	public static void main(String[] args) {
		int[] heights;
		int caseI = 1;
		int expected;

		heights = new int[] {};
		expected = 0;
		LeetPrinter.assertPrint(expected,
				new LargestRectangleHistogram().largestRectangleArea(heights),
				String.format("Case %d: expected %d but found ", caseI++,
						expected));
		heights = new int[] { 4 };
		expected = 4;
		LeetPrinter.assertPrint(expected,
				new LargestRectangleHistogram().largestRectangleArea(heights),
				String.format("Case %d: expected %d but found ", caseI++,
						expected));
		heights = new int[] { 2, 1, 5, 6, 2, 3 };
		expected = 10;
		LeetPrinter.assertPrint(expected,
				new LargestRectangleHistogram().largestRectangleArea(heights),
				String.format("Case %d: expected %d but found ", caseI++,
						expected));
		heights = new int[] { 2, 1, 5, 6, 5, 5, 2, 3 };
		expected = 20;
		LeetPrinter.assertPrint(expected,
				new LargestRectangleHistogram().largestRectangleArea(heights),
				String.format("Case %d: expected %d but found ", caseI++,
						expected));

		System.out.println("Done Successfully");
	}
}

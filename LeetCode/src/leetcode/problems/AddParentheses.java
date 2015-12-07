package leetcode.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddParentheses {
	/*
	 * https://leetcode.com/problems/different-ways-to-add-parentheses/
	 * 
	 * This solution passes on LeetCode although it doesn't handle cases like:
	 * -1+5 -> [-6, 4] 5*-1 -> [-1, -5] basically, playing with negatives and
	 * repeated operators. However, these are extra cases that can be avoided by
	 * trying to validate and clean the input.
	 */

	Map<String, List<Integer>> memo = new HashMap<String, List<Integer>>();

	public List<Integer> diffWaysToCompute(String input) {
		if (input.isEmpty())
			return new ArrayList<Integer>();

		if (!memo.containsKey(input)) {
			List<Integer> evals = new ArrayList<Integer>();
			for (int i = 0; i < input.length(); i++) {
				if (input.charAt(i) == '+' || input.charAt(i) == '-'
						|| input.charAt(i) == '*') {
					List<Integer> leftValues = diffWaysToCompute(
							input.substring(0, i));
					List<Integer> rightValues = diffWaysToCompute(
							input.substring(i + 1, input.length()));
					if (!leftValues.isEmpty() && !rightValues.isEmpty()) {
						for (Integer lv : leftValues) {
							for (Integer rv : rightValues) {
								if (input.charAt(i) == '+')
									evals.add(lv + rv);
								else if (input.charAt(i) == '-')
									evals.add(lv - rv);
								else
									evals.add(lv * rv);
							}
						}
					}
				}
			}

			if (evals.isEmpty()) {
				evals.add(Integer.parseInt(input));
			}

			memo.put(input, evals);
		}

		return memo.get(input);
	}

	public static void main(String[] args) {
		String exp;
		exp = "-1"; // [-1]
		System.out.println(new AddParentheses().diffWaysToCompute(exp));
		exp = "211"; // [211]
		System.out.println(new AddParentheses().diffWaysToCompute(exp));
		exp = "2-1-1"; // [0, 2]
		System.out.println(new AddParentheses().diffWaysToCompute(exp));
		exp = "2*3-4*5"; // [-34, -14, -10, -10, 10]
		System.out.println(new AddParentheses().diffWaysToCompute(exp));
		exp = "20-01-10"; // [29, 9]
		System.out.println(new AddParentheses().diffWaysToCompute(exp));
	}
}

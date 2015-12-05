package leetcode.problems;

import java.util.Stack;

public class ValidParentheses {

	// https://leetcode.com/problems/valid-parentheses/

	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		boolean valid = true;
		for (int i = 0; i < s.length() && valid; i++) {
			switch (s.charAt(i)) {
			case '[':
			case '{':
			case '(':
				stack.push(s.charAt(i));
				break;
			case ']':
				valid = !(stack.isEmpty() || stack.pop() != '[');
				break;
			case '}':
				valid = !(stack.isEmpty() || stack.pop() != '{');
				break;
			case ')':
				valid = !(stack.isEmpty() || stack.pop() != '(');
				break;			
			}
		}
		return valid && stack.isEmpty();
	}
	
	public static void main(String[] args) {
		assert new ValidParentheses().isValid("(a)") == true;
		System.out.println("Done Successfully");
	}
}

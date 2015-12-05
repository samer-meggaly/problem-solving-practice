package leetcode.utils;

public class LeetPrinter {

	public static void assertPrint(Object expected, Object actual,
			String messagePrefix) {
		if (!expected.equals(actual)) {
			System.err.println(messagePrefix + actual.toString());
			System.err.flush();
		}
	}
}

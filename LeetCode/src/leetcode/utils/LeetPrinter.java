package leetcode.utils;

import java.util.Arrays;

public class LeetPrinter {

	public static void assertPrint(Object expected, Object actual,
			String messagePrefix) {
		if (expected == null && actual == null)
			return;
		if (expected == null || !expected.equals(actual)) {
			System.err.println(messagePrefix + actual);
			System.err.flush();
		}
	}

	public static void assertArraysPrint(Object[] expected, Object[] actual,
			String messagePrefix) {
		if (!Arrays.deepEquals(expected, actual)) {
			System.err.println(messagePrefix + Arrays.toString(actual));
			System.err.flush();
		}
	}

	public static void assertArraysPrint(int[] expected, int[] actual,
			String messagePrefix) {
		if (!Arrays.equals(expected, actual)) {
			System.err.println(messagePrefix + Arrays.toString(actual));
			System.err.flush();
		}
	}

	public static void assertArraysPrint(char[] expected, char[] actual,
			String messagePrefix) {
		if (!Arrays.equals(expected, actual)) {
			System.err.println(messagePrefix + Arrays.toString(actual));
			System.err.flush();
		}
	}

	public static void assertArraysPrint(double[] expected, double[] actual,
			String messagePrefix) {
		if (!Arrays.equals(expected, actual)) {
			System.err.println(messagePrefix + Arrays.toString(actual));
			System.err.flush();
		}
	}

	public static String str(int[] array) {
		return Arrays.toString(array);
	}

	public static String str(char[] array) {
		return Arrays.toString(array);
	}
}

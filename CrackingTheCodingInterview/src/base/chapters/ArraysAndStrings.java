package base.chapters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArraysAndStrings {

	static String reverse(String str) {
		char[] chars = str.toCharArray();

		int limit = chars.length / 2;
		for (int i = 0; i < limit; i++) {
			char tmp = chars[i];
			chars[i] = chars[chars.length - i - 1];
			chars[chars.length - i - 1] = tmp;
		}

		return new String(chars);
	}

	static boolean allUnique(String str) {
		Set<Character> marked = new HashSet<Character>(str.length());
		boolean unique = true;
		for (int i = 0; i < str.length() && unique; i++) {
			if (!marked.contains(str.charAt(i))) {
				marked.add(str.charAt(i));
			} else
				unique = false;
		}

		return unique;
	}

	static boolean allUniqueNoStorage(String str) {
		boolean unique = true;
		for (int i = 0; i < str.length() && unique; i++) {
			for (int j = i + 1; j < str.length() && unique; j++) {
				unique = !(str.charAt(i) == str.charAt(j));
			}
		}

		return unique;
	}

	static boolean areAnagrams(String str1, String str2) {
		char[] str1Chars = str1.toCharArray();
		char[] str2Chars = str2.toCharArray();
		Arrays.sort(str1Chars);
		Arrays.sort(str2Chars);
		return Arrays.equals(str1Chars, str2Chars);
	}

	static String compress(String str) {
		StringBuilder sb = new StringBuilder(str.length() * 2);
		char curr;
		for (int i = 0; i < str.length(); i++) {
			curr = str.charAt(i++);
			int counter = 1;
			while (i < str.length() && curr == str.charAt(i)) {
				counter++;
				i++;
			}
			sb.append(curr);
			sb.append(counter);
			i--;
		}
		return sb.toString();
	}

	public static void qSort(int[] arr, int from, int to) {
		if (from < to) {
			int pivotIdx = pivot(arr, from, to);
			qSort(arr, from, pivotIdx - 1);
			qSort(arr, pivotIdx + 1, to);
		}
	}

	private static int pivot(int[] arr, int from, int to) {
		int randIdx = from + (int) (Math.random() * (to - from + 1));
		int pivot = arr[randIdx];
		arr[randIdx] = arr[to];
		arr[to] = pivot;
		int lessEqIdx = from - 1;
		for (int i = from; i < to; i++) {
			if (arr[i] <= pivot) {
				int temp = arr[i];
				arr[i] = arr[++lessEqIdx];
				arr[lessEqIdx] = temp;
			}
		}
		arr[to] = arr[++lessEqIdx];
		arr[lessEqIdx] = pivot;
		return lessEqIdx;
	}

	public static void main(String[] args) {
		System.out.println(allUnique("AAAAA"));
		System.out.println(allUnique("AAa"));
		System.out.println(allUnique("abcdef"));
		System.out.println(allUnique(""));
		System.out.println(allUniqueNoStorage("AAAAA"));
		System.out.println(allUniqueNoStorage("AAa"));
		System.out.println(allUniqueNoStorage("abcdef"));
		System.out.println(allUniqueNoStorage(""));
		System.out.println(areAnagrams("cart", "trac"));
		System.out.println(areAnagrams("cat", "dog"));
		System.out.println(areAnagrams("tart", "art"));
		System.out.println(compress("aabcccccaaa"));
		System.out.println(compress(""));
		System.out.println(compress("abcdef"));
		System.out.println(compress("aaaaa"));
		System.out.println(compress("aaaaad"));
		int[] arr;
		arr = new int[] { 1, 2, 3 };
		qSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));

		arr = new int[] { 3, 2, 5, 3, 1, 7, 7, 8, 1, 0, 9 };
		qSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));

		arr = new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		qSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));

		arr = new int[] { 1, 1, 1, 1, 1, 1, 1, 1 };
		qSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));

		arr = new int[] { 1 };
		qSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));

		arr = new int[] { 1, 345, 7, 2134, 34, 456, 1, 123, 436, 123, };
		qSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));

		arr = new int[] { 100, -213, -32, -234, -545 };
		qSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}

}

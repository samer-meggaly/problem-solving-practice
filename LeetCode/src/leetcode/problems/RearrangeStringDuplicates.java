package leetcode.problems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class RearrangeStringDuplicates {

	public String rearrangeDuplicates(String input) {
		final Map<Character, Integer> charCount = new HashMap<Character, Integer>();
		for (int i = 0; i < input.length(); i++) {
			charCount.put(input.charAt(i),
					charCount.getOrDefault(input.charAt(i), 0) + 1);
		}
		Character[] uniqueChars = charCount.keySet().toArray(new Character[0]);
		Arrays.sort(uniqueChars, new Comparator<Character>() {
			public int compare(Character c1, Character c2) {
				return charCount.get(c1) - charCount.get(c2);
			}
		});

		int front = 0;
		int fill = 0;
		int back = uniqueChars.length - 1;
		boolean consumeFromBack = true;
		char[] output = new char[input.length()];
		while (front < back) {
			if (consumeFromBack) {
				output[fill] = uniqueChars[back];
				int count = charCount.get(uniqueChars[back]);
				charCount.put(uniqueChars[back], count - 1);
				if (count == 1) {
					back--;
				}
			} else {
				output[fill] = uniqueChars[front];
				int count = charCount.get(uniqueChars[front]);
				charCount.put(uniqueChars[front], count - 1);
				if (count == 1) {
					front++;
				}
			}
			fill++;
			consumeFromBack = !consumeFromBack;
		}

		// front == back
		if (charCount.get(uniqueChars[front]) < 2) {
			output[fill] = uniqueChars[front];
			return new String(output);
		}

		return null;
	}

	public static void main(String[] args) {
		System.out.println( // null
				new RearrangeStringDuplicates().rearrangeDuplicates("AA"));
		System.out.println( // ABA
				new RearrangeStringDuplicates().rearrangeDuplicates("AAB"));
		System.out.println( // ABACA
				new RearrangeStringDuplicates().rearrangeDuplicates("AAABC"));
		System.out.println(	// ACABAB
				new RearrangeStringDuplicates().rearrangeDuplicates("AAABBC"));
		System.out.println(	// A
				new RearrangeStringDuplicates().rearrangeDuplicates("A"));
		System.out.println(	// ABC
				new RearrangeStringDuplicates().rearrangeDuplicates("ABC"));
		System.out.println(	// AB
				new RearrangeStringDuplicates().rearrangeDuplicates("AB"));	
		System.out.println(	// ABABAB
				new RearrangeStringDuplicates().rearrangeDuplicates("AAABBB"));
	}
}

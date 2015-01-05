public class WordReverse {

	public static String reverse1(String string) {
		String[] tokens = string.split("\\s+");
		int loops = tokens.length >> 1;
		for (int i = 0; i < loops; i++) {
			String tmp = tokens[i];
			tokens[i] = tokens[tokens.length - i - 1];
			tokens[tokens.length - i - 1] = tmp;
		}

		StringBuilder sb = new StringBuilder();
		for (String t : tokens)
			sb.append(t + " ");
		return sb.toString().trim();
	}

	public static String reverse2(String string) {
		int loops = string.length() >> 1;
		char[] chars = string.toCharArray();
		for (int i = 0; i < loops; i++) {
			char tmp = chars[i];
			chars[i] = chars[chars.length - i - 1];
			chars[chars.length - i - 1] = tmp;
		}

		int from, to;
		for (from = -1, to = 0; to < chars.length; to++) {
			if (chars[to] == ' ' || to == chars.length - 1) {
				from++;
				if (to == chars.length - 1)
					to++;
				if (to > from) {
					int lps = (to - from) >> 1;
					for (int i = 0; i < lps; i++) {
						char tmp = chars[from + i];
						chars[from + i] = chars[to - i - 1];
						chars[to - i - 1] = tmp;
					}
					from = to;
				}
			}
		}

		// following piece of code is useless if we added in the above loop the
		// check (to == chars.length - 1) because now we handle the case where
		// there is no space at the end of the string.

		// if (to - from > 1) {
		// from++;
		// int lps = (to - from) >> 1;
		// for (int i = 0; i < lps; i++) {
		// char tmp = chars[from + i];
		// chars[from + i] = chars[to - i - 1];
		// chars[to - i - 1] = tmp;
		// }
		// }

		return new String(chars);
	}

	public static void main(String[] args) {
		String s;
		s = "Alice likes Bob";
		System.out.println("Original : " + s);
		System.out.println("Reversed1: " + reverse1(new String(s)));
		System.out.println("Reversed2: " + reverse2(new String(s)));
		System.out.println();

		s = "AlicelikesBob";
		System.out.println("Original : " + s);
		System.out.println("Reversed1: " + reverse1(new String(s)));
		System.out.println("Reversed2: " + reverse2(new String(s)));
		System.out.println();

		s = "Alice Bob";
		System.out.println("Original : " + s);
		System.out.println("Reversed1: " + reverse1(new String(s)));
		System.out.println("Reversed2: " + reverse2(new String(s)));
		System.out.println();

		s = "A Bob";
		System.out.println("Original : " + s);
		System.out.println("Reversed1: " + reverse1(new String(s)));
		System.out.println("Reversed2: " + reverse2(new String(s)));
		System.out.println();

		s = "Alice B";
		System.out.println("Original : " + s);
		System.out.println("Reversed1: " + reverse1(new String(s)));
		System.out.println("Reversed2: " + reverse2(new String(s)));
		System.out.println();

		s = "a b c";
		System.out.println("Original : " + s);
		System.out.println("Reversed1: " + reverse1(new String(s)));
		System.out.println("Reversed2: " + reverse2(new String(s)));
		System.out.println();

		s = "a b c d e f";
		System.out.println("Original : " + s);
		System.out.println("Reversed1: " + reverse1(new String(s)));
		System.out.println("Reversed2: " + reverse2(new String(s)));
		System.out.println();

	}

}

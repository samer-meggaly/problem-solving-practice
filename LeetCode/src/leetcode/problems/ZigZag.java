package leetcode.problems;

public class ZigZag {

	// https://leetcode.com/problems/zigzag-conversion/

	public String convert(String s, int numRows) {
		if (numRows < 1)
			return "";
		StringBuilder[] rows = new StringBuilder[numRows];
		for (int i = 0; i < numRows; i++)
			rows[i] = new StringBuilder();

		int sIdx = 0;
		boolean topDown = true;
		while (sIdx < s.length()) {
			for (int r = 0; topDown && r < numRows && sIdx < s.length(); r++) {
				rows[r].append(s.charAt(sIdx++));
			}
			for (int r = 1; !topDown && r < numRows - 1
					&& sIdx < s.length(); r++) {
				rows[numRows - r - 1].append(s.charAt(sIdx++));
			}
			topDown = !topDown;
		}

		StringBuilder output = new StringBuilder();
		for (StringBuilder sb : rows)
			output.append(sb.toString());
		return output.toString();
	}

	public static void main(String[] args) {
		System.out.println(new ZigZag().convert("PAYPPALISHIRING", 5)); // PSAIHYLIGPARNPI
		System.out.println(new ZigZag().convert("PAYPALISHIRING", 4)); // PINALSIGYAHRPI
		System.out.println(new ZigZag().convert("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR
		System.out.println(new ZigZag().convert("PAYPALISHIRING", 1)); // PAYPALISHIRING
		System.out.println(new ZigZag().convert("PAY", 3)); // PAY
		System.out.println(new ZigZag().convert("PAY", 5)); // PAY
		System.out.println(new ZigZag().convert("ABC", 2)); // ACB
	}
}

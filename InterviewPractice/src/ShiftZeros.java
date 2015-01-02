import java.util.Arrays;

public class ShiftZeros {

	public static void shiftZerosRight(Integer[] numbers) {
		if (numbers == null || numbers.length < 2)
			return;

		int idx = 0;
		for (; idx < numbers.length; idx++) {
			if (numbers[idx] == 0)
				break;
		}

		int firstZero = idx++;
		for (; idx < numbers.length; idx++) {
			if (numbers[idx] != 0) {
				numbers[firstZero++] = numbers[idx];
				numbers[idx] = 0;
			}
		}
	}

	public static void main(String[] args) {
		Integer[][] inputs = new Integer[][] {
				new Integer[] { 1, 0, 2, 0, 3, 0, 0, 0, 5 },
				new Integer[] { 1, 0, 2, 0, 3, 0, 5 },
				new Integer[] { 0, 0, 0, 0 }, new Integer[] { 1, 2, 3, 4 },
				new Integer[] { 0, 1 }, new Integer[] { 0, 0 } };

		Integer[][] outputs = new Integer[][] {
				new Integer[] { 1, 2, 3, 5, 0, 0, 0, 0, 0 },
				new Integer[] { 1, 2, 3, 5, 0, 0, 0 },
				new Integer[] { 0, 0, 0, 0 }, new Integer[] { 1, 2, 3, 4 },
				new Integer[] { 1, 0 }, new Integer[] { 0, 0 } };

		for (int i = 0; i < inputs.length; i++) {
			Integer[] in = inputs[i];
			Integer[] ot = outputs[i];
			System.out.println("Input   : " + Arrays.asList(in));
			shiftZerosRight(in);
			System.out.println("Expected: " + Arrays.asList(ot));
			System.out.println("Found   : " + Arrays.asList(in));
		}

	}
}

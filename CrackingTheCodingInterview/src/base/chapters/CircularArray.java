package base.chapters;

import java.util.Iterator;

public class CircularArray<T> implements Iterable<T> {

	private final T[] array;
	private int rotateRight = 0;

	public CircularArray(T[] array) {
		this.array = array;
	}

	public void setRotateRightOffset(int rotateRight) {
		int sign = (rotateRight < 0) ? -1 : 1;
		this.rotateRight = Math.abs(rotateRight) % array.length;
		this.rotateRight *= sign;
	}

	@Override
	public Iterator<T> iterator() {
		return new CircularIterator();
	}

	private final class CircularIterator implements Iterator<T> {

		private int i = 0;

		@Override
		public boolean hasNext() {
			return i < array.length;
		}

		@Override
		public T next() {
			int N = array.length;
			int rotatedI = i - rotateRight + N;
			i++;
			return array[rotatedI % N];
		}

	}

	public static <O> void print(CircularArray<O> ca) {
		for (O obj : ca) {
			System.out.print(obj + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Integer[] nums = new Integer[] { 1, 2, 3, 4, 5 };
		CircularArray<Integer> ca = new CircularArray<Integer>(nums);
		print(ca); // 1 2 3 4 5
		ca.setRotateRightOffset(2);
		print(ca); // 4 5 1 2 3
		ca.setRotateRightOffset(-4);
		print(ca); // 5 1 2 3 4
		ca.setRotateRightOffset(8);
		print(ca); // 3 4 5 1 2
		ca.setRotateRightOffset(-8);
		print(ca); // 4 5 1 2 3
		ca.setRotateRightOffset(0);
		print(ca); // 1 2 3 4 5
	}
}

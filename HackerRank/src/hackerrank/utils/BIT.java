package hackerrank.utils;

public class BIT {

	private final int N;
	private final int[] tree;

	public BIT(int N) {
		this.N = N;
		this.tree = new int[N + 1];
	}

	public void update(int i, int diff) {
		for (; i <= N; i += (i & -i))
			tree[i] += diff;
	}

	public long sum(int i) {
		long sum = 0L;
		for (; i > 0; i -= (i & -i))
			sum += tree[i];
		return sum;
	}
}

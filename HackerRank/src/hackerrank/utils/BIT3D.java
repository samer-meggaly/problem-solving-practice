package hackerrank.utils;
public class BIT3D {
	private int N;
	private int[][][] oldW;
	private long[][][] tree;

	public BIT3D(int N) {
		this.N = N;
		oldW = new int[N + 1][N + 1][N + 1];
		tree = new long[N + 1][N + 1][N + 1];
	}

	private void update(int x, int y, int z, int W) {
		int dW = W - oldW[x][y][z];
		updateDiff(x, y, z, dW);
		oldW[x][y][z] = W;
	}

	public void updateDiff(int x, int y, int z, int dW) {
		for (int xi = x; xi <= N; xi += (xi & -xi)) {
			for (int yi = y; yi <= N; yi += (yi & -yi)) {
				for (int zi = z; zi <= N; zi += (zi & -zi)) {
					tree[xi][yi][zi] += dW;
				}
			}
		}
	}

	public long sum(int x, int y, int z) {
		long sum = 0L;
		for (int xi = x; xi > 0; xi -= (xi & -xi)) {
			for (int yi = y; yi > 0; yi -= (yi & -yi)) {
				for (int zi = z; zi > 0; zi -= (zi & -zi)) {
					sum += tree[xi][yi][zi];
				}
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		int N = 10;
		BIT3D bit = new BIT3D(N);
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for (int z = 1; z <= N; z++) {
					bit.update(x, y, z, 1);
				}
			}
		}

		System.out.println(bit.sum(6, 3, 9)
				- bit.sum(6, 3, 1) 
				- bit.sum(6, 1, 9) 
				- bit.sum(3, 3, 9)
				+ bit.sum(6, 1, 1)
				+ bit.sum(3, 3, 1)
				+ bit.sum(3, 1, 9)
				- bit.sum(3, 1, 1));
		

	}
}
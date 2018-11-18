import java.util.Scanner;

public class CubeSummation {

	private static class BIT3D {
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
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = Integer.parseInt(scanner.nextLine());
		for (int t = 0; t < T; t++) {
			String[] NM = scanner.nextLine().trim().split("\\s");
			int N = Integer.parseInt(NM[0]);
			int M = Integer.parseInt(NM[1]);
			BIT3D bit = new BIT3D(N);
			for (int m = 0; m < M; m++) {
				String[] q = scanner.nextLine().trim().split("\\s");
				if (q[0].equals("UPDATE")) {
					bit.update(Integer.parseInt(q[1]), Integer.parseInt(q[2]),
							Integer.parseInt(q[3]), Integer.parseInt(q[4]));
				} else {
					int x1_1 = Integer.parseInt(q[1]) - 1;
					int y1_1 = Integer.parseInt(q[2]) - 1;
					int z1_1 = Integer.parseInt(q[3]) - 1;
					int x2 = Integer.parseInt(q[4]);
					int y2 = Integer.parseInt(q[5]);
					int z2 = Integer.parseInt(q[6]);
					long sum = bit.sum(x2, y2, z2);
					sum -= bit.sum(x2, y2, z1_1);
					sum -= bit.sum(x2, y1_1, z2);
					sum -= bit.sum(x1_1, y2, z2);
					sum += bit.sum(x2, y1_1, z1_1);
					sum += bit.sum(x1_1, y2, z1_1);
					sum += bit.sum(x1_1, y1_1, z2);
					sum -= bit.sum(x1_1, y1_1, z1_1);
					System.out.println(sum);
				}
			}
		}
		scanner.close();
	}
}
package hackerrank.utils;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Queue;

public class RTree1D {
	static class Node {
		boolean leaf;
		int s, t;
		double m;
		Node l, r;

		Node(int s, int t, boolean leaf) {
			this.leaf = leaf;
			this.s = s;
			this.t = t;
			m = ((t - s) / 2.0) + s;
		}

		Node(Node l, Node r) {
			this(Math.min(l.s, r.s), Math.max(l.t, r.t), false);
			this.l = l;
			this.r = r;
		}

		boolean overlap(int ss, int tt) {
			return !(ss > this.t || this.s > tt);
		}

		@Override
		public String toString() {
			return String.format("(%02d : %02d)", s, t);
		}
	}

	private static Node buildTree(Node[] leaves) {
		Arrays.sort(leaves, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				return Double.compare(n1.m, n2.m);
			}
		});

		Queue<Node> level = new ArrayDeque<Node>();
		for (Node n : leaves)
			level.offer(n);
		while (level.size() > 1) {
			int levelSize = level.size();
			for (int i = 0; i < (levelSize >> 1); i++) {
				Node n1 = level.poll();
				Node n2 = level.poll();
				Node n12 = new Node(n1, n2);
				level.offer(n12);
			}
			if (levelSize % 2 == 1)
				level.offer(level.poll());
		}

		return level.poll();
	}

	private static int countOverlaps(Node root, int ss, int tt) {
		if (root.overlap(ss, tt)) {
			if (root.leaf)
				return 1;
			else
				return countOverlaps(root.l, ss, tt)
						+ countOverlaps(root.r, ss, tt);
		}
		return 0;
	}

	private final Node root;

	public RTree1D(int[][] ranges) {
		Node[] leaves = new Node[ranges.length];
		for (int i = 0; i < ranges.length; i++) {
			leaves[i] = new Node(ranges[i][0], ranges[i][1], true);
		}
		root = buildTree(leaves);
	}

	public static void main(String[] args) {
		int[][] ranges = new int[][] { { 1, 3 }, { 7, 8 }, { 9, 14 }, { 4, 7 },
				{ 8, 10 }, { 11, 16 }, { 17, 19 }, { 2, 5 }, { 6, 12 },
				{ 13, 15 }, { 16, 18 }, };
		RTree1D tree = new RTree1D(ranges);
		System.out.println(tree.root);
		System.out.println(countOverlaps(tree.root, 10, 11));
	}
}

package leetcode.problems.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import leetcode.utils.LeetPrinter;

public class MinimumHeightTrees {

	/*
	 * https://leetcode.com/problems/minimum-height-trees/
	 * 
	 * General information about the problem. The graph is connected, acyclic,
	 * and with no parallel paths.
	 * 
	 * Note that, given the aforementioned graph properties, a graph can have 1
	 * or 2 MHT(s). One simple example of this rule is the SingleLinkedList
	 * graph (0 -> 1 -> 2 -> 3 -> 4).
	 * 
	 * Also that the number of edges is O(N) <= (n - 1), otherwise will not be
	 * acyclic.
	 */

	private List<List<Integer>> graph;

	private void buildGraph(int n, int[][] edges) {
		this.graph = new ArrayList<List<Integer>>(n);
		for (int i = 0; i < n; i++)
			this.graph.add(new ArrayList<Integer>(n));
		for (int[] edge : edges) {
			this.graph.get(edge[0]).add(edge[1]);
			this.graph.get(edge[1]).add(edge[0]);
		}
	}

	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		/*
		 * Better O(N) solution that passes on LeetCode. This solution is not my
		 * OWN thought. This solution works by deleting leaf nodes level by
		 * level.
		 * 
		 * Given that graph description there will be at least 1 node with
		 * degree. So the solution proceeds by deleting 1-degree nodes and
		 * decrementing the degree of its adjacent nodes. Whenever a nodes
		 * degree hits 1, it will be added on the list to be deleted and so on.
		 */
		if (n == 1)
			return Arrays.asList(0);

		buildGraph(n, edges);
		int[] degree = new int[n];
		List<Integer> degree1Vertices = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			degree[i] = graph.get(i).size();
			if (degree[i] == 1)
				degree1Vertices.add(i);
		}

		List<Integer> candidRoots = new ArrayList<Integer>();
		while (!degree1Vertices.isEmpty()) {
			candidRoots = new ArrayList<Integer>(degree1Vertices);
			degree1Vertices.clear();

			for (int u : candidRoots) {
				degree[u] = 0; // mark the vertex itself as deleted
				// decrement the degree of all its adjacent vertices
				for (int v : graph.get(u)) {
					// check if v wasn't already deleted
					if (degree[v] > 0) {
						degree[v]--;
						if (degree[v] == 1)
							degree1Vertices.add(v);
					}
				}
			}
		}

		return candidRoots;
	}

	private int[] visitor;

	public List<Integer> findMinHeightTreesByMidOfLongestPath(int n,
			int[][] edges) {
		/*
		 * O(N) solution that passes on LeetCode. This solution is not my OWN
		 * thought. This solution is based on the fact that the root of the MHT
		 * is in the middle of any of the longest paths in the graph.
		 * 
		 * So we try to find one of the longest paths of the graph and return
		 * its middle(s) as MHT root(s).
		 */
		buildGraph(n, edges);
		int v = getFarthestVertexFromS(0, n);
		int u = getFarthestVertexFromS(v, n);

		List<Integer> longestPath = new ArrayList<Integer>();
		while (visitor[u] != u) {
			longestPath.add(u);
			u = visitor[u];
		}
		longestPath.add(u);

		int m = longestPath.size() >> 1;
		if (longestPath.size() % 2 == 0) {
			return Arrays.asList(longestPath.get(m - 1), longestPath.get(m));
		} else {
			return Arrays.asList(longestPath.get(m));
		}
	}

	private int getFarthestVertexFromS(int s, int n) {
		// Visiting vertices in a BreadthFirst manner.
		this.visitor = new int[n];
		Arrays.fill(visitor, -1);
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(s);
		visitor[s] = s;
		int farthestVertex = s;
		while (!queue.isEmpty()) {
			int v = queue.poll();
			farthestVertex = v;
			for (int u : graph.get(v)) {
				if (visitor[u] == -1) {
					visitor[u] = v;
					queue.offer(u);
				}
			}
		}

		return farthestVertex;
	}

	private boolean[] visited;

	public List<Integer> findMinHeightTreesByDepthOfAllTrees(int n,
			int[][] edges) {
		/*
		 * This solution gives TLE on LeetCode. O(N^2) brute-force solution.
		 */
		buildGraph(n, edges);

		int minTreeHeight = Integer.MAX_VALUE;
		List<Integer> mthRoots = new ArrayList<Integer>(n);
		for (int i = 0; i < n; i++) {
			visited = new boolean[n];
			visited[i] = true;
			int minForRootI = getTreeHeightAtRoot(i);
			if (minForRootI < minTreeHeight) {
				mthRoots = new ArrayList<Integer>();
				minTreeHeight = minForRootI;
			}

			if (minForRootI == minTreeHeight)
				mthRoots.add(i);
		}
		return mthRoots;
	}

	private int getTreeHeightAtRoot(int v) {
		int maxDepth = 0;
		for (int u : graph.get(v)) {
			if (!visited[u]) {
				visited[u] = true;
				maxDepth = Math.max(maxDepth, getTreeHeightAtRoot(u));
			}
		}

		return maxDepth + 1;
	}

	public static void main(String[] args) {
		int n, edges[][];
		List<Integer> expected;
		n = 4;
		edges = new int[][] { { 1, 0 }, { 1, 2 }, { 1, 3 } };
		expected = Arrays.asList(new Integer[] { 1 });
		LeetPrinter.assertPrint(expected,
				new MinimumHeightTrees().findMinHeightTrees(n, edges),
				expected.toString() + " but found: ");
		n = 6;
		edges = new int[][] { { 0, 3 }, { 1, 3 }, { 2, 3 }, { 4, 3 },
				{ 5, 4 } };
		expected = Arrays.asList(new Integer[] { 3, 4 });
		LeetPrinter.assertPrint(expected,
				new MinimumHeightTrees().findMinHeightTrees(n, edges),
				expected.toString() + " but found: ");
		n = 10;
		edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 },
				{ 5, 6 }, { 6, 7 }, { 7, 8 }, { 8, 9 } };
		expected = Arrays.asList(new Integer[] { 4, 5 });
		LeetPrinter.assertPrint(expected,
				new MinimumHeightTrees().findMinHeightTrees(n, edges),
				expected.toString() + " but found: ");
		System.out.println("Done Successfully");
	}
}

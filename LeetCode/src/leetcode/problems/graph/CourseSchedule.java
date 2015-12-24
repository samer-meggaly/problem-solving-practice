package leetcode.problems.graph;

import java.util.ArrayList;
import java.util.List;

import leetcode.utils.LeetPrinter;

public class CourseSchedule {

	/*
	 * https://leetcode.com/problems/course-schedule/
	 * https://leetcode.com/problems/course-schedule-ii/
	 */

	byte[] visited;
	List<List<Integer>> graph;
	List<Integer> topological = new ArrayList<Integer>();

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		graph = buildGraphAdjList(numCourses, prerequisites);
		visited = new byte[numCourses];
		for (int i = 0; i < numCourses; i++) {
			if (visited[i] == 0) {
				if (!dfs(i))
					return new int[0];
			}
		}
		int[] order = new int[topological.size()];
		for (int i = 0; i < numCourses; i++)
			order[i] = topological.get(i);
		return order;
	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		graph = buildGraphAdjList(numCourses, prerequisites);
		visited = new byte[numCourses];
		for (int i = 0; i < numCourses; i++) {
			if (visited[i] == 0) {
				if (!dfs(i))
					return false;
			}
		}
		return true;
	}

	private boolean dfs(int s) {
		visited[s] = 1;
		for (Integer v : graph.get(s)) {
			if (visited[v] == 0) {
				if (!dfs(v))
					return false;
			} else if (visited[v] == 1)
				return false;
		}
		visited[s] = 2;
		topological.add(s);
		return true;
	}

	private List<List<Integer>> buildGraphAdjList(int numVerts, int[][] edges) {
		List<List<Integer>> graph = new ArrayList<List<Integer>>(numVerts);
		for (int i = 0; i < numVerts; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int[] e : edges) {
			graph.get(e[0]).add(e[1]);
		}

		return graph;
	}

	public static void main(String[] args) {
		int numCourses;
		int[][] preCourses;
		numCourses = 3;
		preCourses = new int[][] {};
		LeetPrinter.assertPrint(true,
				new CourseSchedule().canFinish(numCourses, preCourses),
				"Case 1: ");
		System.out.println(LeetPrinter // [ 0, 1, 2 ]
				.str(new CourseSchedule().findOrder(numCourses, preCourses)));
		numCourses = 2;
		preCourses = new int[][] { { 1, 0 } };
		LeetPrinter.assertPrint(true,
				new CourseSchedule().canFinish(numCourses, preCourses),
				"Case 2: ");
		System.out.println(LeetPrinter // [0, 1]
				.str(new CourseSchedule().findOrder(numCourses, preCourses)));
		numCourses = 2;
		preCourses = new int[][] { { 1, 0 }, { 0, 1 } };
		LeetPrinter.assertPrint(false,
				new CourseSchedule().canFinish(numCourses, preCourses),
				"Case 3: ");
		System.out.println(LeetPrinter // []
				.str(new CourseSchedule().findOrder(numCourses, preCourses)));
		numCourses = 4;
		preCourses = new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
		LeetPrinter.assertPrint(true,
				new CourseSchedule().canFinish(numCourses, preCourses),
				"Case 4: ");
		System.out.println(LeetPrinter // [0, 1, 2, 3] OR [0, 2, 1, 3]
				.str(new CourseSchedule().findOrder(numCourses, preCourses)));
		System.out.println("Done Successfully");
	}
}

package leetcode.problems;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class MaxCollinearPoints {

	public static class Point {
		int x;
		int y;

		Point() {
			x = 0;
			y = 0;
		}

		Point(int a, int b) {
			x = a;
			y = b;
		}

		public static Point[] toPoints(int[][] coords) {
			Point[] points = new Point[coords.length];
			for (int i = 0; i < points.length; i++) {
				points[i] = new Point(coords[i][0], coords[i][1]);
			}
			return points;
		}

		public static Point[] toPoints(File fromFile)
				throws FileNotFoundException {
			List<Point> list = new ArrayList<Point>();
			Scanner scanner = new Scanner(new BufferedInputStream(
					new FileInputStream(fromFile)));
			while (scanner.hasNextLine()) {
				String[] coords = scanner.nextLine().trim().split(",");
				list.add(new Point(Integer.parseInt(coords[0]), Integer
						.parseInt(coords[1])));
			}
			scanner.close();
			Point[] points = new Point[list.size()];
			return list.toArray(points);
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}

	public class Solution {
		public int maxPoints(Point[] points) {
			return solve3(points);
		}

		public int solve3(Point[] points) {
			if (points == null || points.length < 1)
				return 0;

			int max = -1;
			for (int i = 0; i < points.length; i++) {
				int duplicates = 1; // for the point [i] itself
				Map<Line, Integer> linesSet = new HashMap<Line, Integer>();
				for (int j = i + 1; j < points.length; j++) {
					if (points[i].x == points[j].x
							&& points[i].y == points[j].y) {
						duplicates++;
					} else {
						Line l = new Line(points[i], points[j]);
						if (!linesSet.containsKey(l))
							linesSet.put(l, 0);
						linesSet.put(l, linesSet.get(l) + 1);
					}
				}

				int localMax = 0;
				for (Integer count : linesSet.values()) {
					localMax = Math.max(localMax, count);
				}
				max = Math.max(max, localMax + duplicates);
			}

			return max;
		}

		public int solve2(Point[] points) {
			if (points == null || points.length < 1)
				return 0;
			Map<Line, Set<Point>> linesSet = new HashMap<Line, Set<Point>>();
			int max = 1;
			for (int i = 0; i < points.length; i++) {
				for (int j = 0; j < points.length; j++) {
					if (i == j)
						continue;
					Line l = new Line(points[i], points[j]);
					if (!linesSet.containsKey(l))
						linesSet.put(l, new HashSet<Point>());
					Set<Point> collinears = linesSet.get(l);
					collinears.add(points[j]);
					max = Math.max(max, collinears.size());
				}
			}

			return max;
		}

		private class Line {
			boolean isVertical;
			/** Slope of the line */
			double m;
			/** The y-intercept of the line */
			double c;

			public Line(Point p1, Point p2) {
				isVertical = p1.x == p2.x;
				if (isVertical) {
					m = Double.POSITIVE_INFINITY;
					c = p1.x;
				} else {
					m = (p1.y - p2.y);
					m /= (p1.x - p2.x);
					if (p1.y == p2.y)
						m = 0.0;
					c = p1.y - (m * p1.x);
				}
			}

			@Override
			public int hashCode() {
				if (isVertical) {
					return (int) c;
				} else {
					int result = 1;
					result = result * 31 + (int) (1E8 * m);
					result = result * 31 + (int) (1E8 * c);
					return result;
				}
			}

			@Override
			public boolean equals(Object other) {
				if (other == null || !(other instanceof Line))
					return false;
				Line o = (Line) other;
				if (isVertical != o.isVertical) {
					return false;
				} else if (isVertical) {
					return c == o.c;
				} else {
					return Math.abs(m - o.m) < 10E-8
							&& Math.abs(c - o.c) < 10E-8;
				}
			}
		}

		public int solve1(Point[] points) {
			if (points == null || points.length < 1)
				return 0;
			int maxCount = 1;
			for (int i = 0; i < points.length; i++) {
				for (int j = i + 1; j < points.length; j++) {
					double angle = Math.toDegrees(Math.atan2(points[i].y
							- points[j].y, points[i].x - points[j].x));
					double slope = (points[i].y - points[j].y);
					slope /= (points[i].x - points[j].x);
					double yIntercept = points[i].y - (slope * points[i].x);
					int count = 2;
					for (int k = 0; k < points.length; k++) {
						if (k != i && k != j) {
							if (angle == 90 || angle == -90) {
								if (points[k].x == points[i].x)
									count++;
							} else if (angle == 0 || angle == 180) {
								if (points[k].y == points[i].y)
									count++;
							} else {
								if (Math.abs(points[k].y
										- (slope * points[k].x + yIntercept)) < 10E-8)
									count++;
							}
						}
					}

					maxCount = Math.max(maxCount, count);
				}
			}

			return maxCount;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Solution solver = new MaxCollinearPoints().new Solution();
		Point[] points;
		points = Point
				.toPoints(new int[][] { new int[] { 0, 0 }, new int[] { 0, 1 },
						new int[] { 0, 5 }, new int[] { 0, 6 },
						new int[] { 0, 2 }, new int[] { 3, 3 },
						new int[] { 1, -1 }, new int[] { 1, 0 },
						new int[] { -1, 0 }, new int[] { -2, 0 }, });
//		points = Point.toPoints(new int[][] { new int[] { 0, 0 },
//				new int[] { 1, 0 } });
//		points = Point
//				.toPoints(new int[][] { new int[] { 3, 1 },
//						new int[] { 12, 3 }, new int[] { 3, 1 },
//						new int[] { -6, -1 } });
//		points = Point.toPoints(new File("data/maxcollinear/input3-22.txt"));
		System.out.println("Points: " + Arrays.asList(points));
		System.out.println("Max Collinear: " + solver.maxPoints(points));
		System.out.println();
	}
}

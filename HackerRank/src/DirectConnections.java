import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class DirectConnections {

	private static final int PRIME_MOD = 1000000007;

	private static class BIT {
		private long[] tree;

		public BIT(int size) {
			this.tree = new long[size + 1]; // 1-based indexing
		}

		public long sumTo(int index) {
			index++; // 1-based indexing
			long sum = 0L;
			while (index > 0) {
				sum += tree[index];
				index -= index & (-index);
			}
			return sum;
		}

		public void update(int index, int val) {
			index++;
			while (index < tree.length) {
				tree[index] += val;
				index += index & (-index);
			}
		}
	}

	private static class City {
		int population;
		int coordinate;
		int coordSortIdx;
	}

	private static int setCoordinatesIndices(City[] cities) {
		int[][] coordCityIndex = new int[cities.length][2];
		for (int i = 0; i < cities.length; i++)
			coordCityIndex[i] = new int[] { cities[i].coordinate, i };
		Arrays.sort(coordCityIndex, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}

		});

		int coordSortIdx = -1;
		int prevCoordValue = -1;
		for (int[] cci : coordCityIndex) {
			int coord = cci[0];
			int cityIdx = cci[1];
			if (coord != prevCoordValue) {
				coordSortIdx++;
				prevCoordValue = coord;
			}
			cities[cityIdx].coordSortIdx = coordSortIdx;
		}

		return coordSortIdx;
	}

	private static long wires(City[] cities) {
		// sort cities in ascending order of population
		Arrays.sort(cities, new Comparator<City>() {

			@Override
			public int compare(City o1, City o2) {
				return o1.population - o2.population;
			}

		});

		// set ascending-ordering-sorting index of every city coordinate
		int maxCoordSortIdx = setCoordinatesIndices(cities);
		int N = maxCoordSortIdx + 1; // length is 1 + maxIndex
		BIT coords = new BIT(N);
		BIT countCities = new BIT(N);
		long wires = 0L;
		for (City c : cities) {
			long sumCoordsBeforeC = coords.sumTo(c.coordSortIdx - 1);
			long countCitiesBeforeC = countCities.sumTo(c.coordSortIdx - 1);
			long wiresBeforeC = c.population
					* (c.coordinate * countCitiesBeforeC - sumCoordsBeforeC);
			wiresBeforeC %= PRIME_MOD;

			long sumCoordsAfterC = coords.sumTo(maxCoordSortIdx)
					- coords.sumTo(c.coordSortIdx);
			long countCitiesAfterC = countCities.sumTo(maxCoordSortIdx)
					- countCities.sumTo(c.coordSortIdx);
			long wiresAfterC = c.population
					* (sumCoordsAfterC - c.coordinate * countCitiesAfterC);
			wiresAfterC %= PRIME_MOD;

			wires += wiresBeforeC + wiresAfterC;
			wires %= PRIME_MOD;

			coords.update(c.coordSortIdx, c.coordinate);
			countCities.update(c.coordSortIdx, 1);
		}

		return wires;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		for (int t = 0; t < T; t++) {
			int N = scanner.nextInt();
			City[] cities = new City[N];
			for (int i = 0; i < N; i++) {
				cities[i] = new City();
				cities[i].coordinate = scanner.nextInt();
			}
			for (int i = 0; i < N; i++) {
				cities[i].population = scanner.nextInt();
			}
			System.out.println(wires(cities));
		}
		scanner.close();
	}

}

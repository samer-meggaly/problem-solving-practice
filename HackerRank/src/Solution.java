import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

	public static class Movie {
		private final int movieId;
		private final float rating;
		private List<Movie> similarMovies; // Similarity is bidirectional

		public Movie(int movieId, float rating) {
			this.movieId = movieId;
			this.rating = rating;
			similarMovies = new ArrayList<Movie>();
		}

		public int getId() {
			return movieId;
		}

		public float getRating() {
			return rating;
		}

		public void addSimilarMovie(Movie movie) {
			similarMovies.add(movie);
			movie.similarMovies.add(this);
		}

		public List<Movie> getSimilarMovies() {
			return similarMovies;
		}
	}

	/*
	 * @param movie Current movie.
	 * 
	 * @param numTopRatedSimilarMovies the maximum number of recommended movies
	 * to return.
	 * 
	 * @return List of top rated similar movies.
	 * 
	 * Assumptions I made: TODO
	 *
	 * Description of my approach: TODO
	 *
	 * Runtime complexity of my approach: TODO
	 *
	 * Justification of runtime complexity: TODO
	 *
	 */
	public static List<Movie> getMovieRecommendations(Movie movie,
			int numTopRatedSimilarMovies) {

		if (movie == null || numTopRatedSimilarMovies < 1)
			return new ArrayList<Movie>();

		int initialCapacity = movie.getSimilarMovies().size();
		Set<Integer> visited = new HashSet<Integer>(initialCapacity);
		PriorityQueue<Movie> ratingMinHeap = new PriorityQueue<Movie>(
				initialCapacity, new Comparator<Movie>() {

					@Override
					public int compare(Movie o1, Movie o2) {
						return Float.compare(o1.getRating(), o2.getRating());
					}
				});

		Queue<Movie> bfsQueue = new ArrayDeque<Movie>();
		bfsQueue.offer(movie);
		visited.add(movie.getId());

		while (!bfsQueue.isEmpty()) {
			Movie srcMovie = bfsQueue.poll();
			for (Movie simMovie : srcMovie.getSimilarMovies()) {
				if (!visited.contains(simMovie.getId())) {
					visited.add(simMovie.getId());
					bfsQueue.offer(simMovie);
					ratingMinHeap.offer(simMovie);
                    if (ratingMinHeap.size() > numTopRatedSimilarMovies)
                        ratingMinHeap.poll();
				}
			}
		}

		return new ArrayList<Movie>(ratingMinHeap);
	}

	private static void checkAgainstTopN(PriorityQueue<Movie> ratingMinHeap,
			Movie movie, int N) {
		if (ratingMinHeap.size() <= N || Float.compare(movie.getRating(),
				ratingMinHeap.peek().getRating()) > 0) {
			ratingMinHeap.offer(movie);
		}

		if (ratingMinHeap.size() > N)
			ratingMinHeap.poll();
	}

	public static void main(String[] args) throws IOException {
		
		String[] keys = new String[] {"breakfast", "pool", "bar"};
		StringBuilder regex = new StringBuilder();
		for (int i = 0; i < keys.length; i++) {
			regex.append('(');
			regex.append(keys[i]);
			regex.append(')');
			if (i < keys.length - 1)
				regex.append('|');
		}
		System.out.println(regex.toString());
		Pattern pattern = Pattern.compile(regex.toString());
		String review = "This hotel is great. You can have lunch or breakfast. Like bars, it has great bar! And the pool";
		review = review.replaceAll("[.|,]", "");
		System.out.println(review);
//		final Map<Integer, Movie> movieMap = new HashMap<Integer, Movie>();
//		Movie rootMovie = null;
//		int numTopRatedSimilarMovies = 0;
//
//		final Scanner in = new Scanner(System.in);
//		in.useLocale(new Locale("en", "US"));
//
//		for (int i = 0; i < 9; i++) {
//			System.out.println("reading input");
//			final String type = in.next();
//
//			if (type.equals("movie")) {
//				final int id = in.nextInt();
//				final float rating = in.nextFloat();
//				movieMap.put(id, new Movie(id, rating));
//			} else if (type.equals("similar")) {
//				final Movie movie1 = movieMap.get(in.nextInt());
//				final Movie movie2 = movieMap.get(in.nextInt());
//				movie1.addSimilarMovie(movie2);
//			} else if (type.equals("params")) {
//				rootMovie = movieMap.get(in.nextInt());
//				numTopRatedSimilarMovies = in.nextInt();
//			} else {
//				// ignore comments and whitespace
//			}
//		}
//		System.out.println("got input");
//		final List<Movie> result = getMovieRecommendations(rootMovie,
//				numTopRatedSimilarMovies);
//		System.out.println("Stuck");
//		String output = "result";
//
//		if (result == null) {
//			output += " <null>";
//		} else {
//			Collections.sort(result, new Comparator() {
//				@Override
//				public int compare(Object m1, Object m2) {
//					return ((Movie) m1).getId() - ((Movie) m2).getId();
//				}
//			});
//
//			for (Movie m : result) {
//				output += " ";
//				output += m.getId();
//			}
//		}
//
////		final String fileName = System.getenv("OUTPUT_PATH");
////		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
////		bw.write(output);
////		bw.newLine();
////		bw.close();
//		
//		System.out.println(output);
	}
}

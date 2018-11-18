package leetcode.problems;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MedianDataStream {

	// https://leetcode.com/submissions/detail/58429545/

	private final Queue<Integer> lftMaxQueue;
	private final Queue<Integer> rgtMinQueue;

	public MedianDataStream() {
		lftMaxQueue = new PriorityQueue<Integer>(10, new Comparator<Integer>() {

			@Override
			public int compare(Integer i1, Integer i2) {
				return -1 * Integer.compare(i1, i2);
			}
		});
		rgtMinQueue = new PriorityQueue<Integer>();
	}

	// Adds a number into the data structure.
	public void addNum(int num) {
		Integer maxLft = lftMaxQueue.peek();
		Integer minRgt = rgtMinQueue.peek();
		if (maxLft != null && num <= maxLft) {
			if (lftMaxQueue.size() > rgtMinQueue.size())
				rgtMinQueue.offer(lftMaxQueue.poll());
			lftMaxQueue.offer(num);
		} else if (minRgt != null && num >= minRgt) {
			if (rgtMinQueue.size() > lftMaxQueue.size())
				lftMaxQueue.offer(rgtMinQueue.poll());
			rgtMinQueue.offer(num);
		} else {
			// num is in the middle of the 2 haves of the stored stream.
			if (lftMaxQueue.size() > rgtMinQueue.size())
				rgtMinQueue.offer(num);
			else
				lftMaxQueue.offer(num);
		}
	}

	// Returns the median of current data stream
	public double findMedian() {
		if (lftMaxQueue.size() > rgtMinQueue.size())
			return lftMaxQueue.peek();
		else if (rgtMinQueue.size() > lftMaxQueue.size())
			return rgtMinQueue.peek();
		else
			return (lftMaxQueue.peek() / 2.0) + (rgtMinQueue.peek() / 2.0);
	}
}

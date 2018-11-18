package leetcode.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityVote {

	/*
	 * https://leetcode.com/problems/majority-element/
	 * https://leetcode.com/problems/majority-element-ii/
	 */

	public int majorityElement_V1(int[] nums) {
		// hashmap count - pass in 35 ms
		Map<Integer, Integer> count = new HashMap<Integer, Integer>(
				nums.length);
		for (int n : nums) {
			if (count.containsKey(n))
				count.put(n, count.get(n) + 1);
			else
				count.put(n, 1);
		}

		int mid = nums.length / 2;
		for (int n : count.keySet()) {
			if (count.get(n) > mid)
				return n;
		}

		throw new IllegalArgumentException();
	}

	public int majorityElement_V2(int[] nums) {
		// majority bit count - pass in 9 ms
		int me = 0;
		int mid = nums.length >>> 1;
		for (int i = 0; i < 32; i++) {
			int bitCount = 0;
			int mask = 1 << i;
			for (int n : nums) {
				if ((n & mask) != 0)
					bitCount++;
			}
			if (bitCount > mid)
				me |= mask;
		}
		return me;
	}

	public int majorityElement(int[] nums) {
		// Boyer-Moore's majority vote count algorithms - pass in 3 ms
		int me = nums[0];
		int mid = nums.length >>> 1;
		int meCount = 1;
		for (int i = 1; i < nums.length && meCount <= mid; i++) {
			if (meCount == 0) {
				meCount++;
				me = nums[i];
			} else if (me == nums[i]) {
				meCount++;
			} else {
				meCount--;
			}
		}
		return me;
	}

	private int count(int[] nums, int me) {
		int count = 0;
		int third = nums.length / 3;
		for (int i = 0; i < nums.length && count <= third; i++)
			if (nums[i] == me)
				count++;
		return count;
	}

	public List<Integer> majorityElements(int[] nums) {
		// Modified Boyer-Moore algorithm - pass in 5 ms
		int me1 = -1;
		int me1Count = 0;
		int me2 = -1;
		int me2Count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (me1Count == 0) {
				if (me2Count > 0 && me2 == nums[i])
					me2Count++;
				else {
					me1Count++;
					me1 = nums[i];
				}
			} else if (me2Count == 0) {
				if (me1Count > 0 && me1 == nums[i])
					me1Count++;
				else {
					me2Count++;
					me2 = nums[i];
				}
			} else if (me1 == nums[i]) {
				me1Count++;
			} else if (me2 == nums[i]) {
				me2Count++;
			} else {
				me1Count--;
				me2Count--;
			}
		}

		List<Integer> majors = new ArrayList<Integer>();
		if (me1Count > 0 && count(nums, me1) > (nums.length / 3))
			majors.add(me1);
		if (me2Count > 0 && count(nums, me2) > (nums.length / 3))
			majors.add(me2);
		return majors;
	}

	public static void main(String[] args) {
		System.out.println(new MajorityVote() // [1, 2]
				.majorityElements(new int[] { 1, 2, 1, 2, 1, 2, 1, 2, 3, 3 }));
		System.out.println(new MajorityVote() // [1]
				.majorityElements(new int[] { 1, 0, 4, 1, 1, 2, 5, 6, 3, 1 }));
		System.out.println(new MajorityVote() // []
				.majorityElements(new int[] { 7, 0, 4, 1, 1, 2, 5, 6, 3, 1 }));
		System.out.println(new MajorityVote() // [1]
				.majorityElements(new int[] { 1, 2, 1, 0, 1, 2, 1, 0, 3, 3 }));
		System.out.println(new MajorityVote() // [1]
				.majorityElements(new int[] { 1, 2, 1, 0, 1, 2, 0, 3, 3, 1 }));
		System.out.println(new MajorityVote() // [1]
				.majorityElements(new int[] { 1, 2, 1, 1, 0, 2, 0, 3, 3, 1 }));
		System.out.println(new MajorityVote() // [1]
				.majorityElements(new int[] { 1, 2, 1, 1, 0, 2, 0, 3, 4, 1 }));
		System.out.println(new MajorityVote() // [1]
				.majorityElements(new int[] { 1, 2, 1, 4, 0, 2, 0, 3, 1, 1 }));
		System.out.println(new MajorityVote() // [1]
				.majorityElements(new int[] { 3, 2, 1, 4, 0, 2, 0, 1, 1, 1 }));
		System.out.println(new MajorityVote() // [1]
				.majorityElements(
						new int[] { 3, 2, 1, 4, 5, 0, 2, 0, 1, 1, 1 }));
		System.out.println(new MajorityVote() // [-1]
				.majorityElements(new int[] { 0, -1, 2, -1 }));
	}
}

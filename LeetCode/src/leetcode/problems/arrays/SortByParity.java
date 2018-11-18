package leetcode.problems.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * <a href=
 * "https://leetcode.com/problems/sort-array-by-parity/">https://leetcode.com/problems/sort-array-by-parity/</a>
 * 
 * @author Samer Makary
 *
 */
public class SortByParity {
    public int[] sortArrayByParity(int[] A) {
        return IntStream.of(A).boxed().sorted(new Comparator<Integer>() {
            public int compare(Integer int1, Integer int2) {
                return (int1 & 1) - (int2 & 1);
            }
        }).mapToInt(Integer::intValue).toArray();
    }

}

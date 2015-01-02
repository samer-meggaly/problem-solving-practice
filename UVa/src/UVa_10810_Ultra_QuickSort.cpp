//============================================================================
// Name        : UVa
// Author      : samer-meggaly
//============================================================================

# include <stdio.h>
# include <cstdio>
# include <stdlib.h>
# include <string.h>
# include <iostream>
# include <iomanip>
# include <sstream>
# include <vector>
# include <map>
# include <algorithm>
# include <math.h>
# include <queue>
# include <bitset>

using namespace std;

typedef unsigned long long ull;
typedef std::vector<std::vector<int> > vvint;
typedef std::vector<std::vector<double> > vvdouble;
typedef std::vector<std::vector<ull> > vvull;

# define REP_INC(i,f,t,s) \
	for(int i = int(f); i < int(t); i += int(s))

# define REP_DEC(i,f,t,s) \
	for(int i = int(f); i > int(t); i -= int(s))

# define EUC_DIST(x1,y1,x2,y2) \
	sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))

template<typename T>
std::string vec2str(std::vector<T> vi) {
	std::ostringstream oss;
	oss << "[ ";
	typename std::vector<T>::iterator itr;
	for (itr = vi.begin(); itr != vi.end(); itr++)
		oss << (*itr) << ((itr < vi.end()-1)? ", ":" ");
	oss << "]";
	return oss.str();
}

std::vector<int> mergeCount(std::vector<int> list, ull& swaps) {
	if (list.size() == 1) {
		swaps = 0L;
		return list;
	} else {
		int middle = list.size() / 2;
		std::vector<int> left(list.begin(), list.begin()+middle);
		std::vector<int> right(list.begin()+middle, list.end());
		ull leftSwaps = 0L, rightSwaps = 0L, mergeSwaps = 0L;
		left = mergeCount(left, leftSwaps);
		right = mergeCount(right, rightSwaps);

		// merging phase
		std::size_t lPtr = 0, rPtr = 0, mPtr = 0;
		while (lPtr != left.size()  && rPtr != right.size()) {
			if (left[lPtr] < right[rPtr]) {
				list[mPtr++] = left[lPtr++];
				mergeSwaps += rPtr;
			} else if (right[rPtr] < left[lPtr]) {
				list[mPtr++] = right[rPtr++];
			} else {
				list[mPtr++] = left[lPtr++];
			}
		}

		while (lPtr != left.size()) {
			list[mPtr++] = left[lPtr++];
			mergeSwaps += rPtr;
		}

		while (rPtr != right.size()) {
			list[mPtr++] = right[rPtr++];
		}
		swaps = leftSwaps + rightSwaps + mergeSwaps;
		return list;
	}
}

ull countMergeSwaps(std::vector<int> list) {
	if (list.size() <= 1) {
		return 0L;
	} else {
		ull swaps = 0L;
		mergeCount(list, swaps);
		return swaps;
	}
}

int ultra_quicksort() {
	int n;
	while (cin >> n && n > 0) {
		std::vector<int> list(n, 0);
		REP_INC(i,0,n,1)
			cin >> list[i];
		cout << countMergeSwaps(list) << endl;
	}
	return EXIT_SUCCESS;
}

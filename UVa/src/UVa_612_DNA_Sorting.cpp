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

struct DNAString {
	std::string dna;
	int inputOrder;
	int sortedness;

	DNAString(std::string dnaString, int n, int swaps) {
		dna = dnaString;
		inputOrder = n;
		sortedness = swaps;
	}
};

bool comp(const DNAString& s1, const DNAString& s2) {
	if (s1.sortedness != s2.sortedness)
		return s1.sortedness < s2.sortedness;
	else
		return s1.inputOrder < s2.inputOrder;
}

int countBubbleSwaps(std::string list, int L) {
	int swaps = 0;
	REP_INC(i,0,L,1) {
		REP_INC(j,0,L-i-1,1) {
			if (list[j] > list[j+1]) {
				int temp = list[j];
				list[j] = list[j+1];
				list[j+1] = temp;
				swaps++;
			}
		}
	}
	return swaps;
}

int dna_sorting() {
	int N, L, size;
	cin >> N;
	REP_INC(i,0,N,1) {
		if (i > 0)
			cout << endl;
		cin >> L >> size;
		std::vector<DNAString> list;
		string tempStr;
		REP_INC(j,0,size,1) {
			cin >> tempStr;
			list.push_back(DNAString(tempStr, j,
					countBubbleSwaps(std::string(tempStr.c_str()), L)));
		}
		std::sort(list.begin(), list.end(), comp);
		REP_INC(j,0,size,1) {
			cout << list[j].dna << endl;
		}
	}
	return EXIT_SUCCESS;
}

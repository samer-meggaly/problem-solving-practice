//============================================================================
// Name        : UVa
// Author      : samer-meggaly
//============================================================================

# include <stdio.h>
# include <stdlib.h>
# include <string.h>
# include <iostream>
# include <sstream>
# include <algorithm>
# include <vector>
# include <math.h>

using namespace std;

# define REP(i,f,t,s) \
	for(int i = int(f); i < int(t); i += int(s))

# define MAX_LEN 20
typedef unsigned long long ull;


int permutations() {
	ull factorials[MAX_LEN];
	factorials[0] = 1;
	for (ull i = 1; i < MAX_LEN; ++i)
		factorials[i] = i * factorials[i - 1];

	int N;
	char* line = NULL;
	size_t sz = 0;
	getline(&line, &sz, stdin);
	string line_str(line);
	istringstream iss(line_str);
	iss >> N;
	REP(n, 0, N, 1) {
		ssize_t ln = getline(&line, &sz, stdin);
		std::vector<char> chars;
		if (ln > 0) {
			REP(i,0,ln-1,1) chars.push_back(line[i]);
			std::sort(chars.begin(), chars.end());

			ull ord, modu;
			getline(&line, &sz, stdin);
			string line_str(line);
			istringstream iss(line_str);
			iss >> ord;
			char out_chars[ln];
			out_chars[--ln] = '\0';
			modu = ln - 1;
			REP(c, 0, ln, 1) {
				ull c_idx = ord / factorials[modu];
				out_chars[c] = chars[c_idx];
				chars.erase(chars.begin()+c_idx);
				ord %= factorials[modu];
				modu--;
			}

			cout << out_chars << endl;
		}
	}

	return EXIT_SUCCESS;
}

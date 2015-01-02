//============================================================================
// Name        : UVa
// Author      : samer-meggaly
//============================================================================

# include <stdio.h>
# include <stdlib.h>
# include <string.h>
# include <iostream>
# include <iomanip>
# include <sstream>
# include <vector>
# include <map>
# include <algorithm>
# include <math.h>

using namespace std;

# define REP_INC(i,f,t,s) \
	for(int i = int(f); i < int(t); i += int(s))

# define REP_DEC(i,f,t,s) \
	for(int i = int(f); i > int(t); i -= int(s))



int cola() {
	int cache[201];
	cache[0] = 0;
	bool one = true;
	REP_INC(i,1,201,1)
	{
		if (one)
			cache[i] = cache[i-1] + 1;
		else
			cache[i] = cache[i-1] + 2;
		one = !one;
	}
	int N;
	while (cin >> N) {
		cout << cache[N] << endl;
	}
	return EXIT_SUCCESS;
}

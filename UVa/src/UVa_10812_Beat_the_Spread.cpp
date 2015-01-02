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
# include <bitset>

using namespace std;

# define REP_INC(i,f,t,s) \
	for(int i = int(f); i < int(t); i += int(s))

# define REP_DEC(i,f,t,s) \
	for(int i = int(f); i > int(t); i -= int(s))


int beat_the_spread() {
	int N;
	cin >> N;
	REP_INC(l,0,N,1)
	{
		int s,d;
		cin >> s >> d;
		if (s >= d && (s+d) % 2 == 0 && (s-d) % 2 == 0) {
			int x = (s+d) / 2;
			int y = (s-d) / 2;
			if (x >= 0 && y >= 0)
				cout << max(x,y) << " " << min(x,y) << endl;
			else
				cout << "impossible" << endl;
		} else
			cout << "impossible" << endl;
	}
	return EXIT_SUCCESS;
}

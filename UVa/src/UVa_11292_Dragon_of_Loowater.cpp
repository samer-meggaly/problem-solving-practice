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

typedef unsigned long long ull;

int dragon_of_loowater() {
	int n,m,c,ptr;
	while (cin >> n >> m && n != 0) {
		ull gc = 0L;
		ull v;
		std::vector<ull> heads, knights;
		if (n <= m) {
			REP_INC(i,0,n,1) {
				cin >> v;
				heads.push_back(v);
			}
			REP_INC(i,0,m,1) {
				cin >> v;
				knights.push_back(v);
			}
			std::sort(heads.begin(), heads.end());
			std::sort(knights.begin(), knights.end());
			c = ptr = 0;
			REP_INC(h,0,n,1)
			{
				bool matched = false;
				while (!matched && ptr != m) {
					if (heads[h] <= knights[ptr]) {
						c++;
						gc += knights[ptr];
						matched = true;
					}
					ptr++;
				}
				if (m == ptr)
					break;
			}
			if (c == n)
				cout << gc << endl;
			else
				cout << "Loowater is doomed!" << endl;
		} else {
			REP_INC(i,0,n,1) cin >> v;
			REP_INC(i,0,m,1) cin >> v;
			cout << "Loowater is doomed!" << endl;
		}
	}
	return EXIT_SUCCESS;
}

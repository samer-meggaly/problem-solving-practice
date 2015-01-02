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

# define SIZE 1025

int rat_attack() {
	std::vector<std::vector<ull> > grid(SIZE, std::vector<ull>(SIZE,0LL));
	int N;
	cin >> N;
	REP_INC(c,0,N,1)
	{
		int d, n;
		int rc, rr, rpop;
		cin >> d >> n;
		REP_INC(g,0,n,1)
		{
			cin >> rc >> rr >> rpop;
			REP_INC(r,max(0,rr-d),min(SIZE,rr+d+1),1)
			{
				REP_INC(c,max(0,rc-d),min(SIZE,rc+d+1),1)
					grid[r][c] += rpop;
			}
		}
		ull exter = grid[0][0];
		int exter_c = 0;
		int exter_r = 0;
		REP_INC(c,0,SIZE,1)
		{
			REP_INC(r,0,SIZE,1)
			{
				if (grid[r][c] > exter && !(r==0 && c==0)) {
					exter = grid[r][c];
					exter_r = r;
					exter_c = c;
				}
				grid[r][c] = 0LL;
			}
		}
		cout << exter_c << " " << exter_r << " " << exter << endl;
	}

	return EXIT_SUCCESS;
}

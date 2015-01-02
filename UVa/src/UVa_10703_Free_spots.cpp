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
# include <algorithm>

using namespace std;

# define REP_INC(i,f,t,s) \
	for(int i = int(f); i < int(t); i += int(s))

# define REP_DEC(i,f,t,s) \
	for(int i = int(f); i > int(t); i -= int(s))


int free_spots() {
	int W,H,N;
	bool grid[501][501];
	while (cin >> W >> H >> N && W != 0) {
		REP_INC(x,1,W+1,1)
			REP_INC(y,1,H+1,1)
				grid[y][x] = false;
		int X1,Y1,X2,Y2;
		REP_INC(c,0,N,1)
		{
			cin >> X1 >> Y1 >> X2 >> Y2;
			REP_INC(x,min(X1,X2),max(X1,X2)+1,1)
				REP_INC(y,min(Y1,Y2),max(Y1,Y2)+1,1)
					grid[y][x] = true;
		}
		getchar();
		int free_spots = 0;
		REP_INC(x,1,W+1,1)
			REP_INC(y,1,H+1,1)
				if (!grid[y][x])
					free_spots++;
		if (free_spots == 0)
			cout << "There is no empty spots." << endl;
		else if (free_spots == 1)
			cout << "There is one empty spot." << endl;
		else
			cout << "There are " << free_spots << " empty spots." << endl;
	}
	return EXIT_SUCCESS;
}

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

int num_items[20];
int items[20][20];
int mem[20][200];

int compute(int level, int left_money, int C) {
	if (level == C)
		return 0;
	if (mem[level][left_money] != -1)
		return mem[level][left_money];

		int spent = 0;
	REP_INC(j,0,num_items[level],1) {
		if (items[level][j] <= left_money) {
			int temp = compute(level + 1, left_money - items[level][j], C);
			if (level == C-1 || temp > 0)
				spent = max(spent, items[level][j] + temp);
		}
	}
	mem[level][left_money] = spent;
	return spent;
}

int wedding_shopping() {
	int N, C, M;
	cin >> N;
	REP_INC(c,0,N,1) {
		cin >> M >> C;
		REP_INC(i,0,C,1) {
			cin >> num_items[i];
			REP_INC(j,0,num_items[i],1) {
				cin >> items[i][j];
			}
		}
		REP_INC(r,0,20,1)
			REP_INC(c,0,200,1)
				mem[r][c] = -1;
		int spent = compute(0, M, C);
		if (spent == 0)
			cout << "no solution" << endl;
		else
			cout << spent << endl;
	}
	return EXIT_SUCCESS;
}

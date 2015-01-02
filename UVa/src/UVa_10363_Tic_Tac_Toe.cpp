//============================================================================
// Name        : UVa
// Author      : samer-meggaly
//============================================================================

# include <stdio.h>
# include <stdlib.h>
# include <string.h>
# include <iostream>
# include <sstream>
# include <vector>
# include <algorithm>

using namespace std;

# define REP_INC(i,f,t,s) \
	for(int i = int(f); i < int(t); i += int(s))

# define REP_DEC(i,f,t,s) \
	for(int i = int(f); i > int(t); i -= int(s))

# define _X 'X'
# define _O 'O'
# define _BLNK '.'

struct board {
	std::string r1;
	std::string r2;
	std::string r3;
	std::vector<bool> stat_X;
	std::vector<bool> stat_O;

	int diff_XO() {
		std::string board_str = r1 + r2 + r3;
		int countX = std::count(board_str.begin(), board_str.end(), _X);
		int countO = std::count(board_str.begin(), board_str.end(), _O);
		return countX - countO;
	}

	std::vector<bool> process(char p) {
		std::vector<bool> stat(8, false);
		stat[0] = std::count(r1.begin(), r1.end(), p) == 3;
		stat[1] = std::count(r2.begin(), r2.end(), p) == 3;
		stat[2] = std::count(r3.begin(), r3.end(), p) == 3;
		std::string c1, c2, c3, ld, rd;
		c1.push_back(r1[0]); c1.push_back(r2[0]); c1.push_back(r3[0]);
		c2.push_back(r1[1]); c2.push_back(r2[1]); c2.push_back(r3[1]);
		c3.push_back(r1[2]); c3.push_back(r2[2]); c3.push_back(r3[2]);
		ld.push_back(r1[0]); ld.push_back(r2[1]); ld.push_back(r3[2]);
		rd.push_back(r1[2]); rd.push_back(r2[1]); rd.push_back(r3[0]);

		stat[3] = std::count(c1.begin(), c1.end(), p) == 3;
		stat[4] = std::count(c2.begin(), c2.end(), p) == 3;
		stat[5] = std::count(c3.begin(), c3.end(), p) == 3;
		stat[6] = std::count(ld.begin(), ld.end(), p) == 3;
		stat[7] = std::count(rd.begin(), rd.end(), p) == 3;

		return stat;
	}

	void set_stats() {
		stat_X = process(_X);
		stat_O = process(_O);
	}

	bool is_win(char p) {
		if (p == _X)
			return std::count(stat_X.begin(), stat_X.end(), true) > 0;
		else if (p == _O)
			return std::count(stat_O.begin(), stat_O.end(), true) > 0;
		else
			return false;
	}

};

int tic_tac_toe() {
	std::string line;
	int N;
	cin >> N;
	REP_INC(i, 0, N, 1) {
		board b;
		cin >> b.r1 >> b.r2 >> b.r3;
		b.set_stats();
		int diff = b.diff_XO();
		bool X_win = b.is_win(_X);
		bool O_win = b.is_win(_O);
		if (diff > 1 || diff < 0 || (X_win && O_win))
			cout << "no" << endl;
		else if((O_win && diff != 0) || (X_win && diff != 1))
			cout << "no" << endl;
		else
			cout << "yes" << endl;
	}
	return EXIT_SUCCESS;
}

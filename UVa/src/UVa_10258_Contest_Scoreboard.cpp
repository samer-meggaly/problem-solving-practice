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

typedef unsigned long long ull;

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

struct Contestant {
	int number, solvedProblems;
	ull penalty;
	int incorrectSubs[10];
	bool solved[10];
	bool participated;
	Contestant(int n) {
		number = n;
		solvedProblems = 0;
		penalty = 0L;
		std::fill(incorrectSubs, incorrectSubs+10, 0);
		std::fill(solved, solved+10, false);
		participated = false;
	}

	void update(int problem, int time, char status) {
		participated = true;
		if (!solved[problem]) {
			if (status == 'I') {
				incorrectSubs[problem]++;
			} else if (status == 'C') {
				solved[problem] = true;
				penalty += time + (20 * incorrectSubs[problem]);
				solvedProblems++;
			}
		}
	}

};

bool comp(const Contestant& c1, const Contestant& c2) {
	if (c1.solvedProblems != c2.solvedProblems) {
		return c1.solvedProblems > c2.solvedProblems;
	} else if (c1.penalty != c2.penalty) {
		return c1.penalty < c2.penalty;
	} else
		return c1.number < c2.number;
}

int contest_scoreboard() {
	int N, c, p, t;
	char s;
	cin >> N >> std::ws;
	REP_INC(i,0,N,1) {
		if (i > 0)
			cout << endl;
		std::vector<Contestant> contestants;
		REP_INC(j,0,101,1) {
			contestants.push_back(Contestant(j));
		}
		std::string line;
		while(getline(cin, line) && line.size() != 0) {
			std::istringstream iss(line);
			iss >> c >> p >> t >> s;
			contestants[c].update(p, t, s);
		}
		std::sort(contestants.begin(), contestants.end(), comp);
		REP_INC(j,0,101,1) {
			Contestant cont = contestants[j];
			if (cont.participated) {
				cout << cont.number << " " << cont.solvedProblems
						<< " " << cont.penalty << endl;
			}
		}
	}

	return EXIT_SUCCESS;
}

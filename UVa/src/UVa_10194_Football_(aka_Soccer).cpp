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

struct Team {
	std::string origname, name;
	int points, games, wins, ties, losses;
	int goal_diff, goal_scor, goal_agai;
	Team(std::string n) {
		origname = name = n;
		std::transform(n.begin(), n.end(), name.begin(), ::tolower);
		points = games = wins = ties = losses = 0;
		goal_diff = goal_scor = goal_agai = 0;
	}

	void played_game(int scored, int against) {
		games++;
		if (scored < against) {
			losses++;
		} else if (scored > against) {
			wins++;
			points += 3;
		} else {
			ties++;
			points += 1;
		}
		goal_scor += scored;
		goal_agai += against;
		goal_diff = goal_scor - goal_agai;
	}

};

std::ostream& operator<<(std::ostream& strm, const Team& t) {
	return strm << t.origname;
}

bool comp(Team a, Team b) {
	if (a.points != b.points) {
		return !(a.points < b.points);
	} else if (a.wins != b.wins) {
		return !(a.wins < b.wins);
	} else if (a.goal_diff != b.goal_diff) {
		return !(a.goal_diff < b.goal_diff);
	} else if (a.goal_scor != b.goal_scor) {
		return !(a.goal_scor < b.goal_scor);
	} else if (a.games != b.games) {
		return a.games < b.games;
	} else {
		return ((a.name.compare(b.name) < 0)? true:false);
	}
}

int football_aka_soccer() {
	int N;
	cin >> N;
	getchar();
	REP_INC(c,0,N,1) {
		std::map<std::string, int> namemap;
		std::vector<Team> teams;
		std::string tempstr, torname;
		getline(cin, torname);
		int tempint;
		cin >> tempint;
		getchar();
		REP_INC(t,0,tempint,1) {
			getline(cin, tempstr);
			namemap[tempstr] = t;
			Team ateam(tempstr);
			teams.push_back(ateam);
		}
		cin >> tempint;
		getchar();
		REP_INC(g,0,tempint,1) {
			getline(cin, tempstr);
			int fhash, at, lhash;
			fhash = tempstr.find_first_of('#');
			at = tempstr.find('@');
			lhash = tempstr.find_last_of('#');
			int scoreA = atoi((tempstr.substr(fhash+1,at-fhash)).c_str());
			int scoreB = atoi((tempstr.substr(at+1,lhash-at)).c_str());
			(*(teams.begin() + namemap[tempstr.substr(0,fhash)])).played_game(scoreA, scoreB);
			(*(teams.begin() + namemap[tempstr.substr(lhash+1)])).played_game(scoreB, scoreA);

		}
		std::sort(teams.begin(), teams.end(), comp);
		std::vector<Team>::iterator itr;
		cout << torname << endl;
		int rank = 0;
		for (itr = teams.begin(); itr != teams.end(); itr++) {
			Team t = (*itr);
			printf("%d) %s %dp, %dg (%d-%d-%d), %dgd (%d-%d)\n", ++rank,
					t.origname.c_str(), t.points, t.games, t.wins, t.ties, t.losses,
					t.goal_diff, t.goal_scor, t.goal_agai);
		}
		if (c < N-1)
			cout << endl;
	}
	return EXIT_SUCCESS;
}

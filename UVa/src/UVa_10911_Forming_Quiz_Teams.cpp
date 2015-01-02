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

# define studdist(s1,s2) \
	sqrt((s1.x-s2.x)*(s1.x-s2.x) + (s1.y-s2.y)*(s1.y-s2.y))

# define ERR_MAX 9999999.0

struct edge {
	int first;
	double second;
	edge() {}
	edge(int v, double d) {
		first = v;
		second = d;
	}
};
typedef std::vector<std::vector<edge> > vve;

struct stud {
	int x;
	int y;
};

template<size_t d>
double bt_solution(int s, int N, double adjmat[][d], int matched) {
	if (s == 2 * N)
		return 0.0;
	double min_dist = ERR_MAX;
	if ((matched & (1 << s)) == 0) {
		matched |= (1 << s);
		REP_INC(os,0,2*N,1) {
			if ((matched & (1 << os)) == 0) {
				double temp = bt_solution(s + 1, N, adjmat, matched | (1 << os));
				min_dist = min(min_dist, adjmat[s][os] + temp);
			}
		}
	} else {
		min_dist = bt_solution(s + 1, N, adjmat, matched);
	}
	return min_dist;
}

bool comp(edge e1, edge e2) {
	if (fabs(e1.second - e2.second) > 1E-10) {
		return e1.second < e2.second;
	} else {
		return e1.first < e2.first;
	}
}

double currminsumdist;
void bt_solution_v2(int s, int N, vve adjmat, int matched, double distsofar) {
	if (s == 2 * N) {
		currminsumdist = min(currminsumdist, distsofar);
		return;
	}

	if ((matched & (1 << s)) == 0) {
		matched |= (1 << s);
		REP_INC(j,0,2*N,1) {
			int os = adjmat[s][j].first;
			if ((matched & (1 << os)) == 0) {
				double newmin = distsofar + adjmat[s][j].second;
				if (newmin < currminsumdist)
					bt_solution_v2(s + 1, N, adjmat, matched | (1 << os), newmin);
			}
		}
	} else {
		bt_solution_v2(s + 1, N, adjmat, matched, distsofar);
	}
}

double memo[(1 << 16)];
double adjmat_arr[16][16];
double dp_solution(int s, int N, int matched) {
	if (matched == (1 << (2*N)) - 1)
		return 0.0;
	if (memo[matched] != 0.0)
		return memo[matched];

	double mindist = ERR_MAX;
	if ((matched & (1 << s)) == 0) {
		matched |= (1 << s);
		REP_INC(os,0,2*N,1) {
			if ((matched & (1 << os)) == 0) {
				double newmin = dp_solution(s + 1, N, matched | (1 << os));
				mindist = min(mindist, newmin + adjmat_arr[s][os]);
			}
		}
	} else {
		mindist = dp_solution(s + 1, N, matched);
	}

	memo[matched] = mindist;
	return mindist;
}

double solve_pruned_backtracking(int N, std::vector<stud> students) {
	vve adjmat(2 * N, std::vector<edge>(2 * N, edge()));
	REP_INC(s1,0,2*N,1) {
		adjmat[s1][s1] = (edge(s1, 0.0));
		REP_INC(s2,s1+1,2*N,1) {
			double dist = studdist(students[s1], students[s2]);
			adjmat[s1][s2] = (edge(s2, dist));
			adjmat[s2][s1] = (edge(s1, dist));
		}
		std::sort(adjmat[s1].begin(), adjmat[s1].end(), comp);
	}
	currminsumdist = ERR_MAX;
	bt_solution_v2(0, N, adjmat, 0, 0.0);
	return currminsumdist;
}

double solve_dynamic_programming(int N, std::vector<stud> students) {
	REP_INC(s1,0,2*N,1) {
		adjmat_arr[s1][s1] = 0.0;
		REP_INC(s2,s1+1,2*N,1) {
			double dist = studdist(students[s1], students[s2]);
			adjmat_arr[s1][s2] = dist;
			adjmat_arr[s2][s1] = dist;
		}
	}
	memset(memo, 0.0, sizeof(memo));
	return dp_solution(0, N, 0);
}

int forming_quiz_teams() {
	std::string line;
	int N;
	int counter = 1;
	while (cin >> N && N) {
		std::vector<stud> students;
		REP_INC(s,0,2*N,1) {
			stud loc;
			cin >> line >> loc.x >> loc.y;
			students.push_back(loc);
		}
//		printf("Case %d: %.2f\n", counter++, solve_pruned_backtracking(N, students));
		printf("Case %d: %.2f\n", counter++, solve_dynamic_programming(N, students));
	}
	return EXIT_SUCCESS;
}

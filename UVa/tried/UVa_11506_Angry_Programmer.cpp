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

# define REP_INC(i,f,t,s) \
	for(int i = int(f); i < int(t); i += int(s))

# define REP_DEC(i,f,t,s) \
	for(int i = int(f); i > int(t); i -= int(s))

# define EUC_DIST(x1,y1,x2,y2) \
	sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))


# define _MAX_MACHINE 51
# define _MAX_WIRE 1001
typedef unsigned long long ull;
typedef std::vector<std::vector<int> > vvi;

int M, W;
int m_costs[_MAX_MACHINE], w_costs[_MAX_WIRE][_MAX_WIRE];

std::string vec2str(std::vector<int> vi) {
	std::ostringstream oss;
	oss << "[ ";
	std::vector<int>::iterator itr;
	for (itr = vi.begin(); itr != vi.end(); itr++)
		oss << (*itr) << ((itr < vi.end()-1)? ", ":" ");
	oss << "]";
	return oss.str();
}

struct path {
	std::vector<int> plist;
	std::bitset<_MAX_MACHINE> pnodes;

	path() {}
	path(int start) {
		plist.push_back(start);
		pnodes[start] = 1;
	}

	bool inpath(int v) {
		return pnodes.test(v);
	}

	void append(int v) {
		plist.push_back(v);
		pnodes[v] = 1;
	}

	path clone() {
		path cloned;
		cloned.plist = std::vector<int>(plist);
		cloned.pnodes = std::bitset<_MAX_MACHINE>(pnodes.to_ulong());
		return cloned;
	}

	int end() {
		return plist[plist.size()-1];
	}
};

vvi get_paths(int start, int end) {
	vvi paths;
	std::queue<path> queue;
	queue.push(path(start));
	while (!queue.empty()) {
		path p = queue.front(); queue.pop();
		int u = p.end();
		if (u == end) {
			cout << vec2str(p.plist) << endl;
		} else {
			REP_INC(v,1,M+1,1) {
				if (!p.inpath(v) && w_costs[u][v] != 0) {
					path temp = p.clone();
					temp.append(v);
					queue.push(temp);
				}
			}
		}
	}
	return paths;
}

int angry_programmer() {
	int u,v,c;
	while (cin >> M >> W && M) {
		REP_INC(i,0,M-2,1) {
			cin >> u;
			cin >> m_costs[u];
		}
		REP_INC(i,0,W,1) {
			cin >> u >> v >> c;
			w_costs[u][v] = w_costs[v][u] = c;
		}
		get_paths(1,M);
	}
	return EXIT_SUCCESS;
}

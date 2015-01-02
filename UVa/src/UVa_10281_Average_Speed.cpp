//============================================================================
// Name        : UVa
// Author      : samer-meggaly
//============================================================================

# include <stdio.h>
# include <stdlib.h>
# include <string.h>
# include <iostream>
# include <sstream>
# include <math.h>
# include <vector>
# include <algorithm>

using namespace std;

# define REP_INC(i,f,t,s) \
	for(int i = int(f); i < int(t); i += int(s))

# define REP_DEC(i,f,t,s) \
	for(int i = int(f); i > int(t); i -= int(s))

# define MK_TIME(t_o,t_str) \
	t_o.t = t_str; \
	t_o.h = (t_o.t[0] - '0') * 10 + (t_o.t[1] - '0'); \
	t_o.m = (t_o.t[3] - '0') * 10 + (t_o.t[4] - '0'); \
	t_o.s = (t_o.t[6] - '0') * 10 + (t_o.t[7] - '0'); \


struct lap_time {
	string t;
	int h;
	int m;
	int s;

	long time2secs() {
		long ss = s;
		ss += ((m * 60) + (h * 60 * 60));
		return ss;
	}
};

int average_speed() {
	std::string line, tstr;
	lap_time ptime;
	MK_TIME(ptime, std::string("00:00:00"));
	int pspeed = 0;
	double dist = 0.0;
	while (getline(cin, line)) {
		bool is_query = line.size() == 8;
		istringstream iss(line);
		lap_time ntime;
		iss >> tstr;
		MK_TIME(ntime, tstr);
		long time_elapsed = ntime.time2secs() - ptime.time2secs();
		dist += ((pspeed / 3600.0) * time_elapsed);
		if (!is_query) iss >> pspeed;
		else printf("%s %.2f km\n", tstr.c_str(), dist);
		ptime = ntime;
	}
	return EXIT_SUCCESS;
}

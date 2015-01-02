//============================================================================
// Name        : UVa
// Author      : samer-meggaly
//============================================================================

# include <stdio.h>
# include <stdlib.h>
# include <string.h>
# include <iostream>
# include <sstream>

using namespace std;

struct snail {
	int h;	// well height
	int u;	// day-time climb distance
	int d;	// night-time slide distance
	float f;	// fatigue factor
	int count_day;	// day counter
	float curr_height;	// snail's current height
	float prev_climb;	// previous day climbed distance

	bool by_day_beg() {
		if (count_day == 0) {
			curr_height = u;
			prev_climb = u;
		} else {
			prev_climb -= f;
			if (prev_climb < 0.0)
				prev_climb = 0.0;
			curr_height += prev_climb;
		}
		count_day++;
		return curr_height > h;
	}

	bool by_day_end() {
		curr_height -= d;
		return curr_height < 0.0;
	}
};

int the_snail() {
	char* line = NULL;
	size_t sz = 0;
	while(!feof(stdin)) {
		ssize_t ln = getline(&line, &sz, stdin);
		if(ln > 0 and line[0] != '0') {
			string line_str (line);
			istringstream iss (line_str);
			snail snl;
			iss >> snl.h >> snl.u >> snl.d >> snl.f;
			snl.count_day = 0;
			snl.curr_height = 0.0;
			snl.prev_climb = 0.0;
			snl.f = (snl.f / 100.0) * snl.u;
			bool done = false;
			while (!done) {
				if (snl.by_day_beg()) {
					done = true;
					cout << "success on day " << snl.count_day << endl;
				} else if (snl.by_day_end()) {
					done = true;
					cout << "failure on day " << snl.count_day << endl;
				}
			}
		} else {
			break;
		}
	}

	return EXIT_SUCCESS;
}

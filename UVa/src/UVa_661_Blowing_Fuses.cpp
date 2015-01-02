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

# define MAX_NUM_DEVICES 20

struct house {
	int fuse_cap;
	int curr_cap;
	int max_cap;
	int n;	// number of devices
	int c[MAX_NUM_DEVICES][2];

	bool toggle_device(int i) {
		if (c[i][0] == 0) {
			curr_cap += c[i][1];
		} else {
			curr_cap -= c[i][1];
		}
		c[i][0] ^= 1;
		max_cap = max(max_cap, curr_cap);
		if (curr_cap > fuse_cap)
			return true;
		else
			return false;
	}
};

int blowing_fuses() {
	char* line = NULL;
	size_t sz = 0;
	int seq_num = 1;
	while(true) {
		ssize_t ln = getline(&line, &sz, stdin);
		if (ln > 0 && line[0] != '0') {
			string line_str (line);
			istringstream iss (line_str);
			house a_house;
			a_house.max_cap = -1;
			a_house.curr_cap = 0;
			int m;
			iss >> a_house.n >> m >> a_house.fuse_cap;
			for (int i = 0; i < a_house.n; i++) {
				a_house.c[i][0] = 0;
				scanf("%d\n", &(a_house.c[i][1]));
			}
			int ops[m];
			for (int i = 0; i < m; i++) {
				scanf("%d\n", &ops[i]);
				ops[i]--;
			}
			bool blown = false;
			for (int i = 0; i < m && !blown; i++) {
				blown = a_house.toggle_device(ops[i]);
			}
			cout << "Sequence " << seq_num++ << endl;
			if (blown) {
				cout << "Fuse was blown." << endl << endl;
			} else {
				cout << "Fuse was not blown." << endl;
				cout << "Maximal power consumption was " << a_house.max_cap;
				cout << " amperes." << endl << endl;
			}
		} else {
			break;
		}
	}

	return EXIT_SUCCESS;
}

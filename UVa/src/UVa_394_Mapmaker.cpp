//============================================================================
// Name        : UVa
// Author      : samer-meggaly
//============================================================================

# include <stdio.h>
# include <stdlib.h>
# include <string.h>
# include <map>
# include <iostream>
# include <sstream>

using namespace std;

# define MAX_DIM 11

struct array_data {
	string name;
	int base;
	int byte_size;
	int D;
	int C[MAX_DIM];
};

int mapmaker() {
	int N, R;
	char* line = NULL;
	size_t sz = 0;
	ssize_t ln = getline(&line, &sz, stdin);
	if (ln > 0) {
		N = atoi(strtok(line, " "));
		R = atoi(strtok(NULL, " "));
	}
	array_data arrs[N];
	std::map<string, int> name_map;
	// read each array data
	int i;
	for (i = 0; i < N; i++) {
		ssize_t ln = getline(&line, &sz, stdin);
		if (ln > 0) {
			string line_str (line);
			istringstream iss (line_str);
			iss >> arrs[i].name;
			name_map[arrs[i].name] = i;
			iss >> arrs[i].base >> arrs[i].byte_size >> arrs[i].D;
			arrs[i].C[arrs[i].D] = arrs[i].byte_size;
			int bounds[arrs[i].D + 1][2];
			int j = 1;
			for (; j <= arrs[i].D; j++) {
				iss >> bounds[j][0] >> bounds[j][1];
			}
			j = arrs[i].D;
			int sumCL = arrs[i].C[j] * bounds[j][0];
			for (; j > 1; j--) {
				arrs[i].C[j - 1] = arrs[i].C[j]
						* (bounds[j][1] - bounds[j][0] + 1);
				sumCL += arrs[i].C[j - 1] * bounds[j - 1][0];
			}
			arrs[i].C[0] = arrs[i].base - sumCL;
		}
	}

	for (i = 0; i < R; i++) {
		ssize_t ln = getline(&line, &sz, stdin);
		if (ln > 0) {
			string line_str (line);
			istringstream iss(line_str);
			string arr_name;
			iss >> arr_name;
			int arr_idx = name_map[arr_name];
			int indexes[arrs[arr_idx].D + 1];
			int d = 1;
			for (; d <= arrs[arr_idx].D; d++)
				iss >> indexes[d];

			std::stringstream ss;
			ss << arr_name;
			ss << "[";
			int addrs = arrs[arr_idx].C[0];
			d = 1;
			for (; d <= arrs[arr_idx].D; d++) {
				addrs += indexes[d] * arrs[arr_idx].C[d];
				if (d != 1)
					ss << ", ";
				ss << indexes[d];
			}
			ss << "] = ";
			ss << addrs;
			cout << ss.str() << '\n';
		}
	}

	return EXIT_SUCCESS;
}

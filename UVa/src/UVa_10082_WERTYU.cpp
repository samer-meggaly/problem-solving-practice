//============================================================================
// Name        : UVa
// Author      : samer-meggaly
//============================================================================

# include <stdio.h>
# include <stdlib.h>
# include <string.h>
# include <iostream>
# include <sstream>
# include <map>
# include <vector>

using namespace std;

# define REP_INC(i,f,t,s) \
	for(int i = int(f); i < int(t); i += int(s))

# define REP_DEC(i,f,t,s) \
	for(int i = int(f); i > int(t); i -= int(s))

char row1[] = { '`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '=', '\0' };
char row2[] = { 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', '[', ']', '\\', '\0' };
char row3[] = { 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', ';', '\'', '\0' };
char row4[] = { 'Z', 'X', 'C', 'V', 'B', 'N', 'M', ',', '.', '/', '\0' };

void row2map(std::map<char, char>& map) {
	REP_INC(r, 1, 5, 1)	{
		char row[100];
		int n;
		if (r == 1) {
			n = sizeof(row1) / sizeof(char);
			strncpy(row, row1, n);
		} else if (r == 2) {
			n = sizeof(row2) / sizeof(char);
			strncpy(row, row2, n);
		} else if (r == 3) {
			n = sizeof(row3) / sizeof(char);
			strncpy(row, row3, n);
		} else  {
			n = sizeof(row4) / sizeof(char);
			strncpy(row, row4, n);
		}

		REP_DEC(i, n-1, 0, 1) {
			map[row[i]] = row[i - 1];
		}
	}
}

int wertyu() {
	std::map<char, char> key_map;
	row2map(key_map);
	key_map[' '] = ' ';

	char* line = NULL; size_t sz = 0;
	while(!feof(stdin)) {
		ssize_t ln = getline(&line, &sz, stdin);
		if (ln > 0) {
			string line_str(line);
			ostringstream oss;
			line_str[--ln] = '\0';
			REP_INC(c, 0, ln, 1) {
				oss << key_map[line_str[c]];
			}
			cout << oss.str() << endl;
		}
	}

	return EXIT_SUCCESS;
}

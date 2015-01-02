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

using namespace std;


int soundex_indexing() {
	char* name = NULL;
	size_t sz = 0;
	map<char, int> code;
	// letters A, E, I, O, U, Y, W and H are never encoded
	code['A'] = code['E'] = code['I'] = code['O'] = code['U'] = code['Y'] = code['W'] = code['H'] = 0;
	code['B'] = code['P'] = code['F'] = code['V'] = 1;
	code['C'] = code['S'] = code['K'] = code['G'] = code['J'] = code['Q'] = code['X'] = code['Z'] = 2;
	code['D'] = code['T'] = 3;
	code['L'] = 4;
	code['M'] = code['N'] = 5;
	code['R'] = 6;
	int code_len = 4;
	string init_line = "         NAME                     SOUNDEX CODE";
	bool print_init_line = true;
	while(!feof(stdin)) {
		ssize_t ln = getline(&name, &sz, stdin);
		if (ln > 0) {
			int idx = 0;
			char code_arr[code_len+1];
			code_arr[idx++] = name[0];
			code_arr[code_len] = '\0';
			int name_len = ln - 1;
			for (int i = 1; i < name_len && idx < code_len; i++) {
				char c = name[i];
				bool enc = !(code[c] == 0 || code[c] == code[name[i-1]]);
				if (enc) {
					code_arr[idx++] = '0' + code[c];
				}
			}

			while (idx < code_len)
				code_arr[idx++] = '0';

			string init_space = "         ";
			int ns_len = 25;
			char name_space[ns_len+1];
			name_space[ns_len] = '\0';
			for (int i = 0; i < name_len; i++)
				name_space[i] = name[i];
			for (int i = name_len; i < ns_len; i++)
				name_space[i] = ' ';
			if (print_init_line) {
				cout << init_line << endl;
				print_init_line = false;
			}

			cout << init_space << name_space << code_arr << endl;
		} else {
			break;
		}
	}
	cout << "                   END OF OUTPUT" << endl;

	return EXIT_SUCCESS;
}

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

int word_scramble() {
	char* line = NULL;
	size_t sz = 0;
	while(!feof(stdin)) {
		ssize_t ln = getline(&line, &sz, stdin);
		if(ln > 0) {
			string line_str (line);
			istringstream iss (line_str);
			string a_str;
			stringstream ss;
			while((iss >> a_str)) {
				for (size_t i = 0; 2 * i < a_str.length(); i++) {
					char c = a_str[i];
					a_str[i] = a_str[a_str.length() - i - 1];
					a_str[a_str.length() - i - 1] = c;
				}
				ss << a_str << " ";
			}
			a_str = ss.str();
			a_str.erase(a_str.find_last_not_of(" ")+1);	// trim the last space
			cout << a_str << endl;
		}
	}

	return EXIT_SUCCESS;
}

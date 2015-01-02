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


int o_dah_dah_dah() {
	map<std::string, char> morse_code_map;
	morse_code_map[".-"] = 'A'; morse_code_map[".---"] = 'J'; morse_code_map["..."] = 'S'; morse_code_map[".----"] = '1'; morse_code_map[".-.-.-"] = '.'; morse_code_map["---..."] = ':';
	morse_code_map["-..."] = 'B'; morse_code_map["-.-"] = 'K'; morse_code_map["-"] = 'T'; morse_code_map["..---"] = '2'; morse_code_map["--..--"] = ','; morse_code_map["-.-.-."] = ';';
	morse_code_map["-.-."] = 'C'; morse_code_map[".-.."] = 'L'; morse_code_map["..-"] = 'U'; morse_code_map["...--"] = '3'; morse_code_map["..--.."] = '?'; morse_code_map["-...-"] = '=';
	morse_code_map["-.."] = 'D'; morse_code_map["--"] = 'M'; morse_code_map["...-"] = 'V'; morse_code_map["....-"] = '4'; morse_code_map[".----."] = '\''; morse_code_map[".-.-."] = '+';
	morse_code_map["."] = 'E'; morse_code_map["-."] = 'N'; morse_code_map[".--"] = 'W'; morse_code_map["....."] = '5'; morse_code_map["-.-.--"] = '!'; morse_code_map["-....-"] = '-';
	morse_code_map["..-."] = 'F'; morse_code_map["---"] = 'O'; morse_code_map["-..-"] = 'X'; morse_code_map["-...."] = '6'; morse_code_map["-..-."] = '/'; morse_code_map["..--.-"] = '_';
	morse_code_map["--."] = 'G'; morse_code_map[".--."] = 'P'; morse_code_map["-.--"] = 'Y'; morse_code_map["--..."] = '7'; morse_code_map["-.--."] = '('; morse_code_map[".-..-."] = '"';
	morse_code_map["...."] = 'H'; morse_code_map["--.-"] = 'Q'; morse_code_map["--.."] = 'Z'; morse_code_map["---.."] = '8'; morse_code_map["-.--.-"] = ')'; morse_code_map[".--.-."] = '@';
	morse_code_map[".."] = 'I'; morse_code_map[".-."] = 'R'; morse_code_map["-----"] = '0'; morse_code_map["----."] = '9'; morse_code_map[".-..."] = '&';

	int N;
	cin >> N;
	getchar();
	REP_INC(l, 0, N, 1)
	{
		if (l > 0)
			cout << endl;
		std::string line;
		int silence = 0;
		getline(cin, line);
		cout << "Message #" << (l+1) << endl;
		size_t idx, c = 0;
		while (c < line.size())
		{
			if (line[c] != ' ') {
				idx = line.find(" ", c);
				if (idx == std::string::npos)
					idx = line.size();

				cout << morse_code_map[line.substr(c, idx-c)];
				c += (idx-c);
				silence = 0;
			} else {
				silence++;
				if (silence == 2) {
					cout << " ";
					silence = 0;
				}
				c++;
			}
		}
		cout << endl;
	}
	return EXIT_SUCCESS;
}

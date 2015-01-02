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

int roman2arabic(std::map<char,int> sym_value, std::string roman) {
	int arabic = 0;
	int len = roman.size();
	int c = 0;
	char cc,nc;
	while (c < len) {
		cc = roman[c];
		if (cc == 'V' || cc == 'L' || cc == 'D' || cc == 'M' || c == len-1) {
			arabic += sym_value[cc];
		} else {
			nc = roman[c + 1];
			if ((cc == 'I' && (nc == 'V' || nc == 'X'))
				|| (cc == 'X' && (nc == 'L' || nc == 'C'))
				|| (cc == 'C' && (nc == 'D' || nc == 'M'))) {
				arabic += (sym_value[nc] - sym_value[cc]);
				c++;
			} else {
				arabic += sym_value[cc];
			}
		}
		c++;
	}
	return arabic;
}

std::string arabicbase2roman(std::map<int,char> value_sym, int d, int base) {
	std::string v;
	v.push_back(value_sym[base]);
	if (d == 1)
		return v;
	else if (d == 2)
		return v + v;
	else
		return v + v + v;
}

std::string arabicdigit2roman(std::map<int,char> value_sym, int d, int base) {
	std::string v;
	if (d <= 3) {
		return arabicbase2roman(value_sym, d, base);
	} else if (d <= 8) {
		v.push_back(value_sym[5*base]);
		if (d == 4)
			v.insert(0, 1, value_sym[base]);
		else if (d >= 6)
			v += arabicbase2roman(value_sym, d-5, base);
	} else {
		v.push_back(value_sym[base]);
		v.push_back(value_sym[10*base]);
	}
	return v;
}

std::string arabic2roman(std::map<int,char> value_sym, int arabic) {
	std::string roman;
	int base = 1;
	while (arabic > 0) {
		int d = arabic % 10;
		if (d != 0)
			roman = arabicdigit2roman(value_sym, d, base) + roman;
		arabic /= 10;
		base *= 10;
	}
	return roman;
}

int roman_numerals() {
	std::map<char, int> sym_value;
	sym_value['I'] = 1; sym_value['V'] = 5; sym_value['X'] = 10; sym_value['L'] = 50;
	sym_value['C'] = 100; sym_value['D'] = 500; sym_value['M'] = 1000;
	std::map<int, char> value_sym;
	value_sym[1] = 'I'; value_sym[5] = 'V'; value_sym[10] = 'X'; value_sym[50] = 'L';
	value_sym[100] = 'C'; value_sym[500] = 'D'; value_sym[1000] = 'M';

	std::string line;
	while (getline(cin, line)) {
		if (!isdigit(line[0]))
			cout << roman2arabic(sym_value, line) << endl;
		else
			cout << arabic2roman(value_sym, atoi(line.c_str())) << endl;
	}

	return EXIT_SUCCESS;
}

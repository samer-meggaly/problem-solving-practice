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
# include <bitset>

using namespace std;

# define REP_INC(i,f,t,s) \
	for(int i = int(f); i < int(t); i += int(s))

# define REP_DEC(i,f,t,s) \
	for(int i = int(f); i > int(t); i -= int(s))


int the_decadary_watch() {
	int cc = 1;
	int ss = 100 * cc;
	int mm = 60 * ss;
	int hh = 60 * mm;
	int trad_day = 24 * hh;
	int deci_day = 1E7;
	int time;
	while (cin >> time) {
		long long trad_time = 0;
		trad_time += cc * (time % 100);
		time /= 100;
		trad_time += ss * (time % 100);
		time /= 100;
		trad_time += mm * (time % 100);
		time /= 100;
		trad_time += hh * (time % 100);

		cout << setfill('0') << setw(7) << (trad_time * deci_day / trad_day) << endl;
	}
	return EXIT_SUCCESS;
}

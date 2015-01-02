//============================================================================
// Name        : UVa
// Author      : samer-meggaly
//============================================================================

# include <stdio.h>
# include <stdlib.h>
# include <string.h>
# include <iostream>
# include <sstream>
# include <vector>
# include <map>
# include <algorithm>

using namespace std;

# define REP_INC(i,f,t,s) \
	for(int i = int(f); i < int(t); i += int(s))

# define REP_DEC(i,f,t,s) \
	for(int i = int(f); i > int(t); i -= int(s))


int list_of_conquests() {
	std::string line;
	std::map<std::string, int> counter;
	std::vector<std::string> countries;
	int N;
	cin >> N;
	getchar();
	REP_INC(l, 0, N, 1)
	{
		getline(cin, line);
		std::istringstream iss(line);
		std::string country;
		iss >> country;
		if (counter.find(country) == counter.end()) {
			countries.push_back(country);
			counter[country] = 1;
		} else {
			counter[country] = counter[country] + 1;
		}
	}
	std::sort(countries.begin(), countries.end());
	REP_INC(i, 0, countries.size(), 1)
	{
		cout << countries[i] << " " << counter[countries[i]] << endl;
	}
	return EXIT_SUCCESS;
}

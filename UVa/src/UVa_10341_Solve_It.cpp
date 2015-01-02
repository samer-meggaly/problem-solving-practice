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

# define EPS 1E-8
# define ERR_VAL 9999.0

# define sec2(x) \
	(1.0/(cos(x)*cos(x)))
# define f(x) \
	(p*exp(-x)+q*sin(x)+r*cos(x)+s*tan(x)+t*x*x+u)
# define df(x) \
	(-p*exp(-x)+q*cos(x)-r*sin(x)+s*sec2(x)+2.0*t*x)

double bisection(int p, int q, int r, int s, int t, int u, double lb, double ub,
		int MAX_ITR = 1000, double TOL = EPS) {
	if (f(lb) * f(ub) > 0)
		return ERR_VAL;
	else {
		double y, prevx, x = ERR_VAL;
		int itr = 0;
		do {
			prevx = x;
			x = (lb + ub) / 2.0;
			y = f(x);
			if (y * f(lb) > 0)
				lb = x;
			else
				ub = x;
		} while (++itr < MAX_ITR && fabs(x - prevx) > TOL);
		return x;
	}
}

int solve_it() {
	int p, q, r, s, t, u;
	double lb = 0.0;
	double ub = 1.0;
	while (cin >> p >> q >> r >> s >> t >> u) {
		if (p == 0 && q == 0 && r == 0 && s == 0 && t == 0) {
			if (u >= 0 && u <= 1)
				printf("%.4f\n", (double) u);
			else
				printf("No solution\n");
		} else {
			double solu;
			solu = bisection(p, q, r, s, t, u, lb, ub);
			if (fabs(solu - ERR_VAL) < EPS) {
				printf("No solution\n");
			} else
				printf("%.4f\n", solu);
		}
	}
	return EXIT_SUCCESS;
}

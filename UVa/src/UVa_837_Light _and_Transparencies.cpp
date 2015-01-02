//============================================================================
// Name        : UVa
// Author      : samer-meggaly
//============================================================================

# include <stdio.h>
# include <stdlib.h>
# include <string.h>
# include <iostream>
# include <sstream>
# include <algorithm>
# include <vector>
# include <math.h>

using namespace std;

# define STR 0
# define END 1

struct point {
	int type;
	float x;
	float y;
	int line;
};

struct seg {
	float from;
	float to;
	double light;
};

bool cmp_points(const point& x1, const point& x2) {
	float diff = fabs(x1.x - x2.x);
	if (diff < 1E-10) {
		return x1.type == END;
	} else {
		return x1.x < x2.x;
	}
}

int light_and_transparencies() {
	int N;
	scanf("%d\n", &N);
	char* line = NULL;
	size_t sz = 0;
	for (int i = 0; i < N; ++i) {
		int M;
		scanf("%d\n", &M);
		double r[M];
		std::vector<point> p;
		for (int l = 0; l < M; l++) {
			getline(&line, &sz, stdin);
			string line_str(line);
			istringstream iss(line_str);
			point p1, p2;
			p1.line = p2.line = l;
			iss >> p1.x >> p1.y >> p2.x >> p2.y >> r[l];
			r[l] += 1E-15;
			if (p1.x < p2.x) {
				p1.type = STR;
				p2.type = END;
			} else {
				p1.type = END;
				p2.type = STR;
			}
			p.push_back(p1);
			p.push_back(p2);
		}

		std::sort(p.begin(), p.end(), &cmp_points);
		double light_per = 1.0;
		std::vector<point>::iterator p_itr = p.begin();
		std::vector<seg> segments;
		for (; p_itr != p.end(); p_itr++) {
			point t = *p_itr;
			if (t.type == STR) {
				light_per *= r[t.line];
			} else {
				light_per /= r[t.line];
			}
			if (p_itr != p.end()-1) {
				float diff = fabs(t.x - (*(p_itr + 1)).x);
				if (!diff < 1E-10) {
					seg segment;
					segment.from = t.x;
					segment.to = (*(p_itr + 1)).x;
					segment.light = light_per;
					segments.push_back(segment);
				}
			}

		}

		std::vector<seg>::iterator seg_itr = segments.begin();
		printf("%d\n", segments.size() + 2);
		printf("%s %.3f %.3f\n", "-inf", (*seg_itr).from, light_per);
		for (; seg_itr != segments.end(); seg_itr++) {
			seg s = *seg_itr;
			printf("%.3f %.3f %.3f\n", s.from, s.to, s.light);
		}
		printf("%.3f %s %.3f\n", (*(seg_itr - 1)).to, "+inf", light_per);
		if (i != N-1)
			printf("\n");
	}

	return EXIT_SUCCESS;
}

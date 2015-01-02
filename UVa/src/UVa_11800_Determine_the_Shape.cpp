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

typedef long long ll;

struct point {
	ll x;
	ll y;

	std::string str() {
		std::ostringstream oss;
		oss << "(X = " << x << ", ";
		oss << "Y = " << y << ")";
		return oss.str();
	}
};

struct IsLeft {
	point ref;
	IsLeft(point ref) {
		this->ref = ref;
	}
	bool operator()(const point& p1, const point& p2) {
		return ((p1.x - ref.x) * (p2.y - ref.y)
				- (p2.x - ref.x) * (p1.y - ref.y)) > 0LL;
	}
};

int get_hull_vextex(std::vector<point> points) {
	point vertex = points[0];
	int idx = 0;
	REP_INC(i, 1, points.size(), 1)
	{
		if (vertex.y != points[i].y) {
			if (points[i].y < vertex.y) {
				vertex = points[i];
				idx = i;
			}
		} else {
			if (points[i].x < vertex.x) {
				vertex = points[i];
				idx = i;
			}
		}
	}
	return idx;
}

std::vector<point> graham_hull(std::vector<point> points) {
	if (points.size() <= 2)
		return points;
	std::vector<point> stack;
	// select the point to be vertex of the hull
	int idx = get_hull_vextex(points);
	point v = points[idx];
	points.erase(points.begin() + idx);
	// sort the rest of the points in counterclockwise-order w.r.t vertex
	std::sort(points.begin(), points.end(), IsLeft(v));
	// add vertex and first point to hull stack
	stack.push_back(v);
	stack.push_back(points[0]);

	REP_INC(i, 1, points.size(), 1)
	{
		point p = points[i];
		bool adjusted = false;
		while (stack.end() - 1 > stack.begin() && !adjusted) {
			IsLeft left_turn(*(stack.end() - 2));
			if (left_turn.operator ()(stack.back(), p)) {
				adjusted = true;
			} else {
				stack.pop_back();
			}
		}
		stack.push_back(p);
	}

	return stack;
}

struct line {
	point p1;
	point p2;
	ll sqrd_len;

	line(point p1, point p2) {
		this->p1 = p1;
		this->p2 = p2;
		ll dx = p1.x - p2.x;
		ll dy = p1.y - p2.y;
		this->sqrd_len = dx * dx + dy * dy;
	}

	std::string str() {
		std::ostringstream oss;
		oss << "P1: " << p1.str() << " - ";
		oss << "P2: " << p2.str();
		return oss.str();
	}
};

std::vector<line> construct_lines(std::vector<point> points) {
	int len = points.size();
	std::vector<line> lines;
	REP_INC(l,0,len,1)
	{
		int l2 = (l + 1) % len;
		lines.push_back(line(points[l], points[l2]));
	}
	return lines;
}

bool is_equilateral(std::vector<line> shape) {
	ll len = shape[0].sqrd_len;
	bool equ = true;
	size_t idx = 1;
	while (equ) {
		equ = len == shape[idx].sqrd_len;
		idx++;
		if (idx == shape.size())
			break;
	}
	return equ;
}

bool are_all90(std::vector<line> shape) {
	int len = shape.size();
	REP_INC(l,0,len,1)
	{
		int l2 = (l + 1) % len;
		ll v1_x = shape[l].p1.x - shape[l].p2.x;
		ll v1_y = shape[l].p1.y - shape[l].p2.y;
		ll v2_x = shape[l2].p1.x - shape[l2].p2.x;
		ll v2_y = shape[l2].p1.y - shape[l2].p2.y;
		if ((v1_x*v2_x + v1_y*v2_y) != 0LL)
			return false;
	}
	return true;
}

bool are_parallel(line l1, line l2) {
	ll l1_dx = l1.p1.x - l1.p2.x;
	ll l1_dy = l1.p1.y - l1.p2.y;
	ll l2_dx = l2.p1.x - l2.p2.x;
	ll l2_dy = l2.p1.y - l2.p2.y;
	return (l1_dy * l2_dx) == (l1_dx * l2_dy);
}

int determine_the_shape() {
	int N;
	cin >> N;
	REP_INC(c,1,N+1,1)
	{
		std::vector<point> points;
		REP_INC(i,0,4,1)
		{
			point p;
			cin >> p.x >> p.y;
			points.push_back(p);
		}
		std::vector<point> hull = graham_hull(points);
		if (hull.size() == points.size()) {
			std::vector<line> edges = construct_lines(hull);
			bool all_equ = is_equilateral(edges);
			bool all_per = are_all90(edges);
			if (all_per) {
				if (all_equ)
					printf("Case %d: Square\n", c);
				else
					printf("Case %d: Rectangle\n", c);
			} else {
				if (all_equ)
					printf("Case %d: Rhombus\n", c);
				else {
					bool para_l1l3 = are_parallel(edges[0], edges[2]);
					bool para_l2l4 = are_parallel(edges[1], edges[3]);
					if (para_l1l3 ^ para_l2l4)
						printf("Case %d: Trapezium\n", c);
					else if (para_l1l3 || para_l2l4)
						printf("Case %d: Parallelogram\n", c);
					else
						printf("Case %d: Ordinary Quadrilateral\n", c);
				}
			}
		} else {
			printf("Case %d: Ordinary Quadrilateral\n", c);
		}
	}
	return EXIT_SUCCESS;
}

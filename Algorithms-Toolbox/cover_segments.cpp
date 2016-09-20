#include <algorithm>
#include <iostream>
#include <climits>
#include <vector>

using namespace std;

struct Segment {
	int start, end;
};

bool compareEnd(const Segment &a, const Segment &b) {
	return a.end < b.end;
}

vector<int> optimal_points(vector<Segment> &segments) {
	vector<int> points;
	int current = segments[0].end;
	points.push_back(current);
	//write your code here
	for (size_t i = 0; i < segments.size(); i++) {
		//If point is inside segment - disregard segment
		if (current >= segments[i].start && current <= segments[i].end) {
			continue;
		}
		else {
			current = segments[i].end;
			points.push_back(current);
			 
		}
	}
	return points;
}

int main() {
	//Takes input
	int n;
	cin >> n;
	vector<Segment> segments(n);
	for (size_t i = 0; i < segments.size(); ++i) {
		cin >> segments[i].start >> segments[i].end;
	}
	sort(segments.begin(), segments.end(), compareEnd);
	vector<int> points = optimal_points(segments);
	//Prints ouput
	cout << points.size() << "\n";
	for (size_t i = 0; i < points.size(); ++i) {
		cout << points[i] << " ";
	}
	//system("PAUSE");
	return 0;
}

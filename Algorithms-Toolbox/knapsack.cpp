#include <iostream>
#include <vector>
#include <algorithm>
#include <random>
#include <cmath>
using namespace std;


int greatest_possible(const vector<int> &v, int max_index) {
	for (int i = v.size() - 1; i >= 0; i--) {
		if (i <= max_index) return v[i];
	}
	return 0;
}

int optimal_weight(unsigned int W, vector<int> &w) {
	//write your code here
	sort(w.begin(), w.end());

	vector<vector<int>> table;
	table.resize(w.size(), vector<int>(W+1, 0));
	int r;
	for (size_t i = 0; i < w.size(); i++) {
		for (size_t j = 0; j < W + 1; j++) {
			r = greatest_possible(w, i);
			if (i > 0) {
				if (r == j) table[i][j] = r;
				else if (r < j) {
					if (table[i - 1][j] > r + table[i - 1][j - r]) table[i][j] = table[i - 1][j];
					else table[i][j] = table[i - 1][j - r] + r;
				}
				else {
					table[i][j] = table[i - 1][j];
				}
			}
			else {
				if (r > j) table[i][j] = 0;
				else table[i][j] = r;
			}
		}

	}
	int max = 0;
	for (size_t i = 0; i < w.size(); i++) {
		for (size_t j = W; j < W + 1; j++) {
			if (table[i][j] > max) max = table[i][j];
		}
	}
	return max;
}
int brute_optimal_weight(unsigned int W, vector<int> &w,vector<int> A) {
	int n = A.size();
	int best = 0;
	for (size_t i = 0; i < pow(2, n); i++) {
		int j = n-1;
		int temp_weight = 0;
		while (A[j] != 0 && j > 0) {
			A[j] = 0;
			j--;
		}
		A[j] = 1;
		for (size_t k = 0; k < n; k++) {
			if (A[k] == 1) {
				temp_weight += w[k];
			}
		}
		if (temp_weight <= W && temp_weight > best) {
			best = temp_weight;
		}
	}
	return best;
}

void tester() {
	random_device rd;     // only used once to initialise (seed) engine
	mt19937 rng(rd());    // random-number engine used (Mersenne-Twister in this case)
	uniform_int_distribution<int> uni_n(1, 10); // guaranteed unbiased
	uniform_int_distribution<int> uni_W(1, 100);
	uniform_int_distribution<int> uni_x(1, 20);
	unsigned int random_n, random_W;
	random_n = uni_n(rng);
	random_W = uni_W(rng);
	cout << "Test Case:" << endl;
	cout << random_W << " " << random_n << endl;
	vector<int> random_w(random_n);
	for (size_t k = 0; k < random_n; k++) {
		random_w[k] = uni_x(rng);
		cout << random_w[k] << " ";
	}
	cout << endl;
	int result_smart = -1, result_brute = -1;
	while (result_brute == result_smart) {
		random_n = uni_n(rng);
		random_W = uni_W(rng);
		cout << "Test Case:" << endl ;
		cout << random_W << " " << random_n << endl;
		vector<int> random_w(random_n);
		for (size_t k = 0; k < random_n; k++) {
			random_w[k] = uni_x(rng);
			cout << random_w[k] << " ";
		}
		cout << endl;
		vector<int> yesNo(random_w.size(), 0);
		result_brute = brute_optimal_weight(random_W, random_w,yesNo);
		
		result_smart = optimal_weight(random_W, random_w);
		cout << "BRUTE: " << result_brute << endl;
		cout << "SMART: " << result_smart << endl;
	}
}

int main() {
	
	unsigned int n, W;
	cin >> W >> n;
	vector<int> w(n);
	for (int i = 0; i < n; i++) {
		cin >> w[i];
	}
	cout << optimal_weight(W, w) << '\n';

	//tester();
	//system("PAUSE");
	return 0;
}

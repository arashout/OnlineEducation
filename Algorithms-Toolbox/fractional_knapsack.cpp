#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

double get_optimal_value(int capacity, vector<double> weights, vector<double> values) {
	double value = 0.0;
	const int size_of_knapsack = weights.size();
	vector<double> unit_values(size_of_knapsack);
	for (int j = 0; j < size_of_knapsack; j++) {
		unit_values[j] = values[j] / weights[j];
	}
	int max_index = 0;

	while (capacity > 0) {
		if (weights.size() == 0) {
			break;
		}
		max_index = distance(begin(unit_values), max_element(begin(unit_values), end(unit_values)));
		if (weights[max_index] > capacity) {
			value = value + capacity * unit_values[max_index];
			capacity = 0;
		}
		else {
			value = value + values[max_index];
			capacity = capacity - weights[max_index];
			unit_values.erase(unit_values.begin() + max_index);
			values.erase(values.begin() + max_index);
			weights.erase(weights.begin() + max_index);
		}
	}
  return value;
}

int main() {
	int n = 0;
	int capacity = 0;
	cin >> n >> capacity;
	vector<double> values(n);
	vector<double> weights(n);
	for (int i = 0; i < n; i++) {
		cin >> values[i] >> weights[i];
	}

	double optimal_value = get_optimal_value(capacity, weights, values);

	cout.precision(10);
	cout << optimal_value << endl;
	return 0;
}

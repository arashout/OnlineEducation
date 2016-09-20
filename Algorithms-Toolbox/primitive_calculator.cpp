#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> optimal_sequence(int n) {
	vector<int> sequence;
	vector<int> min_ops(n + 1, -1);
	min_ops[1] = 0;
	for (int i = 2; i <= n; i++) {
		min_ops[i] = 1 + min_ops[i - 1];
		if (i % 2 == 0) {
			min_ops[i] = min(1 + min_ops[i / 2], min_ops[i]);
		}
		if (i % 3 == 0) {
				min_ops[i] = min(1 + min_ops[i / 3], min_ops[i]);
		}
	}
	sequence.push_back(n);
	while (n > 1) {
		if (min_ops[n] - 1 == min_ops[n - 1]) {
			n--;
			sequence.push_back(n);
		}
		if (n % 3 == 0) {
			n /= 3;
			sequence.push_back(n);
		}
		else if(n % 2 == 0) {
			n /= 2;
			sequence.push_back(n);
		}
	}
	reverse(sequence.begin(), sequence.end());
	return sequence;
}

int main() {
	int n;
	cin >> n;
	vector<int> sequence = optimal_sequence(n);
	cout << sequence.size() - 1 << endl;
	for (size_t i = 0; i < sequence.size(); ++i) {
		cout << sequence[i] << " ";
	}
	return 0;
}

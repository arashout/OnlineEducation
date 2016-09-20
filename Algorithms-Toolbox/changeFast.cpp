#include <iostream>
#include <vector>

using namespace std;

int get_change(int n) {
  //write your code here
	vector<int> coins = { 10,5,1 };
	int count = 0;
	int i = 0;
	while (coins.size() > 0) {
		count = count + n / coins[i];
		n = n % coins[i];
		coins.erase(coins.begin());
	}
	return count;
}

int main() {
	int n;
	cin >> n;
	cout << get_change(n) << '\n';
	//system("PAUSE");
	return 0;
}

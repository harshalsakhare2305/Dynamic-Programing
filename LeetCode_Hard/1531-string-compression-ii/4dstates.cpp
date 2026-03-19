#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    int dp[101][27][101][101];

    int helper(int i, int prev, int freq, int k, string &s) {
        if (k < 0) return INT_MAX;
        if (i == s.length()) return 0;

        if (dp[i][prev][freq][k] != -1) return dp[i][prev][freq][k];

        // خيار delete
        int del = helper(i + 1, prev, freq, k - 1, s);

        int keep = 0;

        if ((s[i] - 'a') != prev) {
            keep = 1 + helper(i + 1, s[i] - 'a', 1, k, s);
        } else {
            int add = 0;
            if (freq == 1 || freq == 9 || freq == 99) {
                add = 1;
            }
            keep = add + helper(i + 1, prev, freq + 1, k, s);
        }

        return dp[i][prev][freq][k] = min(del, keep);
    }

    int getLengthOfOptimalCompression(string s, int k) {
        memset(dp, -1, sizeof(dp));
        return helper(0, 26, 0, k, s);
    }
};

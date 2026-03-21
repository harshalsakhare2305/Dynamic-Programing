//dp[idx][cuts]



// Minimum cost to split substring s[idx…n-1] using exactly cuts splits

// Where:

// cuts = k - 1

import java.util.*;

class Solution {

    int[][] dp;
    int[][] changes;
    int n;

    // ✅ Correct cost function
    int computeCost(int l, int r, String s) {
        int len = r - l + 1;

        // length 1 → invalid (cannot form semi-palindrome)
        if (len == 1) return (int)1e9;

        int minChange = (int)1e9;

        // iterate over number of groups (d)
        for (int d = 1; d < len; d++) {
            if (len % d != 0) continue;

            int groupSize = len / d;

            // we need group size ≥ 2
            if (groupSize < 2) continue;

            int change = 0;

            for (int group = 0; group < d; group++) {
                for (int i = 0; i < groupSize / 2; i++) {

                    int first = group + i * d;
                    int last = group + (groupSize - 1 - i) * d;

                    if (s.charAt(l + first) != s.charAt(l + last)) {
                        change++;
                    }
                }
            }

            minChange = Math.min(minChange, change);
        }

        return minChange;
    }

    // ✅ Split-based DP
    int rec(int idx, int cuts, String s) {

    

        // no splits left → take whole remaining substring
        if (cuts == 0) {
            return changes[idx][n - 1];
        }

        if (dp[idx][cuts] != -1) return dp[idx][cuts];

        int ans = (int)1e9;

        // try all split positions
        for (int j = idx; j < n-1; j++) {

            int leftCost = changes[idx][j];
            if (leftCost == (int)1e9) continue;

            int next = rec(j + 1, cuts - 1, s);

            if (next != (int)1e9) {
                ans = Math.min(ans, leftCost + next);
            }
        }

        return dp[idx][cuts] = ans;
    }

    public int minimumChanges(String s, int k) {

        n = s.length();

        // ✅ Precompute all costs
        changes = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                changes[i][j] = computeCost(i, j, s);
            }
        }

        // ✅ DP array
        dp = new int[n][k];
        for (int[] row : dp) Arrays.fill(row, -1);

        // k-1 splits
        return rec(0, k - 1, s);
    }
}

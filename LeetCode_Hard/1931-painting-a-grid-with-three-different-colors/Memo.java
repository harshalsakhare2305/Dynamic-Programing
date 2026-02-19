//https://leetcode.com/problems/painting-a-grid-with-three-different-colors/
class Solution {


    ArrayList<String> states;
    int MOD = (int) 1e9 + 7;
    int n;
    int m;
    int[][] dp;

    public int solve(int N, int lastrow) {
        if (N == 0)
            return 1;

        if (dp[N][lastrow] != -1)
            return dp[N][lastrow];

        String last = states.get(lastrow);

        int ways = 0;

        for (int i = 0; i < states.size(); i++) {
            if (i == lastrow)
                continue;
            boolean conflict = false;
            for (int j = 0; j < m; j++) {

                if (last.charAt(j) == states.get(i).charAt(j)) {
                    conflict = true;
                    break;
                }
            }

            if (!conflict) {
                ways = (ways + solve(N - 1, i)) % MOD;
            }

        }

        return dp[N][lastrow] = ways;
    }

    public void generateState(String curr, int i, char prev, int m) {
        if (i == m) {
            states.add(curr);
            return;
        }

        for (char c : new char[] { 'R', 'G', 'B' }) {
            if (c == prev)
                continue;

            generateState(curr + c, i + 1, c, m);
        }

        return;
    }

    public int colorTheGrid(int k, int p) {
        this.n = p;
        this.m = k;

        states = new ArrayList<>();
        generateState("", 0, '#', m);

        dp = new int[n][states.size()];

        for (int[] r : dp) {
            Arrays.fill(r, -1);
        }

        int ways = 0;

        for (int i = 0; i < states.size(); i++) {
            ways = (ways + solve(n - 1, i)) % MOD;
        }

        return ways;

    }
}

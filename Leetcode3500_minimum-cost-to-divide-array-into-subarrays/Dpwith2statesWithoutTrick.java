class Solution {
    static final long INF = Long.MAX_VALUE / 4;

    long[][] dp;
    long[] prefix;
    long[] costPrefix;

    public long helper(int i, int idx, int n, int k) {
        if (idx == n) return 0;
        if (i > n) return INF;

        if (dp[i][idx] != -1) return dp[i][idx];

        long ans = INF;

        for (int j = idx; j < n; j++) {
            long sumNums = prefix[j];
            long sumCost = costPrefix[j] - (idx > 0 ? costPrefix[idx - 1] : 0);

            long take = (sumNums + (long) k * i) * sumCost;
            take += helper(i + 1, j + 1, n, k);

            ans = Math.min(ans, take);
        }

        return dp[i][idx] = ans;
    }

    public long minimumCost(int[] nums, int[] cost, int k) {
        int n = nums.length;

        dp = new long[n + 1][n + 1];
        for (long[] row : dp) Arrays.fill(row, -1);

        prefix = new long[n];
        costPrefix = new long[n];

        prefix[0] = nums[0];
        costPrefix[0] = cost[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
            costPrefix[i] = costPrefix[i - 1] + cost[i];
        }

        return helper(1, 0, n, k);
    }
}

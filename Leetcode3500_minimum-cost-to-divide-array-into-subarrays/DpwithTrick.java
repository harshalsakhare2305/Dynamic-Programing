//https://leetcode.com/problems/minimum-cost-to-divide-array-into-subarrays/description/

class Solution {
    static final long INF = Long.MAX_VALUE / 4;

    long[][] dp;
    long[] prefix;
    long[] costPrefix;
    int n;
    int k;

    public long helper(int st,int ei,int k) {
      
      if(ei==n && st==n)return 0;
      if(ei>=n)return INF;

      long ct =prefix[ei]*(costPrefix[ei]-(st>0?costPrefix[st-1]:0));
      ct+=k*(costPrefix[n-1]-(st>0?costPrefix[st-1]:0));


      if(dp[st][ei]!=-1)return dp[st][ei];

      long take =ct+helper(ei+1,ei+1,k);
      long ntake=helper(st,ei+1,k);

      return dp[st][ei]=Math.min(take,ntake);
    }

    public long minimumCost(int[] nums, int[] cost, int k) {
        this.n = nums.length;
        this.k = k;

        dp = new long[n+1][n+1];
        for(long[]r:dp){
            Arrays.fill(r, -1);
        }

        prefix = new long[n];
        costPrefix = new long[n];

        prefix[0] = nums[0];
        costPrefix[0] = cost[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
            costPrefix[i] = costPrefix[i - 1] + cost[i];
        }

        return helper(0,0,k);
    }
}

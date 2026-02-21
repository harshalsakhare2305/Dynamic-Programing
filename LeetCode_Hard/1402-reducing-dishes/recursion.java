//https://leetcode.com/problems/reducing-dishes/?envType=problem-list-v2&envId=dynamic-programming
class Solution {
    int[][]dp;
    int extreme=(int)1e9+7;
    int rec(int idx,int time,int[] satisfaction){
        if(idx==satisfaction.length)return 0;

        if(dp[idx][time]!=extreme)return dp[idx][time];

        int take=(time+1)*satisfaction[idx]+rec(idx+1,time+1,satisfaction);

        int ntake=rec(idx+1,time,satisfaction);

        return dp[idx][time]=Math.max(take,ntake);
    }
    public int maxSatisfaction(int[] satisfaction) {
        int n=satisfaction.length;
        Arrays.sort(satisfaction);
        dp=new int[n][n+1];

      for(int[]r:dp){
        Arrays.fill(r,extreme);
      }

      return rec(0,0,satisfaction);

        


    }
}

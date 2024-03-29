import java.util.*;
public class Solution {
    public static int helper(int idx, int W, int[] weight, int[] value, int[][] dp) {
        int n = weight.length;
        if (W == 0)
            return 0;
        if (idx == 0) {
            
         return (int)(value[0]*(W/weight[0]));
            
        }

        if (dp[idx][W] != -1)
            return dp[idx][W];
        int pick = 0;
        if (weight[idx] <= W) {
            pick = value[idx] + helper(idx , W - weight[idx], weight, value, dp);
        }

        int npick = helper(idx - 1, W, weight, value, dp);
        dp[idx][W] = Math.max(pick, npick);

        return dp[idx][W];
    }

    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
   
             int[][] dp =new int[n][w+1];
        // for(int i=0;i<n;i++){
        // Arrays.fill(dp[i], -1);
        // }

        // return helper(n-1,w, weight, profit,dp);
               int[] prev = new int[w + 1];
       

        for (int i = 0; i <=w; i++) {
        
              
                  prev[i]=(int)(profit[0]*(i/weight[0]));
              
        }

        for (int i = 1; i < n; i++) {
               int[] temp = new int[w + 1];
            for (int j = 1; j <= w; j++) {
                int pick = 0;
                if (weight[i] <= j) {
                    pick = profit[i] + temp[j-weight[i]];
                }

                int npick = prev[j] ;
                temp[j] = Math.max(pick, npick);
            }

            prev=temp;
        }
      return prev[w];
    }
}

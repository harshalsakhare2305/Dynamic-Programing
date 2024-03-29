//https://www.naukri.com/code360/problems/0-1-knapsack_920542?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
import java.util.*;
import java.io.*;

public class Solution {
    public static int helper(int idx, int W, int[] weight, int[] value, int[][] dp) {
        int n = weight.length;
        
        if (W == 0)
            return 0;
        if (idx == 0) {
            if (weight[0] <= W)
                return value[0];
            else
                return 0;
        }

        if (dp[idx][W] != -1)
            return dp[idx][W];
        int pick = 0;
        if (weight[idx] <= W) {
            pick = value[idx] + helper(idx - 1, W - weight[idx], weight, value, dp);
        }

        int npick = helper(idx - 1, W, weight, value, dp);
        dp[idx][W] = Math.max(pick, npick);

        return dp[idx][W];
    }

    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        int[][] dp =new int[n][maxWeight+1];
        for(int i=0;i<n;i++){
        Arrays.fill(dp[i], -1);
        }

        return helper(n-1, maxWeight, weight, value,dp);

      //Tabulation 

        int[][] dp = new int[n][maxWeight + 1];
       
//At ind==0, we are considering the first element, if the capacity of the knapsack is greater than the weight of the first item, we return val[0] as answer. We will achieve this using a for loop. 
      //ep. if dp[3][4];
             //  0 1 2 3 4
             //  0 0 0 0 0     
             //  0 0 0 0 0
             //  0 0 0 0 0  

      // if W=3 and weight[0]=4 then theid cant take that item so dp[0][4]=0
      //and if weight[0]=2 then for W equals from weight[0] to that W value theif can take that item so this
        for (int i = weight[0]; i <=maxWeight; i++) {
        
                dp[0][i] = value[0];
        
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= maxWeight; j++) {
                int pick = 0;
                if (weight[i] <= j) {
                    pick = value[i] + dp[i-1][j-weight[i]];
                }

                int npick = dp[i-1][j] ;
                dp[i][j] = Math.max(pick, npick);
            }
        }
      return dp[n-1][maxWeight];

      //Tabulation With Space Optimization

        int[] prev = new int[maxWeight + 1];
        for (int i = weight[0]; i <=maxWeight; i++) {
                prev[i] = value[0];
        }

        for (int i = 1; i < n; i++) {
               int[] temp = new int[maxWeight + 1];
            for (int j = 1; j <= maxWeight; j++) {
                int pick = 0;
                if (weight[i] <= j) {
                    pick = value[i] + prev[j-weight[i]];
                }

                int npick = prev[j] ;
                temp[j] = Math.max(pick, npick);
            }

            prev=temp;
        }
      return prev[maxWeight];
    }
}

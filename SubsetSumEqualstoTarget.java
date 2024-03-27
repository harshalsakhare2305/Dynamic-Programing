import java.util.*;
import java.io.*;

public class Solution {

    public static boolean helper(int[] arr, int idx, int k, int[][] dp) { //Memoization
        if (k == 0)
            return true;
        if (idx == 0)
            return (arr[0] == k);

        if (dp[idx][k] != -1)
            return (dp[idx][k] == 0 ? false : true);
        boolean npick = helper(arr, idx - 1, k, dp);
        boolean pick = false;
        if (k >= arr[idx]) {
            pick = helper(arr, idx - 1, k - arr[idx], dp);
        }
        dp[idx][k] = npick || pick ? 1 : 0;
        return (npick || pick);
    }

    public static boolean subsetSumToK(int n, int k, int arr[]) {

      
        int[][] dp =new int[n][k+1];
        for(int i=0;i<n;i++){
        Arrays.fill(dp[i],-1);
        }
        return helper(arr,n-1,k,dp);

      //Tabulation
        boolean[][] dp = new boolean[n][k + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                boolean npick = dp[i-1][j];
                boolean pick = false;
                if (j >= arr[i]) {
                    pick = dp[i-1][j-arr[i]];
                }

                dp[i][j]=(pick||npick);
            }
        }

        return dp[n-1][k];

    //SpaceOptimization
      boolean[] prev =new boolean[k+1];
     prev[0]=true;

        if (arr[0] <= k) {
            prev[arr[0]] = true;
        }

        for (int i = 1; i < n; i++) {
              boolean[] temp=new boolean[k+1];
            for (int j = 0; j <= k; j++) {
                boolean npick = prev[j];
                boolean pick = false;
                if (j >= arr[i]) {
                    pick = prev[j-arr[i]];
                }

                temp[j]=(pick||npick);
            }

            prev=temp;
        }

        return prev[k];
    }
}

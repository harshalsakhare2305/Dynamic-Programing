import java.util.*;
public class Solution {
    public static int helper(int[][] points, int idx, int last, int[][] dp) {    // Memoization
        int n = points.length;
        if (idx == 0) {
            int maxi = 0;
            for (int task = 0; task < 3; task++) {
                if (task != last) {
                    maxi = Math.max(maxi, points[idx][task]);
                }
            }
            return maxi;
        }
        if (dp[idx][last] != -1) return dp[idx][last];

        int maxi = 0;
        for (int task = 0; task < 3; task++) {
            if (task != last) {
                int point = points[idx][task] + helper(points, idx - 1, task, dp);
                maxi = Math.max(maxi, point);
            }
        }
        dp[idx][last] = maxi;
        return dp[idx][last];
    }

    public static int ninjaTraining(int n, int points[][]) {
        int[][] dp = new int[n][4];
    //Base Cases
        dp[0][0] =Math.max(points[0][1],points[0][2]);
        dp[0][1] =Math.max(points[0][0],points[0][2]);
        dp[0][2] =Math.max(points[0][1],points[0][0]);
        dp[0][3] =Math.max(points[0][1],Math.max(points[0][2],points[0][0]));

        for(int  day=1;day<n;day++){
            for(int last=0;last<4;last++){
                    dp[day][last]=0;
                  for (int task = 0; task < 3; task++) {
                      if (task != last) {
                          int point = points[day][task] + dp[day-1][task];
                         dp[day][last] = Math.max(dp[day][last], point);
            }
        }
              
            }
        }


      return dp[n-1][3];

  //SPACE OPTIMIZATION 
     //Base Cases
     int[] prev =new int[4];
        prev[0] =Math.max(points[0][1],points[0][2]);
        prev[1] =Math.max(points[0][0],points[0][2]);
        prev[2] =Math.max(points[0][1],points[0][0]);
        prev[3] =Math.max(points[0][1],Math.max(points[0][2],points[0][0]));

        for(int  day=1;day<n;day++){
                 int[] temp=new int[4];
            for(int last=0;last<4;last++){
                   
                  for (int task = 0; task < 3; task++) {
                      if (task != last) {
                          int point = points[day][task] + prev[task];
                         temp[last] = Math.max(temp[last], point);
            }
        }
              
            }
            prev=temp;
        }


      return prev[3];


    }
}

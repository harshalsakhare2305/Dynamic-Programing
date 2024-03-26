//https://leetcode.com/problems/minimum-falling-path-sum/
class Solution {
      int max=1000000;
    public int helper(int[][] matrix,int i,int j,  int[][] dp){
            int n =matrix.length;
          
          if(i<0 || j<0 || j>=n ) return max;
        if(i==0) return matrix[i][j];

        if(dp[i][j]!=-1) return dp[i][j];
      int upl = matrix[i][j]+helper(matrix,i-1,j-1,dp);
      int up = matrix[i][j]+helper(matrix,i-1,j,dp);
      int upr = matrix[i][j]+helper(matrix,i-1,j+1,dp);

      dp[i][j]=Math.min(upl,Math.min(up,upr));
       return dp[i][j];
    }
    public int minFallingPathSum(int[][] matrix) {
        int n =matrix.length;
       // int[][] dp =new int[n][n];
    //     for(int i=0;i<n;i++){
    //         Arrays.fill(dp[i],-1);
    //     }
    //     int min =Integer.MAX_VALUE;
    //   for(int i=0;i<n;i++){
    //       min =Math.min(min, helper(matrix,n-1,i,dp));
    //   }
      int[] prev =new int[n];
      for(int i=0;i<n;i++){
        prev[i]=matrix[0][i];
      }
      for(int i=1;i<n;i++){
        int[] temp =new int[n];
        for(int j=0;j<n;j++){
      int upl =max;
      if(j>0){
        upl = matrix[i][j]+prev[j-1];
      }
      int up = matrix[i][j]+prev[j];
        int upr=max;
        if(j+1<n){
              upr=matrix[i][j]+prev[j+1];
        }

         temp[j]=Math.min(upl,Math.min(up,upr));
            
        }

        prev=temp;
      }
     int min =Integer.MAX_VALUE;
      for(int i=0;i<n;i++){
      min =Math.min(min,prev[i]);
      }
      return min;
    } 
}


//https://leetcode.com/problems/triangle/
class Solution {
    public int helper(List<List<Integer>> triangle,int r,int c,int[][] dp){
        int n =triangle.size();
        if(r==n-1){
            return triangle.get(r).get(c);
        }

        if(dp[r][c]!=-1) return dp[r][c];

        int down = triangle.get(r).get(c)+helper(triangle,r+1,c,dp);
        int dia= triangle.get(r).get(c)+helper(triangle,r+1,c+1,dp);
        dp[r][c]=Math.min(down,dia);
        return dp[r][c];
    }
    public int minimumTotal(List<List<Integer>> triangle) {
         int n =triangle.size();
     //   int[][] dp =new int[n][n];
        // for(int i=0;i<n;i++){
        //     Arrays.fill(dp[i],-1);
        // }
        // return helper(triangle,0,0,dp);

        //base case
      int[] next = new int[n];
      for(int i=0;i<n;i++){
        next[i]=triangle.get(n-1).get(i);
      }

        for(int i=n-2;i>=0;i--){
            int[]  temp =new int[n];
            for(int j=i;j>=0;j--){
              int down = triangle.get(i).get(j)+next[j];
              int dia= triangle.get(i).get(j)+next[j+1];
                 temp[j]=Math.min(down,dia);
            }

            next=temp;
        }
       return next[0];
    }
}

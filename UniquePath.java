class Solution {
    public int helper(int m,int n,int r,int c,  int[][] dp){
        
        if(r==0 && c==0){
            return 1;
        }
         if(dp[r][c]!=-1)  return dp[r][c];
        int rightpath=0;
        if(c-1>=0 && c-1<n){
            rightpath =helper(m,n,r,c-1,dp);
        }

        int downpath=0;
        if(r-1>=0 && r-1<m){
            downpath=helper(m,n,r-1,c,dp);
        }

        dp[r][c]=rightpath+downpath;

        return dp[r][c];

    }
    public int uniquePaths(int m, int n) {
      int[][] dp =new int[m][n];
      for(int i=0;i<m;i++){
       Arrays.fill(dp[i],-1);
      }

      return helper(m,n,m-1,n-1,dp);   //Memoization

      //Tabulation with Space optimization
    int[] prev =new int[n];
    Arrays.fill(prev,0);
       for(int i=0;i<m;i++){
         int[] temp =new int[n];
        for(int j=0;j<n;j++){
            if(i==0 && j==0){
               temp[j]=1;
            }else{
               
                int right =0;
                if(j-1>=0){
                   right =temp[j-1];
                }
                
                
                   int bottom =prev[j];
                

                temp[j]=right+bottom;
                
            }
          
        }
        prev=temp;
       }

       return prev[n-1];
    }
}

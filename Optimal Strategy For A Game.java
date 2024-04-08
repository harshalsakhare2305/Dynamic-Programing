//https://www.geeksforgeeks.org/problems/optimal-strategy-for-a-game-1587115620/1
class solve
{
   public static long helper(int i,int j,int[]arr,long[][]dp){
       int n=j-i+1;
       if(i>j){
           return 0;
       }
       if(dp[i][j]!=-1) return dp[i][j];
       long pickf=arr[i]+Math.min(helper(i+2,j,arr,dp),helper(i+1,j-1,arr,dp));
       long pickr=arr[j]+Math.min(helper(i+1,j-1,arr,dp),helper(i,j-2,arr,dp));
       
       dp[i][j]=Math.max(pickf,pickr);
       return dp[i][j];
       
       
   }
    static long countMaximum(int n, int arr[])
    {  
        long[][] dp =new long[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        
        return helper(0,n-1,arr,dp);
        
    }
}

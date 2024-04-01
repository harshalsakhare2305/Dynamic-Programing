//https://www.geeksforgeeks.org/problems/reach-a-given-score-1587115621/1
class Geeks {
    public long helper(int idx,int i,long[][] dp,int[]a){
         if(idx==0) return 1;
         if( i==3||idx<0) return 0;
        if(dp[idx][i]!=-1) return dp[idx][i];
        long pick =helper(idx-a[i],i,dp,a);
        long npick =helper(idx,i+1,dp,a);
        
         dp[idx][i]=pick+npick;
        return dp[idx][i];
    }
    public long count(int n) {
      long[][]dp =new long[n+1][3];
      int[] a ={3,5,10};
     for(int i=0;i<=n;i++){
         Arrays.fill(dp[i],-1);
     }
      return helper(n,0,dp,a);
    }
}

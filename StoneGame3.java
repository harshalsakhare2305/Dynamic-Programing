class Solution {
    public int solveForAlice(int idx,int[] piles,int[] dp){
    if(idx>=piles.length) return 0;
    if(dp[idx]!=-1) return dp[idx];
    int result =Integer.MIN_VALUE;
    int stone=0;
    for(int i=0;i<3;i++){
        if(idx+i<piles.length){
            stone+=piles[idx+i];
            int temp=stone-solveForAlice(idx+i+1,piles,dp);
            result=Math.max(result,temp);
        }
    }

    /* we wrote the function which returns the diff of score of ALice and Score of bob 
      int diff =score of Alice-score of Bob 
      
      if diff >0 ie. the score of alice is higher*/

    return dp[idx]=result;
  }
    public String stoneGameIII(int[] piles) {
            int n=piles.length;
        int[] dp =new int[n+1];
            Arrays.fill(dp,-1);
     int diff= solveForAlice(0,piles,dp);
      if(diff>0){
        return new String("Alice");
      }else if(diff<0){
        return new String("Bob");
      }

      return new String("Tie");
    }
}

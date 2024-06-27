class Solution {
  public int solveForAlice(int idx,int person,int[] piles,int M,int[][][] dp){
    if(idx>=piles.length) return 0;
    if(dp[idx][person][M]!=-1) return dp[idx][person][M];
    int result=(((person==0)?-1:Integer.MAX_VALUE));
    int stones=0;
    for(int i=1;i<=Math.min(2*M,piles.length-idx);i++){
        stones+=piles[idx+i-1];
        if(person==0){ //Alice Turn
           result=Math.max(result,stones+solveForAlice(idx+i,1,piles,Math.max(i,M),dp));
        }else{
              result=Math.min(result,solveForAlice(idx+i,0,piles,Math.max(i,M),dp));
        }


    }

    return dp[idx][person][M]=result;
  }
    public int stoneGameII(int[] piles) {
        int n=piles.length;
        int[][][] dp =new int[n+1][2][n+1];
        for(int i=0;i<=n;i++){
           for(int j=0;j<2;j++){
             Arrays.fill(dp[i][j],-1);
           }
        }
     return solveForAlice(0,0,piles,1,dp);

    }
}



//*******************************************************************
//   SOLUTION 2
//*******************************************************************


class Solution {
  public int getDiff(int idx,int[] piles,int M,int[][] dp){
    if(idx>=piles.length) return 0;
    if(dp[idx][M]!=-1) return dp[idx][M];
    int result=Integer.MIN_VALUE;
    int score=0;
   for(int i=1;i<=Math.min(2*M,piles.length-idx);i++){
      score+=piles[idx+i-1];
      int temp =score-getDiff(idx+i,piles,Math.max(i,M),dp);
      result=Math.max(result,temp);
}

return dp[idx][M]=result;
  
  }
    public int stoneGameII(int[] piles) {
        int n=piles.length;
        int[][] dp =new int[n+1][2*n];
        for(int i=0;i<=n;i++){
            Arrays.fill(dp[i],-1);
        }
        int total=0;
        for(int i:piles) total+=i;
     int diff=getDiff(0,piles,1,dp);
     /* Alice Score=A
        Bob Score =B
        
         A-B=diff
         A+B=total
         Add both 
         2A=diff+total
         A=(diff+total)/2;
          */
     return (diff+total)/2;
    }
}

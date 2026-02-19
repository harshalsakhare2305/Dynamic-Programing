//https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/description/?envType=problem-list-v2&envId=dynamic-programming
class Solution {
    int MOD =(int)1e9+7;
    int[][] dp ;
            String[] states;

    int solve(int n,int lastrow){
        if(n==0)return 1;

        if(dp[n][lastrow]!=-1)return dp[n][lastrow];
        
        String last=states[lastrow];

        int ways=0;

        for(int i=0;i<12;i++){
            if(i==lastrow)continue;

            boolean conflict=false;

            for(int c=0;c<3;c++){
                if(last.charAt(c)==states[i].charAt(c)){
                    conflict=true;
                    break;
                }
            }

            if(!conflict){
              ways=(int)((long)ways+solve(n-1,i))%MOD;
            }
        }

        return dp[n][lastrow]=(ways%MOD);

    }
    public int numOfWays(int n) {
        
         states =new String[]{"RYG","RGY","RYR","RGR","YRG","YGR","YRY","YGY","GRY","GYR","GRG","GYG"};

        dp=new int[n][12];
        for(int[]r:dp){
            Arrays.fill(r,-1);
        }

        int ways=0;
        for(int i=0;i<12;i++){
            ways=(int)((long)ways+solve(n-1,i))%MOD;
        }

        return ways;
    }
}

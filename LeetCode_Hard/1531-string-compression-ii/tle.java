class Solution {

    int dp[][][][];
    public int helper(int i,int prev,int freq,int k,String s){
        if(k<0)return Integer.MAX_VALUE;

        if(i==s.length())return 0;

        if(dp[i][prev][freq][k]!=-1)return dp[i][prev][freq][k];

        
        int delete =helper(i+1,prev,freq,k-1,s);

        int keepit=0;

      
        if(s.charAt(i)-'a' != prev){
            keepit=1+helper(i+1,s.charAt(i)-'a',1,k,s);
        }else{
            int inaddition=0;
            if(freq==1 || freq==9 || freq==99){
                inaddition=1;
            }

            keepit=inaddition+helper(i+1,prev,freq+1,k,s);
        }


        return dp[i][prev][freq][k]=Math.min(delete,keepit);


    }
    public int getLengthOfOptimalCompression(String s, int k) {
        dp=new int[101][27][101][101];
        for(int[][][]r3:dp){
            for(int[][]r2:r3){
                for(int[]r1:r2){
                    Arrays.fill(r1,-1);
                }
            }
        }


        return helper(0,26,0,k,s);


    }
}

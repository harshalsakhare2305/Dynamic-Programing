//https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/
class Solution {
    int[][] ColCharOccur;
    long[][]dp;
    int MOD=(int)1e9+7;
    public long rec(int i,int k,String[] words, String target){
        if(i>=target.length())return 1;

        if(k>=words[0].length())return 0;

        if(dp[i][k]!=-1)return dp[i][k];

        long ans=0;
        ans=(ans+rec(i,k+1,words,target)+MOD)%MOD;
        int freq=ColCharOccur[k][target.charAt(i)-'a'];
        if(freq>0){
              ans=(ans+(freq*rec(i+1,k+1,words,target))%MOD+MOD)%MOD;
        }


        return dp[i][k]=ans;


    }
    public int numWays(String[] words, String target) {
        int len=words.length;
        int n=words[0].length();

      ColCharOccur=new int[n][26];

      for(int j=0;j<n;j++){
        for(int i=0;i<len;i++){
            char ch=words[i].charAt(j);

            ColCharOccur[j][ch-'a']++;
        }
      }

      dp=new long[1001][1001];

      for(long[]r:dp){
        Arrays.fill(r,-1);
      }

      return (int)(rec(0,0,words,target)+MOD)%MOD;



    }
}

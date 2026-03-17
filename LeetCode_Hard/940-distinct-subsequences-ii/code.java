//https://leetcode.com/problems/distinct-subsequences-ii/
class Solution {
    public int distinctSubseqII(String s) {
        int[] lastchar =new int[26];
        int mod =(int)1e9+7;
        Arrays.fill(lastchar,-1);
        int n=s.length();
        long[] dp =new long[n+1];

        dp[0]=1;
        long total=0;
       

        for(int i=1;i<=n;i++){
          char ch =s.charAt(i-1);
           dp[i]=(2*dp[i-1])%mod;
           if(lastchar[ch-'a']!=-1){
              dp[i]=(dp[i]-(lastchar[ch-'a']>=0?dp[lastchar[ch-'a']-1]:0)+mod)%mod;
           }

           lastchar[ch-'a']=i;
        
        }

        return (int)(dp[n]-1+mod)%mod;

    }
}

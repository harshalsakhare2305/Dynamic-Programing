class Solution {
    public boolean WildCard(int i,int j,String s,String p,int[][]dp){
        //Base case
       if(i==0 && j==0) return true;
        if(i==0 && j>0)return false;
       if(j==0 && i>0){
         
         for(int k=0;k<=i;k++)
           if(p.charAt(k-1)!='*') return false;
           return true;
       }

        if(dp[i][j]!=-1) return (dp[i][j]==1)?true:false;
        if(p.charAt(i-1)==s.charAt(j-1) || p.charAt(i-1)=='?'){
            dp[i][j]= (WildCard(i-1,j-1,s,p,dp)==true)?1:0;
            
        }else if(p.charAt(i-1)=='*'){
            dp[i][j]=(WildCard(i-1,j,s,p,dp) || WildCard(i,j-1,s,p,dp)==true)?1:0;
        }

        return  (dp[i][j]==1)?true:false;
    }
    public boolean isMatch(String s, String p) {
        int n=s.length();
        int m =p.length();
        int[][]dp =new int[m+1][n+1];
        for(int i=0;i<=m;i++){
            Arrays.fill(dp[i],-1);
        }

        return WildCard(m,n,s,p,dp);    //Memoization Call

      //Tabulation Mathod 

        dp[0][0]=1;
        for(int j=1;j<=n;j++){
            dp[0][j]=0;
        }

        for(int i=1;i<=m;i++){
            if(p.charAt(i-1)!='*'){
                dp[i][0]=0;
            }else{
                dp[i][0]=1;
            }
        }

        for(int i=1;i<=m;i++){
            boolean flag =true;
             for(int k=1;k<=i;k++)
           if(p.charAt(k-1)!='*'){
            flag=false;
            break;
           }
           dp[i][0]=(flag==true?1:0);
       }

       for(int i=1;i<=m;i++){
        for(int j=1;j<=n;j++){
             if(p.charAt(i-1)==s.charAt(j-1) || p.charAt(i-1)=='?'){
            dp[i][j]= dp[i-1][j-1];
            
        }else if(p.charAt(i-1)=='*'){
            dp[i][j]= dp[i-1][j] | dp[i][j-1];
        }
        }
       }
        

        return dp[m][n]==0?false:true;

      //Space Optimization

      int[]prev =new int[n+1];
       prev[0]=1;
        for(int j=1;j<=n;j++){
            prev[j]=0;
        }

       for(int i=1;i<=m;i++){
           int[]curr =new int[n+1];

               boolean flag =true;
             for(int k=1;k<=i;k++)
           if(p.charAt(k-1)!='*'){
            flag=false;
            break;
           }
           curr[0]=(flag==true?1:0);
        for(int j=1;j<=n;j++){
             if(p.charAt(i-1)==s.charAt(j-1) || p.charAt(i-1)=='?'){
            curr[j]= prev[j-1];
            
        }else if(p.charAt(i-1)=='*'){
            curr[j]= prev[j] | curr[j-1];
        }
        }
        prev=curr;
       }
        

        return prev[n]==0?false:true;
    }
}

//https://leetcode.com/problems/edit-distance/
class Solution {
    public int Edit(int i,int j,String s,String t,int[][]dp){

         if(i==0) return j;
         if(j==0) return i;
         if(dp[i][j]!=-1) return dp[i][j];
        if(s.charAt(i-1)==t.charAt(j-1)){
            dp[i][j]=Edit(i-1,j-1,s,t,dp);
        }else{
            int insert =Edit(i,j-1,s,t,dp); //Insertion
            int delete =Edit(i-1,j,s,t,dp); //deletion
            int replace=Edit(i-1,j-1,s,t,dp); //Replace

            dp[i][j]=1+Math.min(insert,Math.min(delete,replace));
        }

        return dp[i][j];
    }
    public int minDistance(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();

     //   int[][] dp =new int[n+1][m+1];
        // for(int i=0;i<=n;i++){
        //     Arrays.fill(dp[i],-1);
        // }

        // return Edit(n,m,word1,word2,dp);
        int[] prev =new int[m+1];
       for(int j=0;j<=m;j++){
        prev[j]=j;
       }

    //    for(int i=0;i<=n;i++){
    //     prev[0]=i;
    //    }

       for(int i=1;i<=n;i++){
         int[] temp =new int[m+1];
         temp[0]=i;
        for(int j=1;j<=m;j++){
             if(word1.charAt(i-1)==word2.charAt(j-1)){
            temp[j]= prev[j-1];
        }else{
            int insert =temp[j-1];//Insertion
            int delete = prev[j];//deletion
            int replace=prev[j-1];//Replace

            temp[j]=1+Math.min(insert,Math.min(delete,replace));
        }
       }

       prev=temp;
       }
       return prev[m];
    }
}

//https://codeforces.com/contest/580/problem/D

import java.io.*;

import java.util.*;

public class Main {

  

       


        
    

    static final long INF = -(long)1e18;
    static long[][] dp;
    static final int MOD=(int)1e9+7;
    //static long Maxi=0;

    static long rec(int mask,int lastchosen,int[][] bonus,int[]a,int n,int m){
         
        if(Integer.bitCount(mask)==m){return 0;}

        

        if(dp[mask][lastchosen+1]!=INF)return dp[mask][lastchosen+1];


        long maxi=INF;

        for(int j=0;j<n;j++){
            
        boolean zerobit= ((mask & (1<<j))==0);

        if(zerobit){
            int newmask= mask | (1<<j);
           long ans=INF;
           if(lastchosen==-1){
            ans=a[j]+rec(newmask, j, bonus, a, n, m);
           }else{
            ans=a[j]+bonus[lastchosen][j]+rec(newmask, j, bonus, a, n, m);
           }

           maxi=Math.max(maxi,ans);
        }
        }
       
      
        

        return dp[mask][lastchosen+1]=maxi;
    }

    public static void solve(Scanner sc) {
         
      int n=sc.nextInt();
      int m=sc.nextInt();
      int k=sc.nextInt();

      int[]a=new int[n];

      for(int i=0;i<n;i++){
        a[i]=sc.nextInt();
      }

      int[][] bonus=new int[n][n];
      for(int i=0;i<k;i++){
        int x=sc.nextInt()-1;
        int y=sc.nextInt()-1;
        int c=sc.nextInt();

        bonus[x][y]=c;
      }

      int fullmask=1<<n;


      dp=new long[fullmask][n+1];

      for(long[]r:dp){
        Arrays.fill(r, INF);
      }

      long ans=rec(0, -1, bonus, a, n, m);
      System.out.println(ans);






    }

    public static void main(String[] args) throws Exception {

        try {
            System.setIn(new FileInputStream("input.txt"));
            System.setOut(new PrintStream(new FileOutputStream("output.txt")));
        } catch (Exception ignored) {
        }

        Scanner sc = new Scanner(System.in);
        // int t=sc.nextInt();
        // while(t-->0){
        //    solve(sc);
        // }

       solve(sc);

         
        sc.close();
    }
}

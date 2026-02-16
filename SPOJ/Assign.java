//https://www.spoj.com/problems/ASSIGN/


import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    static final long INF = -(long)1e18;
    static long[] dp;
    static final int MOD=(int)1e9+7;

    public static void solve(Scanner sc) {
         
      int n=sc.nextInt();
     int[][] grid= new int[n][n];

     for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            grid[i][j]=sc.nextInt();
        }
     }


    int fullMask=(1<<n);
    
    dp=new long[fullMask];

    dp[0]=1;

    for(int mask=0;mask<fullMask;mask++){
        
        int i=Integer.bitCount(mask);
        for(int j=0;j<n;j++){
           
            if(((mask & (1<<j))==0) && grid[i][j]==1){
                int  nextmask= mask | (1<<j);

                dp[nextmask]+=dp[mask];
            }
        }
    }

    System.out.println(dp[fullMask-1]);



    }

    public static void main(String[] args) throws Exception {

        try {
            System.setIn(new FileInputStream("input.txt"));
            System.setOut(new PrintStream(new FileOutputStream("output.txt")));
        } catch (Exception ignored) {
        }

        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0){
           solve(sc);
        }

     //   solve(sc);

         
        sc.close();
    }
}

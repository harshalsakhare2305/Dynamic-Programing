//https://www.codingninjas.com/studio/problems/chocolate-pickup_3125885?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf&leftPanelTabValue=PROBLEM
import java.util.*;
import java.io.*;

public class Solution {

	public static int mini = -10000000;

	public static int helper(int[][] grid, int i, int j1, int j2, int[][][] dp) {
		int m = grid.length;
		int n = grid[0].length;
		if (j1 < 0 || j1 >= n || j2 < 0 || j2 >= n)
			return mini;
		if (i == m - 1) {
			if (j1 == j2)
				return grid[i][j1];
			else {
				return grid[i][j1] + grid[i][j2];
			}
		}

		if (dp[i][j1][j2] != -1)
			return dp[i][j1][j2];
		int max = Integer.MIN_VALUE;
		for (int k = -1; k <= 1; k++) {
			for (int j = -1; j <= 1; j++) {
				int sum = 0;
				if (j1 == j2) {
					sum = grid[i][j1];

				} else {
					sum = grid[i][j1] + grid[i][j2];
				}

				sum += helper(grid, i + 1, j1 + k, j2 + j, dp);
				max = Math.max(max, sum);
			}
		}

		dp[i][j1][j2] = max;
		return max;

	}

	public static int maximumChocolates(int r, int c, int[][] grid) {
		// Write your code here.
  //		int[][][] dp = new int[r][c][c];
		// return helper(grid, 0, 0, c-1,dp);

		// Base Case
	// 	for (int j1 = 0; j1 < c; j1++) {
	// 		for (int j2 = 0; j2 < c; j2++) {
	// 			if (j1 == j2) {
	// 				dp[r - 1][j1][j2] = grid[r - 1][j1];
	// 			} else {
	// 				dp[r - 1][j1][j2] = grid[r - 1][j1] + grid[r - 1][j2];
	// 			}
	// 		}
	// 	}

	// 	for (int i = r-2; i>=0; i--) {
	// 		for (int j1 = 0; j1 < c; j1++) {
	// 			for (int j2 = 0; j2 < c; j2++) {
	// 				int max = Integer.MIN_VALUE;
	// 				for (int k = -1; k <= 1; k++) {
	// 					for (int j = -1; j <= 1; j++) {
	// 						int sum = 0;
	// 						if (j1 == j2) {
	// 							sum = grid[i][j1];

	// 						} else {
	// 							sum = grid[i][j1] + grid[i][j2];
	// 						}

	// 						if (j1 + k >= 0 && j1 + k < c && j2 + j >= 0 && j2 + j < c) {
	// 							sum += dp[i+1][j1 + k][j2 + j];
	// 						} else {
	// 							sum += mini;
	// 						}
	// 						max = Math.max(max, sum);
						
	// 					}
	// 				}

	// 					dp[i][j1][j2]=max;

	// 			}
	// 		}
	// 	}

    //  return dp[0][0][c-1];

   int [][] prev =new int[c][c];

		for (int j1 = 0; j1 < c; j1++) {
			for (int j2 = 0; j2 < c; j2++) {
				if (j1 == j2) {
				prev[j1][j2] = grid[r - 1][j1];
				} else {
					prev[j1][j2] = grid[r - 1][j1] + grid[r - 1][j2];
				}
			}
		}

		for (int i = r-2; i>=0; i--) {
			 int [][] temp=new int[c][c];
			for (int j1 = 0; j1 < c; j1++) {
				for (int j2 = 0; j2 < c; j2++) {
					int max = Integer.MIN_VALUE;
					for (int k = -1; k <= 1; k++) {
						for (int j = -1; j <= 1; j++) {
							int sum = 0;
							if (j1 == j2) {
								sum = grid[i][j1];

							} else {
								sum = grid[i][j1] + grid[i][j2];
							}

							if (j1 + k >= 0 && j1 + k < c && j2 + j >= 0 && j2 + j < c) {
								sum += prev[j1 + k][j2 + j];
							} else {
								sum += mini;
							}
							max = Math.max(max, sum);
						
						}
					}

						temp[j1][j2]=max;

				}
			}
			prev=temp;
		}

     return prev[0][c-1];
	}
}

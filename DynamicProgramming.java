import java.util.Arrays;

public class DynamicProgramming {
    public static void dynamicProgramming(int[] arr, int numTrucks) {
        int n = arr.length;
        int[][] dp = new int[n + 1][numTrucks + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][1] = sum(arr, 0, i - 1);
        }

        for (int i = 1; i <= numTrucks; i++) {
            dp[1][i] = arr[0];
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= numTrucks; j++) {
                int best = Integer.MAX_VALUE;
                for (int k = 1; k < i; k++) {
                    best = Math.min(best, Math.max(dp[k][j - 1], sum(arr, k, i - 1)));
                }
                dp[i][j] = best;
            }
        }

        for(int i = 0; i < n+1; i++) {
            for(int j = 0; j < numTrucks + 1; j ++) {
                System.out.print(dp[i][j] + " | ");
            }
            System.out.println();
        }
    }

    private static int sum(int[] arr, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += arr[i];
        }
        return sum;
    }

}

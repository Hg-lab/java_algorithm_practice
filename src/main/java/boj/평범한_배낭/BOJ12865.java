package boj.평범한_배낭;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class BOJ12865 {

    static int[] V;
    static int[] W;
    static int n;
    static int capacity;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        capacity = Integer.parseInt(st.nextToken());
        V = new int[n];
        W = new int[n];
        dp = new int[1001][100001];
        for(int i = 0; i < n; i++ ) {
            st = new StringTokenizer(br.readLine(), " ");
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getMax(0,0));
    }

    public static int getMax(int i, int w) {
        if(dp[i][w] != 0) return dp[i][w];
        if(i == n) return 0;

        int n1 = 0;
        if(w + W[i] <= capacity)  n1 = V[i] + getMax(i + 1, w + W[i]);
        int n2 = getMax(i + 1, w);
        return dp[i][w] = Math.max(n1, n2);
    }
}

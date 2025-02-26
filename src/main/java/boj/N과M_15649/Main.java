package boj.N과M_15649;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<List<Integer>> answer = new ArrayList<>();

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dfs(new ArrayList<>(), new boolean[N + 1]);

        for (List<Integer> list : answer) {
            for (int a : list) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }

    private static void dfs(List<Integer> list, boolean[] checked) {
        if (list.size() == M) {
            answer.add(list);
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!checked[i]) {
                list.add(i);
                checked[i] = true;
                dfs(new ArrayList<>(list), checked);
                checked[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
}

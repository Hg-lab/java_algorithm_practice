package boj.친구_1058;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<Integer, Set<Integer>> idxFriend = new HashMap<>();
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                map[i][j] = s.charAt(j);

                // 친구추가
                if (map[i][j] == 'Y') {
                    if (!idxFriend.containsKey(i)) {
                        idxFriend.put(i, new HashSet<>());
                    }
                    if(i != j) {
                        idxFriend.get(i).add(j);
                    }
                    for (int k = 0; k < map[j].length; k++) {
                        if(map[j][k] == 'Y') {
                            if(i != k) {
                            idxFriend.get(i).add(k);

                            }
                        }
                    }
                }
            }
        }

        //idxFriend 사이즈 최대값
        System.out.println("idxFriend = " + idxFriend);
        int max = 0;
        for (Integer key : idxFriend.keySet()) {
            int size = idxFriend.get(key).size();
            max = Math.max(max, size);
        }
        System.out.println(max);
    }
}




package 프로그래머스.Level2.순위_검색;
import java.util.*;

class Main {
    public static void main(String[] args) {
        new Solution().solution(
                new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"},
                new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"}
        );
    }
}

class Solution {
    HashMap<String, ArrayList<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = {};
        // info -> 모든 경우의 수 map
        for (String row : info) {
            allInfo(row.split("\\s"), "", 0);
        }

        // map key 정렬
        for (String s : map.keySet()) {
            Collections.sort(map.get(s));
        }
        answer = new int[query.length];
        // query
        for (int i = 0; i < query.length; i++) {
            String queryString = query[i].replaceAll("\\s|and|[0-9]", "");
            String[] split = query[i].split("\\sand\\s|\\s");
            int queryScore = Integer.parseInt(query[i].split("\\sand\\s|\\s")[4]);
            if(map.containsKey(queryString)) {
                ArrayList<Integer> scoreList = map.get(queryString);
                answer[i] = scoreList.size() - binarySearchLower(scoreList, queryScore);
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    private void allInfo(String[] row, String s, int depth) {
        if(depth == 4) {
            ArrayList<Integer> arr = map.getOrDefault(s, new ArrayList<>());
            arr.add(Integer.parseInt(row[4]));
            map.put(s, arr);
            return;
        }

        allInfo(row, s+row[depth], depth+1);
        allInfo(row, s+"-", depth+1);
    }

    private int binarySearchLower(ArrayList<Integer> scoreList, int queryScore) {
        int left = 0;
        int right = scoreList.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (scoreList.get(mid) <= queryScore) {
                right = mid;
            } else {
                left += 1;
            }
        }
        return left;
    }

}
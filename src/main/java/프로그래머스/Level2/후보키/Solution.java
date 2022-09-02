package 프로그래머스.Level2.후보키;

import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

class Solution {

    String[][] relation;
    ArrayList<Set<Integer>> candidateCols = new ArrayList<>();
    public int solution(String[][] relation) {
        this.relation = relation;

        int answer = 0;
        int numCols = relation[0].length;
        int[] columnIndex = new int[numCols];
        boolean[] visited = new boolean[numCols];
        for(int i = 0; i < numCols; i++) {
            columnIndex[i] = i;
        }


        for(int r = 1; r <= numCols; r++) {
            columnCombination(columnIndex, visited, 0, r);
        }


        return answer = candidateCols.size();
    }

    public void columnCombination(int[] arr, boolean[] visited, int start, int r) {
        if(r == 0) {
            Set<Integer> visitedIndexes = new HashSet<>();
            for(int i = 0; i < visited.length; i++) {
                if(visited[i]) visitedIndexes.add(i);
            }

            for(Set<Integer> set : candidateCols) {
                if(visitedIndexes.containsAll(set)) return;
            }


            if(isDuplicated(visited)) {
                Set<Integer> set = new HashSet<>();
                for(int i = 0; i < visited.length; i++) {
                    if(visited[i])
                        set.add(i);
                }
                candidateCols.add(set);
            }
        }

        for(int i = start; i < arr.length; i++) {
            visited[i] = true;
            columnCombination(arr, visited, i+1, r-1);
            visited[i] = false;
        }

    }

    public boolean isDuplicated(boolean[] visited) {
        Set<String> set = new HashSet<>();

        for(String[] tuple : relation) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < visited.length; i++) {
                if(visited[i]) {
                    sb.append(tuple[i]+"/");
                }
            }
            if(set.contains(sb.toString())) {
                return false;
            }
            set.add(sb.toString());
        }

        return true;
    }
}
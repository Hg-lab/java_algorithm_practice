package 프로그래머스.Level2.피로도;

import java.util.*;
// 최소 필요 피로도 = 탐험 전 최소로 필요한 피로도
// 소모 피로도 = 탐험한 후 소모되는 피로도
// 하루에 한번씩 탐험
// k: 현재피로도
// dungeons[i][0]: 최소피로도
// dungeons[i][1]: 소모피로도
// return: 탐헐할 수 있는 최대 던전 수
class Solution {
    List<List<Integer>> dungeonList = new ArrayList<>();
    boolean[] checked;
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        int numOfDungeons = dungeons.length;
        checked = new boolean[numOfDungeons];

        for(int i = 1; i <= numOfDungeons; i++) {
            // 순열 n P r
            permutation(numOfDungeons, checked, new ArrayList<>(), i);
        }

        // 순열 중 성공한 경우 확인
        for(int i = 0; i < dungeonList.size(); i++) {
            int copiedK = k;
            boolean isCompleted = false;
            for(int j = 0; j < dungeonList.get(i).size(); j++) {
                int index = dungeonList.get(i).get(j);
                if(copiedK < dungeons[index][0]) break;
                copiedK -= dungeons[index][1];

                // 순열 중 마지막 던전까지 돌았을 때 피로도가 0 보다 크면 성공함
                if(j == dungeonList.get(i).size()-1 && copiedK >= 0) {
                    isCompleted = true;
                }
            }

            if(isCompleted)  {
                answer = Math.max(dungeonList.get(i).size(), answer);
            }
        }

        return answer;
    }

    private void permutation(int numOfDungeons, boolean[] checked, List<Integer> list, int r) {
        if(list.size()==r) {
            dungeonList.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < numOfDungeons; i++) {
            if(!checked[i]) {
                checked[i] = true;
                list.add(i);
                // System.out.println(list.toString());
                permutation(numOfDungeons, checked, list, r);
                checked[i] = false;
                list.remove(list.size()-1);
            }
        }
    }
}
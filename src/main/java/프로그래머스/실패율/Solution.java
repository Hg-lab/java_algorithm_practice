package 프로그래머스.실패율;
// 실패율 = 스테이지에 도달했지만 아직 클리어하지 못한 플레이어 수 / 스테이지에 도달한 플레이어 수
// 전체 스테이지 N, 게임을 이용하는 사용자가 멈춰있는 스테이지의 번호 배열 stages
// return : 실패율이 높은 스테이지부터 내림차순 배열

// 1 <= N <= 500
// 1 <= stages.length <= 200000
// 1 <= stages[i] <= N+1
// stages[i] == N+1 는 마지막 스테이지까지 클리어한 사용자
// 실패율이 같다면 작은 스테이지가 먼저 오도록
// 스테이지에 도달한 유저가 없으면 실패율은 0

import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] stageCount = new int[N+1];
        Map<Integer,Double> failRatio = new HashMap<Integer,Double>(); // N+1 까지 있으므로

        Arrays.sort(stages);
        // stages loop 돌면서 각 스테이지별 클리어하지 못한 수 저장
        for(int i = 0; i < stages.length; i++) {
            stageCount[stages[i]-1]++;
        }



        for(int i = 0; i < stageCount.length; i++) {
            if(stageCount[i] == 0) {
                failRatio.put(i+1, 0d);
            }
            failRatio.put(i+1,(double)stageCount[i]);
        }

        // 실패율 저장
        for(Integer key : failRatio.keySet()) {
            double denominator = 0;
            for(Integer keySecond : failRatio.keySet()) {
                if(keySecond >= key) {
                    denominator += failRatio.get(keySecond);
                }
            }
            failRatio.put(key, failRatio.get(key)/denominator);
        }

        // Map.Entry 리스트 작성
        List<Map.Entry<Integer,Double>> entryList = new ArrayList<Map.Entry<Integer, Double>>(failRatio.entrySet());

        // 비교함수 Comparator를 사용하여 내림차순으로 정렬
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Double>>() {
            // compare로 값을 비교
            @Override
            public int compare(Map.Entry<Integer, Double> obj1, Map.Entry<Integer, Double> obj2) {
                // 내림 차순 정렬
                if(obj1.getValue() > obj2.getValue()) {
                    return -1;
                } else if(obj1.getValue() == obj2.getValue()) {
                    return obj1.getKey().compareTo(obj2.getKey());
                } else return 1;
            }
        });

        int iter = 0;
        for(Map.Entry<Integer,Double> e : entryList) {

            if(e.getKey() <= N) {
                answer[iter++] = e.getKey();
            }

        }

        return answer;
    }
}
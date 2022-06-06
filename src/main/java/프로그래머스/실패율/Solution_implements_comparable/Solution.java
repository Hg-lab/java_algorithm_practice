package 프로그래머스.실패율.Solution_implements_comparable;

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
    static class Fail implements Comparable<Fail> {
        int stage;
        double failRatio;

        public Fail(int stage, double failRatio) {
            this.stage = stage;
            this.failRatio = failRatio;
        }

        @Override
        public int compareTo(Fail o) {
            if(o.failRatio == this.failRatio) {
                // 실패율이 같다면 stage를 오름차순
                return this.stage - o.stage;
            }
            // 생성자의 매개변수가 앞에 오기 때문에 내림차순
            return Double.compare(o.failRatio, this.failRatio);
        }
    }


    public int[] solution(int N, int[] stages) {
        // int[] answer = {};
        int[] answer = new int[N];
        int[] stagesCount = new int[N+1];
        int sum = 0;
        ArrayList<Fail> failList = new ArrayList<Fail>();

        for(int stage : stages) {
            stagesCount[stage-1]++;
        }

        for(int cnt : stagesCount) {
            sum += cnt;
        }

        for(int i = 0; i < stagesCount.length-1; i++) {
            double denominator = (double)sum;
            double ratio = 0d;
            if(denominator != 0d) ratio = stagesCount[i]/denominator;
            failList.add(new Fail(i+1,ratio));
            sum -= stagesCount[i];
        }

        Collections.sort(failList);

        // test 용 print
        // for(Fail f : failList) {
        //     System.out.print(f.stage + " : " + f.failRatio);
        //     System.out.println();
        // }

        for(int i = 0; i < answer.length; i++) {
            answer[i] = failList.get(i).stage;
        }

        return answer;
    }
}
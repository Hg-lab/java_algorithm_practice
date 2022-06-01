package 프로그래머스.신고_결과_받기;

// 동일한 유저 신고는 1회로 처리
// k번 이상 등록되면 정지
// 2 <= id_list.length <= 1000
// 1 <= id_list[] <= 10
// id_list 중복 없음
// 1 <= report.length <= 200000
// 3 <= report[] <= 21
// report[] = 이용자 -> 신고아이디
// k번이상 신고받아 정지되면 신고한사람에게 1회 취합되어 메일
//
import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        // 중복제거를 위한 변수 생성
        HashMap<String, Integer> reportHm = new HashMap<String, Integer>();

        // 신고당한 횟수
        HashMap<String, Integer> reported = new HashMap<String, Integer>();

        // 같은 신고 사항 중복제거
        for(int i = 0; i < report.length; i++ ) {
            reportHm.put(report[i], 1);
        }

        List<String> overThanK = new ArrayList<String>();

        for(String rpted : reportHm.keySet()) {
            String[] reptedArr = rpted.split(" ");
            reported.put(reptedArr[1], reported.getOrDefault(reptedArr[1], 0) + 1); // 신고당한 횟수 추가
            // System.out.println(reptedArr[0] + " : " + reptedArr[1]);
            // System.out.println(reptedArr[1] + " : " + reported.get(reptedArr[1]));

            if(reported.get(reptedArr[1]) >= k) {
                overThanK.add(reptedArr[1]);
                // int idx = Arrays.asList(id_list).indexOf(reptedArr[0]);
                // System.out.println(reptedArr[0]);
                // answer[idx]++;
            }
        }

        for(String rpted : reportHm.keySet()) {
            String[] reptedArr = rpted.split(" ");
            if(overThanK.indexOf(reptedArr[1]) > -1) {
                int idx = Arrays.asList(id_list).indexOf(reptedArr[0]);
                answer[idx]++;
            }
        }



        // System.out.println(reported.toString());


        return answer;
    }
}
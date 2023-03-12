package 프로그래머스.Level1.개인정보_수집_유효기간;

import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        Map<String, Integer> termsMap = new HashMap<>();
        Map<Integer, String> expireMap = new HashMap<>();

        for(String t: terms) {
            String[] tArr = t.split(" ");
            termsMap.put(tArr[0], Integer.parseInt(tArr[1]));
        }

        for(int i = 0; i < privacies.length; i++) {
            String[] privacyArr = privacies[i].split(" ");

            String expireDate = computeExpire(privacyArr[0], termsMap.get(privacyArr[1]));

            expireMap.put(i, expireDate);
        }
        // 오늘일자와 비교
        List<Integer> answerList = new ArrayList<>();
        String[] tArr = today.split("[.]");
        int todayInt = Integer.parseInt(tArr[0] + tArr[1] + tArr[2]);
        for(int key: expireMap.keySet()) {
            if(todayInt > Integer.parseInt(expireMap.get(key))) {
                answerList.add(key+1);
            }
        }
        answer = new int[answerList.size()];

        return answer = answerList.stream().mapToInt(Integer::intValue).toArray();
    }

    private String computeExpire(String dateStr, int month) {
        // System.out.println(dateStr);
        String[] ymdStrArr = dateStr.split("[.]");
        // System.out.println(Arrays.toString(ymdStrArr));

        int[] ymd = new int[3];

        int index = 0;
        for(String s: ymdStrArr) {
            ymd[index++] = Integer.parseInt(s);
        }

        ymd[1] += month;
        ymd[2] -= 1;



        if(ymd[2] < 1) {
            ymd[1]--;
            ymd[2] = 28;
        }



        if(ymd[1] > 12) {
            ymd[0] += ymd[1] / 12;
            ymd[1] %= 12;
        }

        if(ymd[1] == 0) {
            ymd[0]--;
            ymd[1] = 12;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < ymd.length; i++) {
            StringBuilder appending = new StringBuilder(Integer.toString(ymd[i]));

            if(appending.length() == 1) appending.insert(0, "0");

            sb.append(appending);
        }



        return sb.toString();
    }
}
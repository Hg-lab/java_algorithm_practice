package 프로그래머스.Level2._3차_압축;

import java.util.*;

class Solution_3 {

    public int[] solution(String msg) {
        // A~Z 까지 모두 사전에 등록하기
        String[] strArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
        HashMap<String, Integer> hashMap = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < strArr.length; i++) {
            hashMap.put(strArr[i], i + 1);
        }
        // 재귀함수에 넣기
        LZW(hashMap, list, msg);

        int[] answer = new int[list.size()];
        int index = 0;
        for (int num : list) {
            answer[index] = num;
            index++;
        }

        return answer;
    }

    private void LZW(HashMap<String, Integer> hashMap, ArrayList<Integer> list, String str) {

        for (int i = 0; i < str.length(); i++) {
            String subStr = str.substring(0, i + 1);
            // 만약 사전에 없다면 사전에 등록하기 그리고 현재 문자 or 문자열 인덱스 값 출력,
            // 현재 문자 or 문자열 다음의 문자에서 다시 시작
            if (!hashMap.containsKey(subStr)) {
                hashMap.put(subStr, hashMap.size() + 1);
                list.add(hashMap.get(str.substring(0, i)));
                LZW(hashMap, list, str.substring(i));
                break;
            } else {
                if (i + 1 == str.length()) {
                    // 만약 현재 문자가 마지막이라면 마지막 문자 인덱스 출력하기
                    list.add(hashMap.get(str.substring(0, i + 1)));
                }
            }
        }
    }
}
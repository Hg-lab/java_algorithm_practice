package 프로그래머스.Level2.뉴스_클러스터링;

import java.util.*;

// 교집합의 크기를 합집합으로 나눈다
// 대소문자는 같은것으로 취급한다.
class Solution {
    List<String> str1List = new ArrayList<>();
    List<String> str2List = new ArrayList<>();
    static final String REGEX = "^[a-zA-Z]*$";
    static final int NUMBER = 65536;

    public int solution(String str1, String str2) {
        int answer = 0;
        str1List = split(str1);
        str2List = split(str2);
        List<String> unioned = union(new ArrayList<>(str1List), new ArrayList<>(str2List));
        List<String> intersectioned = intersection(new ArrayList<>(str1List), new ArrayList<>(str2List));

        if(unioned.size() == 0) return NUMBER;
        return answer = (int)Math.floor((float)((float)intersectioned.size()/(float)unioned.size()) * NUMBER);
    }

    private List<String> split(String s) {
        List<String> result = new ArrayList<>();
        for(int i = 0; i <= s.length()-2;i++) {
            StringBuilder splited = new StringBuilder(s.substring(i, i+2));
            if(splited.toString().matches(REGEX)) {
                result.add(splited.toString());
            }
        }
        return result;
    }

    private List<String> union(List<String> a, List<String> b) {
        for(int i = 0; i < a.size(); i++) {
            for(int j = 0; j < b.size(); j++) {
                String strA = a.get(i).toLowerCase();
                String strB = b.get(j).toLowerCase();
                if(strA.equals(strB)) {
                    b.remove(j);

                    break;
                }
            }
        }
        a.addAll(b);
        return a;
    }


    private List<String> intersection(List<String> a, List<String> b) {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < a.size(); i++) {
            for(int j = 0; j < b.size(); j++) {
                String strA = a.get(i).toLowerCase();
                String strB = b.get(j).toLowerCase();
                if(strA.equals(strB)) {
                    result.add(strB);
                    b.remove(j);
                    break;
                }
            }
        }
        return result;
    }
}
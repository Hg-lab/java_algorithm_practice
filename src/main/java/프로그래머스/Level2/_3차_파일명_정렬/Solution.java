package 프로그래머스.Level2._3차_파일명_정렬;

import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = files;
        Arrays.sort(answer, new FileComparator());
        return answer;
    }
}

class FileComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        String head1 = s1.split("[0-9]")[0];
        String head2 = s2.split("[0-9]")[0];

        StringBuilder number1Sb = new StringBuilder();
        char prev = ' ';
        for(int i = 0; i < s1.length(); ++i) {
            if(isNumber(prev) && !isNumber(s1.charAt(i))) break;
            if(isNumber(s1.charAt(i))) {
                number1Sb.append(s1.charAt(i)-'0');
            }
            prev = s1.charAt(i);
        }
        StringBuilder number2Sb = new StringBuilder();
        prev = ' ';
        for(int i = 0; i < s2.length(); ++i) {
            if(isNumber(prev) && !isNumber(s2.charAt(i))) break;
            if(isNumber(s2.charAt(i))) {
                number2Sb.append(s2.charAt(i)-'0');
            }
            prev = s2.charAt(i);
        }

        Integer num1 = Integer.parseInt(number1Sb.toString());
        Integer num2 = Integer.parseInt(number2Sb.toString());

        if(head1.equals(head2) && num1.equals(num2)) return 0;

        if(head1.equals(head2)) return num1.compareTo(num2);

        return head1.compareTo(head2);
    }

    private boolean isNumber(char c) {
        if(0 <= c-'0' && c-'0' <= 9) return true;
        return false;
    }
}

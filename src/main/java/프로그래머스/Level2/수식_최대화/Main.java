package 프로그래머스.Level2.수식_최대화;

import java.util.*;
// if answer < 0 answer = abs(answer)
public class Main {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.solution("100-200*300-500+20");
    }
}

class Solution {
    List<String> priorities = new ArrayList<>();
    String operators = "+*-";

    public long solution(String expression) {
        long answer = 0;
        boolean[] checked = new boolean[operators.length()];

        // give priority
        permutation(operators, checked, "");
        List<String> operatorList = getOperatorList(expression);
        List<String> numberList = new ArrayList<>(Arrays.asList(expression.split("["+operators+"]")));
        long max = 0L;
        for(String s : priorities) {
            List<String> copiedOperatorList = new ArrayList<>(operatorList);
            List<String> copiedNumberList = new ArrayList<>(numberList);

            long result = 0L;
            char[] arr = s.toCharArray();
            for(char a : arr) {

                for(int i = 0; i < copiedOperatorList.size(); i++) {
                    System.out.println(a + ", " + copiedOperatorList.get(i));
                    if(String.valueOf(a).equals(copiedOperatorList.get(i))) {
                        // operation
                        copiedNumberList.set(i, compute(a,copiedNumberList.get(i),copiedNumberList.get(i+1)));
                        // System.out.println(result);
                        copiedNumberList.remove(i+1);
                        copiedOperatorList.remove(i);
                        i--;
                        if(copiedNumberList.size() == 1) {
                            result = Long.parseLong(copiedNumberList.get(0));
                            break;
                        }
                    }
                }
            }
            max = Math.max(max, Math.abs(result));
        }

        return answer = max;
    }

    private String compute(char operator, String x, String y) {
        if(operator == '-') {
            return Long.toString(Long.parseLong(x) - Long.parseLong(y));
        }
        if(operator == '+') {
            return Long.toString(Long.parseLong(x) + Long.parseLong(y));
        }
        if(operator == '*') {
            return Long.toString(Long.parseLong(x) * Long.parseLong(y));
        }
        return "";
    }

    // remove blank
    private List<String> getOperatorList(String expression) {
        String[] arr = expression.split("[0-9]");
        List<String> result = new ArrayList<>();
        for(String s : arr) {
            if(operators.contains(s) && !s.equals("")) {
                result.add(s);
            }
        }
        return result;
    }

    private void permutation(String operators, boolean[] checked, String s) {
        if(operators.length() == s.length()) {
            priorities.add(s);
            return;
        }

        for(int i = 0; i < operators.length(); i++) {
            if(!checked[i]) {
                checked[i] = true;
                StringBuilder sb = new StringBuilder(s);
                sb.append(operators.charAt(i));
                permutation(operators, checked, sb.toString());
                checked[i] = false;
            }
        }
    }
}
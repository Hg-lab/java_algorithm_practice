package 프로그래머스.Level2.단체사진_찍기;

public class Main {
    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        solution.solution(2, new String[]{"N~F=0", "R~T>2"});
    }
}


// members: {A, C, F, J, M, N, R, T} 8명
// 1 <= n <= 100

class Solution {

    String[] members = {"A", "C", "F", "J", "M", "N", "R", "T"};
    String[] data;
    int meetingConditionNum = 0;

    public int solution(int n, String[] data) {
        this.data = data;
        int answer = 0;
        boolean[] checked = new boolean[members.length];
        permutation(checked, new StringBuilder(""));

        return answer = meetingConditionNum;
    }

    // dfs
    public void permutation(boolean[] checked, StringBuilder inLine) {
        if(inLine.length() == members.length) {
            if(checkCondition(inLine.toString())) ++meetingConditionNum;
            return;
        }
        for(int i = 0; i < members.length; i++) {
            if(!checked[i]) {
                checked[i] = true;
                inLine.append(members[i]);
                permutation(checked, inLine);
                checked[i] = false;
                inLine.deleteCharAt(inLine.length()-1);
            }
        }

    }

    public boolean checkCondition(String inLine) {



        for(String s : data) {
            String comparison = String.valueOf(s.charAt(3));
            String firstMember = String.valueOf(s.charAt(0));
            String secondMember = String.valueOf(s.charAt(2));
            int diffCondition = Integer.parseInt(String.valueOf(s.charAt(4)));

            int difference = Math.abs(inLine.indexOf(firstMember) - inLine.indexOf(secondMember))-1;

            if(comparison.equals("=")){
                if(difference != diffCondition) return false;
            }
            if(comparison.equals("<")){
                if(!(difference < diffCondition)) return false;
            }
            if(comparison.equals(">")){
                if(!(difference > diffCondition)) return false;
            }
        }
        return true;
    }
}
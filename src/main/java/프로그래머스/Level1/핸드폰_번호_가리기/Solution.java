package 프로그래머스.Level1.핸드폰_번호_가리기;

class Solution {
    public String solution(String phone_number) {
        String answer = "";

        StringBuilder phone_numberSb = new StringBuilder(phone_number);

        for(int i = 0; i < phone_numberSb.length()-4; i++) {

            phone_numberSb.setCharAt(i,'*');
            System.out.println(phone_numberSb.charAt(i));
        }

        answer = phone_numberSb.toString();

        return answer;
    }
}
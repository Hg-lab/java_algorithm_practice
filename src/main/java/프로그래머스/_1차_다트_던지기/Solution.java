package 프로그래머스._1차_다트_던지기;


import java.util.*;
// 3 times
// score range : 0 ~ 10
// S D T : score power n
// *: total + previous score *2 , #: total - previous
// At the first turn, * multiple first score
// * can show up in a row.
// * + # -> (-) * 2
// S, D, T exist only one for each score
// * # exist only one or not exist at all for each score
class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        String regexNum = "^[0-9]*$";
        String regexPrize = "\\*|\\#";
        String regexMultiple = "S|T|D";
        int num = 0;
        int power = 0;
        int sum = 0;
        int prize = 0;
        ArrayList<Integer> calculating = new ArrayList<Integer>();
        ArrayList<String> dartResultList = new ArrayList<String>();
        String previousNum = "";


        for(int i = 0; i < dartResult.length(); i++) {
            if(Character.toString(dartResult.charAt(i)).equals("0")&&previousNum.equals("1")) {
                dartResultList.remove(dartResultList.size()-1);
                dartResultList.add("10");
            } else {
                previousNum = Character.toString(dartResult.charAt(i));
                dartResultList.add(previousNum);
            }

        }


        for(int i = 0; i < dartResultList.size(); i++) {


            // When number shows up, run calculate.
            if(dartResultList.get(i).matches(regexNum)) {
                // sum += num;
                calculating.add(num);
                // System.out.println(num);
                num = Integer.parseInt(dartResultList.get(i));
            }

            if(dartResultList.get(i).matches(regexMultiple)) {
                if(dartResultList.get(i).equals("S")) power = 1;
                if(dartResultList.get(i).equals("D")) power = 2;
                if(dartResultList.get(i).equals("T")) power = 3;
                num = (int)Math.pow(num, power);

                if(i == dartResultList.size()-1) {
                    // sum += num;
                    calculating.add(num);
                    System.out.println(num);
                }
            }

            if(dartResultList.get(i).matches(regexPrize)) {
                if(dartResultList.get(i).matches("\\*")) {
                    prize = 2;
                    num = num * prize;
                    int last = calculating.get(calculating.size()-1);
                    last = last * prize;
                    calculating.remove(calculating.size()-1);
                    calculating.add(last);
                }
                if(dartResultList.get(i).matches("\\#")){
                    prize = -1;
                    num = num * prize;
                }


                if(i == dartResultList.size()-1) {
                    // sum += num;
                    calculating.add(num);
                    // System.out.println(num);
                }

            }

        }

        for(Integer c : calculating) {
            sum += c;
        }
        answer = sum;
        return answer;
    }
}
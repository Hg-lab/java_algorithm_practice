package 프로그래머스.Level1.성격_유형_검사하기;

import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        String[] categories = {"RT", "CF", "JM", "AN"};
        Map<String, Integer> scoreMap = new HashMap<>();

        // compute score
        for(int i = 0; i < choices.length; i++) {
            int score = 0;
            String type = "";
            if(choices[i] == 4) score = 0;
            if(choices[i] > 4) {
                type = String.valueOf(survey[i].charAt(1));
                score = choices[i] - 4;
            }
            if(choices[i] < 4) {
                type = String.valueOf(survey[i].charAt(0));
                score = 4 - choices[i];
            }
            scoreMap.put(type, scoreMap.getOrDefault(type, 0) + score);

        }

        System.out.println(scoreMap.toString());


        int[][] indexAndScore = new int[categories.length][2];
        for(String key : scoreMap.keySet()) {
            int categoryIndex = 0;
            for(int i = 0; i < categories.length; i++) {
                if(!categories[i].contains(key)) continue;
                if(categories[i].contains(key)) {
                    indexAndScore[i][categories[i].indexOf(key)] = scoreMap.getOrDefault(key, 0);
                }
            }
        }

        StringBuilder answerSb = new StringBuilder();
        for(int i = 0; i < indexAndScore.length; i++) {
            if(indexAndScore[i][0] < indexAndScore[i][1]) {
                answerSb.append(categories[i].charAt(1));
            } else {
                answerSb.append(categories[i].charAt(0));
            }
        }



        return answer = answerSb.toString();
    }
}
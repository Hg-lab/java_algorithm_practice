package 프로그래머스.Level2.튜플;

import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        String[] splitedS = s.split("},");
        PriorityQueue<String[]> heap = new PriorityQueue(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                Integer l1 = Integer.valueOf(o1.length);
                Integer l2 = Integer.valueOf(o2.length);
                return l1.compareTo(l2);
            }
        });
        for(String str: splitedS) {
            String parsedStr = str.replaceAll("[\\{|\\}]", "");
            String[] tupleSets = parsedStr.split(",");
            heap.offer(tupleSets);
        }

        Set<String> set = new HashSet<>();
        List<Integer> answerList = new ArrayList<>();
        while(!heap.isEmpty()) {
            String[] arr = heap.poll();
            for(String element: arr) {
                if(set.add(element)) {
                    answerList.add(Integer.parseInt(element));
                }
            }
        }
        answer = new int[answerList.size()];
        return answer = answerList.stream().mapToInt(Integer::intValue).toArray();
    }
}

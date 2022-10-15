package 프로그래머스.Level2.메뉴_리뉴얼;

// 최소 2가지 이상 주문한 메뉴
// 최소 2명 이상 손님으로부터 주문된
// 2 <= orders.length <= 20
// 1 <= course[i] <= 10
import java.util.*;

class Solution_refactored {
    List<String> answerList = new ArrayList<>();
    Map<String, Integer> combinationMap = new HashMap<>();
    int max;

    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        PriorityQueue<String> pq = new PriorityQueue<>();
        for(int i = 0; i < course.length; i++) {
            max = 0;
            for(String order: orders) {
                if(course[i] > order.length()) continue;;
                doCombination(order, course[i], 0, "");
            }
            for(String s: combinationMap.keySet()) {
                if(s.length() != course[i]) continue;
                if(combinationMap.get(s) < 2) continue;
                if(max == combinationMap.get(s))
                    pq.offer(s);
            }
        }

        answer = new String[pq.size()];
        int index = 0;
        while(!pq.isEmpty()) {
            answer[index++] = pq.poll();
        }

        return answer;
    }


    // 주문한 메뉴로 조합
    private void doCombination(String orderedMenu, int selectingNum, int start, String menu) {

        if(selectingNum == menu.length()) {
            char[] menuAsArr = menu.toCharArray();
            Arrays.sort(menuAsArr);
            menu = new String(menuAsArr);
            combinationMap.put(menu, combinationMap.getOrDefault(menu,0) + 1);
            max = Math.max(max, combinationMap.get(menu));
            return;
        }

        for(int i = start; i < orderedMenu.length(); i++) {
            doCombination(orderedMenu, selectingNum, i+1, menu+orderedMenu.charAt(i));
        }
    }

}
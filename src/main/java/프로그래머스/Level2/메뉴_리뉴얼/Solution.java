package 프로그래머스.Level2.메뉴_리뉴얼;


// 최소 2가지 이상 주문한 메뉴
// 최소 2명 이상 손님으로부터 주문된
// 2 <= orders.length <= 20
// 1 <= course[i] <= 10
import java.util.*;

class Solution {
    List<String> answerList = new ArrayList<>();
    boolean[] checked;
    Map<String, Integer> combinationMap = new HashMap<>();
    String[] orders;

    public String[] solution(String[] orders, int[] course) {
        this.orders = orders;
        String[] answer = {};
        String orderedMenus = getOrderedMenus(orders);
        for(String order: orders) {
            for(int i = 0; i < course.length; i++) {

                checked = new boolean[order.length()];
                if(course[i] > order.length()) break;
                doCombination(order, checked, 0, course[i]);
            }
        }
        Map<Integer,Integer> courseMostNum = new HashMap<>();
        for(int num: course) {
            int max = 0;
            for(String key : combinationMap.keySet()) {
                if(num != key.length()) continue;
                max = Math.max(combinationMap.get(key), max);
            }
            courseMostNum.put(num,max);
        }

        for(String key : combinationMap.keySet()) {
            if(combinationMap.get(key) >= 2 && combinationMap.get(key) == courseMostNum.get(key.length())) {
                answerList.add(key);
            }
        }

        answer = answerList.stream().toArray(String[]::new);
        Arrays.sort(answer);
        return answer;
    }



    private void doCombination(String orderedMenus, boolean[] checked, int start, int selectingNum) {

        if(selectingNum == 0) {
            StringBuilder order = new StringBuilder();
            for(int i = 0; i < checked.length; i++) {
                if(checked[i]) {
                    order.append(orderedMenus.charAt(i));
                }
            }
            String orderStr = order.toString();
            char[] StringtoChar = orderStr.toCharArray();
            Arrays.sort(StringtoChar);
            String SortedString = new String(StringtoChar);
            combinationMap.put(SortedString, combinationMap.getOrDefault(SortedString,0) + 1);
            return;
        }

        for(int i = start; i < orderedMenus.length(); i++) {
            if(!checked[i]) {
                checked[i] = true;
                doCombination(orderedMenus, checked, i+1, selectingNum-1);
                checked[i] = false;
            }
        }
        return;
    }

    private String getOrderedMenus(String[] orders) {
        StringBuilder orderedMenus = new StringBuilder();
        for(String order : orders) {
            for(char menu : order.toCharArray()) {
                if(!orderedMenus.toString().contains(String.valueOf(menu))) {
                    orderedMenus.append(menu);
                }
            }
        }
        return orderedMenus.toString();
    }

}
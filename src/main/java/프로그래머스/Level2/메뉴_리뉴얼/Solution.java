package 프로그래머스.Level2.메뉴_리뉴얼;


// 최소 2가지 이상 주문한 메뉴
// 최소 2명 이상 손님으로부터 주문된
// 2 <= orders.length <= 20
// 1 <= course[i] <= 10
import java.util.*;

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
        // String orderedMenus = getOrderedMenus(orders);

        for(String order: orders) {
            for(int i = 0; i < course.length; i++) {

                checked = new boolean[order.length()];
                // course는 오름차순 이므로 메뉴 개수보다 많으면 break
                if(course[i] > order.length()) break;
                // 이미 주문한 메뉴로 원하는 코스 숫자에 맞추어 조합을 만듦
                doCombination(order, checked, 0, course[i]);
            }
        }
        // 삽입된 맵에서 메뉴 코스 길이별로 가장 많이 주문된 숫자를 구해 hash를 만듦
        Map<Integer,Integer> courseMostNum = new HashMap<>();
        for(int num: course) {
            int max = 0;
            for(String key : combinationMap.keySet()) {
                if(num != key.length()) continue;
                max = Math.max(combinationMap.get(key), max);
            }
            courseMostNum.put(num,max);
        }

        // 2번 이상 주문되었으면서 가장 많이 주문된 코스만 답에 저장
        // combinationMap 중 >=2 가 메뉴 후보이고 그중 가장 많이 주문된 조합을 찾아야함
        // ex) 1번 테스트케이스에서 A, D는 2번 주문 되어서 후보지만 최종 선정되지 않음
        for(String key : combinationMap.keySet()) {
            if(combinationMap.get(key) >= 2 && combinationMap.get(key) == courseMostNum.get(key.length())) {
                answerList.add(key);
            }
        }

        answer = answerList.stream().toArray(String[]::new);
        Arrays.sort(answer);
        return answer;
    }


    // 주문한 메뉴로 조합
    private void doCombination(String orderedMenus, boolean[] checked, int start, int selectingNum) {

        if(selectingNum == 0) {
            StringBuilder order = new StringBuilder();
            for(int i = 0; i < checked.length; i++) {
                if(checked[i]) {
                    order.append(orderedMenus.charAt(i));
                }
            }
            // 조합된 메뉴구성을 sorting 하여 맵에 삽입
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

    // // 한번이라도 주문한 메뉴를 모수로 구하기 위함
    // private String getOrderedMenus(String[] orders) {
    //     StringBuilder orderedMenus = new StringBuilder();
    //     for(String order : orders) {
    //         for(char menu : order.toCharArray()) {
    //             if(!orderedMenus.toString().contains(String.valueOf(menu))) {
    //                 orderedMenus.append(menu);
    //             }
    //         }
    //     }
    //     return orderedMenus.toString();
    // }

}
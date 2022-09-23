package 프로그래머스.Level2.위장;
import java.util.*;

public class wrong_Solution {

    boolean[] checked;
    List<Integer> combinationedList = new ArrayList<>();
    public int solution(String[][] clothes) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();
        for(String[] c : clothes) {
            map.put(c[1], map.getOrDefault(c[1], 0) + 1);
        }

        int[] targetArr = new int[map.keySet().size()];
        int index = 0;
        for(String key : map.keySet())
            targetArr[index++] = map.get(key);

        checked = new boolean[targetArr.length];
        for(int depth = 1; depth <= targetArr.length; depth++) {
            combination(targetArr, checked, 0, depth, 1);
        }
        return answer = combinationedList.stream().mapToInt(Integer::intValue).sum();
    }

    private void combination(int[] targetArr, boolean[] checked, int start, int depth, int value) {
        if(depth == 0) {
            combinationedList.add(value);
            return;
        }

        for(int i = start; i < targetArr.length; i++) {
            if(!checked[i]) {
                checked[i] = true;
                value *= targetArr[i];

                combination(targetArr, checked, start+1, depth-1, value);
                checked[i] = false;
                value /= targetArr[i];
            }
        }
    }
}
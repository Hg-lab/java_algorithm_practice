package 프로그래머스.Level2.이모티콘_할인행사;


import java.util.*;

class Solution {

    List<int[]> discountList = new ArrayList<>();
    int[] discounts = {10,20,30,40};
    Map<Integer, Integer> subscriberIncome = new HashMap<>();
    int[] emoticons;

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        this.emoticons = emoticons;

        dfs(new int[emoticons.length], emoticons.length, 0);

        for(int i = 0; i< discountList.size(); i++) {
            int subCnt = 0;
            int income = 0;
            for(int j = 0; j < users.length; j++) {
                int[] a = discountList.get(i);

                String[] subsAndIncome = compute(discountList.get(i).clone(), users[j].clone());

                if(subsAndIncome[0] == "T") subCnt++;
                else income += Integer.parseInt(subsAndIncome[1]);
            }
            if(subscriberIncome.getOrDefault(subCnt, 0) <= income)
                subscriberIncome.put(subCnt, income);
        }
        int[] test1 = {40, 40, 20, 40};
        for(int[] user: users) {
            compute(test1, user);
        }


        System.out.println(subscriberIncome.toString());

        int maxSubscriber = Integer.MIN_VALUE;
        for(int key: subscriberIncome.keySet()) {
            maxSubscriber = Math.max(maxSubscriber, key);
        }
        answer[0] = maxSubscriber;
        answer[1] = subscriberIncome.get(maxSubscriber);

        return answer;
    }

    private String[] compute(int[] discountArr, int[] userInfo) {

        List<Integer> buyList = new ArrayList<>();
        for(int i = 0; i < discountArr.length; i++) {
            if(discountArr[i] >= userInfo[0]) {
                buyList.add(i);
            }
        }
        int totalBuying = 0;
        for(int i = 0; i < emoticons.length; i++) {
            if(buyList.contains(i)){
                totalBuying += (emoticons[i] * (100-discountArr[i])/100);
            }
        }

        String canSubscribe = "F";

        if(totalBuying >= userInfo[1]) canSubscribe = "T";

        String[] rtn = {canSubscribe, Integer.toString(totalBuying)};
        return rtn;
    }

    private void dfs(int[] arr, int length, int depth) {
        if(depth == length) {
            discountList.add(arr.clone());
            return;
        }
        for(int i = depth; i < arr.length; i++) {
            if(arr[i] == 0) {
                for(int k = 0; k < 4; k++) {
                    arr[i] = discounts[k];
                    dfs(arr, length, depth+1);
                }
                arr[i] = 0;
            }
        }
    }
}
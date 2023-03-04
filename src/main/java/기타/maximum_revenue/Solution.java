package 기타.maximum_revenue;
// https://hyanghope.tistory.com/62

// int[] v: 물건 가격 배열
// n: 가격 변동 기간
// 기간 내 가지고 있던 물건을 팔고, 반드시 하나를 사야한다.
// 같은 날에 팔고 바로 살 수 없다.
// 가장 비싸게 팔고 가장 싸게 팔아서 그 차익이 최대가 되는 경우, 또는 손실이 최소가 되는 경우를 구하라.
public class Solution {
    public static void main(String[] args) {
        System.out.println(-1 == solution2(10, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        System.out.println(5 == solution2(6, new int[]{4, 1, 7, 6, 5, 2}));
        System.out.println(8 == solution2(8, new int[]{4, 9, 3, 2, 1, 1, 6, 10}));
    }

    public static int solution(int n, int[] v) {
        int answer = -1;
        int maxPrice = v[0];
        int minPrice = v[1];
        int maxRevenue = v[0] - v[1];
        for(int i = 2; i < n; i++) {
            if(v[i] > maxPrice) maxPrice = v[i++];
            if(v[i] < minPrice) minPrice = v[i];
            int revenue = maxPrice - v[i];
            if(maxRevenue < revenue) maxRevenue = revenue;
        }
        return maxRevenue;
    }

    public static int solution2(int n, int[] v) {
        int max_price = v[0];
        int max_revenue = Integer.MIN_VALUE;
        for(int i = 1; i < n; i++) {
            int revenue = max_price - v[i];
            if(max_revenue < revenue) {
                max_revenue = revenue;
            }
            if(v[i] > max_price)
                max_price = v[i];
        }
        return max_revenue;

    }

    public static int solution3(int n, int[] v) {
        int answer = 0;
        int maxPrice = v[0];
        int maxProfit = Integer.MIN_VALUE;
        int presentProfit = 0;
        for(int i = 1; i < v.length; i++) {
            presentProfit = maxPrice - v[i];
            if(presentProfit > maxProfit) {
                maxProfit = presentProfit;
            }
            if(maxPrice < v[i]) {
                maxPrice = v[i];
            }
        }

        return answer = maxProfit;
    }
}

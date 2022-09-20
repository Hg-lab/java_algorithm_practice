package 프로그래머스.Level2.주차_요금_계산;

import java.util.*;


// 시간을 누적시켜서 최종 계산금액 구해야함
class Solution {

    Map<String, String> parkingLot = new HashMap<>();
    Map<String, Integer> accumulatedMin = new HashMap<>();
    int[] fees;

    public int[] solution(int[] fees, String[] records) {
        int[] answer = new int[records.length];
        this.fees = fees;

        // 입출차로 비용 누적
        for(String record : records) {
            String[] arr = record.split(" ");
            if(arr[2].equals("IN")) {
                // 주차장에 저장
                parkingLot.put(arr[1], arr[0]);
            } if(arr[2].equals("OUT")) {
                int mins = computeMinute(parkingLot.get(arr[1]), arr[0]);
                accumulatedMin.put(arr[1], accumulatedMin.getOrDefault(arr[1], 0) + mins);
                parkingLot.remove(arr[1]);
            }
        }

        // 남아있는 차량 23:59 출차
        if(!parkingLot.isEmpty()) {
            // 동시성 에러로 Set copy
            for(String car: Set.copyOf(parkingLot.keySet())) {
                int mins = computeMinute(parkingLot.get(car), "23:59");
                accumulatedMin.put(car, accumulatedMin.getOrDefault(car, 0) + mins);
                parkingLot.remove(car);
            }
        }

        // 금액 계산
        for(String car: accumulatedMin.keySet()) {
            accumulatedMin.put(car, computeFee(accumulatedMin.get(car)));
        }

        List<Map.Entry<String, Integer>> sorting = new ArrayList<>(accumulatedMin.entrySet());

        Collections.sort(sorting, new Comparator<>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        answer = new int[sorting.size()];
        for(int i = 0 ; i < sorting.size(); i ++) {
            answer[i] = sorting.get(i).getValue();
        }

        return answer;
    }

    // input: 시간 -> return 금액
    private int computeFee(int minute) {
        if(minute <= fees[0]) {
            return fees[1];
        } else {
            int unitTime = (int)Math.ceil((double)(minute - fees[0]) / (double)fees[2]);
            return fees[1] +  unitTime * fees[3];
        }
    }

    private int computeMinute(String in, String out) {
        int inHour = Integer.parseInt(in.substring(0,2));
        int inMin = Integer.parseInt(in.substring(3,5));
        int outHour = Integer.parseInt(out.substring(0,2));
        int outMin = Integer.parseInt(out.substring(3,5));
        int minute = outMin < inMin ? (outHour - 1 - inHour) * 60 + (outMin + 60 - inMin) : (outHour - inHour) * 60 + (outMin - inMin);

        return minute;
    }
}


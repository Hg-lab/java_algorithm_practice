package 프로그래머스.Level2.호텔_대실;

import java.util.*;

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int solution1 = solution.solution(new String[][]{{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}});
        System.out.println("solution1 = " + solution1);
    }
}

class Solution {
    class Booking {
        int start;
        int end;
        int duration;
        boolean isBooked = false;

        Booking(String[] bookTime) {
            this.start = Integer.valueOf(bookTime[0].split("\\:")[0]) * 60
                    + Integer.valueOf(bookTime[0].split("\\:")[1]);
            this.end = Integer.valueOf(bookTime[1].split("\\:")[0]) * 60
                    + Integer.valueOf(bookTime[1].split("\\:")[1]) + 10;
            this.duration = end - start;
        }

        boolean isConflict(Booking booking) {
            // booking이 먼저인 경우
            if (booking.start <= this.start && this.start < booking.end) {
                return true;
            }

            //start가 먼저인 경우
            if (this.start <= booking.start && booking.start < this.end) {
                return true;
            }
            return false;
        }

        void book(ArrayList<Booking> room) {
            this.isBooked = true;
            room.add(this);
        }
    }

    public int solution(String[][] book_time) {
        int answer = 0;
        ArrayList<Booking> bookingList = new ArrayList<>();
        Arrays.stream(book_time).forEach(b -> bookingList.add(new Booking(b)));
        Collections.sort(bookingList, (b1, b2) -> b1.start-b2.start);

        ArrayList<ArrayList<Booking>> roomList = new ArrayList<>();
        ArrayList<Booking> newRoom;
        for (Booking booking : bookingList) {
            if(roomList.isEmpty()) {
                newRoom = new ArrayList<>();
                booking.book(newRoom);
                roomList.add(newRoom);
                continue;
            }
            for (ArrayList<Booking> room : roomList) {
                boolean isAvailableRoom = true;
                for (Booking booked : room) {
                    if (booking.isConflict(booked)) isAvailableRoom = false;
                }
                if (isAvailableRoom) {
                    booking.book(room);
                    if(booking.isBooked) break;
                }
            }

            if (!booking.isBooked) {
                newRoom = new ArrayList<>();
                booking.book(newRoom);
                roomList.add(newRoom);
            }
        }

        return roomList.size();
    }
}
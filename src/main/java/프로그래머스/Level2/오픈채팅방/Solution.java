package 프로그래머스.Level2.오픈채팅방;

import java.util.HashMap;
import java.util.ArrayList;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};

        HashMap<String, String> usersInChat = new HashMap<>();
        ArrayList<String> resultList = new ArrayList<>();
        String enterMension = "님이 들어왔습니다.";
        String leaveMension = "님이 나갔습니다.";
        for(String recordString : record) {
            String[] splited = recordString.split(" ");
            if(splited[0].equals("Enter")) {
                usersInChat.put(splited[1], splited[2]);
                resultList.add(splited[1] + enterMension);
            }
            if(splited[0].equals("Change")) {
                usersInChat.put(splited[1], splited[2]);
            }
            if(splited[0].equals("Leave")) {
                resultList.add(splited[1] + leaveMension);
            }
        }

        for(int i = 0; i< resultList.size(); i++) {
            String userid = resultList.get(i).replace(enterMension,"");
            userid = userid.replace(leaveMension,"");


            if(usersInChat.get(userid) != null) {
                resultList.set(i, resultList.get(i).replace(userid, usersInChat.get(userid)));
            }
        }

        return answer = resultList.stream().toArray(String[] :: new);
    }
}

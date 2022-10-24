package 프로그래머스.Level2._3차_방금그곡;

import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        List<Music> list = new ArrayList<>();
        for(int i = 0; i < musicinfos.length; ++i) {
            int duration = getDuration(musicinfos[i]);
            String title = musicinfos[i].split(",")[2];
            String pitches = getPitches(musicinfos[i].split(",")[3]);

            pitches = getWholePitches(duration, pitches);
            String target = getPitches(m);

            if(pitches.contains(target)) {

                list.add(new Music(duration, i, title, pitches));
            }
        }

        if(list.size() == 0) return "(None)";
        if(list.size() == 1) return list.get(0).title;
        Collections.sort(list, new Comparator<>() {
            public int compare(Music m1, Music m2) {
                Integer d1 = Integer.valueOf(m1.duration);
                Integer d2 = Integer.valueOf(m2.duration);
                if(d1 == d2) return m1.index - m2.index;
                return d2.compareTo(d1);
            }
        });
        return list.get(0).title;
    }
    private String getWholePitches(int duration, String pitches) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < duration; i++) {
            sb.append(pitches.charAt(i % pitches.length()));
        }
        return sb.toString();
    }

    private String getPitches(String pitches) {
        // a b c d e ... 로 변환
        String[] references = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        String replacement;

        for(int i = 0; i < references.length; ++i) {
            if(references[i].contains("#")) {
                replacement = Character.toString((char)('a' + i));
                pitches = pitches.replaceAll(references[i], replacement);
            }

        }
        for(int i = 0; i < references.length; ++i) {
            if(!references[i].contains("#")) {
                replacement = Character.toString((char)('a' + i));
                pitches = pitches.replaceAll(references[i], replacement);
            }
        }
        return pitches;
    }

    private int getDuration(String s) {
        String startAt = s.split(",")[0];
        String endAt = s.split(",")[1];

        int hourDiff = Integer.parseInt(endAt.split(":")[0]) - Integer.parseInt(startAt.split(":")[0]);
        int minDiff = Integer.parseInt(endAt.split(":")[1]) - Integer.parseInt(startAt.split(":")[1]);
        return hourDiff*60 + minDiff;
    }
}

class Music {
    int duration;
    int index;
    String title;
    String pitches;

    public Music(int duration, int index, String title, String pitches) {
        this.duration = duration;
        this.index = index;
        this.title = title;
        this.pitches = pitches;

    }
}
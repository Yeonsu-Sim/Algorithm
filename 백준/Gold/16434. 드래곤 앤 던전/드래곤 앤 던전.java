import java.io.*;
import java.util.*;

public class Main {
    public static class Room {
        boolean isMonster;
        int a;
        int h;

        public Room(boolean isMonster, int a, int h) {
            this.isMonster = isMonster;
            this.a = a;
            this.h = h;
        }

    }

    static Room[] rooms;
    static int N, attack;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        attack = Integer.parseInt(st.nextToken());

        rooms = new Room[N];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            
            rooms[i] = new Room(t == 1, a, h);
        }

        long start = 1L;
        long end = Long.MAX_VALUE;

        while (start <= end) {
            long mid = (start + end) / 2;
            if (isDied(mid, attack)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(start);
    }

    public static boolean isDied(long maxH, long curA) {
        long curH = maxH;
        for (Room r : rooms) {
            if (r.isMonster) {

                // 바로 죽이는 경우
                if (curA >= r.h) continue;

                // 몬스터와 대치하는 경우
                int needTime = (int) ((long)r.h / curA) + ((long)r.h % curA == 0 ? 0 : 1);
                long needH = (long)(needTime-1)*r.a;

                // 용사가 죽는 경우
                if (curH - needH <= 0) {
                    return true;
                }
                curH -= needH;

            } else {
                curH += r.h;
                // 이 부분?
                curH = Math.min(curH, maxH);
                curA += r.a;
            }
        }
        return false;
    }
}
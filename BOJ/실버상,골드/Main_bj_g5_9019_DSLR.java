import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_bj_g5_9019_DSLR {

    static class DSLR {
        int num;
        String command;
        DSLR(int num, String command) {
            this.num = num;
            this.command = command;
        }
    }

    static boolean[] vistied;
    static int T, ori, res;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ori = Integer.parseInt(st.nextToken());
            res = Integer.parseInt(st.nextToken());

            Queue<DSLR> queue = new ArrayDeque<>();
            vistied = new boolean[10000];

            vistied[ori] = true;
            queue.offer(new DSLR(ori, ""));

            while(!queue.isEmpty()) {
                DSLR cur = queue.poll();

                if(cur.num == res) {
                    sb.append(cur.command).append("\n");
                    break;
                }
                // d1 d2 d3 d4
                // 10000이하이면 모듈러 연산을해도 같은 값이기에 상관없다.
                int D = (2*cur.num) % 10000;
                // 0일때만 9999로 변경
                int S = (cur.num == 0)? 9999 : cur.num-1;
                // (cur.num%1000) d2 d3 d4 를 한자리씩 올리고 d1을 d4에다가 넣기
                int L = (cur.num%1000)*10 + cur.num/1000;
                // L과 반대로 (cur.num%10)*1000 -> d4를 d1에  cur.num/10 -> d1,d2,d3 를 d2,d3,d4에 넣기
                int R = (cur.num%10)*1000 + cur.num/10;

                if(!vistied[D]) {
                    vistied[D] = true;
                    queue.offer(new DSLR(D, cur.command+"D"));
                }
                if(!vistied[S]) {
                    vistied[S] = true;
                    queue.offer(new DSLR(S, cur.command+"S"));
                }
                if(!vistied[L]) {
                    vistied[L] = true;
                    queue.offer(new DSLR(L, cur.command+"L"));
                }
                if(!vistied[R]) {
                    vistied[R] = true;
                    queue.offer(new DSLR(R, cur.command+"R"));
                }
            }
        }
        System.out.print(sb);
        br.close();
    }
}
import java.io.*;
import java.util.StringTokenizer;

public class SWEA_8382_방향전환 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer str = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(str.nextToken());
            int y1 = Integer.parseInt(str.nextToken());
            int x2 = Integer.parseInt(str.nextToken());
            int y2 = Integer.parseInt(str.nextToken());

            int abs_x = Math.abs(x2-x1);
            int abs_y = Math.abs(y2-y1);
            int cnt = 0;

            if((abs_x+abs_y) == 1) {
                cnt = 1;
            }else if (abs_x == abs_y) {
                cnt = 2 * abs_x;
            } else if ((abs_x > abs_y) && (abs_x-abs_y)%2 == 0) {
                cnt = abs_x*2;
            } else if ((abs_x < abs_y) && (abs_x-abs_y)%2 == 0) {
                cnt = abs_y*2;
            } else if ((abs_x > abs_y) && (abs_x-abs_y)%2 != 0) {
                cnt = abs_x*2-1;
            }else if ((abs_x < abs_y) && (abs_x-abs_y)%2 != 0) {
                cnt = abs_y*2-1;
            }

            bw.write("#" + test_case + " " + cnt + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
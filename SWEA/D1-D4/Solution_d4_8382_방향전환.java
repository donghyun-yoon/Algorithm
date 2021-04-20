import java.io.*;
import java.util.StringTokenizer;

public class Solution_d4_8382_방향전환 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int x = Math.abs(x1-x2);
            int y = Math.abs(y1-y2);
            int d = Math.max(x, y) * 2 - (Math.abs(x+y)%2);
            sb.append("#").append(tc).append(" ").append(d).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}
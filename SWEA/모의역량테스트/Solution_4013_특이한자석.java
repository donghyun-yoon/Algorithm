import java.io.*;
import java.util.StringTokenizer;

public class Solution_4013_특이한자석 {

    static int K, moveIdx[], magnet[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            int K = Integer.parseInt(br.readLine());

            magnet = new int[4][8];

            for(int i=0; i<4; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<8; j++) magnet[i][j] = Integer.parseInt(st.nextToken());
            }
            
            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int index = Integer.parseInt(st.nextToken())-1;
                int d = Integer.parseInt(st.nextToken());
                moveIdx = new int[4];
                moveCheck(d, index, moveIdx);
                for(int j=0; j<4; j++) {
                    if(moveIdx[j] != 0) move(moveIdx[j], j);
                }
            }

            int res = magnet[0][0] + magnet[1][0]*2 + magnet[2][0]*4 + magnet[3][0]*8;
            
            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    static void moveCheck(int d, int index, int[] moveIdx) {
        if(moveIdx[index] != 0 || index < 0 || index >= 4) return;
        
        moveIdx[index] = d;
        //자석의 붙어있는 면
        int ns = magnet[index][6];
        int ne = magnet[index][2];
        int nd = (d==1)?-1:1;
        
        if(index > 0 && ns != magnet[index-1][2]) {
            moveCheck(nd, index-1, moveIdx);
        }
        if(index < 3 && ne != magnet[index+1][6]) {
            moveCheck(nd, index+1, moveIdx);
        }

        return;
    }

    static void move(int d, int index) {
        int t;
        switch(d) {
            case 1:
            t = magnet[index][0];
            magnet[index][0] = magnet[index][7];
            magnet[index][7] = magnet[index][6];
            magnet[index][6] = magnet[index][5];
            magnet[index][5] = magnet[index][4];
            magnet[index][4] = magnet[index][3];
            magnet[index][3] = magnet[index][2];
            magnet[index][2] = magnet[index][1];
            magnet[index][1] = t;
            break;
            case -1:
            t = magnet[index][0];
            magnet[index][0] = magnet[index][1];
            magnet[index][1] = magnet[index][2];
            magnet[index][2] = magnet[index][3];
            magnet[index][3] = magnet[index][4];
            magnet[index][4] = magnet[index][5];
            magnet[index][5] = magnet[index][6];
            magnet[index][6] = magnet[index][7];
            magnet[index][7] = t;
            break;
        }
    }
}
import java.io.*;
import java.util.StringTokenizer;

public class Solution_d4_5643_키순서 {

    static final int INF = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            int[][] adjMatrix = new int[N+1][N+1];
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    adjMatrix[i][j] = (i==j)? 0 : INF;
                }
            }

            for(int i=0; i<M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjMatrix[from][to] = 1;
            }
            
            //플로이드워셜
            for(int k=1; k<=N; k++) {
                for(int i=1; i<=N; i++) {
                    if(k == i) continue;
                    for(int j=1; j<=N; j++) {
                        if(k == j || i == j) continue;
                        if(adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]) {
                            adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                        }
                    }
                }
            }

            int res = 0;
            for(int i=1; i<=N; i++) {
                boolean flag = true;
                for(int j=1; j<=N; j++) {
                    if(adjMatrix[i][j] == INF && adjMatrix[j][i] == INF) {
                        flag = false;
                        break;
                    }
                }
                if(flag) res++;
            }

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}
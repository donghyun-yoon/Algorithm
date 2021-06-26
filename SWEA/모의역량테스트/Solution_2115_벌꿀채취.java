import java.io.*;
import java.util.StringTokenizer;

public class Solution_2115_벌꿀채취 {

    static int[][] map,dp;
    static int N,M,C,maxSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp = new int[N][N-M+1];
            // 부분집합으로 범위내에서 만들수 있는 최대값을 dp배열에 미리 만들어 놓는다.
            for(int i=0; i<N; i++) {
                for(int j=0; j<=N-M; j++) {
                    maxSum = 0;
                    subset(0, i, j, 0, 0);
                    dp[i][j] = maxSum;
                }
            }

            int res = 0;
            //조합을 사용
            for(int i=0; i<N; i++) {
                for(int j=0; j<=N-M; j++) {
                    
                    //조합 수들간의 최대값을 찾는다.
                    for(int x=i; x<N; x++) {
                        for(int y=(x==i)?j+M:0; y<=N-M; y++) {
                            res = Math.max(res, dp[i][j] + dp[x][y]);
                        }
                    }

                }
            }
            
            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }

        System.out.print(sb);
        br.close();
    }

    private static void subset(int cnt, int i, int j, int c, int max) {
        if(c > C) return;

        if(cnt == M) {
            maxSum = Math.max(maxSum, max);
            return;
        }

        subset(cnt+1, i, j+1, c+map[i][j], max+(map[i][j] * map[i][j]));
        subset(cnt+1, i, j+1, c, max);
    }

}
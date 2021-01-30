import java.util.Scanner;

public class SWEA_6109_추억의2048게임 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            String cmd = sc.next();
            int[][] in = new int[N][N];
            int[][] out = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    in[i][j] = sc.nextInt();
                }
            }
            switch (cmd) {
            case "up":
                for (int j = 0; j < N; j++) { // 열 우선 탐색
                    for (int i = 0; i < N - 1; i++) { // 위부터 아래로
                        if (in[i][j] == 0) // 현재 값이 0이면 패스
                            continue;

                        int idx = i + 1;

                        while (in[idx][j] == 0) { // 비교할 값이 0이면 다음값
                            if (idx == N - 1) // 끝까지 탐색
                                break;
                            idx++;
                        }

                        if (in[idx][j] == 0) // 비교할 값이 0이면 패스
                            continue;

                        if (in[i][j] == in[idx][j]) { // 합치고 지우고
                            in[i][j] += in[i][j];
                            in[idx][j] = 0;
                            i = idx;
                        }
                    }
                    int cur = 0;
                    for (int i = 0; i < N; i++) { // 결과 입력
                        if (in[i][j] != 0) {
                            out[cur++][j] = in[i][j];
                        }
                    }
                }
                break;
            case "down":
                for (int j = 0; j < N; j++) { // 열 우선 탐색
                    for (int i = N - 1; i > 0; i--) { // 아래부터 위
                        if (in[i][j] == 0) // 현재 값이 0이면 패스
                            continue;

                        int idx = i - 1;

                        while (in[idx][j] == 0) { // 비교할 값이 0이면 다음값
                            if (idx == 0) // 끝까지 탐색
                                break;
                            idx--;
                        }

                        if (in[idx][j] == 0) // 비교할 값이 0이면 패스
                            continue;
                        if (in[i][j] == in[idx][j]) { // 합치고 지우고
                            in[i][j] += in[i][j];
                            in[idx][j] = 0;
                            i = idx;
                        }

                    }
                    int cur = N - 1;
                    for (int i = N - 1; i >= 0; i--) { // 결과 입력
                        if (in[i][j] != 0) {
                            out[cur--][j] = in[i][j];
                        }
                    }
                }
                break;
            case "left":
                for (int i = 0; i < N; i++) { // 행우선 탐색
                    for (int j = 0; j < N - 1; j++) { // 왼쪽부터 오른쪽으로
                        if (in[i][j] == 0) // 현재 값이 0이면 패스
                            continue;

                        int idx = j + 1;

                        while (in[i][idx] == 0) { // 비교할 값이 0이면 다음값
                            if (idx == N - 1) // 끝까지 탐색
                                break;
                            idx++;
                        }

                        if (in[i][idx] == 0) // 비교할 값이 0이면 패스
                            continue;

                        if (in[i][j] == in[i][idx]) { // 합치고 지우고
                            in[i][j] += in[i][j];
                            in[i][idx] = 0;
                            j = idx;
                        }
                    }
                    int cur = 0;
                    for (int j = 0; j < N; j++) { // 결과 입력
                        if (in[i][j] != 0) {
                            out[i][cur++] = in[i][j];
                        }
                    }
                }
                break;

            case "right":
                for (int i = 0; i < N; i++) { // 행우선 탐색
                    for (int j = N-1; j > 0; j--) { // 오른쪽 부터 왼쪽으로
                        if (in[i][j] == 0) // 현재 값이 0이면 패스
                            continue;

                        int idx = j - 1;

                        while (in[i][idx] == 0) { // 비교할 값이 0이면 다음값
                            if (idx == 0) // 끝까지 탐색
                                break;
                            idx--;
                        }

                        if (in[i][idx] == 0) // 비교할 값이 0이면 패스
                            continue;

                        if (in[i][j] == in[i][idx]) { // 합치고 지우고
                            in[i][j] += in[i][j];
                            in[i][idx] = 0;
                            j = idx;
                        }
                    }
                    int cur = N-1;
                    for (int j = N-1; j >=0 ; j--) { // 결과 입력
                        if (in[i][j] != 0) {
                            out[i][cur--] = in[i][j];
                        }
                    }
                }
                break;

            }

            System.out.println("#" + tc);
            for (int[] is : out) {
                for (int i : is) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }

        } // end of TC
    }// end of Main
}
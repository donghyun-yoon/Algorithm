import java.util.Scanner;

class SWEA_2805_농작물수확하기 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            String[] str = new String[N];
            int[][] farm = new int[N][N];

            for (int i = 0; i < N; i++) {
                String s = sc.next();
                for (int j = 0; j < N; j++) {
                    farm[i][j] = s.charAt(j)-'0';
                }
            }

            int half = N / 2;
            int index = 1, sum = 0;

            /*for (int i = half - 1; i >= 0; i--) {
                for (int j = index; j < farm.length - index; j++) {
                    sum += farm[i][j];
                }
                index++;
            }
            for (int i = 0; i < farm.length; i++)
                sum += farm[half][i];
            index = 1;
            for (int i = half + 1; i < farm.length; i++) {
                for (int j = index; j < farm.length - index; j++) {
                    sum += farm[i][j];
                }
                index++;
            }*/

            for(int i=0; i<half; i++) {
                for(int j=0; j<index; j++) {
                    sum += farm[i][half-i+j];
                    sum += farm[N-1-i][half-i+j];
                }
                index += 2;
            }
            for(int i=0; i<N; i++) sum += farm[half][i];

            System.out.println("#" + test_case + " " + sum);
        }
    }
}
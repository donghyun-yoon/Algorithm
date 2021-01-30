import java.util.Scanner;

class SWEA_1954_달팽이숫자 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();

            int[][] snail = new int[N][N];

            int row=0, col=0;
            int num=1;
            int check = 0;

            for(; col<N; col++) snail[row][col] = num++;
            col--;

            while(N > 0) {
                if(check%2==0) N--;
                    check++;
                    switch(check%4) {
                        case 0: {
                            for(int i=0; i<N; i++) snail[row][++col] = num++;
                            break;
                        }
                        case 1: {
                            for(int i=0; i<N; i++) snail[++row][col] = num++;
                            break;
                        }
                        case 2: {
                            for(int i=0; i<N; i++) snail[row][--col] = num++;
                            break;
                        }
                        case 3: {
                            for(int i=0; i<N; i++) snail[--row][col] = num++;
                            break;
                        }
                        default:
                    }
            }

            System.out.println("#"+test_case);
            for(int i=0; i<snail.length; i++) {
                for(int j=0; j<snail.length; j++) {
                    System.out.print(snail[i][j] + " ");
                }
                System.out.println("");
            }
        }
    }
}
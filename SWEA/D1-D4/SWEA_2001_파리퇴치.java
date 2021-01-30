import java.util.Scanner;

class SWEA_2001_파리퇴치 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[][] fly = new int[N][N];
            for(int i=0; i<fly.length; i++)
                for(int j=0; j<fly.length; j++)
                    fly[i][j] = sc.nextInt();

            int sum,max=0;
            for(int i=0; i<fly.length-M+1; i++) {
                for(int j=0; j<fly.length-M+1; j++) {
                    sum=0;
                    for(int x=i; x<i+M; x++) {
                        for(int y=j; y<j+M; y++) {
                            sum += fly[x][y];
                        }
                    }
                    if(sum > max) max = sum;
                }
            }
            
            System.out.println("#"+test_case+" "+max);
        }
    }
}
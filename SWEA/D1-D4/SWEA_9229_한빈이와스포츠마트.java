import java.util.Scanner;

class SWEA_9229_한빈이와스포츠마트 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[] snack = new int[N];

            for(int i=0; i<N; i++) snack[i] = sc.nextInt();

            int tmp,max=-1;
            for(int i=0; i<N-1; i++) {
                for(int j=i+1; j<N; j++) {
                    tmp = snack[i] + snack[j];
                if(tmp > max && tmp <= M) max = tmp;
                }
            }
            
            System.out.println("#"+test_case+" "+max);
        }
    }
}
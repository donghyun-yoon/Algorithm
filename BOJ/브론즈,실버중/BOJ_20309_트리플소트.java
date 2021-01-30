import java.util.Scanner;

public class BOJ_20309_트리플소트 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for(int i=1; i<=N; i++) {
            int num = sc.nextInt();
            if(i%2 != num%2) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}
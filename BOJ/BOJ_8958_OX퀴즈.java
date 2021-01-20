import java.util.Scanner;

public class BOJ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        String[] str = new String[N];
        int[] cnt = new int[N];

        for(int i=0; i<N; i++) {
            str[i] = sc.nextLine();
        }

        for(int i=0; i<N; i++) {
            int index = 0;
            for(int j=0; j<str[i].length(); j++) {
                if(str[i].charAt(j) == 'X') {
                    index = 0;
                    continue;
                }
                index++;
                cnt[i] += index;
            }
        }

        for(int i: cnt) {
            System.out.println(i);
        }
        sc.close();
    }
}

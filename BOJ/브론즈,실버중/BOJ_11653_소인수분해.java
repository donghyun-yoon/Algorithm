import java.util.Scanner;

public class BOJ_11653_소인수분해 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        
        int index = 2;
        while(true) {
            if(N==1) break;
            if(N%index == 0) {
                N /= index;
                System.out.println(index);
            } else {
                index++;
            }
        }
        sc.close();
    }
}
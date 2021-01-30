import java.util.Scanner;

class SWEA_1206_View {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        for (int test_case = 1; test_case <= 10; test_case++) {
            int N = sc.nextInt();
            int[] structure = new int[N];

            for(int i=0; i<structure.length; i++) structure[i] = sc.nextInt();

            int cnt=0;
            for(int i=2; i<(structure.length-2); i++) {
                while(structure[i-2]<structure[i] && structure[i-1]<structure[i]
                && structure[i+1]<structure[i] && structure[i+2]<structure[i]) {
                    structure[i]--;
                    cnt++;
                }
            }

            System.out.println("#"+test_case+" "+cnt);
        }
    }
}
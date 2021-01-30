import java.util.Scanner;

class SWEA_1204_최빈수구하기 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        int[] mode = new int[101];
        for (int i = 0; i < mode.length; i++) {mode[i] = 0;}

        for (int test_case = 0; test_case < T; test_case++) {
            int test_case_num = sc.nextInt();
            int grade;

            for (int i = 0; i < 1000; i++) {mode[sc.nextInt()]++;}

            int mode_index = 0;
            for (int i = 1; i < mode.length-1; i++) {
                if (mode[i] >= mode[mode_index]) {mode_index = i;}
            }

            System.out.println("#" + test_case_num + " " + mode_index);
            
            for (int i = 0; i < mode.length; i++) {mode[i] = 0;}
        }
    }
}
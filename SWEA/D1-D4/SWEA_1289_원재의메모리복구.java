import java.util.Scanner;

class SWEA_1289_원재의메모리복구 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        char[] initial;
        String original;

        for (int test_case=0; test_case<T; test_case++) {
            original = sc.next();
            initial = new char[original.length()];

            for(int i=0;i<initial.length;i++) {initial[i] = '0';}

            int cnt=0;
            int move_index=0;
            
            for(int i=0;i<original.length();i++) {
                if(original.charAt(i) == '1') {move_index=i; break;}
            }

            for(;move_index<original.length();move_index++) {
                if(initial[move_index] != original.charAt(move_index)) {
                    if(original.charAt(move_index) == '1') {
                        for(int i=move_index;i<original.length();i++) initial[i] = '1';
                    } else {for(int i=move_index;i<original.length();i++) initial[i] = '0';}
                    cnt++;
                } else {continue;}
                if(original.equals(String.valueOf(initial))) {break;}
            }

            System.out.println("#"+(test_case+1)+" "+cnt);
        }
    }
}
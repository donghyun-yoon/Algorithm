import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_3752_가능한시험점수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            boolean[] check = new boolean[10001];
            ArrayList<Integer> list = new ArrayList<Integer>();

            list.add(0);

            StringTokenizer str = new StringTokenizer(br.readLine(), " ");
            while(str.hasMoreTokens()) {
                int num = Integer.parseInt(str.nextToken());
                
                int size = list.size()-1;

                for(int i = size; i >= 0; i--) {
                    int sum = num + list.get(i);

                    if(!check[sum]) {
                        list.add(sum);
                        check[sum] = true;
                    }
                }
            }

            System.out.println("#" + test_case + " " + list.size());
        }
    }
}
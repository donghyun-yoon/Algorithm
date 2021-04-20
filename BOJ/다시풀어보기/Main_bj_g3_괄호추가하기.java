import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main_bj_g3_괄호추가하기 {
    
    static int res = Integer.MIN_VALUE;
    static ArrayList<Character> ops;
    static ArrayList<Integer> nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        ops = new ArrayList<>();
        nums = new ArrayList<>();

        String mathExpression = br.readLine();
        for(int i=0; i<N; i++) {
            char c = mathExpression.charAt(i);
            if(i%2 == 0) nums.add(Character.getNumericValue(c));
            else ops.add(c);
        }

        dfs(nums.get(0), 0);
        System.out.println(res);

        br.close();
    }

    static void dfs(int sum, int opIdx) {
        if(opIdx >= ops.size()) {
            res = Math.max(res , sum);
            return;
        }

        //괄호를 치지않고 계산.
        int cal = calc(ops.get(opIdx), sum, nums.get(opIdx+1));
        dfs(cal, opIdx+1);
        //괄호를 칠수있을때
        if(opIdx+1 < ops.size()) {
            //오른쪽에 있는 값을 괄호 처리하여 먼저 계산한다.
            cal = calc(ops.get(opIdx+1), nums.get(opIdx+1), nums.get(opIdx+2));
            cal = calc(ops.get(opIdx), sum, cal);
            dfs(cal, opIdx+2);
        }
    }

    static int calc(char op, int a, int b) {
        int res = 0;
        switch(op) {
            case '+':
                res = a+b;
                break;
            case '-':
                res = a-b;
                break;
            case '*':
                res = a*b;
                break;
        }
        return res;
    }

}
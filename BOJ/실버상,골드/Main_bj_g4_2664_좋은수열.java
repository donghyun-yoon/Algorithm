import java.io.*;

public class Main {

    static boolean isCheck = false;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        goodSequence("");
        br.close();
    }

    static void goodSequence(String s) {
        if (isCheck)
            return;

        if (s.length() == N) {
            isCheck = true;
            System.out.println(s);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (checkSequence(s + i)) {
                goodSequence(s + i);
            }
        }
    }

    static boolean checkSequence(String s) {
        for (int i = 1; i <= s.length() / 2; i++) {
            if (s.substring(s.length() - i).equals(s.substring(s.length() - 2 * i, s.length() - i))) {
                return false;
            }
        }
        return true;
    }

}
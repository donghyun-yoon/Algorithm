import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        ArrayDeque<Integer> aDeque = null;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            char[] p = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());

            aDeque = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine(), "[],");
            for (int i = 0; i < n; i++) {
                aDeque.offer(Integer.parseInt(st.nextToken()));
            }

            boolean errorFlag = false;
            boolean reverseFlag = false;
            for (int i = 0, size = p.length; i < size; i++) {
                if (p[i] == 'R') {
                    reverseFlag = !reverseFlag;
                } else {
                    if (aDeque.isEmpty()) {
                        errorFlag = true;
                        break;
                    }
                    if (reverseFlag) {
                        aDeque.pollLast();
                    } else {
                        aDeque.pollFirst();
                    }
                }
            }

            if (errorFlag) {
                sb.append("error");
            } else {
                sb.append("[");
                if (aDeque.size() > 0) {
                    while (!aDeque.isEmpty()) {
                        if (aDeque.size() == 1) {
                            sb.append(aDeque.poll());
                        } else {
                            if (reverseFlag) {
                                sb.append(aDeque.pollLast()).append(",");
                            } else {
                                sb.append(aDeque.pollFirst()).append(",");
                            }
                        }
                    }
                }
                sb.append("]");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

}
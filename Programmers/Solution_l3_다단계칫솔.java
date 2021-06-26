import java.io.*;

//10:05-10:45분
//다단계칫솔판매
public class Solution_l3_다단계칫솔 {

  static String[] enroll = { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" };
  static String[] referral = { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" };
  static String[] seller = { "sam", "emily", "jaimie", "edward" };
  static int[] amount = { 2, 3, 5, 4 };
  static int[] result;

  public static void main(String[] args) throws Exception {
    int length = enroll.length;
    result = new int[length];

    for (int i = 0; i < seller.length; i++) {
      dfs(seller[i], amount[i] * 100);
    }

    for (int v : result)
      System.out.print(v + " ");
  }

  static void dfs(String sel, int income) {
    if (refString(sel).equals("-")) {
      int div = income / 10;
      result[enrollIdx(sel)] += income - div;
      return;
    }
    if (income < 10) {
      result[enrollIdx(sel)] += income;
      return;
    }

    int div = income / 10;
    result[enrollIdx(sel)] += income - div;
    dfs(refString(sel), div);
  }

  static int enrollIdx(String s) {
    for (int i = 0; i < enroll.length; i++) {
      if (enroll[i].equals(s)) {
        return i;
      }
    }
    return -1;
  }

  static String refString(String s) {
    for (int i = 0; i < enroll.length; i++) {
      if (enroll[i].equals(s)) {
        return referral[i];
      }
    }
    return "";
  }
}
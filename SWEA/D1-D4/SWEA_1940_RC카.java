import java.util.Scanner;

class SWEA_1940_RC카 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            
            int pregear=1, a=0, speed=0, d=0;
            while(N-->0) {
                int gear = sc.nextInt();
                switch(gear) {
                    case 0: {
                        if(pregear == 1) {
                            d += speed;
                        } else {
                            d += speed;
                        }
                        break;
                    }
                    case 1: {
                        a = sc.nextInt();
                        speed += a;
                        d += speed;
                        break;
                    }
                    case 2: {
                        a = sc.nextInt();
                        speed -= a;
                        if(speed <= 0) speed = 0;
                        d += speed;
                        break;
                    }
                    default:
                }
                pregear = gear;
            }

            System.out.println("#"+test_case+" "+d);
        }
    }
}
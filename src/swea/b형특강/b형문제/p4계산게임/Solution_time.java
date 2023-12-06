package swea.b형특강.b형문제.p4계산게임;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution_time {
    private static BufferedReader br;
    private static UserSolution usersolution = new UserSolution();

    private final static int CMD_INIT = 100;
    private final static int CMD_PUT = 200;
    private final static int CMD_FIND = 300;
    private final static int CMD_CHANGE = 400;

    private final static int MAX_CARD_NUM = 5;
    private final static int MAX_RET_NUM = 4;

    private final static int numbers[] = new int[MAX_CARD_NUM];
    private final static int ret_numbers[] = new int[MAX_RET_NUM];
    private final static int ans_numbers[] = new int[MAX_RET_NUM];
    static int putCardsTime, findNumberTime, changeJokerTime;

    private static boolean run() throws Exception {

        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");

        int query_num = Integer.parseInt(stdin.nextToken());
        int ret, ans;
        boolean ok = false;

        for (int q = 0; q < query_num; q++) {
            stdin = new StringTokenizer(br.readLine(), " ");
            int query = Integer.parseInt(stdin.nextToken());

            if (query == CMD_INIT) {
                int joker = Integer.parseInt(stdin.nextToken());
                for (int i = 0; i < MAX_CARD_NUM; i++)
                    numbers[i] = Integer.parseInt(stdin.nextToken());
                usersolution.init(joker, numbers);
                ok = true;
            } else if (query == CMD_PUT) {
                int dir = Integer.parseInt(stdin.nextToken());
                for (int i = 0; i < MAX_CARD_NUM; i++)
                    numbers[i] = Integer.parseInt(stdin.nextToken());
            	long beforeTime = System.currentTimeMillis();
                usersolution.putCards(dir, numbers);
                long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
                putCardsTime += (afterTime - beforeTime); 
            } else if (query == CMD_FIND) {
                int num, Nth;
                num = Integer.parseInt(stdin.nextToken());
                Nth = Integer.parseInt(stdin.nextToken());
                ans = Integer.parseInt(stdin.nextToken());
            	long beforeTime = System.currentTimeMillis();
                ret = usersolution.findNumber(num, Nth, ret_numbers);
                long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
                findNumberTime += (afterTime - beforeTime); 
                if (ans != ret) {
                	ok = false;
                }
                if (ans == 1) {
                    for (int i = 0; i < MAX_RET_NUM; i++) {
                        ans_numbers[i] = Integer.parseInt(stdin.nextToken());
                        if (ans_numbers[i] != ret_numbers[i]) {
                            ok = false;
                        }
                    }
                }
                if(!ok) {
                	System.out.println("\nfindNumber error : " + ans + " - " + ret);
                	System.out.println("num : " + num);
                	System.out.println("Nth : " + Nth);
                	System.out.println(Arrays.toString(ans_numbers) + " : " + Arrays.toString(ret_numbers) + "\n");
                }
            } else if (query == CMD_CHANGE) {
                int value = Integer.parseInt(stdin.nextToken());
            	long beforeTime = System.currentTimeMillis();
                usersolution.changeJoker(value);
                long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
                changeJokerTime += (afterTime - beforeTime); 
            }

        }
        return ok;
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;

        System.setIn(new java.io.FileInputStream("src/swea/b형특강/b형문제/p4계산게임/sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
        T = Integer.parseInt(stinit.nextToken());
        MARK = Integer.parseInt(stinit.nextToken());
        
        long beforeTimeAll = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
        for (int tc = 1; tc <= T; tc++) {
        	putCardsTime = findNumberTime = changeJokerTime = 0;
        	long beforeTime = System.currentTimeMillis();
            int score = run() ? MARK : 0;
            long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
            long secDiffTime = (afterTime - beforeTime); 
            System.out.println("#" + tc + " " + score + " " + putCardsTime + "ms " + findNumberTime  + "ms " + changeJokerTime + "ms => " + secDiffTime + "ms ");
        }
        long afterTimeAll = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTimeAll = (afterTimeAll - beforeTimeAll); //두 시간에 차 계산
        System.out.println("전체시간 : " + secDiffTimeAll + "ms");
        br.close();
    }
}
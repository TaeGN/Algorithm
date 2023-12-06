package swea.b형특강.b형문제.p6AI로봇;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
    private final static int CALL_JOB				= 100;
    private final static int RETURN_JOB				= 200;
    private final static int BROKEN					= 300;
    private final static int REPAIR 				= 400;
    private final static int CHECK					= 500;

    private static UserSolution usersolution = new UserSolution();
    
    static long begin, end;
    static int[] t = new int[5];
    
    private static int run (BufferedReader br, int score) throws Exception 
    {
        int N, Q;
        int wIDCnt = 1;
        int cTime, mNum, rID, wID, mOpt;
        int res = -1, ans;
        
        N = Integer.parseInt(br.readLine());
        usersolution.init(N);

        Q = Integer.parseInt(br.readLine());
        
        while (Q-- > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int cmd = Integer.parseInt(st.nextToken());

            switch (cmd)
    		{
            case CALL_JOB:
                cTime = Integer.parseInt(st.nextToken());
                mNum = Integer.parseInt(st.nextToken());
                mOpt = Integer.parseInt(st.nextToken());
                begin = System.currentTimeMillis();
                res = usersolution.callJob(cTime, wIDCnt, mNum, mOpt);  
                end = System.currentTimeMillis();
                t[0] += end - begin;
                ans = Integer.parseInt(st.nextToken());
                if (ans != res) {
                	System.out.println("---- CALL_JOB ----");
                	System.out.println("cTime : " + cTime);
                	System.out.println("wIDCnt : " + wIDCnt);
                	System.out.println("mNum : " + mNum);
                	System.out.println("mOpt : " + mOpt);
                	System.out.println("ans : " + ans);
                	System.out.println("res : " + res);
                    score = 0;
                }
                wIDCnt++;
                break;
            case RETURN_JOB:
                cTime = Integer.parseInt(st.nextToken());
                wID = Integer.parseInt(st.nextToken());
                begin = System.currentTimeMillis();
                usersolution.returnJob(cTime, wID);
                end = System.currentTimeMillis();
                t[1] += end - begin;
                break;
            case BROKEN:
                cTime = Integer.parseInt(st.nextToken());
                rID = Integer.parseInt(st.nextToken());
                begin = System.currentTimeMillis();
                usersolution.broken(cTime, rID);
                end = System.currentTimeMillis();
                t[2] += end - begin;
                break;
            case REPAIR:
                cTime = Integer.parseInt(st.nextToken());
                rID = Integer.parseInt(st.nextToken());
                begin = System.currentTimeMillis();
                usersolution.repair(cTime, rID);
                end = System.currentTimeMillis();
                t[3] += end - begin;
                break;
            case CHECK:
                cTime = Integer.parseInt(st.nextToken());
                rID = Integer.parseInt(st.nextToken());
                begin = System.currentTimeMillis();
                res = usersolution.check(cTime, rID);
                end = System.currentTimeMillis();
                t[4] += end - begin;
                ans = Integer.parseInt(st.nextToken());;
                if (ans != res) {
                	System.out.println("---- CHECK ----");
                	System.out.println("cTime : " + cTime);
                	System.out.println("rID : " + rID);
                	System.out.println("ans : " + ans);
                	System.out.println("res : " + res);
                    score = 0;
                }
                break;
            default:
                score = 0;
                break;
            }       
        }

        return score;
    }
    
    public static void main(String[] args) throws Exception
    {
    	System.setIn(new java.io.FileInputStream("src/swea/b형특강/b형문제/p6AI로봇/sample_input.txt"));
	    
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(br.readLine(), " ");

        int TC = Integer.parseInt(line.nextToken());
        int Ans = Integer.parseInt(line.nextToken());
        long b = System.currentTimeMillis();
        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            System.out.print("#" + testcase + " " + run(br, Ans) + " : ");
            int sum = 0;
            for (int i = 0; i < 5; i++) {
            	sum += t[i];
				System.out.print(t[i] + "ms ");
				t[i] = 0;
			}
            System.out.println("=> " + sum + "ms ");
        }
        long e = System.currentTimeMillis();
        System.out.println("전체 : " + (e - b) + "ms");
        br.close();
    }
}
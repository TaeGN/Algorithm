package swea.b형특강.b형문제.p5긴사다리게임;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private final static int CMD_INIT				= 1;
	private final static int CMD_ADD				= 2;
	private final static int CMD_REMOVE				= 3;
	private final static int CMD_NUMBER_OF_CROSS	= 4;
	private final static int CMD_PARTICIPANT		= 5;
	
	private final static UserSolution usersolution = new UserSolution();

	static int removeTime, addTime, crossTime, participantTime;
	static long beforeTime, afterTime;
	
	private static boolean run(BufferedReader br) throws Exception
	{
		StringTokenizer st;

		int numQuery;

		int mX, mY, mID;

		int userAns, ans;

		boolean isCorrect = false;

		numQuery = Integer.parseInt(br.readLine());

		for (int q = 0; q < numQuery; ++q)
		{
			st = new StringTokenizer(br.readLine(), " ");

			int cmd;
			cmd = Integer.parseInt(st.nextToken());

			switch (cmd)
			{
			case CMD_INIT:
				usersolution.init();
				isCorrect = true;
				break;
			case CMD_ADD:
				mX = Integer.parseInt(st.nextToken());
				mY = Integer.parseInt(st.nextToken());
				beforeTime = System.currentTimeMillis();
				usersolution.add(mX, mY);
                afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
                addTime += (afterTime - beforeTime); 
				break;
			case CMD_REMOVE:
				mX = Integer.parseInt(st.nextToken());
				mY = Integer.parseInt(st.nextToken());
				beforeTime = System.currentTimeMillis();
				usersolution.remove(mX, mY);
                afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
                removeTime += (afterTime - beforeTime); 
				break;
			case CMD_NUMBER_OF_CROSS:
				mID = Integer.parseInt(st.nextToken());
				beforeTime = System.currentTimeMillis();
				userAns = usersolution.numberOfCross(mID);
                afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
                crossTime += (afterTime - beforeTime); 
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans)
				{
					System.out.println("-------- CMD_NUMBER_OF_CROSS ----------");
					System.out.println("mID : " + mID);
					System.out.println("userAns : " + userAns);
					System.out.println("ans : " + ans);
					isCorrect = false;
				}
				break;
			case CMD_PARTICIPANT:
				mX = Integer.parseInt(st.nextToken());
				mY = Integer.parseInt(st.nextToken());
				beforeTime = System.currentTimeMillis();
				userAns = usersolution.participant(mX, mY);
                afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
                participantTime += (afterTime - beforeTime); 
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans)
				{
					System.out.println("-------- CMD_PARTICIPANT ----------");
					System.out.println("mX : " + mX);
					System.out.println("mY : " + mY);
					System.out.println("userAns : " + userAns);
					System.out.println("ans : " + ans);
					isCorrect = false;
				}
				break;
			default:
				isCorrect = false;
				break;
			}
		}
		return isCorrect;
	}

	public static void main(String[] args) throws Exception
	{
		int TC, MARK;
	
		System.setIn(new java.io.FileInputStream("src/swea/b형특강/b형문제/p5긴사다리게임/sample_input.txt"));
	        
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		long beforeTimeAll = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
		for (int testcase = 1; testcase <= TC; ++testcase)
		{
			removeTime = addTime = crossTime = participantTime = 0;
            long beforeTime = System.currentTimeMillis();
            int score = run(br) ? MARK : 0;
            long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
            long secDiffTime = (afterTime - beforeTime); 
            System.out.println("#" + testcase + " " + score + " " + addTime + "ms " + removeTime  + "ms " + crossTime + "ms " + participantTime + "ms => " + secDiffTime + "ms ");
		}
        long afterTimeAll = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long secDiffTimeAll = (afterTimeAll - beforeTimeAll); //두 시간에 차 계산
        System.out.println("전체시간 : " + secDiffTimeAll + "ms");

		br.close();
	}
}
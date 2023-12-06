import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;


class UserSolution {
    private static final int MAX_CARD = 50000;

    private static class Table {
        public static int joker;
        public static int begin, end;
        public static int[] cards = new int[MAX_CARD * 2 + 5];
        public static LinkedList<Integer>[][] idxList = new LinkedList[20][20];
        // idxList[joker][score] := 조커의 점수가 joker일 때, 점수가 score인 인덱스 리스트
        // 예를 들어, 현재 joker = 9점이고, findNumber의 타겟 점수가 19 점이라면,
        // 우리는 idxList[9][19] => 원하는 인덱스 리스트를 바로 알 수 있따.

        public static void updateIdx(int idx, int mdir) {
            int sum = 0;
            int joker_cnt = 0;
            for (int i = 0; i < 4; i++) {
                if (cards[idx + i] == -1)
                    joker_cnt++;
                else
                    sum += cards[idx + i];
            }
            for (int i = 0; i < 20; i++) {  // 조커 점수가 i 라고 가정하자.
                int num = (sum + (joker_cnt * i)) % 20;
                if (mdir == 0)  // 왼쪽
                    idxList[i][num].add(0, idx);
                else  // 오른쪽
                    idxList[i][num].add(idx);
            }
        }

        public static void init(int mJoker, int[] mNumbers) {
            joker = mJoker % 20;
            begin = end = MAX_CARD;
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    idxList[i][j] = new LinkedList<>();
                }
            }
            for (int i = 0; i < 5; i++) {
                cards[end + i] = mNumbers[i];
            }
            end += 5;
            for (int i = 0; i < 2; i++) {
                updateIdx(MAX_CARD + i, 1);
            }
        }

        public static void pushFront(int[] mNumbers) {
            begin -= 5;
            for (int i = 0; i < 5; i++) {
                cards[begin + i] = mNumbers[i];
            }
            int target = begin;
            for (int i = 4; i >= 0; i--) {
                updateIdx(target + i, 0);
            }
        }

        public static void pushBack(int[] mNumbers) {
            for (int i = 0; i < 5; i++) {
                cards[end + i] = mNumbers[i];
            }
            int target = end - 3;
            end += 5;
            for (int i = 0; i < 5; i++) {
                updateIdx(target + i, 1);
            }
        }

        public static int find(int mNum, int mNth, int[] ret) {
            LinkedList<Integer> list = idxList[joker][mNum];
            if (mNth > list.size())
                return 0;
            int idx = list.get(mNth - 1);
            for (int i = 0; i < 4; i++) {
                ret[i] = cards[idx + i];
            }
            return 1;
        }

        void changeJoker(int mJoker) {
            joker = mJoker % 20;
        }
    }


    private static Table t = new Table();

    void init(int mJoker, int mNumbers[]) {
        t.init(mJoker, mNumbers);
    }

    void putCards(int mDir, int mNumbers[]) {
        if (mDir == 0) {
            t.pushFront(mNumbers);
        } else {
            t.pushBack(mNumbers);
        }
    }

    int findNumber(int mNum, int mNth, int ret[]) {
        return t.find(mNum, mNth, ret);
    }

    void changeJoker(int mValue) {
        t.changeJoker(mValue);
    }
}

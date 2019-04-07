import java.util.Scanner;

public class QuadTree {
    int N;
    int videoArr[][];
    StringBuilder sb;

    QuadTree() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        videoArr = new int[N][N];
        scan.nextLine();
        for (int i = 0; i < N; i++) {
            String str = scan.nextLine();
            for (int j = 0; j < N; j++) {
                videoArr[i][j] = Character.getNumericValue(str.charAt(j));
            }
        }
        sb = new StringBuilder();
    }

    void solveQT(int row, int col, int n) {

        if (canComp(row, col, n) != -1) {
            sb.append(videoArr[row][col]);
            return;
        }

        sb.append("(");


        //왼쪽 위
        if (canComp(row, col, n / 2) != -1)
            sb.append(videoArr[row][col]);
        else
            solveQT(row, col, n / 2);

        //오른쪽 위
        if (canComp(row, col + n / 2, n / 2) != -1)
            sb.append(videoArr[row][col + n / 2]);
        else
            solveQT(row, col + n / 2, n / 2);

        //왼쪽 아래
        if (canComp(row + n / 2, col, n / 2) != -1)
            sb.append(videoArr[row + n / 2][col]);
        else
            solveQT(row + n / 2, col, n / 2);

        //오른쪽 아래
        if (canComp(row + n / 2, col + n / 2, n / 2) != -1)
            sb.append(videoArr[row + n / 2][col + n / 2]);
        else
            solveQT(row + n / 2, col + n / 2, n / 2);

        sb.append(")");
    }

    //해당 부분을 압축할 수 있는지 확인
    int canComp(int row, int col, int n) {
        int canComp;

        if (videoArr[row][col] == 0)
            canComp = 0;
        else
            canComp = 1;

        for (int i = row; i < row + n; i++) {
            for (int j = col; j < col + n; j++) {
                if (videoArr[i][j] != canComp)
                    return -1;
            }
        }
        return canComp;
    }

}

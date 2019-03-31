import java.util.Scanner;

public class GoDown {

    public void goDown() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int maxTable[][] = new int[n][3];
        int minTable[][] = new int[n][3];
        int max, min;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxTable[i][j] = sc.nextInt();
                minTable[i][j] = maxTable[i][j];
            }
        }

        for (int i = n - 1; i > 0; i--) {
            if (maxTable[i][0] > maxTable[i][1])
                maxTable[i - 1][0] += maxTable[i][0];
            else
                maxTable[i - 1][0] += maxTable[i][1];


            maxTable[i - 1][1] += getMax(maxTable[i]);

            if (maxTable[i][1] > maxTable[i][2])
                maxTable[i - 1][2] += maxTable[i][1];
            else
                maxTable[i - 1][2] += maxTable[i][2];
        }
        max = getMax(maxTable[0]);

        for (int i = n - 1; i > 0; i--) {
            if (minTable[i][0] < minTable[i][1])
                minTable[i - 1][0] += minTable[i][0];
            else
                minTable[i - 1][0] += minTable[i][1];


            minTable[i - 1][1] += getMin(minTable[i]);

            if (minTable[i][1] < minTable[i][2])
                minTable[i - 1][2] += minTable[i][1];
            else
                minTable[i - 1][2] += minTable[i][2];
        }

        min = getMin(minTable[0]);

        System.out.println(max + " " +min);
    }

    public void goDown_dp() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int table[][] = new int[n][3];
        int max, min;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = sc.nextInt();
            }


        }

        int preMax[] = new int[3];
        int postMax[] = new int[3];
        int preMin[] = new int[3];
        int postMin[] = new int[3];

        for(int i = 0; i < 3; i++) {
            preMax[i] = postMax[i] = table[0][i];
            preMin[i] = postMin[i] = table[0][i];
        }

        for(int i = 1; i < n; i++) {
            postMax[0] = table[i][0] + Math.max(preMax[0], preMax[1]);
            postMax[1] = table[i][1] + getMax(preMax);
            postMax[2] = table[i][2] + Math.max(preMax[1], preMax[2]);

            postMin[0] = table[i][0] + Math.min(preMin[0], preMin[1]);
            postMin[1] = table[i][1] + getMin(preMin);
            postMin[2] = table[i][2] + Math.min(preMin[1], preMin[2]);

            for(int j = 0; j < 3; j++) {
                preMax[j] = postMax[j];
                preMin[j] = postMin[j];
            }
        }
        max = getMax(postMax);
        min = getMin(postMin);

        System.out.println(max + " " + min);
    }

    int getMax(int[] line) {
        int temp = line[0];
        for(int i = 0; i < line.length; i++) {
            if(temp < line[i])
                temp = line[i];
        }
        return temp;
    }

    int getMin(int[] line) {
        int temp = line[0];
        for(int i = 0; i < line.length; i++) {
            if(temp > line[i])
                temp = line[i];
        }
        return temp;
    }
}


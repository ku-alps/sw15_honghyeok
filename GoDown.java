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


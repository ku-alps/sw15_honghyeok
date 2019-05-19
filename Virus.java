import java.util.Scanner;

public class Virus {
    int n;
    int pairNum;
    int[] root;
    int infected = 0;

    Virus() {
        Scanner sc = new Scanner(System.in);
        n =sc.nextInt();
        pairNum = sc.nextInt();
        root = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            root[i] = i;
        }
        for(int i = 0; i < pairNum; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            union(x, y);
        }
    }

    int find(int x) {
        if(root[x] == x) {
            return x;
        } else {
            return find(root[x]);
        }
    }

    void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

//        root[rootY] = rootX;
//        for(int i = 1; i <= n; i++) {
//            System.out.print(root[i] + " ");
//        }
        System.out.println();
    }

    int solve() {
        int r = find(1);

        for(int i = 2; i <= n; i++) {
            if(r == find(i))
                infected++;
        }
        return infected;
    }
}

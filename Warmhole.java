import java.util.Scanner;

public class Warmhole {
    int nCase;
    Graph[] graphSet;
    boolean[] isPossible;
    final int INF = 29999;
    class Graph {
        class Edge {
            int src, dest, weight;
            Edge() {
                src = dest = weight = 0;
            }
        }
        int V, E;
        Edge[] edges;

        Graph(int v, int e) {
            V = v;
            E = e;
            edges = new Edge[e];
            for(int i = 0; i < e; i++)
                edges[i] = new Edge();
        }
    }

    Warmhole() {
        Scanner scan = new Scanner(System.in);
        nCase = scan.nextInt();
        graphSet = new Graph[nCase];
        isPossible = new boolean[nCase];

        for (int i = 0; i < nCase; i++) {
            int n = scan.nextInt();
            int m = scan.nextInt();
            int w = scan.nextInt();
            graphSet[i] = new Graph(n, 2*m+w);

            int edgeCount = 0;
            for (int j = 0; j < m; j++, edgeCount++) {
                int s = scan.nextInt();
                int e = scan.nextInt();
                int t = scan.nextInt();
                graphSet[i].edges[edgeCount].src = s;
                graphSet[i].edges[edgeCount].dest = e;
                graphSet[i].edges[edgeCount].weight = t;
                edgeCount++;
                graphSet[i].edges[edgeCount].src = e;
                graphSet[i].edges[edgeCount].dest = s;
                graphSet[i].edges[edgeCount].weight = t;

            }
            for(int j = 0; j < w; j++, edgeCount++) {
                int s = scan.nextInt();
                int e = scan.nextInt();
                int t = scan.nextInt();
                graphSet[i].edges[edgeCount].src = s;
                graphSet[i].edges[edgeCount].dest = e;
                graphSet[i].edges[edgeCount].weight = -t;
            }
        }
    }

    void solve() {
        for(int i = 0; i < nCase; i++) {
            Graph map = graphSet[i];
            for(int j = 1; j < map.V + 1; j++) {
                isPossible[i] = hasNegativeCycle(map, j);
                if(isPossible[i])
                    break;
            }
        }
    }

    boolean hasNegativeCycle(Graph map, int sp) {
        int[] dist = new int[map.V + 1];
        for(int i = 0; i < map.V + 1; i++) {
            dist[i] = INF;
        }
        dist[sp] = 0;

        for(int i = 0; i < map.V; i++) {
            for(int j = 0; j < map.E; j++) {
                int u = map.edges[j].src;
                int v = map.edges[j].dest;
                int w = map.edges[j].weight;
                if(dist[v] > dist[u] + w && dist[u] != INF) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        for(int i = 0; i < map.E; i++) {
            int u = map.edges[i].src;
            int v = map.edges[i].dest;
            int w = map.edges[i].weight;
            if(dist[v] > dist[u] + w && dist[u] != INF) {
                return true;
            }
        }
        return false;
    }

    void printAnswer() {
        solve();
        for(int i = 0; i < nCase; i++) {
            if(isPossible[i])
                System.out.print("YES ");
            else
                System.out.print("NO ");
        }
        System.out.println();
    }

}

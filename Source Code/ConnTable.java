package cs542project_sec03_shah_priyank;

import java.util.*;

/**
 *
 * @author Priyank
 */
public class ConnTable {

    Scanner sc = new Scanner(System.in);
    CreateTopology ct = new CreateTopology();
    int i = 0, j = 0;
    int size = 0;
    int[][] router = null;
    int[] distance = new int[50];
    int[] visit = null;
    int[] path = new int[50];
    public int[][] next = null;
    int bt = 0;
    int nextnode = 0;
    public int val;

    boolean flag = false ;
    public ConnTable() {

    }

    public void getRouterinfo(int[][] router, int size) {
        this.router = router;
        this.size = size;
        next = new int[size][size];

        visit = new int[size];
        
        if(flag) {
            
        } else {
            System.out.println("Enter The Node Number:");
            val = sc.nextInt() - 1;
            flag = false;
        }
        
        System.arraycopy(router[val], 0, distance, 0, router[val].length);

        for (i = 0; i < size; i++) {
            if (distance[i] == -1) {
                distance[i] = 9999;
            }
            visit[i] = 0;
            path[i] = val;

            for (int j = i; j < i + 1; j++) {
                next[i][j] = 0;
            }
        } 

        
        visit[val] = 1;
        
        for (i = 0; i < size; i++) {
            int min = 9999;

            for (j = 0; j < size; j++) {

                if (distance[j] < min && distance[j] != 0 && visit[j] != 1) {
                    min = distance[j];
                    nextnode = j;
                }
                //System.out.println("nextnode:"+nextnode);
            } // int j is free / i occupied
            visit[nextnode] = 1;
            
            for (j = 0; j < size; j++) {

                if (visit[j] != 1 && router[nextnode][j] != -1) {
                    if (min + router[nextnode][j] < distance[j]) {
                        distance[j] = min + router[nextnode][j];
                        path[j] = nextnode;
//                        System.out.println("distance["+j+"]:"+distance[j]);
//                        System.out.println("path["+j+"]:"+path[j]);
                    }
                }
            } // j free
        } // i free

        for (i = 0; i < size; i++) {
            next[i][0] = path[i] + 1;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (next[i][j] != val + 1 && next[i][j] != 0) {
                    bt = next[i][j] - 1;
                    if (next[bt][0] != bt + 1) {
                        next[i][j + 1] = next[bt][0];
                    }

                }
            }
        }

        int track = 0;
        //check condition & put node to print 
        System.out.println();
        System.out.println("Routing Table:\n");
        System.out.println("----------------------------");
        System.out.println("|Current Node | Next Node  |");
        System.out.print("----------------------------");
        for (i = 0; i < size; i++) {
            j = 0;
            System.out.println();
            if (i == val) {
                System.out.print("|   "+(i + 1) + "\t      |   " + "-"+ "\t   |");
            } else {
                do {
                    if (next[i][j] == val + 1 && track == 0) {
                        System.out.print("|   " + (i + 1) + "\t      |   " + (i + 1) + "\t   |");
                    }
                    if (next[i][j] != val + 1 && next[i][j] != 0) {
                        track = track + 1;
                    }
                    if (next[i][j] == val + 1 && track != 0) {
                        System.out.print("|   " + (i + 1) + "\t      |   " + (next[i][j - 1]) + "\t   |");
                        track = track + 1;
                    }
                    j = j + 1;
                } while (next[i][j] != 0);
               
                track = 0;
            }
            
        }
     System.out.println("\n----------------------------"); 
    }

    public void updateRouter(int trackchoice, ShortPathRoute spr) {
        System.out.println("Please enter the Router Number:");
        int downrouter = sc.nextInt() - 1;

        for (int i = 0; i < size; i++) {
            router[downrouter][i] = -1;
            router[i][downrouter] = -1;
        }
     //   System.out.println("Routing Table after Modified topology is:");
        if(trackchoice>2) {
            flag=true;
            getRouterinfo(router, size);
            spr.findpath(size, val, distance, next, flag);
        }
      //  getRouterinfo(router, size);
       
    }

    public int[][] getNext() {

        return this.next;
    }

    public int[] getDistance() {
        return this.distance;
    }

    public int getVal() {

        return val;

    }

    int[][] getUpdatedRouter() {
        return this.router;
    }

    

}

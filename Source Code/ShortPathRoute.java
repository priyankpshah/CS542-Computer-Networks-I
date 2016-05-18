/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs542project_sec03_shah_priyank;

import java.awt.BorderLayout;
import java.util.Scanner;

/**
 *
 * @author Priyank
 */
public class ShortPathRoute {

    int distance[] = null;
    int source = 0;
    int next[][] = null;
    int size = 0;
    int destination = 0;
    int i = 0, j = 0;
    Scanner sc = new Scanner(System.in);
    ConnTable cn = null;

    public ShortPathRoute() {

    }

    void findpath(int size, int source, int distance[], int next[][], boolean flag) {

        this.size = size;
        this.source = source;
        this.next = next;
        this.distance = distance;

        if(flag) {
            
        } else {
            System.out.println("Please Enter the destination node number:");
            destination = sc.nextInt() - 1;
            flag = false;
        }
        
        if (distance[destination] != 9999) {
            System.out.println("Path to Traverse to this node is:");
            if (destination != source && destination <= size) {
                System.out.print((destination + 1));
            }

            for (i = 0; i < size; i++) {
                if (next[destination][i] != 0) {
                    System.out.print("<<" + next[destination][i]);
                }
            }
            System.out.println("\nMin Cost to Traverse to this node is:" + distance[destination]);
        } else {
            System.out.println("Router is Down/Routing is not possible!!");
        }
    }

}

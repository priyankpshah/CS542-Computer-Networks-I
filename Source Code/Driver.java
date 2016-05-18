package cs542project_sec03_shah_priyank;

import java.util.Scanner;

/**
 *
 * @author Priyank
 */
public class Driver {

    int router[][] = new int[6][6];
    int size = 0;
    CreateTopology ct = null;
    ConnTable cn = null;
    ShortPathRoute spr = null;
    boolean flagR = false;

    public Driver() {
        
        cn = new ConnTable();
        spr = new ShortPathRoute();
        functions();
    }

    public static void main(String[] args) {
        Driver dr = new Driver();
    }

    private void functions() {

        try {
            int flag = 1, i = 0, j = 0;
            Scanner sc = new Scanner(System.in);
            int trackchoice = 1;
            while (flag == 1) {
                System.out.println("\n1.Create a Network Topology \n2.Build a Connection Table\n3.Shortest Path to Destination Router\n4.Modify A Topology\n5. Exit");
                System.out.println("Please Enter the Choice:");
                int choice = sc.nextInt();
                switch (choice) {

                    case 1: {
                        ct = new CreateTopology();
                        
                        System.out.println("Option 1 Selected");
                        router = ct.Topology();
                        if(router.length == router[0].length )
                        {
//                            if(router.length>=8)
//                            {
                            size = ct.getSize();
                            trackchoice=2;
                            break;
//                            }
//                            else
//                            {
//                                System.out.println("Minimum Required Router are less than 8, Please Enter the matrix having 8 or more nodes!!");
//                                break;
//                            }
                        } else {
                            System.out.println("Note : Please enter Matrix with equal number or row and columns");
                            break;
                        }
                        
                    }

                    case 2: {
                        if (trackchoice > 1) {
                            System.out.println("Option 2 Selected");
                            if (flagR) {
                                cn.getRouterinfo(cn.getUpdatedRouter(), size);
                            } else {
                                cn.getRouterinfo(router, size);
                            }
                            trackchoice=3;
                            break;

                        } else {
                            System.out.println("Please input the topology file first:");
                            break;
                        }
                    }

                    case 3: {

                        if (trackchoice > 2) {
                            System.out.println("Option 3 Selected");
                            spr.findpath(size, cn.getVal(), cn.getDistance(), cn.getNext(), false);
                            break;
                        } else if (trackchoice == 1) {
                            System.out.println("Please input the topology file!!!");
                            break;

                        } else if (trackchoice == 2) {
                            System.out.println("Please generate the routing table first!!!!");
                            break;
                        }
                    }
                    case 4: {
                        if (trackchoice > 1) {
                            System.out.println("Option 4 Selected");
                            cn.updateRouter(trackchoice, spr);
                            flagR = true;
                            break;
                        } else {
                            System.out.println("Please Input the Topology file first!!!");
                        }
                        break;
                    }

                    case 5: {
                        System.out.println("Thank You!");
                        System.exit(0);
                        break;
                    }

                    default: {
                        System.out.println("Please Enter the Correct Choice:");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

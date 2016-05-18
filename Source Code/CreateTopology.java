package cs542project_sec03_shah_priyank;

import java.io.*;
import java.util.*;

/**
 *
 * @author Priyank
 */
public class CreateTopology {

    public static int[][] value = null;
    String line;
    int i = 0, j = 0;
    public int size = 0;
    Scanner sc = new Scanner(System.in);
    // ConnTable cn = new ConnTable();
    int row=0, column = 0;

    public CreateTopology() {

    }

    public int[][] Topology() {
        try {
            
            System.out.println("Please Enter the file name:");
            String filename = sc.nextLine();
            
            findMatrixSize(filename);
            value = new int[column][row];
            
            FileReader fr = new FileReader(filename);
//            FileReader fr = new FileReader("test.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                for (String str : values) {
                    int cost = Integer.parseInt(str);
                    value[i][j] = cost;
                    i++;
                }
                i = 0;
                j++;

                size++;

            }

            for (i = 0; i < size; i++) {
                for (j = 0; j < size; j++) {
                    System.out.print(value[i][j] + " ");
                }
                System.out.print("\n");
            }

            br.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
            
        return value;
    }

    int getSize() {
        return this.size;

    }

    private void findMatrixSize(String filename) {
        try{
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                column = values.length;
                row++;
            }
        } catch(Exception e){
            System.out.println("Exception during read from file (Please use n*n matrix only): " +e );
        }
    }

}

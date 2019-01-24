
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class reader {

    public reader() {
    }


    public static void main(String[]args){
        int size=5;
        try {

            Scanner sc = new Scanner(new File("C:/Users/Christian/myGrid.txt"));
            int rows = size;
            int columns = size;
            Object[][] myGrid = new Object[rows][columns];
            while (sc.hasNextLine()) {
                for (int i = 0; i < myGrid.length; i++) {
                    String[] line = sc.nextLine().trim().split(" ");
                    for (int j = 0; j < line.length; j++) {
                        myGrid[i][j] = Integer.parseInt(line[j]);
                    }
                }
            }
            System.out.println(Arrays.deepToString(myGrid));

            for (int i = 0; i < myGrid.length; i++) {
                for (int j = 0; j < myGrid[i].length; j++)
                    System.out.print(myGrid[i][j].toString() + ", ");
                System.out.println();
                System.out.println();
            }

        }catch (IOException i){

        }

    }








}

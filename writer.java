import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;



public class writer {

    public writer(){

    }

    public static void main(String []args){

        Object[][] myGrid = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19 ,20}, {21, 22, 23, 24 ,25}};
        try (
                PrintStream output = new PrintStream(new File("C:/Users/Christian/myGrid.txt"));) {
            for (int i = 0; i < myGrid.length;i++) {
                String s= "";
                for (int j = 0; j < myGrid[i].length; j++) {
                    s+=  myGrid[i][j] + " ";
                }
                output.println(s);
            }
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
}

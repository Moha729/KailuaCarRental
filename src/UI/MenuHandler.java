package UI;

import java.util.Scanner;

public class MenuHandler {

    Scanner scan = new Scanner(System.in);
    public MoTools tools = new MoTools();



    public int getMainOptions(String text1, String text2, String text3, String text4){
        System.out.print(tools.doubleButton(text1, text2));
        System.out.print(tools.doubleButton(text3, text4));

        while(!scan.hasNextInt()){
            tools.customizedButton(40,1, "Not valid - try again!");
        }
        return scan.nextInt();

    }

}

package UI;

import java.util.Scanner;

public class MoTools {

    Scanner scan = new Scanner(System.in);

    public String returnStringInfo(int width, int height, String text){
        scan = new Scanner(System.in);
        customizedButton(width, height, text);
        //scan.nextLine();
        String info = scan.next();
        return info;
    }
    public int returnIntInfo(int width, int height, String text){
        scan = new Scanner(System.in);
        customizedButton(width, height, text);
        //scan.nextLine();
        int info = scan.nextInt();
        return info;
    }


    public void customizedButton(int width, int height, String text){

        margeTop(width);
        System.out.println();
        margingCentre(height, width, text);
        margeTop(width);

    }
    public void margingCentre(int height, int width, String text1, String text2) {

        margeMiddlSpace(height, width);
        System.out.print(centerString(width / 2, text1));
        System.out.println(centerString(width / 2, text2));
        margeMiddlSpace(height, width );
    }

    public void margingCentre(int height, int width, String text){

        margeMiddlSpace(height, width);
        System.out.println(centerString(width, text));
        margeMiddlSpace(height, width);
    }

    public void margeMiddlSpace(int height, int width) {
        for (int i = 0; i < height / 2; i++) {
            System.out.print("|");
            for (int j = 1; j <= width; j++) {
                System.out.print(" ");
            }
            System.out.println("|");
        }
    }

    public void margeTop(int width){
        System.out.print(" ");
        for (int i = 0; i <= width - 1; i++)
            System.out.print("_");
    }

    public String welcomeText(String INTROTEXT) {
        String BOX1_1 = " ------------------------------------------------------------------------------------------------";
        String BOX1_2 = "|                                                                                                |";
        String centerText = centerString(96, INTROTEXT);

        return BOX1_1 + "\n" + BOX1_2 + "\n" + centerText + "\n" + BOX1_2 + "\n" + BOX1_1;
    }//return String.format("%s\n%s\n|\t\t\t\t\t\t\t\t%35s\t\t\t\t\t\t\t\t |\n%s\n%s\n", BOX1_1, BOX1_2, INTROTEXT, BOX1_2, BOX1_1);

    public static String centerString(int width, String s) {
        return String.format("|%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s)) + "|";
    }

    public String dobbleButton(String text1, String text2) {
        String margeTop    = " ___________________________________________________________ ";
        String middleSpace = "|                                                           |";
        String margeCenter = centerString(59, text1);
        String margeCenter2 = centerString(59, text2);
        String margeBottom = " ___________________________________________________________ ";
        return ("\n" + margeTop + margeTop + "\n" + middleSpace + middleSpace + "\n" + margeCenter + margeCenter2 + "\n" + middleSpace + middleSpace + "\n" + margeBottom + margeBottom);
    }

    public void dobbleButton(int width, int height, String text1, String text2) {

        margeTop(width);
        System.out.println();
        for (int i = 0; i < height / 2; i++) {
            System.out.print("|");
            for (int j = 1; j <= width / 2; j++) {
                System.out.print(" ");
            }
            System.out.print("|");
            for (int j = 1; j <= width / 2; j++) {
                System.out.print(" ");
            }
            System.out.print("|");
        }
        System.out.println();
        System.out.print(centerString(width / 2, text1));
        System.out.print(centerString(width / 2, text2));
        System.out.println();

        for (int i = 0; i < height / 2; i++) {
            System.out.print("|");
            for (int j = 1; j <= width / 2; j++) {
                System.out.print(" ");
            }
            System.out.print("|");
            for (int j = 1; j <= width / 2; j++) {
                System.out.print(" ");
            }
            System.out.print("|");
        }
    }
}

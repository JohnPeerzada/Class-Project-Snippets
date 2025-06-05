/*
John Peerzada
CSE 174
This program gives a list of options to the user and
interprets a given array in a certain way.
*/

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Assignment4 {
    // Declaring global variables
    static String fileName = "a";
    static Customer[] list = new Customer[15];
    static Scanner kb = new Scanner(System.in);
    static Customer[] listAlt = new Customer[15];

    // Start of main method, switches between each program depending on the 
    // users input 
    
    public static void main(String[] args) throws FileNotFoundException {
        int count;
        int off = 0;
        int on = 0;
        do {
            menu();
            count = kb.nextInt();
            switch (count) {
                case 1:
                    off++;
                    System.out.printf("\nEnter the name of the file: ");
                    fileName = kb.next();
                    arrayList(fileName, list);
                    break;
                case 2:
                    if (off != 0) {
                        displayList(list);
                        
                    } else {
                        System.out.printf("No data has been loaded yet!\n\n");
                    }
                    break;
                case 4:
                    if (off != 0) {
                        sort1(list);
                        on = 1;
                    } else {
                        System.out.printf("No data has been loaded yet!\n\n");
                    }
                    break;
                default:
            }
        } while (count != 6);
        System.out.printf("End!");
    }
    
    /**
    Displaying the main menu.
    */
    
    public static void menu() {
        System.out.printf("1. Load from a file\n");
        System.out.printf("2. Print the loaded list\n");
        System.out.printf("3. Sort the list based on the nicknames\n");
        System.out.printf("4. sort the list based on the ids\n");
        System.out.printf("5. Print the sorted list\n");
        System.out.printf("6. Exit\n");
        System.out.printf("Enter a number[1-6]: ");
    }     
    
    /**
    creates an array out of the given file.
    @param a filename given by the user
    @param an empty array list
    @return a complete array of a costumer object list
    */
    
    public static Customer[] arrayList(String fileName, Customer[] list)
         throws FileNotFoundException {
        Scanner fr = new Scanner(new File(fileName));
        String name;
        int num;
        int crumb = 0;
        while (fr.hasNext() == true) {
            name = fr.next();
            num = fr.nextInt();
            list[crumb] = new Customer(name, num);
            crumb++;
        }
        System.out.printf("Loading from the file is done!\n\n");
        return list;
    }
    
    /**
    displays the list to the user unsorted.
    @param the completed unsorted costumer array
    */
    
    public static void displayList(Customer[] list) {
        System.out.printf("\n**** Printing the list ****\n");
        String a;
        String b;
        String c;
        int i = 0;
        int j = 0;
        do {
            j = 0;
            while (j != 10 && i < list.length - 1) {
                a = list[i].toString();
                System.out.printf(i + 1 + ". " + a + "\n");
                i++;
                j++;
            }
            System.out.printf("Enter something to continue/enter s to stop: ");
            b = "" + kb.next();
        } while (!b.equals("s"));
        System.out.printf("\n");
    }
    
    /**
    Sorts the list based on the id numbers.
    @param the customer object array
    */
    
    public static void sort1(Customer[] list) {
        int hold = 0;
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = i + 1; j < list.length - 1; j++) {
                if (list[hold].getId() < list[j].getId()) {
                    hold = hold;
                } else {
                    hold = j;
                }
            }
            listAlt[i] = list[hold];
            
        }
        System.out.printf("Sorting is done!\n\n");
    }
    
    public static void sort2() {
    
    }   
    
    /**
    displays the sorted customer object array.
    @param the sorted customer object array
    */
    
    public static void displayListAlt(Customer[] listAlt) {
        System.out.printf("\n**** Printing the list ****\n");
        String a;
        String b;
        String c;
        int i = 0;
        int j = 0;
        do {
            j = 0;
            while (j != 10 && i < list.length - 1) {
                a = listAlt[i].toString();
                System.out.printf(i + 1 + ". " + a + "\n");
                i++;
                j++;
            }
            System.out.printf("Enter something to continue/enter s to stop: ");
            b = "" + kb.next();
        } while (!b.equals("s"));
        System.out.printf("\n");
    }
    //end of main method
}

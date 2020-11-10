import com.opencsv.exceptions.CsvException;
import java.io.*;
import java.io.BufferedReader;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public static void main(String[] args) throws IOException, CsvException {
        String csvItem = "src/inventory/items.csv";
        String csvPrice = "src/inventory/prices.csv";
        BufferedReader brItem = null;
        BufferedReader brPrice = null;
        String line = "";
        String cvsSplitBy = ",";

        int counterItem = 0;
        String setIdItem;

        try {

            brItem = new BufferedReader(new FileReader(csvItem));
            brItem.readLine();
//            brPrice = new BufferedReader(new FileReader(csvPrice));

            while ((line = brItem.readLine()) != null) {

                String[] item = line.split(cvsSplitBy);
                counterItem = counterItem + 1;
                setIdItem = item[0];
                getPrice(counterItem, setIdItem, item[1]);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (brPrice != null) {
                try {
                    brPrice.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("____Inventory Items not Shown Do not have stock____");
//        getUserInput(counterItem);
    }

    public static void getPrice(int getCounter, String getId, String description) {

        String csvPrice = "src/inventory/prices.csv";
        BufferedReader brPrice;
        String line;
        String cvsSplitBy = ",";
        int counterPrice;
        String setIdPrice;
        String setPrice;


        try {

            brPrice = new BufferedReader(new FileReader(csvPrice));
            brPrice.readLine();

            while ((line = brPrice.readLine()) != null) {

                String[] price = line.split(cvsSplitBy);
                counterPrice = getCounter;
                setIdPrice = price[0];
                double vatPrice;
                double vatOnly;
                vatPrice = Double.parseDouble(price[1])*1.15;
                vatOnly = Double.parseDouble(price[1])*0.15;
                if (setIdPrice.equals(getId)) {
                    setPrice = (counterPrice + ". ID: " + getId + ", description:" + description  + " price exc VAT: " + price[1] + " Vat Amount: " + df2.format(vatOnly) + " Price Inc VAT: " + df2.format(vatPrice));
                    getQuantity(getId,setPrice);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
    }
}

    public static void getQuantity(String getId, String getPrice) {

        String csvQuantity = "src/inventory/quantities.csv";
        BufferedReader brQuantity;
        String line = "";
        String cvsSplitBy = ",";
        String setIdQuantity;

        try {

            brQuantity = new BufferedReader(new FileReader(csvQuantity));
            brQuantity.readLine();

            while ((line = brQuantity.readLine()) != null) {

                String[] quantity = line.split(cvsSplitBy);
                setIdQuantity = quantity[0];

                if (setIdQuantity.equals(getId) ) {
                    System.out.println(getPrice + " Quantity: " + quantity[1]);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static int getUserInput(int counterMax){
//        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//        System.out.println("Enter ID");
//        int userInput = myObj.nextInt();
//        if (userInput > 0 && userInput < counterMax) {
//              // Read user input
//            System.out.println("ID selected is: " + userInput);  // Output user input
//            return userInput;
//        } else{
//            System.out.println("Enter a value of an ID?");
//            getUserInput(counterMax);
//        }
//        return -1;
//    }
//
//    public static void getInputSearch(int searchInt){
//        String csvItem = "src/inventory/items.csv";
//        BufferedReader brItem = null;
//        BufferedReader brPrice = null;
//        String line = "";
//        String cvsSplitBy = ",";
//
//        int counterItem = searchInt;
//        String setIdItem;
//
//        try {
//
//            brItem = new BufferedReader(new FileReader(csvItem));
////            brPrice = new BufferedReader(new FileReader(csvPrice));
//
//            while ((line = brItem.readLine()) != null) {
//
//                String[] item = line.split(cvsSplitBy);
//                counterItem = counterItem + 1;
//                setIdItem = item[0];
//                getPrice(counterItem, setIdItem, item[1]);
//
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}





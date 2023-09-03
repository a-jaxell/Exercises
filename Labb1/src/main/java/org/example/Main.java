package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        MenuNav navigation = new MenuNav();
        DataEntry prices = new DataEntry();
        DataEntry hours = new DataEntry();
        hours.readFromCsv("./src/main/resources/timmar.csv");
        do {
            MenuNav.printMenu();
            navigation.handleInput();

            switch (navigation.getUserChoice()) {
                case "1" -> {
                    System.out.println("Inmatning");
                    enterPrice(hours.getData(), prices, navigation);
                }
                case "2" -> {
                    System.out.println("Min, Max och Medel");
                    minMax(prices,hours);
                }
                case "3" -> {
                    sortDescending(prices, hours);
                }
                case "4" ->  {
                    System.out.println("Bästa laddningstid (4h)");
                    optimalChargeTime(prices, hours);
                }
                case "e" -> System.out.println("Programmet avslutas...");
                default -> System.out.println( navigation.getUserChoice() + " Invalid input");
            }
        } while (!navigation.getUserChoice().equals("e"));
        System.exit(0);
    }
    static void enterPrice(ArrayList<ArrayList<String>> hours,DataEntry prices, MenuNav navigation) {
        ArrayList<ArrayList<String>> result = prices.getData();
        String userChoice;
        do {
            MenuNav.printPriceMenu();
            userChoice = navigation.handleInput();
            switch (userChoice) {
                case "1" -> {
                    Scanner sc = new Scanner(System.in);
                    System.out.println(
                            """
                                        Vänligen skriv in öre per kWh för dygnets samtliga timmar.
                                        \
                                        """);

                    for (int i = 0; i <= 23; i++) {
                        System.out.print(hours.get(i) + ": ");
                        if (sc.hasNextInt()) {
                            result.get(i).add(0, String.valueOf(sc.nextInt()));
                        }
                    }
                }
                case "2" -> {
                    System.out.println(
                            """
                                        Läser in från 'priser.csv'...
                                        \
                                        """);
                    prices.readFromCsv("./src/priser.csv");
                }
                case "b" -> {
                    System.out.println("Tillbaka...");
                }
            }
        } while (!userChoice.equals("b")) ;
    }
    static void minMax(DataEntry prices, DataEntry hours) {
        if (isDataEmpty(prices) || isDataEmpty(hours)){
            MenuNav.printMissingInput();
            return;
        }
        ArrayList<ArrayList<String>> list = combineLists(prices, hours);
        ArrayList<ArrayList<String>> sortedPrices = sortMinPrice(list);

        int sum = 0;
        for (var price : sortedPrices) {
            sum += Integer.parseInt(price.get(1));
        }
        System.out.printf("---------------------------%n");
        System.out.printf("|      Min/Max/Medel      |%n");
        System.out.printf("---------------------------%n");
        System.out.printf("                           %n");
        System.out.printf("| %-10s | %-10s |%n","MAX", sortedPrices.get(sortedPrices.size() - 1).get(1) + " öre");
        System.out.printf("| %-10s | %-10s |%n","MIN", sortedPrices.get(0).get(1) + " öre");
        System.out.printf("| %-10s | %-10s |%n","MEDEL", (sum / sortedPrices.size()) + " öre");
    }
    // Sorts in descending order
    static ArrayList<ArrayList<String>> sortMaxPrice(ArrayList<ArrayList<String>> list){
        list.sort((a, b) ->
                Integer.compare( Integer.parseInt(b.get(1)),Integer.parseInt(a.get(1))));
        return list;
    }
    // Sorts in ascending order.
    static ArrayList<ArrayList<String>> sortMinPrice(ArrayList<ArrayList<String>> list){
        list.sort(Comparator.comparingInt(b -> Integer.parseInt(b.get(1))));
        return list;
    }
    static void sortDescending(DataEntry prices, DataEntry hours){
        if(isDataEmpty(prices) || isDataEmpty(hours)){
            MenuNav.printMissingInput();
            return;
        }
        ArrayList<ArrayList<String>> list = combineLists(prices, hours);
        sortMinPrice(list);
        System.out.printf("---------------------------%n");
        System.out.printf("|        Sortering        |%n");
        System.out.printf("---------------------------%n");

        for(var timestamp: list){
            System.out.printf("| %-10s | %-10s |%n","kl " + timestamp.get(0),timestamp.get(1) + " öre");
        }
    }
    // Adds data from one list into another. Have to be the same length
    static ArrayList<ArrayList<String>> combineLists(DataEntry priceData, DataEntry timeData){

        ArrayList<ArrayList<String>> prices = priceData.getData();
        ArrayList<ArrayList<String>> hours = timeData.getData();
        if(prices.size() != hours.size()){
            throw new IllegalArgumentException("Lists have to be of the same length");
        }
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        for(int i = 0; i<hours.size(); i++){
            ArrayList<String> hour = hours.get(i);
            ArrayList<String> price = prices.get(i);

            ArrayList<String> combined = new ArrayList<>(hour);
            combined.addAll(price);
            result.add(combined);
        }
        return result;
    }
    static void optimalChargeTime(DataEntry prices, DataEntry hours){
        if(isDataEmpty(prices) || isDataEmpty(hours)){
            MenuNav.printMissingInput();
            return;
        }
        ArrayList<ArrayList<String>> combinedList = combineLists(prices, hours);
        int cheapestSum = Integer.MAX_VALUE;
        List<ArrayList<String>> cheapestSegment = null;

        for(int i = 0; i <= combinedList.size() - 4; i++){
            List<ArrayList<String>> segment = combinedList.subList(i,i+4);
            int segmentSum = 0;
            for(var cost: segment){
                segmentSum += Integer.parseInt(cost.get(1));
            }
            if(segmentSum < cheapestSum){
                cheapestSum = segmentSum;
                cheapestSegment = segment;
            }
        }
        String timeToCharge = cheapestSegment.get(0).get(0).substring(0,2) +"-"+ cheapestSegment.get(3).get(0).substring(3);
        System.out.println(timeToCharge);
        System.out.printf("---------------------------%n");
        System.out.printf("|   Bästa laddningstid    |%n");
        System.out.printf("---------------------------%n");
        System.out.printf("                           %n");
        System.out.printf("| %-10s | %-8s |%n","Ladda mellan:","kl "+ timeToCharge);
        System.out.printf("| %-10s | %-8s |%n","TOTAL PRIS: ",cheapestSum + " öre");
        System.out.printf("| %-10s | %-8s |%n","MEDEL PRIS: ",cheapestSum / 4 + " öre");
    }
    static boolean isDataEmpty(DataEntry input){
        ArrayList<ArrayList<String>> data = input.getData();
        if(data == null || data.isEmpty()) return true;
        for(ArrayList<String> row : data){
            if(!row.isEmpty()){
                return false;
            }
        }
        return true;
    }
}
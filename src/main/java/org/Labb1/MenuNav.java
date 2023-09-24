package org.Labb1;

import java.util.Scanner;

public class MenuNav {
    private String userChoice ="";
    public String getUserChoice() {
        return userChoice;
    }
    public void setUserChoice(String userChoice) {
        this.userChoice = userChoice;
    }

    static void printMenu() {
        System.out.println(
                """
                            Elpriser
                            ========
                            1. Inmatning
                            2. Min, Max och Medel
                            3. Sortera
                            4. Bästa Laddningstid (4h)
                            e. Avsluta
                            \
                            """);
    }

    public static void printPriceMenu() {
        System.out.println(
                """
                        1. Mata in manuellt
                        2. Läs in från priser.csv (placerad i samma mapp)
                        b. Tillbaka
                        """
        );
    }
    public static void printMissingInput(){
        System.out.println("""
                
                Vänligen mata in priser i menvyal 1 först.
                \
                """);
    }

    public String handleInput() {
        Scanner sc = new Scanner(System.in);
        setUserChoice(sc.nextLine().toLowerCase());
        return this.userChoice;
    }
}

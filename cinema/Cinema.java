package cinema;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class Cinema {

    private static char[][] cinemaHall;
    private static double soldTickets = 0;
    private static int currentIncome = 0;
    private static int totalIncome;
    private static double totalNumbersOfSeats;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Write your code here

        createCinema();
        getTotalIncome();

        int command;
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            command = scanner.nextInt();

            switch (command) {
                case 1:
                    printCinemaHall();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Error! Wrong command!");
            }
        } while(command != 0);
    }

    public static void createCinema() {
        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = scanner.nextInt();

        cinemaHall = new char[numberOfRows][numberOfSeats]; // sets the size of the arrays representing the cinema hall

        for(int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                cinemaHall[i][j] = 'S';  // Add a character S at every index of the arrays(every seat on the hall).
            }
        }
    }

    public static void printCinemaHall() {
        System.out.println("Cinema:");
        System.out.print(" "); // Prints a space at the beginning on the line where we print the number of columns
        for (int i = 1; i <= cinemaHall[0].length; i++) {
            System.out.print(" " + i); // Prints the number of columns
        }
        System.out.println();
        for (int i = 0; i < cinemaHall.length; i++) {
            System.out.print(i + 1); // Print the number of rows (length of the container array)
            for (int j = 0; j < cinemaHall[0].length; j++) {
                System.out.print(" " + cinemaHall[i][j]); // Print the seat in the correct position.
            }
            System.out.println(); // pass to next line at the end of the row
        }
    }

    public static void buyTicket() {
        System.out.println("Enter a row number:");
        int seatRow = scanner.nextInt(); // chooses the row
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt(); // chooses a seat number

        while (seatRow > cinemaHall.length || seatNumber > cinemaHall[0].length) {
            System.out.println("Wrong input!");
            System.out.println("Enter a row number:");
            seatRow = scanner.nextInt(); // chooses the row
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt(); // chooses a seat number
        }

        while (cinemaHall[seatRow - 1][seatNumber - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            System.out.println("Enter a row number:");
            seatRow = scanner.nextInt(); // chooses the row
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt(); // chooses a seat number
        }

        int priceTicket;

        if (totalNumbersOfSeats <= 60) { // if number of seats are less/equal to 60, the price is $10 for every seat
            priceTicket = 10;
            currentIncome += priceTicket; // Add the price of the ticket to currentIncome
            totalIncome = priceTicket * (int)totalNumbersOfSeats; // Get the totalIncome
        } else { // otherwise, the price is divided as follows
            int frontHalfOfRows = cinemaHall.length / 2; // front seats are the first half of columns
            if (seatRow <= frontHalfOfRows) {
                priceTicket = 10; // price for front seats
                currentIncome += priceTicket; // Add the price of the ticket to currentIncome
            } else {
                priceTicket = 8; // price for back seats
                currentIncome += priceTicket; // Add the price of the ticket to currentIncome
            }
        }

        System.out.println("Ticket price: $" + priceTicket);
        cinemaHall[seatRow - 1][seatNumber - 1] = 'B'; // We decrement seatRow and seatNumber to match the correct index
        soldTickets++; // increment the number of sold tickets
    }

    public static void statistics() {
        System.out.println("Number of purchased tickets: " + (int) soldTickets);
        NumberFormat formatter = new DecimalFormat("#0.00");
        double percSoldTickets = (soldTickets / totalNumbersOfSeats) * 100;
        if (soldTickets != 0) {
            System.out.println("Percentage: " + formatter.format(percSoldTickets) + "%");
        } else {
            System.out.println("Percentage: 0.00%");
        }
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void getTotalIncome() {
        totalNumbersOfSeats = cinemaHall.length * cinemaHall[0].length;
        if (totalNumbersOfSeats <= 60) { // if number of seats are less/equal to 60, the price is $10 for every seat
            totalIncome = 10 * (int)totalNumbersOfSeats; // Get the totalIncome
        } else { // otherwise, the price is divided as follows
            int frontHalfOfRows = cinemaHall.length / 2; // front seats are the first half of columns
            int totalFirstHalf = 10 * frontHalfOfRows * cinemaHall[0].length;
            int totalBackHalf = 8 * (frontHalfOfRows + 1) * cinemaHall[0].length;
            totalIncome = totalFirstHalf + totalBackHalf;
        }
    }

}
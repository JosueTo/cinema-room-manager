package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Print the structure of a room cinema
        System.out.println("Cinema:");
        System.out.println("  1 2 3 4 5 6 7 8");
        System.out.println("1 S S S S S S S S");
        System.out.println("2 S S S S S S S S");
        System.out.println("3 S S S S S S S S");
        System.out.println("4 S S S S S S S S");
        System.out.println("5 S S S S S S S S");
        System.out.println("6 S S S S S S S S");
        System.out.println("7 S S S S S S S S");

        // Calculate the profits of the room
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsPerRow = scanner.nextInt();
        int totalSeats = rows * seatsPerRow;
        int seatsFrontHalf;
        int seatsBackHalf;
        int profit;

        if (totalSeats <= 60) {
            profit = 10 * totalSeats;
        } else if (rows % 2 == 0) {
            seatsFrontHalf = (rows / 2) * seatsPerRow;
            seatsBackHalf = (rows / 2) * seatsPerRow ;
            profit = (seatsFrontHalf * 10) + (seatsBackHalf * 8);
        } else {
            seatsFrontHalf = (rows / 2) * seatsPerRow;
            seatsBackHalf = ((rows / 2) + 1) * seatsPerRow ;
            profit = (seatsFrontHalf * 10) + (seatsBackHalf * 8);
        }

        System.out.println("Total income:");
        System.out.println("$" + profit);
    } // main
} // class
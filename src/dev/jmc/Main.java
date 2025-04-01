package dev.jmc;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

record Place(String name, int distance) {
    @Override
    public String toString() {
        return String.format("%s (%d)", name, distance);
    }
}

public class Main {
    public static void main(String[] args) {

        LinkedList<Place> itinerary = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        itinerary.addAll(List.of(
                new Place("Sydney", 0),
                new Place("Melbourne", 877),
                new Place("Brisbane", 917),
                new Place("Adelaide", 1374),
                new Place("Alice Springs", 2771),
                new Place("Perth", 3923),
                new Place("Darwin", 3972)
        ));

        Place cairn = new Place("Cairn", 1990);
        addPlace(itinerary, cairn);
        addPlace(itinerary, new Place("cairn", 1990));
        System.out.println(itinerary);
        addPlace(itinerary, new Place("Canberra", 247));


        ListIterator<Place> placesIterator = itinerary.listIterator();
        String option;
        printMenu();
        boolean isForward = true; // to track direction

        do {

            if (!placesIterator.hasPrevious()) {
                System.out.println("Starting point: " + placesIterator.next());
            }

            if (!placesIterator.hasNext()) {
                System.out.println("End of journey: " + placesIterator.previous());
            }
            option = scanner.nextLine().toUpperCase().substring(0, 1);
            switch (option) {
                case "F":
                    traverseItinerary(placesIterator, true);
                    break;
                case "B":
                    traverseItinerary(placesIterator, false);
                    break;
                case "C":

                case "L":
                    System.out.println(itinerary);
                    break;
                case "M":
                    printMenu();
                    break;
                case "Q":
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid input. Please enter one of the options bellow.");
                    printMenu();
            }

        } while (true);

    }

    public static void addPlace(LinkedList<Place> list, Place place) {
        if (list.contains(place)) {
            System.out.println(place.name() + " is already on the itinerary.");
            return;
        }

        for (Place p : list) {
            if (p.name().equalsIgnoreCase(place.name())) {
                System.out.println(place.name() + " is already on the itinerary.");
                return;
            }
        }

//        for (int i = 0; i < list.size(); i++) {
//            if (place.distance() < list.get(i).distance()) {
//                list.add(i, place);
//                return;
//            }
//        }

        int matchedIndex = 0;
        for (Place p : list) {
            if (place.distance() < p.distance()) {
                list.add(matchedIndex, place);
                return;
            }
            matchedIndex++;
        }

        list.add(place);
    }

    public static void printMenu() {
        System.out.println("""
                Available actions(select word or letter):
                (F)orward
                (B)ackward
                (C)hange Direction
                (L)ist Places
                (M)enu
                (Q)uit
                """);
    }

//    public static void traverseItinerary(ListIterator<Place> listIterator, boolean isForward) {
//        if (isForward) {
//            while(listIterator.hasNext()) {
//                System.out.println(listIterator.next());
//            }
//        } else {
//            while(listIterator.hasPrevious()) {
//                System.out.println(listIterator.previous());
//            }
//        }
//    }

    public static void traverseItinerary(ListIterator<Place> listIterator, boolean isForward) {
        if (isForward) {
            if (listIterator.hasNext()) {
                System.out.println(listIterator.next());
            }
        } else {
            if (listIterator.hasPrevious()) {
                System.out.println(listIterator.previous());
            }
        }
    }
}

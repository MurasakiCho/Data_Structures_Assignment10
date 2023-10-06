/*
Name: Briana O'Neal
Class: CS 3305/W01
Term: Fall 2022
Instructor: Sharon Perry
Assignment: 10-Hashing
*/

public class Assignment10 {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("Smith", 30);
        map.put("Anderson", 31);
        map.put("Lewis", 29);
        map.put("Cook", 29);
        map.put("Smith", 65);

        System.out.println("Entries in map: " + map);

        System.out.println("The age for Lewis is " + map.get("Lewis"));

        System.out.println("Is Smith in the map? " + map.containsKey("Smith"));

        System.out.println("Is age 33 in the map? " + map.containsValue(33));

        map.remove("Smith");
        System.out.println("Entries in map: " + map);

        map.clear();
        System.out.println("Entries in map: " + map);
    }
}

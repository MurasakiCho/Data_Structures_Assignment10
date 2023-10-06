import java.util.*;
public class MyHashMap {
    public static void main(String[] args) {
        //initialize scanner and map
        Scanner scan = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<>();

        //obtain string
        System.out.println("Enter a string: ");
        String userString = scan.nextLine();

        //split string by regex, put into array
        String[] parts = userString.split(" ");

        for(String string: parts){
            System.out.println(string);
        }

        //compare each word, keep counter, place into map (word, occurrences)
        for(int i = 0; i < parts.length; i++){
            int counter = 1;
            for(int j = i+1; j < parts.length; j++){
                if(parts[i].equals(parts[j])){
                    counter++;
                }
            }
            map.put(parts[i], counter);
        }

        //test
        System.out.println(map);

    }
}

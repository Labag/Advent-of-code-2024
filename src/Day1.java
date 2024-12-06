import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class Day1 {
    public String path;
    public List<Integer> list1;
    public List<Integer> list2;

    public Day1() {
        path = "src/inputs/input-1.txt";
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+"); // Sépare les chiffres par espace
                if (parts.length == 2) {
                    list1.add(Integer.parseInt(parts[0]));
                    list2.add(Integer.parseInt(parts[1]));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public int ResolveProblem1() {
        int sum = 0;
        List<Integer> list1Problem1 = new ArrayList<>(list1.stream().toList());
        List<Integer> list2Problem1 = new ArrayList<>(list2.stream().toList());

        // parcourir
        while (!list1Problem1.isEmpty()) {
            int smallestIndex1 = smallestIndex(list1Problem1);
            int smallestIndex2 = smallestIndex(list2Problem1);
            sum += abs(list1Problem1.get(smallestIndex1) - list2Problem1.get(smallestIndex2));
            list1Problem1.remove(smallestIndex1);
            list2Problem1.remove(smallestIndex2);
        }
        return sum;
    }

    public int smallestIndex(List<Integer> list) {
        int smallest = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < list.get(smallest)) {
                smallest = i;
            }
        }
        return smallest;
    }

    public int ResolveProblem2() {
        int sum = 0;
        List<Integer> list1Problem2 = list1.stream().toList();
        List<Integer> list2Problem2 = list2.stream().toList();

        for (int i : list1Problem2) {
            int nbOccurrences = numberOfOccurrences(list2Problem2, i);
            sum += i * nbOccurrences;
        }
        return sum;
    }

    public int numberOfOccurrences(List<Integer> list, int target) {
        return (int) list.stream().filter(x -> x == target).count();
    }
}

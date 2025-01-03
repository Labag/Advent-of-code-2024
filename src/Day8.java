import utils.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day8 {
    private final List<List<String>> charsMatrix;

    public Day8() {
        String filePath = "src/inputs/input-8.txt";
        charsMatrix = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> chars = new ArrayList<>(Arrays.asList(line.split("")));
                charsMatrix.add(chars);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int ResolveProblem1() {
        return findAntinodes("problem1");
    }

    private int findAntinodes(String problem) {
        int sum = 0;

        List<List<String>> newMatrix = initResMatrix();
        Map<String, List<Pair>> frequenciesMap = sortFrequencies();

        for (String key : frequenciesMap.keySet()) {
            List<Pair> pairs = frequenciesMap.get(key);
            if (pairs.size() < 2) {
                continue;
            }

            for (int i = 0; i < pairs.size() - 1; i++) {
                for (int j = i + 1; j < pairs.size(); j++) {
                    Pair pair1 = new Pair(pairs.get(i).x, pairs.get(i).y);
                    Pair pair2 = new Pair(pairs.get(j).x, pairs.get(j).y);

                    if (Objects.equals(problem, "problem1")) {
                        sum += addNewAntinodes(pair1, pair2, 1, newMatrix);
                    }

                    if (Objects.equals(problem, "problem2")) {
                        boolean addMore = true;
                        int k = 1;
                        while (addMore) {
                            int res = addNewAntinodes(pair1, pair2, k, newMatrix);
                            sum += res;
                            k++;
                            addMore = res > 0; // if we didn't add any, we don't need to continue
                        }
                    }
                }
            }
        }
        return sum;
    }

    private int addNewAntinodes(Pair pair1, Pair pair2, int k, List<List<String>> newMatrix) {
        int newAntinodes = 0;

        Pair node1;
        Pair node2;

        int diffX = Math.abs(pair1.x - pair2.x); // position en hauteur
        int diffY = Math.abs(pair1.y - pair2.y); // position en largeur
        if (pair1.y < pair2.y) {
            node1 = new Pair(pair1.x - k * diffX, pair1.y - k * diffY);
            node2 = new Pair(pair2.x + k * diffX, pair2.y + k * diffY);
        } else {
            node1 = new Pair(pair1.x - k * diffX, pair1.y + k * diffY);
            node2 = new Pair(pair2.x + k * diffX, pair2.y - k * diffY);
        }

        boolean isWithinBounds1 = isWithinBounds(node1, newMatrix);
        boolean isWithinBounds2 = isWithinBounds(node2, newMatrix);
        if (isWithinBounds1) {
            if (!Objects.equals(newMatrix.get(node1.x).get(node1.y), "#")) {
                newMatrix.get(node1.x).set(node1.y, "#");
                newAntinodes++;
            }
        }

        if (isWithinBounds2) {
            if (!Objects.equals(newMatrix.get(node2.x).get(node2.y), "#")) {
                newMatrix.get(node2.x).set(node2.y, "#");
                newAntinodes++;
            }
        }

        return newAntinodes;
    }

    private boolean isWithinBounds(Pair node, List<List<String>> newMatrix) {
        return node.x >= 0 && node.y >= 0 && node.x < newMatrix.size() && node.y < newMatrix.getFirst().size();
    }

    private Map<String, List<Pair>> sortFrequencies() {
        Map<String, List<Pair>> dictionary = new HashMap<>();

        for (int i = 0; i < charsMatrix.size(); i++) {
            for (int j = 0; j < charsMatrix.get(i).size(); j++) {
                String value = charsMatrix.get(i).get(j);
                if (dictionary.containsKey(value)) {
                    dictionary.get(value).add(new Pair(i, j));
                } else {
                    if (!value.equals(".")) {
                        List<Pair> pairs = new ArrayList<>();
                        pairs.add(new Pair(i, j));
                        dictionary.put(value, pairs);
                    }
                }
            }
        }
        return dictionary;
    }

    private List<List<String>> initResMatrix() {
        List<List<String>> resMatrix = new ArrayList<>();
        int rows = charsMatrix.size();
        int cols = charsMatrix.getFirst().size();

        for (int i = 0; i < rows; i++) {
            List<String> tmpList = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                tmpList.add(".");
            }
            resMatrix.add(tmpList);
        }

        return resMatrix;
    }

    public int ResolveProblem2() {
        return findAntinodes("problem2");
    }
}

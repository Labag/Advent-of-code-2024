import utils.Direction;
import utils.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day10 {

    private final List<List<String>> charsMatrix;
    private final Set<Pair> ninePositions = new HashSet<>();

    public Day10() {
        String filePath = "src/inputs/input-10.txt";
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
        int sum = 0;
        List<Pair> zerosPositions = findAllZerosPos();
        // lancer une exploration depuis chaque position
        for (Pair p : zerosPositions) {
            sum+= headScore(p);
        }
        return sum;
    }

    private int headScore(Pair p) {
        ninePositions.clear();
        findPathToNine(p, "1");
        return ninePositions.size();
    }

    private void findPathToNine(Pair p, String numberToFind) {
        List<Pair> possiblePaths = new ArrayList<>();
        // gauche
        if (isWithinBounds(Direction.left, p))
            if (Objects.equals(charsMatrix.get(p.x).get(p.y - 1), numberToFind))
                possiblePaths.add(new Pair(p.x, p.y-1));

        // droite
        if (isWithinBounds(Direction.right, p))
            if (Objects.equals(charsMatrix.get(p.x).get(p.y + 1), numberToFind))
                possiblePaths.add(new Pair(p.x, p.y+1));
        // haut
        if (isWithinBounds(Direction.up, p))
            if (Objects.equals(charsMatrix.get(p.x - 1).get(p.y), numberToFind))
                possiblePaths.add(new Pair(p.x - 1, p.y));
        // bas
        if (isWithinBounds(Direction.down, p))
            if (Objects.equals(charsMatrix.get(p.x + 1).get(p.y), numberToFind))
                possiblePaths.add(new Pair(p.x + 1, p.y));

        if (Objects.equals(numberToFind, "9")) {
            ninePositions.addAll(possiblePaths);
        } else {
            for (Pair number : possiblePaths) {
                findPathToNine(number, Integer.toString(Integer.parseInt(numberToFind) + 1));
            }
        }
    }

    private boolean isWithinBounds(Direction direction, Pair p) {
        return switch (direction) {
            case left -> p.y - 1 >= 0;
            case right -> p.y + 1 < charsMatrix.getFirst().size();
            case up -> p.x - 1 >= 0;
            case down -> p.x + 1 < charsMatrix.size();
        };
    }

    private List<Pair> findAllZerosPos() {
        List<Pair> zerosPositions = new ArrayList<>();
        for (int i = 0; i < charsMatrix.size(); i++) {
            for (int j = 0; j < charsMatrix.get(i).size(); j++) {
                if (charsMatrix.get(i).get(j).equals("0")) {
                    zerosPositions.add(new Pair(i, j));
                }
            }
        }
        return zerosPositions;
    }
}

import utils.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day10 {

    private final List<List<String>> charsMatrix;
    private final Set<Pair> ninePositions = new HashSet<>();
    private final Set<List<Pair>> hikingTrails = new HashSet<>();

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
        List<Pair> possiblePaths = getPossiblePaths(p, numberToFind);

        if (Objects.equals(numberToFind, "9")) {
            ninePositions.addAll(possiblePaths);
        } else {
            int nextNumberToFind = Integer.parseInt(numberToFind) + 1;
            for (Pair number : possiblePaths) {
                findPathToNine(number, Integer.toString(nextNumberToFind));
            }
        }
    }

    private boolean isWithinBounds(Pair p) {
        return p.x >= 0 && p.x < charsMatrix.size() &&
                p.y >= 0 && p.y < charsMatrix.getFirst().size();
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

    public int ResolveProblem2() {
        int sum = 0;
        List<Pair> zerosPositions = findAllZerosPos();
        for (Pair p : zerosPositions) {
            sum+= rating(p);
        }
        return sum;
    }

    private int rating(Pair p) {
        hikingTrails.clear();
        List<Pair> trail = new ArrayList<>();
        findPathToNine2(p, "1", trail);
        return hikingTrails.size();
    }

    private void findPathToNine2(Pair p, String numberToFind, List<Pair> trail) {
        trail.add(p);
        List<Pair> possiblePaths = getPossiblePaths(p, numberToFind);

        if (Objects.equals(numberToFind, "9")) {
            for (Pair ninePosition : possiblePaths) {
                trail.add(ninePosition);
                hikingTrails.add(trail);
                trail.remove(ninePosition);
            }
        } else {
            int nextNumberToFind = Integer.parseInt(numberToFind) + 1;
            for (Pair number : possiblePaths) {
                findPathToNine2(number, Integer.toString(nextNumberToFind), trail);
            }
        }

    }

    private List<Pair> getPossiblePaths(Pair p, String numberToFind) {
        List<Pair> possiblePaths = new ArrayList<>();

        // Offsets pour chaque direction : {dx, dy}
        int[][] directions = {
                {0, -1}, // gauche
                {0, 1},  // droite
                {-1, 0}, // haut
                {1, 0}   // bas
        };

        for (int[] direction : directions) {
            int newX = p.x + direction[0];
            int newY = p.y + direction[1];

            if (isWithinBounds(new Pair(newX, newY)) && Objects.equals(charsMatrix.get(newX).get(newY), numberToFind)) {
                possiblePaths.add(new Pair(newX, newY));
            }
        }
        return possiblePaths;
    }
}

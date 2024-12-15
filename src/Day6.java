import utils.Direction;
import utils.Pair;
import utils.PositionNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Day6 {
    private final List<List<String>> charsMatrix;

    public Day6() {
        String filePath = "src/inputs/input-6.txt";
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
        Pair currentPosition;

        try {
             currentPosition = findInitialPosition();
        } catch (PositionNotFoundException e) {
            System.out.println(e.getMessage());
            return -1;
        }

        Direction direction = Direction.up;
        Pair nextPosition = move(currentPosition, direction);
        while (nextPosition.x >= 0 && nextPosition.y >= 0
                && nextPosition.x < charsMatrix.size()  && nextPosition.y < charsMatrix.get(nextPosition.x).size()) {
            if (Objects.equals(charsMatrix.get(nextPosition.x).get(nextPosition.y), "#")) {
                direction = turn(direction);
            } else {
                charsMatrix.get(currentPosition.x).set(currentPosition.y, "X");
                currentPosition = nextPosition;
            }

            nextPosition = move(currentPosition, direction);
        }
        charsMatrix.get(currentPosition.x).set(currentPosition.y, "X"); // for the last before exiting the matrix
        return sumUpVisitedLocations();
    }

    private Pair findInitialPosition() throws PositionNotFoundException {
        for (int i = 0; i < charsMatrix.size(); i++) {
            for (int j = 0; j < charsMatrix.get(i).size(); j++) {
                if (charsMatrix.get(i).get(j).equals("^")) {
                    return new Pair(i, j);
                }
            }
        }
        throw new PositionNotFoundException();
    }

    private int sumUpVisitedLocations() {
        int sum = 0;
        for (List<String> matrix : charsMatrix) {
            sum += (int) matrix.stream().filter(x -> Objects.equals(x, "X")).count();
        }
        return sum;
    }

    private Direction turn(Direction direction) {
        return switch (direction) {
            case up -> Direction.right;
            case right -> Direction.down;
            case down -> Direction.left;
            case left -> Direction.up;
        };
    }

    private Pair move(Pair currentPosition, Direction direction) {
        return switch (direction) {
            case Direction.up -> currentPosition.add(new Pair(-1, 0));
            case Direction.down -> currentPosition.add(new Pair(1, 0));
            case Direction.left -> currentPosition.add(new Pair(0, -1));
            case Direction.right -> currentPosition.add(new Pair(0, 1));
        };
    }

    public int ResolveProblem2() {
        // remplacer un . par un #
        // tester si ça boucle -> comment ?
        // si oui return, sinon refaire avec un autre
        return 0;
    }
}

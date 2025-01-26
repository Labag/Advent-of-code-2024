import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day11 {
    private List<Long> stonesList;

    public Day11() {
        String filePath = "src/inputs/input-11.txt";
        stonesList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                for (String part : parts) {
                    stonesList.add((long) Integer.parseInt(part));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int ResolveProblem1() {
        for (int i = 0; i < 25; i++) {
            splitStonesList();
        }
        return stonesList.size();
    }

    private void splitStonesList() {
        List<Long> newStonesList = new ArrayList<>();
        for (Long stone : stonesList) {
            String number = String.valueOf(stone);
            if (stone == 0) {
                newStonesList.add(1L);
            } else if (number.length() % 2 == 0) {
                int middle = number.length() / 2;
                newStonesList.add(Long.parseLong(number.substring(0, middle)));
                newStonesList.add(Long.parseLong(number.substring(middle)));
            } else {
                newStonesList.add(stone * 2024);
            }
        }
        stonesList = newStonesList;
    }
}

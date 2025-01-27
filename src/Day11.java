import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day11 {
    private final List<Long> stonesList;

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
        List<Long> myStonesList = stonesList;
        for (int i = 0; i < 25; i++) {
            myStonesList = splitStonesList(myStonesList);
        }
        return myStonesList.size();
    }

    // adding in a list
    // if there is a speration : first goes in the list and second goes in a second list
    // we iterate to


    private List<Long> splitStonesList(List<Long> myStonesList) {
        List<Long> newStonesList = new ArrayList<>(myStonesList.size() * 2);

        for (Long stone : myStonesList) {
            if (stone == 0) {
                newStonesList.add(1L);
            } else {
                int digitCount = (int) Math.log10(stone) + 1;
                if (digitCount % 2 == 0) {
                    int middlePower = (int) Math.pow(10, (double) digitCount / 2);
                    long firstPart = stone / middlePower;
                    long secondPart = stone % middlePower;
                    newStonesList.add(firstPart);
                    newStonesList.add(secondPart);
                } else {
                    newStonesList.add(stone * 2024);
                }
            }
        }
        return newStonesList;
    }

    public long ResolveProblem2() {
        List<List<Long>> allStonesList = new ArrayList<>();
        allStonesList.add(stonesList);
        for (int i = 0; i < 75; i++) {
            List<List<Long>> newStonesToAdd = new ArrayList<>();
            for (List<Long> currentStoneList : allStonesList) {
                List<Long> stoneList = splitStonesList(currentStoneList);

                if (stoneList.size() > 10000) {
                    newStonesToAdd.addAll(splitList(stoneList));
                } else {
                    newStonesToAdd.add(stoneList);
                }
            }
            allStonesList = newStonesToAdd;
        }
        return getTotalElements(allStonesList);
    }

    private long getTotalElements(List<List<Long>> allStonesList)  {
        long sum = 0;
        for (List<Long> tests : allStonesList) {
            sum += tests.size();
        }
        return sum;
    }

    private List<List<Long>> splitList(List<Long> stoneList) {
        List<List<Long>> chunks = new ArrayList<>();
        for (int i = 0; i < stoneList.size(); i += 10000) {
            chunks.add(new ArrayList<>(stoneList.subList(i, Math.min(stoneList.size(), i + 10000))));
        }
        return chunks;
    }
}

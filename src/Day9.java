
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day9 {
    private final List<Integer> diskMap;
    private int idAlreadyExplored;

    public Day9() {
        String filePath = "src/inputs/input-9.txt";
        diskMap = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String s : line.split("")) {
                    diskMap.add(Integer.parseInt(s));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public long ResolveProblem1() {
        List<String> blocksList = translateToBlockList();
        int lastDigitIndex = findLastDigit(blocksList);
        int firstEmptySpaceIndex = findFirstEmptySpace(blocksList);
        while (firstEmptySpaceIndex < lastDigitIndex) {
            String tmp = blocksList.get(firstEmptySpaceIndex);
            blocksList.set(firstEmptySpaceIndex, blocksList.get(lastDigitIndex));
            blocksList.set(lastDigitIndex, tmp);

            firstEmptySpaceIndex = findFirstEmptySpace(blocksList);
            lastDigitIndex = findLastDigit(blocksList);
        }
        return checkSum(blocksList);
    }

    private long checkSum(List<String> blocksList) {
        long sum = 0;
        for (int i = 0; i < blocksList.size(); i++) {
            String s = blocksList.get(i);
            if (!s.equals(".")) {
                sum += (long) Integer.parseInt(s) * i;
            }
        }
        return sum;
    }

    private int findFirstEmptySpace(List<String> blocksList) {
        for (int i = 0; i < blocksList.size(); i++) {
            if (blocksList.get(i).equals(".")) {
                return i;
            }
        }
        return -1;
    }

    private int findLastDigit(List<String> blocksList) {
        for (int i = blocksList.size() - 1; i >= 0; i--) {
            if (!blocksList.get(i).equals(".")) {
                return i;
            }
        }
        return -1;
    }

    private List<String> translateToBlockList() {
        List<String> blocksList = new ArrayList<>();
        int id = 0;
        boolean isFile = true;
        for (Integer i : diskMap) {
            if (isFile) {
                for (int j = 1; j <= i; j++) {
                    blocksList.add(String.valueOf(id));
                }
                id++;
                isFile = false;
            } else {
                for (int j = 1; j <= i; j++) {
                    blocksList.add(".");
                }
                isFile = true;
            }
        }
        return blocksList;
    }

    public long ResolveProblem2() {
        List<String> blocksList = translateToBlockList();
        idAlreadyExplored = blocksList.size()-1;
        while (idAlreadyExplored > 1) {
            while (blocksList.get(idAlreadyExplored).equals(".")) {
                idAlreadyExplored--;
            }
            int endIndexLastFile = idAlreadyExplored;
            findLastFile(blocksList);
            int startIndexLastFile = idAlreadyExplored + 1;

            int sizeLookingFor = (endIndexLastFile - startIndexLastFile) + 1;

            int startIndexFirstBlank = findFirstBlankMatchingSize(blocksList, sizeLookingFor);
            if (startIndexFirstBlank != -1) {
                for (int i = startIndexLastFile; i <= endIndexLastFile; i++) {
                    blocksList.set(startIndexFirstBlank, blocksList.get(i));
                    blocksList.set(i, ".");
                    startIndexFirstBlank++;
                }
            }
        }
        return checkSum(blocksList);
    }

    private int findFirstBlankMatchingSize(List<String> blocksList, int sizeLookingFor) {
        int start = -1;
        int end;
        for (int i = 0; i < blocksList.size(); i++) {
            if (blocksList.get(i).equals(".")) {
                if (start == -1) {
                    start = i;
                }
                end = i;
                if ((end - start) + 1 == sizeLookingFor && start < idAlreadyExplored) {
                    return start;
                }
            } else {
                start = -1;
            }
        }
        return -1;
    }

    private void findLastFile(List<String> blocksList) {
        String value = blocksList.get(idAlreadyExplored);
        for (int i = idAlreadyExplored; i >= 0; i--) {
            if (!blocksList.get(i).equals(value)) {
                idAlreadyExplored = i;
                break;
            }
        }
    }
}

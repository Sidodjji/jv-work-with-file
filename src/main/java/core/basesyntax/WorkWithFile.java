package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readLinesFromFile(fromFileName);
        int[] totals = calculateTotals(lines);
        writeReportToFile(totals, toFileName);
    }
    private List<String> readLinesFromFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fileName, e);
        }
    }
    private int[] calculateTotals(List<String> lines) {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[0].equals(SUPPLY_OPERATION)) {
                supplyAmount += Integer.parseInt(parts[1]);
            } else if (parts[0].equals(BUY_OPERATION)) {
                buyAmount += Integer.parseInt(parts[1]);
            }
        }
        int result = supplyAmount - buyAmount;
        return new int[]{supplyAmount, buyAmount, result};
    }
    private void writeReportToFile(int[] totals, String fileName) {
        String report = SUPPLY_OPERATION + "," + totals[0] + System.lineSeparator()
                + BUY_OPERATION + "," + totals[1] + System.lineSeparator()
                + RESULT_OPERATION + "," + totals[2];
        try {
            Files.writeString(Path.of(fileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + fileName, e);
        }
    }
}

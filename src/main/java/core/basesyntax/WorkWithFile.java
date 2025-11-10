package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        try {
            List<String> lines = Files.readAllLines(Path.of(fromFileName));
            for (String line: lines) {
                String[] lineResult = line.split(",");
                if (lineResult[0].equals("supply")) {
                    supplyAmount += Integer.parseInt(lineResult[1]);
                }
                if (lineResult[0].equals("buy")) {
                    buyAmount += Integer.parseInt(lineResult[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int result = supplyAmount - buyAmount;
        String output = "supply," + supplyAmount + System.lineSeparator()
                + "buy," + buyAmount + System.lineSeparator()
                + "result," + result;
        try {
            Files.writeString(Path.of(toFileName), output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

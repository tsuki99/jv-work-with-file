package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final int INITIAL_SUM = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        int sumBuy = INITIAL_SUM;
        int sumSupply = INITIAL_SUM;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();

            while (value != null) {
                String[] data = value.split(COMMA);
                int number = Integer.parseInt(data[1]);

                if (value.contains(BUY)) {
                    sumBuy += number;
                } else if (value.contains(SUPPLY)) {
                    sumSupply += number;
                }

                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(getReport(sumBuy, sumSupply));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file");
        }
    }

    private String getReport(int sumBuy, int sumSupply) {
        int result = sumSupply - sumBuy;

        return SUPPLY + COMMA + sumSupply + System.lineSeparator()
                + BUY + COMMA + sumBuy + System.lineSeparator()
                + "result" + COMMA + result;
    }
}

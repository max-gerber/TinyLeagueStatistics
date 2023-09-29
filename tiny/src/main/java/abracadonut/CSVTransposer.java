package abracadonut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVTransposer {
    public static void transposeCSV() {
        String inputFile = "statisticsPreTransposition.csv";
        String outputFile = "statistics.csv";


        List<List<String>> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<String> row = new ArrayList<>();
                for (String value : values) {
                    row.add(value);
                }
                data.add(row);
            }

            int maxCols = data.stream().mapToInt(List::size).max().orElse(0);

            List<List<String>> transposedData = transpose(data, maxCols);

            try (FileWriter writer = new FileWriter(outputFile)) {
                for (List<String> row : transposedData) {
                    writer.write(String.join(",", row) + "\n");
                }
                System.out.println("Successfully wrote data to " + outputFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cleanup();
    }

    private static List<List<String>> transpose(List<List<String>> data, int maxCols) {
        List<List<String>> transposedData = new ArrayList<>();

        for (int i = 0; i < maxCols; i++) {
            List<String> transposedRow = new ArrayList<>();
            for (List<String> row : data) {
                transposedRow.add(i < row.size() ? row.get(i) : "");
            }
            transposedData.add(transposedRow);
        }

        return transposedData;
    }

    private static void cleanup() { 
        try {
            Files.delete(Paths.get("statisticsPreTransposition.csv"));
        } catch (NoSuchFileException e) {
            System.out.println("Error deleting statisticsPreTransposition.csv: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error deleting statisticsPreTransposition.csv: " + e.getMessage());
        }
    }
}

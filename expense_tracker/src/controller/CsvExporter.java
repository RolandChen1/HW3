package controller;

import model.Transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Utility class responsible for exporting expense transactions to a CSV file.
 * This class is intentionally decoupled from MVC layers to satisfy OCP and
 * improve modularity/testability.
 */
public class CsvExporter {

    private static final String CSV_EXTENSION = ".csv";
    private static final String HEADER = "Amount,Category,Date";

    /**
     * Exports the provided list of transactions into a CSV file with header row.
     *
     * @param transactions List of transactions to export
     * @param fileName     Output file name (must end with .csv)
     * @throws IOException If writing to file fails
     */
    public static void export(List<Transaction> transactions, String fileName) throws IOException {

        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be empty.");
        }

        if (!fileName.toLowerCase().endsWith(CSV_EXTENSION)) {
            throw new IllegalArgumentException("File name must end with .csv");
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(HEADER + "\n");

            for (Transaction t : transactions) {
                writer.write(
                    t.getAmount() + "," +
                    t.getCategory() + "," +
                    t.getTimestamp() + "\n"
                );
            }
        }
    }
}

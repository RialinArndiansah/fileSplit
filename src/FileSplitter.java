import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplitter {

    public static Queue<String> splitFile(String filePath, int numberOfParts) {
        Queue<String> partsQueue = new LinkedList<>();
        StringBuilder fileContent = new StringBuilder();

        // Membaca isi file
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                fileContent.append(fileScanner.nextLine()).append("\n");
            }
            System.out.println("Isi file penuh:\n" + fileContent.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + filePath);
            return partsQueue;
        }

        // Memotong isi file menjadi beberapa bagian
        int totalLength = fileContent.length();
        int partSize = totalLength / numberOfParts;
        int startIndex = 0;

        for (int i = 0; i < numberOfParts; i++) {
            int endIndex = Math.min(totalLength, startIndex + partSize);
            partsQueue.add(fileContent.substring(startIndex, endIndex));
            System.out.println("Memotong bagian dari " + startIndex + " ke " + endIndex);
            startIndex = endIndex;
        }

        return partsQueue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan path file: ");
        String filePath = scanner.nextLine();

        System.out.print("Masukkan jumlah bagian untuk memotong file: ");
        int numberOfParts = scanner.nextInt();

        Queue<String> partsQueue = splitFile(filePath, numberOfParts);

        System.out.println("\nIsi file yang telah dipotong:");
        int partNumber = 1;
        while (!partsQueue.isEmpty()) {
            System.out.println("Bagian " + partNumber + ":");
            System.out.println(partsQueue.poll());
            System.out.println("-----------");
            partNumber++;
        }

        scanner.close();
    }
}
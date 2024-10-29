package semester3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan nama file (dengan path jika diperlukan): ");
        String fileName = scanner.nextLine();

        System.out.print("Masukkan jumlah bagian untuk memotong file: ");
        int jumlahBagian = scanner.nextInt();
        scanner.nextLine();

        StringBuilder fileContent = new StringBuilder();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                fileContent.append(fileScanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
            return;
        }

        String content = fileContent.toString();
        int panjangBagian = content.length() / jumlahBagian;
        Queue<String> queue = new LinkedList<>();

        for (int i = 0; i < jumlahBagian; i++) {
            int start = i * panjangBagian;
            int end = (i == jumlahBagian - 1) ? content.length() : (i + 1) * panjangBagian;
            queue.add(content.substring(start, end));
        }
        
        int bagian = 1;
        while (!queue.isEmpty()) {
            System.out.println("Bagian " + bagian + ":");
            System.out.println(queue.poll());
            System.out.println("--------------");
            bagian++;
        }

        scanner.close();
    }
}

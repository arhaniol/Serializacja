import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Serializacja {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text;
        String filePath = "list.bin";
        List<String> list = new LinkedList<>();
        boolean isNotEnd;

        System.out.println("Wprowadź imię lub zakończ '-'");
        do {
            text = scanner.next();
            isNotEnd = !text.equals("-");
            if (isNotEnd) {
                list.add(text);
            }
        } while (isNotEnd);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        readSerializedFile(filePath);
    }

    private static void readSerializedFile(String filePath) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            List<String> list = (List<String>) inputStream.readObject();
            System.out.println("Odczytana zawarotość pliku:");
            for (String text : list) {
                System.out.println(text);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Serializacja {
    public static void main(String[] args) {
        System.out.println("Task 1:");
        task1();
        System.out.println("Task 2:");
        task2();
    }

    private static void task2() {
        Human human1=new Human();
        human1.setName("Jacek");
        human1.setAge(21);
        String filePath="human.bin";

        try(ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream(filePath))){
            System.out.println("Serializuje obiekt: "+human1.toString());
            outputStream.writeObject(human1);
            System.out.println("Obiekt zserializowany");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))){
            System.out.println("Deserializuje obliekt...");
            Human human2=(Human)inputStream.readObject();
            System.out.println(String.format("Imie: %s, wiek: %d",human2.getName(),human2.getAge()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void task1(){
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

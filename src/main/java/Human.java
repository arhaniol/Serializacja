import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Calendar;

public class Human implements Serializable {
    private String name;
    private transient int age;
    private int yearOfBorn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        yearOfBorn = Calendar.getInstance().get(Calendar.YEAR) - age;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        age=Calendar.getInstance().get(Calendar.YEAR)-yearOfBorn;
    }
}

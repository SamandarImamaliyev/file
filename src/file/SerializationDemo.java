package file;

import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) {
        try (ObjectOutputStream objOStrm = new ObjectOutputStream(new FileOutputStream("serial.txt"))) {
            MyClass object1 = new MyClass("hello", -7, 2.7e10);
            System.out.println("object1= " + object1);
            System.out.println("now writing to file");
            objOStrm.writeObject(object1);
        } catch (Exception e) {
            System.out.println("Exception during serialization: " + e);
        }
        try (ObjectInputStream objIStrm = new ObjectInputStream(new FileInputStream("serial.txt"))) {
            System.out.println("now reading from file");
            MyClass object2 = (MyClass) objIStrm.readObject();
            System.out.println("object2= " + object2);
        } catch (Exception e) {
            System.out.println("Exception during deserialization: " + e);
        }
    }
}

class MyClass implements Serializable {

     String s;
    int i;
    double d;

    public MyClass(String s, int i, double d) {
        this.s = s;
        this.i = i;
        this.d = d;

    }

    public String toString() {
        return "s=" + s + "; i=" + i + "; d=" + d;
    }
}

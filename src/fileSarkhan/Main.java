package fileSarkhan;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        FileUtility fu = new FileUtility();
        byte[] b = fu.readBytesNIO("a.txt");

        System.out.println("length= "+b.length);

        for(int i=0;i<b.length;i++){
            System.out.print((char) b[i]);
        }

//        *********

//        try {
//            fu.writeIntoFile("a.txt", "how are you?");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            fu.appendIntoFile("a.txt", "sdsdsd");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////        ************
//        String str = fu.read("a.txt");
//        System.out.println(str);
////        ************
//        byte[] arr = fu.readBytes("test.jpg");
//        fu.writeBytes("test2.jpg", arr);
////        *************
//
//
//        User u = new User();
//        u.name = "armud";
//        u.password = "alma";
//
//        System.out.println("1. " + u.password);
//        fu.writeObjectToFile(u, "meyve");
//////        ************
////
//        System.out.println("*********");
//        User user = (User) fu.readFileDeserialize("meyve");
//        System.out.println(user.name);
//        System.out.println(user.password);
////        ********

//        fu.fromURL("https://docs.oracle.com/en/java/javase/18/docs/api/index.html", "newUrl");

    }
}

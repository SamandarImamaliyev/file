package file;

public class GFG {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler());
        System.out.println("salam 1");
        System.out.println(10/0);
        System.out.println("salam 2");
    }
}

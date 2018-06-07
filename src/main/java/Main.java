/**
 * Main class for creating and starting the CheckThread
 */
public class Main {
    public static void main(String[] args) {
        CheckThread check = new CheckThread();
        check.start();
        System.out.println("Thread started");
    }
}
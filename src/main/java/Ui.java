import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String read() {
        return sc.nextLine();
    }

    public void displayError(Exception e) {
        System.err.println(e);
    }

}

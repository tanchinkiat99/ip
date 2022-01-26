import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String path;
    private File file;

    public Storage(String path) {
        this.path = path;
        this.file = null;
    }

    public File initialize() throws IOException {
        Path p = Paths.get("./data");
        File f = new File("./data/tutu.txt");
        if (!Files.exists(p)) {
            File path = new File("./data");
            path.mkdir();
        }
        f.createNewFile();
        this.file = f;
        return f;
    }

    public void update(ArrayList<Task> items) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        for (int i = 1; i <= items.size(); i++) {
            fw.write(i + ". " + items.get(i - 1).isDone() + "\n");
        }
        fw.close();
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        try {
            Scanner reader = new Scanner(this.file);
            while (reader.hasNextLine()) {
                String task = reader.nextLine();
                System.out.println(task);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File was not found");
        } finally {
            Tutu.separator();
        }
    }

}

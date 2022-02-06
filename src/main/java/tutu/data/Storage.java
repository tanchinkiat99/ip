package tutu.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public void update(TaskList items) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        for (int i = 1; i <= items.taskNumber(); i++) {
            fw.write(i + ". " + items.retrieve(i).isDone() + "\n");
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
        }
    }

    public String guiList() {
        String tasks = "Here are the tasks in your list:";
        try {
            Scanner reader = new Scanner(this.file);
            while (reader.hasNextLine()) {
                String task = reader.nextLine();
                tasks += ("\n" + task);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File was not found");
        }
        return tasks;
    }

}

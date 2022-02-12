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

    /**
     * Constructor to create a Storage object.
     * @param path Path to file.
     */
    public Storage(String path) {
        this.path = path;
        this.file = null;
    }

    /**
     * Creates file to store task list if file does not exist.
     * @throws IOException If file or directory search fails.
     */
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

    /**
     * Updates the list of items in TaskList object.
     * @param items TaskList object containing list of tasks.
     */
    public void update(TaskList items) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        for (int i = 1; i <= items.taskNumber(); i++) {
            fw.write(i + ". " + items.retrieve(i).isDone() + "\n");
        }
        fw.close();
    }

    /**
     * Lists the tasks currently stored in the data file.
     * @return String containing current tasks.
     */
    public String list() {
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

package gson.test.project;

import java.io.*;
import java.util.Optional;


public class TaskTracer {
    public static void main(String[] args) throws IOException {

        TaskManager taskManager = new TaskManager();
        taskManager.addTask("test");

    }
}
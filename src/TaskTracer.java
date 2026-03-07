import java.util.Scanner;

public class TaskTracer{
    public static void main(String[] args){
        /*
         * Task tracker is a project used to track and manage your tasks. In this task, you will build a simple command
         * line interface (CLI) to track what you need to do, what you have done, and what you are currently working on.
         * This project will help you practice your programming skills, including working with the filesystem, handling
         * user inputs, and building a simple CLI application.
         */

        System.out.println("Welcome to Task-Tracer!");
        System.out.println("Enter your command down below");
        System.out.print("> ");

        Scanner input = new Scanner(System.in);

        String prefix = input.next(); // Prefix of my script, it should be 'task-cli'
        String command = input.next(); // Add, Delete, Update and more
        String commandRequest = input.next(); // It depends on the command, but it can be something like this 'task-cli add HomeWork'

        if(Task.isPrefixValid(prefix)){
            System.out.println("Valid prefix!");
        }else{
            System.out.println("Invalid prefix!");
        }

        if(Task.isCommandValid(command)){
            System.out.println("Valid command!");
        }else{
            System.out.println("Invalid command!");
        }

        System.out.println(prefix + " " + command + " " + commandRequest);
    }
}
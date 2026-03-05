import java.util.Scanner;

public class TaskTracer{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("> ");
        String userInput = input.nextLine();

        while(!userInput.startsWith("task-cli")){
            if(userInput.length() < 8){
                System.out.println("Too short");
            }else{
                System.out.println(userInput.substring(0, 7) + "is not recognized as a command!");
            }

            System.out.println("Make sur to enter 'task-cli' before your request.");
            System.out.print("> ");
            userInput = input.nextLine();
        }
        System.out.println(userInput);
    }
}
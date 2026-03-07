public class Task {


    public Task(){
        // Nothing
    }

    // Simple boolean static method that check if the prefix is valid
    public static boolean isPrefixValid(String prefix){
        return prefix.equalsIgnoreCase("task-cli");
    }

    // Simple boolean static method that check if the command entered in the field is valid, {true, false}
    public static boolean isCommandValid(String commandToTest){
        final String[] commands = {
                "Add", "Update", "Delete",
                "List", "Mark-In-Progress",
                "Mark-Done"
        };

        for(String command : commands){
            if(commandToTest.equalsIgnoreCase(command)){
                return true;
            }
        }

        return false;
    }

    public static boolean isRunning(){
        return true;
    }
}

package mortis.ui;
/**
 * Ui class to handle user interactions.
*/ 

public class Ui {
    
     private final String WELCOME_MESSAGE = """
            Hello, I'm Mortis. 
            I'm case sensitive, please be nice to me.
            Enter time in the format "yyyy-MM-dd HHmm".
            E.g. 2023-10-15 1800.
            """;

     public void displayWelcomeMessage() {
          System.out.println(WELCOME_MESSAGE);
     }

     public void displayGoodbyeMessage() {
          System.out.println("Goodbye, user.");
     }

     public void lineBreak() {
          System.out.println("____________________________________________________________\n");
     }

     public void unknownCommandMessage() {
          System.out.println("""
                            I don't know that command...
                            My understood commands are: 
                            >list (shows all tasks),
                            >mark <num> (mark task at position <num>),
                            >unmark <num> (unmark task at position <num>),
                            >delete <num> (delete task at position <num>),
                            >find <description> (find tasks based on search),
                            >add todo, <description> (add a todo task)
                            >add deadline, <description>, <ddl> (add a deadline task)
                            >add event, <description>, <start>, <end> (add an event task)
                            >bye (terminate the program)
                            """);
     }

     public void findError() {
          System.out.println("Please provide a valid keyword to find.");
     }
}

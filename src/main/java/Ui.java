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
}

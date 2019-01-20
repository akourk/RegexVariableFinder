// Author       :   Alex Kourkoumelis
// Date         :   1/14/2018
// Title        :   Regex Variable Finder
// Description  :   Uses java.util.regex.* to find all the variables in a .java file.
//                  Prints out all the found variables and their values


import java.io.*;
import java.util.*;
import java.util.regex.*;

public class RegexVariableFinder {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Hello World!");

        //name of the file
        File file = new File("A.java");
        Scanner scanner = new Scanner(file);

        //initializing variables
        String input = "";
        String text = "";

        //concatenates String text into a replica of the file
        while(scanner.hasNextLine()) {
            input = scanner.nextLine();
            text += input + "\n";
        }

        //for error checking:
        //System.out.println(text);

        //the regex pattern to identify variables and their values
        //note: this only finds variables initialized with a real value,
        //initialized variables with no value will be ignored
        //also note: leaves a semicolon on each value. This is removed later
        Pattern pattern = Pattern.compile("[a-zA-Z]*\\s[a-zA-Z]*\\s=\\s[a-zA-Z_0-9]*.*[a-zA-Z_0-9]*");
        Matcher matcher = pattern.matcher(text);

        //Prints the list of variables
        while(matcher.find()) {
            String groupToken = matcher.group();

            //split token into 4 groups separated by a space:
            //type, name, =, value;
            //"=" is ignored
            String[] splitToken = groupToken.split(" ", 4);

            //removes the semicolon from the variable string
            splitToken[3] = splitToken[3].substring(0, splitToken[3].length() - 1);

            //prints all strings except the "=".
            System.out.println("Type: " + splitToken[0]);
            System.out.println("Variable name: " + splitToken[1]);
            System.out.println("Value: " + splitToken[3]);

            //for error checking. prints entire matched line
            //System.out.println(matcher.group());
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^");
        }
    }
}

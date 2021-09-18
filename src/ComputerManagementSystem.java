/*

< Log File >
 - First Integer: The number of records followed
 - Each Line contains information below:
  1) Timestamp
  2) Name of Machine
  3) Type of Event: POLICY, INVENTORY, SOFTWARE UPDATES
  4) Additional Information: PolicyNumber, Software/Hardware, UpdateVersion
  5) Action Results: Applied/Unapplied, Completed/Interrupted, Non-compliant/Compliant/UpdateFailed
  -------------------------------------------------------------------------------------------------
   Timestamp  |  Name of Machine  |  Type of Event   |  Additional Information  |  Action Results
  -------------------------------------------------------------------------------------------------
   10:40:18am |  Whildwind        |  POLICY          |  418174                  |  Applied
   11:32:05am |  Sassafras        |  POLICY          |  418777                  |  Unapplied
   -------------------------------------------------------------------------------------------------
   11:32:02am |  Eucalypt         |  INVENTORY       |  Software                |  Completed
   11:45:09am |  Turnip           |  INVENTORY       |  Hardware                |  Interrupted
   -------------------------------------------------------------------------------------------------
   10:51:32am | Puppies           |  SOFTWAREUPDATES |  221                     |  Non-Compliant
   10:51:32am | Puppies           |  SOFTWAREUPDATES |  221                     |  Compliant
   10:51:32am | Puppies           |  SOFTWAREUPDATES |  223                     |  UpdatedFailed
   -------------------------------------------------------------------------------------------------

< Classes >

1. Database: HashMap, ArrayList, Exceptions
 - loadLogData(): read log files from 'logfile.log' -> HashMap + ArrayList
 - showData(): display all log files on screen


* Logic of 'database'
* 1. Read a log file
* 2. Save it into Hash

 */


import java.io.File;
import java.io.IOException;
import java.util.*;

public class ComputerManagementSystem {

    private boolean programStillRunning;   // Whether the program is still running - should main menu be shown again?

    private Scanner scanner;                // The scanner through which we obtain user input for the menu
    //private HashMap<String, LogFileData> logData; // HashMap for data from the Log File
    private HashMap<Integer, String> logData;
    private ArrayList<LogFileData> logFileArrayList = new ArrayList<LogFileData>();

    // Constructor for the program
    public ComputerManagementSystem()
    {
        logData = new HashMap<Integer, String>();   // Map for Log Data
        logFileArrayList = new ArrayList<LogFileData>();
        scanner = new Scanner(System.in);
    }

    public void start(){

        int menuOption;

        programStillRunning = true;

        while (programStillRunning) {

            showMenu();                            // Show menu for users to input an option
            menuOption = getUserSelection(1, 5);   // Obtain user's selection
            processMenuOption(menuOption);         // Do the user's action
        }
    }

    public int getUserSelection (int lower, int upper) {

        int userInput;

        if (lower > upper)
            return 0;

        do {
            System.out.println("Choose a menu option (" + lower + " - " + upper + "): ");
            userInput = scanner.nextInt();     // Get an input from user
            scanner.nextLine();

            if (userInput < lower || userInput > upper)
                System.out.println("Please choose a number between " + lower + " and " + upper + "!");
        } while (userInput < lower || userInput > upper);
        System.out.println();

        return userInput;
    }

    public void showMenu() {
        System.out.println("\n:::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println("1. Load a log file");
        System.out.println("2. Display all machines having any events");
        System.out.println("3. Display all failed events across all machines");
        System.out.println("4. Generate a text file report for all events");
        System.out.println("5. Exit");
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }

    public void processMenuOption(int menuOption) {
        switch (menuOption) {
            case 1:
                loadLogData();
                break;
            case 2:
                //System.out.println("2. Displaying all machines having any events.....");
                //System.out.println();
                displayAllMachines();
                break;
            case 3:
                System.out.println("3. Displaying all failed events across all machines.....");
                System.out.println();
                break;
            case 4:
                System.out.println("4. Generating a text file report for all events....");
                System.out.println();
                break;
            case 5:
                System.out.println("5. Okay! The system will be closed! Thank you. Bye!");
                programStillRunning = false;
                break;
        }
    }


    public void loadLogData() {

        String fileName;   // File Name from user input
        Scanner fileScanner = null;   // Load a file
        String timestamp, nameOfMachine, typeOfEvents, additionalInfo, actionResults;

        // Get a name of a log file from users
        System.out.print("Enter a name of the log file you want to load (e.g. logfile1.log ): ");
        fileName = scanner.next();
        System.out.println("Name of the File: " + fileName);

        try {

            // Load a log file
            //File file = new File("/Users/hyejung/Google Drive/Hobby - Computing/Programming/Java/ICT711/Assignment/src/Data/" + fileName);
            //LogFileArrayList file = TxtFile.read("/Users/hyejung/Google Drive/Hobby - Computing/Programming/Java/ICT711/Assignment/src/Data/" + fileName);

            // 여기 하는 중: 텍스트파일을 어레이 리스트로 읽어와야함 ㅠㅠㅠ

            // Load a log file
            File file = new File("/Users/hyejung/Google Drive/Hobby - Computing/Programming/Java/ICT711/Assignment/src/Data/" + fileName);
            fileScanner = new Scanner(file);
            int numberOfLines = fileScanner.nextInt();

            for (int i = 1; i == numberOfLines; i++) {

                String line = fileScanner.nextLine();   // Get an input of each line from a log file
                logData.put(i, line); // Save data into HashMap  -> Format: (LineNumber, Contents)

                String[] logFileContents = line.split(" ");  // Split each line into an array
                timestamp = logFileContents[0];      // Save each data into a correct position
                nameOfMachine = logFileContents[1];  // Save each data into a correct position
                typeOfEvents = logFileContents[2];   // Save each data into a correct position
                additionalInfo = logFileContents[3]; // Save each data into a correct position
                actionResults = logFileContents[4];  // Save each data into a correct position
                logFileArrayList.add(new LogFileData(timestamp, nameOfMachine, typeOfEvents, additionalInfo, actionResults));

            }
            /*
            while (fileScanner.hasNextLine()) {
            <or>
            for (int i = 0; i < numberOfLines; i++) {
                String line = fileScanner.nextLine();
                //String[] eachLine = line.split(" ");
                System.out.println(line);
            }
            */
        } catch (IOException e) {   // FileNotFoundException e   --> e.printStackTrace();
            System.out.println("\nThe file name '" + fileName +"' is an invalid name. Please start again.");
            //e.printStackTrace();
            loadLogData();
        }
        fileScanner.close();

    }
/*
    public void loadLogData() {
        //System.out.println("1111. Loading a log file....");

        String fileName;   // File Name from user input
        Scanner fileScanner = null;   // Load a file
        String timestamp, nameOfMachine, typeOfEvents, additionalInfo, actionResults;

        try {
            // Get a name of a log file from users
            System.out.print("Enter a name of the log file you want to load (e.g. <logfile1.log>): ");
            fileName = scanner.next();
            System.out.println("Name of the File: " + fileName);

            // Load a log file
            File file = new File("/Users/hyejung/Google Drive/Hobby - Computing/Programming/Java/ICT711/Assignment/src/Data/" + fileName);
            fileScanner = new Scanner(file);
            int numberOfLines = fileScanner.nextInt();


            //while ((line = fileScanner.nextLine()) != null) {
            for (int i = 0; i < numberOfLines; i++) {

                        String line = fileScanner.nextLine();
                        String[] eachLine = line.split(" ");

                        System.out.println(line);

                        LogDB logDB = new LogDB();
                /*
                        // Timestamp is not recorded
                        logDB.setNameOfMachine(eachLine[1]);
                        logDB.setTypeOfEvents(eachLine[2]);
                        logDB.setAdditionalInfo(eachLine[3]);
                        logDB.setActionResults(eachLine[4]);
            }
        } catch (IOException e) {
            System.out.println("\nIt's an invalid name. Please start again.");
            //e.printStackTrace();
            loadLogData();
        }
    }
*/

    // Display all machines having any events
    public void displayAllMachines() {

        //Iterator <String> keys = logData.keySet().iterator();    // Set an iterator

        System.out.println(LogFileData());
        //for (int i = 0; i < loadLogData().length; i++) {
        //    System.out.println(logFileArrayList);
        //}
    }

}









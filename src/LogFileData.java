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
 */

import java.util.ArrayList;
import java.util.List;

public class LogFileData {

    private String timeStamp;
    private String nameOfMachine;
    private String typeOfEvents;
    private String additionalInfo;
    private String actionResults;

    private static ArrayList<LogFileData> allLogs = null;

    public LogFileData() {
        this.timeStamp = "";
        this.nameOfMachine = "";
        this.typeOfEvents = "";
        this.additionalInfo = "";
        this.actionResults = "";
    }

    public LogFileData(String timeStamp, String nameOfMachine, String typeOfEvents, String additionalInfo, String results) {
        this.timeStamp = timeStamp;
        this.nameOfMachine = nameOfMachine;
        this.typeOfEvents = typeOfEvents;
        this.additionalInfo = additionalInfo;
        this.actionResults = results;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNameOfMachine() {
        return nameOfMachine;
    }

    public void setNameOfMachine(String nameOfMachine) {
        this.nameOfMachine = nameOfMachine;
    }

    public String getTypeOfEvents() {
        return typeOfEvents;
    }

    public void setTypeOfEvents(String typeOfEvents) {
        this.typeOfEvents = typeOfEvents;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getActionResults() {
        return actionResults;
    }

    public void setActionResults(String actionResults) {
        this.actionResults = actionResults;
    }

    public static List<LogFileData> getAllLogFileData () {

        // need to work here

        return (List<LogFileData>) allLogs.clone() ;

    }




    @Override
    public String toString() {
        return "LogDB: " +
                "Timestamp: '" + timeStamp + '\'' +
                ", Name Of Machine: '" + nameOfMachine + '\'' +
                ", Type Of Events: '" + typeOfEvents + '\'' +
                ", Additional Information: '" + additionalInfo + '\'' +
                ", Action Results: '" + actionResults + '\''
                ;
    }
}

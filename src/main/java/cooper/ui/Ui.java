package cooper.ui;

import cooper.finance.FinanceManager;
import cooper.verification.UserRole;

import javax.sound.sampled.Line;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Ui {

    private static final String LOGO = "            /$$$$$$   /$$$$$$  /$$$$$$$                    \n"
            +                  "           /$$__  $$ /$$__  $$| $$__  $$                   \n"
            +                  "  /$$$$$$$| $$  \\ $$| $$  \\ $$| $$  \\ $$ /$$$$$$   /$$$$$$ \n"
            +                  " /$$_____/| $$  | $$| $$  | $$| $$$$$$$//$$__  $$ /$$__  $$\n"
            +                  "| $$      | $$  | $$| $$  | $$| $$____/| $$$$$$$$| $$  \\__/\n"
            +                  "| $$      | $$  | $$| $$  | $$| $$     | $$_____/| $$      \n"
            +                  "|  $$$$$$$|  $$$$$$/|  $$$$$$/| $$     |  $$$$$$$| $$      \n"
            +                  " \\_______/ \\______/  \\______/ |__/      \\_______/|__/      ";

    private static final String LINE = "=========================================================================";
    private static final String TABLE_LINE = "-----------------------------------";

    private static final String GREETING = "Hello I'm cOOPer! Nice to meet you!";

    private static final Scanner scanner = new Scanner(System.in);
    private static final PrintStream printStream = System.out;


    public static String getInput() {
        showPrompt();
        return scanner.nextLine();
    }

    public static void showLogo() {
        show(LOGO);
    }

    public static void showIntroduction() {
        showGreetingMessage();
        showLoginRegisterMessage();
    }

    private static void showGreetingMessage() {
        show(LINE);
        show(GREETING);
        show(LINE);
    }

    private static void showLoginRegisterMessage() {
        show("Login or register to gain access to my features!");
        show("To login, enter \"login  [yourUsername] as [yourRole]\"");
        show("To register, enter \"register [yourUsername] as [yourRole]\"");
        show(LINE);
    }

    public static void showPleaseRegisterMessage() {
        show(LINE);
        show("Your username does not exist, please register!");
        show(LINE);
    }

    public static void showPleaseLoginMessage() {
        show(LINE);
        show("Your username already exists, please login!");
        show(LINE);
    }

    public static void showRegisteredSuccessfullyMessage(String username, UserRole userRole) {
        String userRoleAsString = (userRole == UserRole.ADMIN) ? "admin" : "employee";
        show(LINE);
        show(username + " is now successfully registered as an " + userRoleAsString + "!");
        show(LINE);
    }

    public static void showLoggedInSuccessfullyMessage(String username) {
        show(LINE);
        show("You are now logged in successfully as " + username + "!");
        show(LINE);
    }

    public static void showIncorrectRoleMessage() {
        show(LINE);
        show("You are logging in with an incorrect role! Please try again.");
        show(LINE);
    }

    public static void showInvalidUserRoleError() {
        show(LINE);
        show("Invalid role entered! Role can only be admin or employee.");
        show(LINE);
    }

    public static void showText(String text) {
        show(text);
    }

    /**
     * Exception message to show file path error.
     **/
    public static void showInvalidFilePathError() {
        show(LINE);
        show("Parser received invalid input file path!");
        show(LINE);
    }

    /**
     * Exception message to show invalid command error.
     **/
    public static void showUnrecognisedCommandError() {
        show(LINE);
        show("I don't recognise the command you entered. Enter \"help\" to view available commands.");
        show(LINE);
    }

    /**
     * Exception message to show invalid command argument error.
     **/
    public static void showInvalidCommandArgumentError() {
        show(LINE);
        show("You have keyed in invalid command arguments.");
        show(LINE);
    }

    /**
     * Exception message to show invalid number (such as task id) error.
     **/
    public static void showInvalidNumberError() {
        show(LINE);
        show("Unrecognised number.");
        show(LINE);
    }

    public static void showDuplicateUsernameError() {
        show(LINE);
        show("The username has already been entered under that timeslot.");
        show(LINE);
    }

    public static void showBye() {
        show(LINE);
        show("Bye, see you next time! :D");
        show(LINE);
    }

    public static void showPrompt() {
        printStream.print(">> ");
    }

    /**
     * Close streams properly.
     */
    public static void closeStreams() {
        scanner.close();
        printStream.close();
    }

    private static void show(String printMessage) {
        printStream.println(printMessage);
    }

    public static void printBalanceSheet(ArrayList<Integer> balanceSheet) {
        show(LINE);
        show("This is the company's current Balance Sheet:");
        for (int i = 0; i < balanceSheet.size(); i++) {
            show(i + 1 + ". " + balanceSheet.get(i));
        }
        show(LINE);
    }

    public static void printAddCommand(String amount, boolean isInflow) {
        show(LINE);
        show("Success!");
        show("Amount: " + (isInflow ? "+" : "-") + amount + " has been added to the Balance Sheet.");
        show(LINE);
    }

    public static void printAvailableCommand(String time, String username) {
        show(LINE);
        show("Success!");
        show(username + "'s availability has been added to " + time);
        show(LINE);
    }

    public static void printAvailabilities(HashMap<String, ArrayList<String>> meetings) {
        Ui.printMeetingTableHeader();
        for (String timing: meetings.keySet()) {
            Ui.showText(timing + " | " + listOfAttendees(meetings.get(timing)));
        }
    }

    public static String listOfAttendees(ArrayList<String> attendees) {
        StringBuilder listOfAttendees = new StringBuilder("");
        for (String attendee : attendees) {
            /* don't need comma for last attendee */
            int indexOfLastAttendee = attendees.size() - 1;
            if (attendee.equals(attendees.get(indexOfLastAttendee))) {
                listOfAttendees.append(attendee);
            } else {
                listOfAttendees.append(attendee).append(", ");
            }
        }
        return String.valueOf(listOfAttendees);
    }

    public static void printMeetingTableHeader() {
        show(LINE);
        show("These are the availabilities:");
        show(TABLE_LINE);
    }

    public static void printAdminHelp() {
        show(LINE);
        show("Here are the commands available to an admin along with their formats:");
        show("add       | add [amount]");
        show("list      | list");
    }

    public static void printEmployeeHelp() {
        show(LINE);
        show("Here are the commands available to an employee along with their formats:");
    }

    public static void printGeneralHelp() {
        show("available | available [yourUsername] at [availableTime]");
        show("meetings  | meetings");
        show(LINE);
    }
}
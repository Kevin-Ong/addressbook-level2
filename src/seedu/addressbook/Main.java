package seedu.addressbook;

import static seedu.addressbook.TextUi.*;
import static seedu.addressbook.storage.StorageFile.*;

import seedu.addressbook.commands.*;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.storage.StorageFile;

import java.io.*;


/**
 * Entry point of the address book application.
 * Initializes the application and starts the interaction with the user.
 */
public class Main {

    /**
     * Default file path used if the user doesn't provide the file name.
     */
    public static final String DEFAULT_STORAGE_FILEPATH = "addressbook.txt";

    /**
     * These message is not in the TextUi class because program launch and initialisation messages may be shown
     * outside the given UI (should be logged at launch console), and needs to be shown before the UI is ready.
     */
    public static final String MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE = "Launch arguments format: [STORAGE_FILE_PATH]";
    public static final String MESSAGE_INIT_FAILED = "Failed to initialise address book application. Exiting...";

    /**
     * Version info of the program.
     */
    public static final String VERSION = "AddessBook Level 1 - Version 1.0";


    /**
     * Signals that the main application had a problem while initialising.
     */
    public static class MainInitialisationException extends Exception {}

    private final TextUi ui;
    private final Parser parser;
    private final StorageFile storageFile;
    private final AddressBook addressBook;

    /**
     * Sets up the different components and loads up the model from the storage file.
     *
     * @param storageFilePath file path of the desired storage file
     * @param inputStream user text input source
     * @param outputStream user text output acceptor
     *
     * @throws MainInitialisationException if there were problems
     */
    public Main(String storageFilePath, InputStream inputStream, PrintStream outputStream)
            throws MainInitialisationException {
        this.ui = new TextUi(inputStream, outputStream);
        this.parser = new Parser();
        try {
            this.storageFile = new StorageFile(storageFilePath);
            this.addressBook = storageFile.loadAddressBookFromFile();
        } catch (InvalidStorageFilePathException | StorageOperationException e) {
            ui.showToUser(e.getMessage());
            throw new MainInitialisationException();
        }
    }

    /**
     * Entry point.
     */
    public static void main(String... launchArgs) {
        System.out.println(MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE);
        try {
            final Main main = new Main(getStorageFilePathFromLaunchArgs(launchArgs), System.in, System.out);
            main.start();
        } catch (MainInitialisationException mie) {
            System.out.println(MESSAGE_INIT_FAILED);
        }
    }

    /**
     * Retrieves the string representing the intended storage file path as specified in the launch args.
     * Defaults to {@link #DEFAULT_STORAGE_FILEPATH} if no storage file argument is found.
     * 
     * @param launchArgs full program launch arguments passed to application main method
     */
    private static String getStorageFilePathFromLaunchArgs(String... launchArgs) {
        return launchArgs.length > 0 ? launchArgs[0] : DEFAULT_STORAGE_FILEPATH;
    }

    /**
     * Displays the goodbye message and exits the runtime.
     */
    private void exitProgram() {
        Command exit = new ExitCommand();
        exit.injectDependencies(ui, addressBook);
        exit.execute();
    }

    /*
     * ==============NOTE TO STUDENTS======================================
     * Notice how this method solves the whole problem at a very high level.
     * We can understand the high-level logic of the program by reading this
     * method alone.
     * ====================================================================
     */

    /**
     * Starts program execution after all dependencies and components successfully initialised.
     */
    public void start() {
        ui.showWelcomeMessage(VERSION);
        ui.showToUser(String.format(MESSAGE_USING_STORAGE_FILE, storageFile.toString()));
        // [read input, execute, response] loop
        while (true) {
            String userCommand = ui.getUserCommand();
            ui.echoLastEnteredUserCommand();
            String feedback = executeCommand(userCommand);
            ui.showResultToUser(feedback);
        }
    }

    /**
     * Processes user input into desired command, then executes and returns feedback.
     * 
     * @param userInputString raw input from user
     * @return feedback about how the command was executed
     */
    private String executeCommand(String userInputString) {
        try {
            final Command command = parser.parseCommand(userInputString);
            command.injectDependencies(ui, addressBook);
            final String result = command.execute();
            saveChangesToStorageFile();
            return result;
        } catch (Parser.ParseException pe) {
            return pe.getMessage();
        }
    }

    /**
     * Saves any changes to the storage file. Exits program if an error is encountered.
     */
    private void saveChangesToStorageFile() {
        try {
            storageFile.saveAddressBookToFile(addressBook);
        } catch (StorageOperationException soe) {
            ui.showToUser(soe.getMessage());
            exitProgram();
        }
    }


}
package dill.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import dill.command.Command;
import dill.command.ListCommand;
import dill.command.AddCommand;
import dill.command.DeleteCommand;
import dill.command.MarkCommand;
import dill.command.UnmarkCommand;
import dill.command.HelpCommand;
import dill.command.ExitCommand;
import dill.task.Deadline;
import dill.task.Event;
import dill.task.ToDo;
import dill.exception.InvalidCommandException;

/**
 * Represents the user input interpreter of Dill.
 */
public class Parser {
    /**
     * Creates an instance of Parser.
     */
    public Parser() {}

    /**
     * Deciphers raw user input and converts them into Commands.
     * Handles syntax validation and parameter extraction.
     *
     * @param userInput The raw string input of the user.
     * @return A specific command that can be executed by Dill.
     * @throws InvalidCommandException If the input is unrecognizable or missing parameters.
     */
    public static Command parse(String userInput) throws InvalidCommandException {
        if (userInput.equals("bye")) {
            return new ExitCommand();
        }
        if (userInput.equals("list")) {
            return new ListCommand();
        }
        if (userInput.equals("help")) {
            return new HelpCommand();
        }
        if (userInput.startsWith("mark")) {
            return validateMark(userInput);
        }
        if (userInput.startsWith("unmark")) {
            return validateUnmark(userInput);
        }
        if (userInput.startsWith("todo")) {
            return validateToDo(userInput);
        }
        if (userInput.startsWith("deadline")) {
            return validateDeadline(userInput);
        }
        if (userInput.startsWith("event")) {
            return validateEvent(userInput);
        }
        if (userInput.startsWith("delete")) {
            return validateDelete(userInput);
        }
        throw new InvalidCommandException("    I'm not quite sure what you meant.\n" +
                                          "    Type \"help\" if you wish to view a list of available commands.");
    }

    private static Command validateMark(String userInput) throws InvalidCommandException {
        if (!userInput.startsWith("mark ") || userInput.equals("mark ")) {
            throw new InvalidCommandException("    Please specify an entry to mark.");
        }
        try {
            int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("    Entry must be an integer!");
        }
    }

    private static Command validateUnmark(String userInput) throws InvalidCommandException {
        if (!userInput.startsWith("unmark ") || userInput.equals("unmark ")) {
            throw new InvalidCommandException("    Please specify an entry to unmark.");
        }
        try {
            int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("    Entry must be an integer!");
        }
    }

    private static Command validateToDo(String userInput) throws InvalidCommandException {
        if (!userInput.startsWith("todo ") || userInput.equals("todo ")) {
            throw new InvalidCommandException("    Please specify todo task name.");
        }
        String taskName = userInput.substring(5);
        return new AddCommand(new ToDo(taskName));
    }

    private static Command validateDeadline(String userInput) throws InvalidCommandException {
        int byIndex = userInput.indexOf("/by ");
        if (!userInput.startsWith("deadline ") || userInput.equals("deadline ") || byIndex == 9) {
            throw new InvalidCommandException("    Please specify deadline task name.");
        }
        if (byIndex < 0) {
            throw new InvalidCommandException("    Please specify a deadline.");
        }
        try {
            String taskName = userInput.substring(9, byIndex - 1);
            String rawDate = userInput.substring(byIndex + 4);
            LocalDate date = LocalDate.parse(rawDate);
            return new AddCommand(new Deadline(taskName, date));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("    Please enter dates in the format yyyy-mm-dd.");
        }
    }

    private static Command validateEvent(String userInput) throws InvalidCommandException {
        int startIndex = userInput.indexOf("/start ");
        int endIndex = userInput.indexOf("/end ");
        if (!userInput.startsWith("event ") || userInput.equals("event ") || startIndex == 6 || endIndex == 6) {
            throw new InvalidCommandException("    Please specify event task name.");
        }
        if (startIndex < 0) {
            throw new InvalidCommandException("    Please specify a start time.");
        }
        if (endIndex < 0) {
            throw new InvalidCommandException("    Please specify an end time.");
        }
        if (endIndex < startIndex) {
            throw new InvalidCommandException("    Please specify start time followed by end time, in that order.");
        }
        try {
            String taskName = userInput.substring(6, userInput.indexOf("/start") - 1);
            String rawStartDate = userInput.substring(userInput.indexOf("/start") + 7, userInput.indexOf("/end") - 1);
            String rawEndDate = userInput.substring(userInput.indexOf("/end") + 5);
            LocalDate startDate = LocalDate.parse(rawStartDate);
            LocalDate endDate = LocalDate.parse(rawEndDate);
            return new AddCommand(new Event(taskName, startDate, endDate));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("    Please enter dates in the format yyyy-mm-dd.");
        }
    }

    private static Command validateDelete(String userInput) throws InvalidCommandException {
        if (!userInput.startsWith("delete ") || userInput.equals("delete ")) {
            throw new InvalidCommandException("    Please specify an entry to delete.");
        }
        try {
            int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("    Entry must be an integer!");
        }
    }
}

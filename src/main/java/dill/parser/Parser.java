package dill.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dill.command.AddCommand;
import dill.command.CheerCommand;
import dill.command.Command;
import dill.command.DeleteCommand;
import dill.command.ExitCommand;
import dill.command.FindCommand;
import dill.command.HelpCommand;
import dill.command.ListCommand;
import dill.command.MarkCommand;
import dill.command.UnmarkCommand;
import dill.exception.InvalidCommandException;
import dill.task.Deadline;
import dill.task.Event;
import dill.task.ToDo;

/**
 * Represents the user input interpreter of Dill.
 */
public class Parser {
    private static final Pattern DEADLINE_ARGS_FORMAT = Pattern.compile("(.+)\\s+/by\\s+(.+)");
    private static final Pattern EVENT_ARGS_FORMAT = Pattern.compile("(.+)\\s+/start\\s+(.+?)\\s+/end\\s+(.+)");

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
        String[] inputParts = userInput.trim().split("\\s+", 2); // Split into command and arguments
        String cmd = inputParts[0];
        String args = inputParts.length == 2 ? inputParts[1] : ""; // Input args could be empty

        switch (cmd) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        case "cheer":
            return new CheerCommand();
        case "mark":
            return validateMark(args);
        case "unmark":
            return validateUnmark(args);
        case "delete":
            return validateDelete(args);
        case "todo":
            return validateToDo(args);
        case "deadline":
            return validateDeadline(args);
        case "event":
            return validateEvent(args);
        case "find":
            return validateFind(args);
        default:
            throw new InvalidCommandException("I'm not quite sure what you meant.\n"
                    + "Type \"help\" if you wish to view a list of available commands.");
        }
    }

    private static Command validateMark(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Please specify an entry to mark.");
        }
        try {
            int taskIndex = Integer.parseInt(args) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Entry number must be an integer!");
        }
    }

    private static Command validateUnmark(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Please specify an entry to unmark.");
        }
        try {
            int taskIndex = Integer.parseInt(args) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Entry number must be an integer!");
        }
    }

    private static Command validateDelete(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Please specify an entry to delete.");
        }
        try {
            int taskIndex = Integer.parseInt(args) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Entry number must be an integer!");
        }
    }

    private static Command validateToDo(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Please specify a todo task in the format todo <task-name>");
        }
        return new AddCommand(new ToDo(args));
    }

    private static Command validateDeadline(String args) throws InvalidCommandException {
        Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(args);
        if (!matcher.matches()) {
            throw new InvalidCommandException("Please specify a deadline task in the format "
                    + "deadline <task-name> /by <yyyy-mm-dd>");
        }
        try {
            String taskName = matcher.group(1);
            String rawDate = matcher.group(2);
            LocalDate date = LocalDate.parse(rawDate);
            return new AddCommand(new Deadline(taskName, date));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Please enter dates in the format yyyy-mm-dd.");
        }
    }

    private static Command validateEvent(String args) throws InvalidCommandException {
        Matcher matcher = EVENT_ARGS_FORMAT.matcher(args);
        if (!matcher.matches()) {
            throw new InvalidCommandException("Please specify an event task in the format "
                    + "event <task-name> /start <yyyy-mm-dd> /end <yyyy-mm-dd>");
        }
        try {
            String taskName = matcher.group(1);
            String rawStartDate = matcher.group(2);
            String rawEndDate = matcher.group(3);
            LocalDate startDate = LocalDate.parse(rawStartDate);
            LocalDate endDate = LocalDate.parse(rawEndDate);
            if (startDate.isAfter(endDate)) {
                throw new InvalidCommandException("Start date cannot be after the end date!");
            }
            return new AddCommand(new Event(taskName, startDate, endDate));
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Please enter dates in the format yyyy-mm-dd.");
        }
    }

    private static Command validateFind(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Please specify a keyword for matching.");
        }
        return new FindCommand(args);
    }
}

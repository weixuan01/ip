package dill.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dill.command.AddCommand;
import dill.command.CheerCommand;
import dill.command.CloneCommand;
import dill.command.Command;
import dill.command.DeleteCommand;
import dill.command.ExitCommand;
import dill.command.FindCommand;
import dill.command.HelpCommand;
import dill.command.ListCommand;
import dill.command.MarkCommand;
import dill.command.UnmarkCommand;
import dill.command.UpdateCommand;
import dill.command.ViewCommand;
import dill.exception.InvalidCommandException;
import dill.task.Deadline;
import dill.task.Event;
import dill.task.ToDo;
import dill.task.UpdateFields;

/**
 * Represents the user input interpreter of Dill.
 */
public class Parser {
    private static final Pattern DEADLINE_ARGS_FORMAT = Pattern.compile("(.+?)\\s+/by\\s+(.+)");
    private static final Pattern EVENT_ARGS_FORMAT = Pattern.compile("(.+?)\\s+/start\\s+(.+?)\\s+/end\\s+(.+)");
    private static final Pattern UPDATE_ARGS_FORMAT = Pattern.compile("(/.+?)\\s+(.+?)(?=\\s+(/.+?)\\s+(.+)|$)");
    private static final int INDEX_COMMAND = 0;
    private static final int INDEX_ARGS = 1;
    private static final int PARSE_MAX_PARTS = 2;
    private static final int UPDATE_MAX_PARTS = 2;
    private static final int UPDATE_INDEX_TASKID = 0;
    private static final int UPDATE_INDEX_ARGS = 1;


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
        assert userInput != null : "User input should not be null";
        String[] inputParts = userInput.trim().split("\\s+", PARSE_MAX_PARTS); // Split into command and arguments
        String cmd = inputParts[INDEX_COMMAND];
        String args = inputParts.length == PARSE_MAX_PARTS ? inputParts[INDEX_ARGS] : ""; // Input args could be empty

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
        case "view":
            return validateView(args);
        case "update":
            return validateUpdate(args);
        case "clone":
            return validateClone(args);
        default:
            throw new InvalidCommandException("I'm not quite sure what you meant.\n"
                    + "Type \"help\" if you wish to view a list of available commands.");
        }
    }

    private static Command validateMark(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Please specify a task id to mark.");
        }
        int taskIndex = parseTaskId(args) - 1;
        return new MarkCommand(taskIndex);
    }

    private static Command validateUnmark(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Please specify a task id to unmark.");
        }
        int taskIndex = parseTaskId(args) - 1;
        return new UnmarkCommand(taskIndex);
    }

    private static Command validateDelete(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Please specify a task id to delete.");
        }
        int taskIndex = parseTaskId(args) - 1;
        return new DeleteCommand(taskIndex);
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
        assert matcher.groupCount() == 3 : "Matcher should have 2 groups";
        String taskName = matcher.group(1);
        String rawDate = matcher.group(2);
        LocalDate date = parseDate(rawDate);
        return new AddCommand(new Deadline(taskName, date));
    }

    private static Command validateEvent(String args) throws InvalidCommandException {
        Matcher matcher = EVENT_ARGS_FORMAT.matcher(args);
        if (!matcher.matches()) {
            throw new InvalidCommandException("Please specify an event task in the format "
                    + "event <task-name> /start <yyyy-mm-dd> /end <yyyy-mm-dd>");
        }
        assert matcher.groupCount() == 3 : "Matcher should have 3 groups";
        String taskName = matcher.group(1);
        String rawStartDate = matcher.group(2);
        String rawEndDate = matcher.group(3);
        LocalDate startDate = parseDate(rawStartDate);
        LocalDate endDate = parseDate(rawEndDate);
        if (startDate.isAfter(endDate)) {
            throw new InvalidCommandException("Start date cannot be after the end date!");
        }
        return new AddCommand(new Event(taskName, startDate, endDate));
    }

    private static Command validateFind(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Please specify a keyword for matching.");
        }
        return new FindCommand(args);
    }

    private static Command validateView(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Please specify a date to view.");
        }
        LocalDate date = parseDate(args);
        return new ViewCommand(date);
    }

    private static Command validateUpdate(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Please specify a task id to update.");
        }

        String[] updateParts = args.split("\\s+", UPDATE_MAX_PARTS);
        if (updateParts.length == 1) {
            throw new InvalidCommandException(
                    "Please specify the fields to update using a flag followed by a value e.g., /by 2026-03-14");
        }

        int taskIndex = parseTaskId(updateParts[UPDATE_INDEX_TASKID]) - 1;
        UpdateFields updateFields = new UpdateFields();

        String updateArgs = updateParts[UPDATE_INDEX_ARGS];
        Matcher matcher = UPDATE_ARGS_FORMAT.matcher(updateArgs);
        while (matcher.find()) {
            String flag = matcher.group(1);
            String value = matcher.group(2);
            setUpdateFields(updateFields, flag, value);
        }
        if (updateFields.isEmpty()) {
            throw new InvalidCommandException(
                    "Please specify a new value for this task field e.g., /by 2026-03-14");
        }
        return new UpdateCommand(taskIndex, updateFields);
    }

    private static Command validateClone(String args) throws InvalidCommandException {
        if (args.isEmpty()) {
            throw new InvalidCommandException("Please specify a task id to clone.");
        }
        int taskIndex = parseTaskId(args) - 1;
        return new CloneCommand(taskIndex);
    }

    private static int parseTaskId(String taskId) throws InvalidCommandException {
        try {
            return Integer.parseInt(taskId);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Task id must be an integer!");
        }
    }

    private static LocalDate parseDate(String date) throws InvalidCommandException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Please specify dates in the format yyyy-mm-dd.");
        }
    }

    private static void setUpdateFields(
            UpdateFields updateFields, String flag, String value) throws InvalidCommandException {
        switch (flag) {
        case "/name":
            updateFields.setTaskName(value);
            break;
        case "/by":
            updateFields.setByDate(parseDate(value));
            break;
        case "/start":
            updateFields.setStartDate(parseDate(value));
            break;
        case "/end":
            updateFields.setEndDate(parseDate(value));
            break;
        default:
            throw new InvalidCommandException("These flags are currently supported: /name, /by, /start, /end");
        }
    }
}

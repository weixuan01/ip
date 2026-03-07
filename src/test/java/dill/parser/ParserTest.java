package dill.parser;

import dill.command.*;

import dill.exception.InvalidCommandException;
import dill.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    // Test mark command with valid syntax
    @Test
    void parseMarkValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("mark 2"));
        assertEquals(MarkCommand.class, cmd.getClass());
    }

    // Test mark command with no task id
    @Test
    void parseMarkNoTaskId() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("mark "));
        assertEquals("Whoops! You forgot to give me a task id to mark. (e.g., mark 3)", e.getMessage());
    }

    // Test mark command with non-integer task id
    @Test
    void parseMarkNonInteger() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("mark x"));
        assertEquals("Hold on! The task id must be an integer!", e.getMessage());
    }

    // Test unmark command with valid syntax
    @Test
    void parseUnmarkValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("unmark 2"));
        assertEquals(UnmarkCommand.class, cmd.getClass());
    }

    // Test unmark command with no task id
    @Test
    void parseUnmarkNoTaskId() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("unmark "));
        assertEquals("Whoops! You forgot to give me a task id to unmark. (e.g., unmark 3)", e.getMessage());
    }

    // Test unmark command with non-integer task id
    @Test
    void parseUnmarkNonInteger() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("unmark x"));
        assertEquals("Hold on! The task id must be an integer!", e.getMessage());
    }

    // Test bye command with valid syntax
    @Test
    void parseByeValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("bye"));
        assertEquals(ExitCommand.class, cmd.getClass());
    }

    // Test bye command with invalid syntax
    @Test
    void parseByeInvalid() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("bye x"));
        assertEquals("No need for long goodbyes! Just type \"bye\" and you are set.", e.getMessage());
    }

    // Test list command with valid syntax
    @Test
    void parseListValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("list"));
        assertEquals(ListCommand.class, cmd.getClass());
    }

    // Test list command with invalid syntax
    @Test
    void parseListInvalid() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("list x"));
        assertEquals("Oops, I do not quite understand that! Did you mean just \"list\"?", e.getMessage());
    }

    // Test help command with valid syntax
    @Test
    void parseHelpValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("help"));
        assertEquals(HelpCommand.class, cmd.getClass());
    }

    // Test help command with invalid syntax
    @Test
    void parseHelpInvalid() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("help x"));
        assertEquals("Oops, I do not quite understand that! Did you mean just \"help\"?", e.getMessage());
    }

    // Test cheer command with valid syntax
    @Test
    void parseCheerValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("cheer"));
        assertEquals(CheerCommand.class, cmd.getClass());
    }

    // Test cheer command with invalid syntax
    @Test
    void parseCheerInvalid() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("cheer x"));
        assertEquals("Oops, I do not quite understand that! Did you mean just \"cheer\"?", e.getMessage());
    }

    // Test todo command with invalid syntax (empty task name)
    @Test
    void parseToDoNoTaskName() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("todo"));
        assertEquals("Wait, what are we doing? Please tell me a task name! (e.g., todo study)"
                , e.getMessage());
    }

    // Test find command with valid syntax
    @Test
    void parseFindValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("find assignment"));
        assertEquals(FindCommand.class, cmd.getClass());
        assertEquals("assignment", ((FindCommand)cmd).getKeyword());
    }

    // Test find command with invalid syntax (empty keyword)
    @Test
    void parseFindNoKeyword() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("find "));
        assertEquals("What are we looking for? Please give me a keyword to search!", e.getMessage());
    }

    // Test view command with valid syntax
    @Test
    void parseViewValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("view 2026-03-14"));
        assertEquals(ViewCommand.class, cmd.getClass());
        assertEquals(LocalDate.parse("2026-03-14"), ((ViewCommand)cmd).getDate());
    }

    // Test view command with invalid syntax (empty date)
    @Test
    void parseViewNoDate() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("view "));
        assertEquals("Which date are we looking at? Please give me a date!", e.getMessage());
    }

    // Test view command with invalid syntax (invalid date)
    @Test
    void parseViewInvalidDate() {
        InvalidCommandException e = assertThrows(
                InvalidCommandException.class, () -> Parser.parse("view x"));
        assertEquals("Uh oh, I cannot read that date format! Please use: yyyy-mm-dd.", e.getMessage());
    }

    // Test todo command with valid syntax
    @Test
    void parseToDoValid() {
        Command cmd = assertDoesNotThrow(() -> Parser.parse("todo myname"));
        assertEquals(AddCommand.class, cmd.getClass());
        assertEquals(ToDo.class, ((AddCommand)cmd).getTask().getClass());
    }
}

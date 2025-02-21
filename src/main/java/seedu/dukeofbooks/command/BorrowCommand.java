package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.LoanController;
import seedu.dukeofbooks.data.exception.DuplicateActionException;
import seedu.dukeofbooks.data.person.Person;
import seedu.dukeofbooks.data.loan.LoanRecords;
import seedu.dukeofbooks.data.book.BorrowableItem;

import java.time.LocalDateTime;

public final class BorrowCommand extends LoanCommand {
    public static final String COMMAND_WORD = "borrow";
    public static final String SUCCESS_MSG = "Borrow is successful.";
    public static final String FAIL_MSG = "This item is not borrowable.";
    public static final String ERROR_MSG = "Cannot borrow: %s";
    public static final String NOT_FOUND_MSG = "Item not found!";
    public static final String INVALID_SYNTAX = "Expected: borrow -title TITLE";
    private final Person person;
    private final BorrowableItem item;

    public BorrowCommand(LoanRecords loanRecords,Person person, BorrowableItem item) {
        super(loanRecords);
        this.person = person;
        this.item = item;
    }

    @Override
    public CommandResult execute() {
        assert person != null;
        if (item == null) {
            return new CommandResult(NOT_FOUND_MSG);
        }
        if (item.isBorrowed()) {
            return new CommandResult(FAIL_MSG);
        }
        LocalDateTime borrowTime = LocalDateTime.now();
        try {
            LoanController.borrowItem(loanRecords, person, item, borrowTime);
        } catch (DuplicateActionException dae) {
            return new CommandResult(String.format(ERROR_MSG, dae.getMessage()));
        }
        return new CommandResult(SUCCESS_MSG);
    }
}

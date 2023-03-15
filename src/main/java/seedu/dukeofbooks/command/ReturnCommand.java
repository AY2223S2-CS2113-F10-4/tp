package seedu.dukeofbooks.command;

import seedu.dukeofbooks.controller.LoanController;
import seedu.dukeofbooks.data.exception.LoanRecordNotFoundException;
import seedu.dukeofbooks.data.loan.LoanRecords;
import seedu.dukeofbooks.data.person.Person;
import seedu.dukeofbooks.data.book.BorrowableItem;

public final class ReturnCommand extends LoanCommand {
    public static final String COMMAND_WORD = "return";
    private static final String SUCCESS_MSG = "Item has been returned!";
    private static final String FAIL_MSG = "Item is not borrowed!";
    private static final String ERROR_MSG_F = "Cannot return item: %s";
    Person person;
    BorrowableItem item;
    private String title;
    
    public ReturnCommand(LoanRecords loanRecords, Person person, BorrowableItem item) {
        super(loanRecords);
        this.person = person;
        this.item = item;
    }

    public ReturnCommand(String title){
        this.title=title;
    }

    @Override
    public CommandResult execute() {
        if (!item.isBorrowed()) {
            return new CommandResult(FAIL_MSG);
        }
        try {
            LoanController.returnItem(loanRecords, person, item);
        } catch (LoanRecordNotFoundException e) {
            return new CommandResult(String.format(ERROR_MSG_F, e.getMessage()));
        }
        return new CommandResult(SUCCESS_MSG);
    }
}

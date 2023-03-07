package seedu.dukeofbooks.data.book;

import seedu.dukeofbooks.common.IVerifiable;

public class Isbn implements IVerifiable{
    private String isbn;
    public Isbn(String isbn) {
        setISBN(isbn);;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Isbn // instanceof handles nulls
                && this.hasSameData((Isbn) other));
    }

    private boolean hasSameData(Isbn other) {
        return isbn == other.getISBN();
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Isbn: %S", isbn);
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }
    
}

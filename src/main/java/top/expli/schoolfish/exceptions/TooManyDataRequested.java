package top.expli.schoolfish.exceptions;

public class TooManyDataRequested extends Exception {
    public TooManyDataRequested() {
        super();
    }

    public TooManyDataRequested(String message) {
        super(message);
    }
}

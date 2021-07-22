package utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class InputOutput implements AutoCloseable {
    public InputStream input;
    public OutputStream output;
    public PrintStream print;

    InputOutput(String input) throws Exception {
        this.output = new ByteArrayOutputStream();
        this.print = new PrintStream(output, true);
        this.input = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8.name()));
    }

    @Override
    public void close() throws Exception {
        this.input.close();
        this.print.close();
        this.output.close();
    }
}

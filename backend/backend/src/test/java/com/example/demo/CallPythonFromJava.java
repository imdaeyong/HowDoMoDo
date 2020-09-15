package com.example.demo;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CallPythonFromJava {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void givenPythonScript_whenPythonProcessExecuted_thenSuccess()
            throws ExecuteException, IOException {
        String line = "python " + resolvePythonScriptPath("hello.py");
        CommandLine cmdLine = CommandLine.parse(line);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);

        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(streamHandler);

        int exitCode = executor.execute(cmdLine);
        assertEquals("No errors should be detected", 0, exitCode);
        assertEquals("Should contain script output: ", "Hello SSAFY!", outputStream.toString()
                .trim());
    }

    private String resolvePythonScriptPath(String filename) {
        File file = new File("src/test/resources/" + filename);
        return file.getAbsolutePath();
    }
}

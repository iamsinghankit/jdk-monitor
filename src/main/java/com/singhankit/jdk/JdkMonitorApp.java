package com.singhankit.jdk;

import com.singhankit.jdk.cmd.JdkMonCmd;
import com.singhankit.jdk.core.Log;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @author Ankit Singh
 */
public class JdkMonitorApp {

    public static void main(String... args) {
        var commandLine = new CommandLine(new JdkMonCmd());
        commandLine.setExecutionExceptionHandler(globalExceptionHandler());
        commandLine.execute(args);
    }

    private static CommandLine.IExecutionExceptionHandler globalExceptionHandler() {
        return (ex, commandLine, parseResult) -> {
            Log.error("Something we wrong, please contact developer...");
            Log.info("""
                    Generated "jdkmon.error", please create a issue on https://github.com/iamsinghankit/jdk-monitor/issues/
                    """);
            write(Path.of("jdkmon.error"), ex);
            return 1;
        };
    }

    private static void write(Path file, Exception ex) {
        var sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        try {
            Files.write(file, sw.toString().getBytes(), StandardOpenOption.CREATE);
        } catch(IOException e) {
            Log.error("Error while generating file: " + file.getFileName() + ", switching to console.");
            Log.error(sw.toString());
        }
    }
}

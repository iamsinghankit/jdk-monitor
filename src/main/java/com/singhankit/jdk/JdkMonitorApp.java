package com.singhankit.jdk;

import com.singhankit.jdk.cmd.JdkMonCmd;
import com.singhankit.jdk.core.Log;
import picocli.CommandLine;

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
        return (ex, __, ___) -> {
            Log.error("Something we wrong, please contact developer...");
            write(ex);
            return 1;
        };
    }

    private static void write(Exception ex) {
        Path file = Path.of("jdkmon.error");
        var sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        try {
            Files.write(file, sw.toString().getBytes(), StandardOpenOption.CREATE);
            Log.info("Generated \"" + file.getFileName() + "\", please create a issue on https://github.com/iamsinghankit/jdk-monitor/issues/");
        } catch(IOException e) {
            Log.error("Error while generating file: \"" + file.getFileName() + "\", switching to console.");
            Log.error(sw.toString());
            Log.info("Please create a issue on https://github.com/iamsinghankit/jdk-monitor/issues/");
        }
    }
}

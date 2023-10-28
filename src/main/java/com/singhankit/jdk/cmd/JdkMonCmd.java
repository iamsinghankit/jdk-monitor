package com.singhankit.jdk.cmd;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;
import com.singhankit.jdk.core.JdkClient;
import com.singhankit.jdk.core.JdkClient.Vendor;
import com.singhankit.jdk.core.Log;
import picocli.CommandLine.Command;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Ankit Singh
 */
@Command(name = "jdkmon",
        subcommands = {JdkFeaturesCmd.class, JdkSchedulesCmd.class},
        mixinStandardHelpOptions = true,
        description = "JDK Monitor monitor the openjdk projects for newer releases details.",
        version = "jdkmon v0.1")
public class JdkMonCmd implements Callable<Integer> {

    private final JdkClient jdkClient = JdkClient.of(Vendor.OPENJDK);

    @Override
    public Integer call() throws Exception {
        Log.info("Fetching all the available JDKs... ");
        try {
            var allJdks = jdkClient.listAllJdk();
            String table = AsciiTable.getTable(AsciiTable.FANCY_ASCII, allJdks,
                    List.of(new Column().header("Version").headerAlign(HorizontalAlign.RIGHT).with(jdk -> jdk.split(" ", 2)[0]),
                            new Column().header("Status").headerAlign(HorizontalAlign.RIGHT).with(jdk -> jdk.split(" ", 2)[1])));
            Log.info(table);
        } catch(UnknownHostException ex) {
            Log.error("Please check internet connection, not able to access the resources...");
        }
        return 0;
    }

}

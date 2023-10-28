package com.singhankit.jdk.cmd;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;
import com.singhankit.jdk.core.JdkClient;
import com.singhankit.jdk.core.JdkClient.Vendor;
import com.singhankit.jdk.core.Log;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Ankit Singh
 */
@Command(name = "--features",
        description = "List all the features targeted for the specific jdk version release.")
public class JdkFeaturesCmd implements Callable<Integer> {

    private final JdkClient jdkClient = JdkClient.of(Vendor.OPENJDK);

    @Parameters(description = "Java version.", paramLabel = "<java-version>")
    private String version;

    @Override
    public Integer call() throws Exception {
        try {
            List<String> features = jdkClient.getFeatures(version);
            String table = AsciiTable.getTable(AsciiTable.FANCY_ASCII, features,
                    List.of(new Column().header("Jeps").headerAlign(HorizontalAlign.RIGHT).with(jdk -> jdk.split(" ", 2)[0].replace(":", "")),
                            new Column().header("Description").headerAlign(HorizontalAlign.RIGHT).with(jdk -> jdk.split(" ", 2)[1])));
            Log.info(table);
        } catch(UnknownHostException ex) {
            Log.error("Please check internet connection, not able to access the resources...");
        }
        return 0;
    }
}

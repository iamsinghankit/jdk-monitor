package com.singhankit.jdk.core;

import java.io.IOException;
import java.util.List;

/**
 * @author Ankit Singh
 */
public interface JdkClient {

    static JdkClient of(Vendor vendor) {
        return switch(vendor) {
            case null -> throw new IllegalArgumentException("Vendor required!");
            case OPENJDK -> new OpenJdkClient();
        };
    }

    List<String> listAllJdk() throws IOException;

    String getJepUrl();

    String latestJdk() throws IOException;

    List<String> getSchedules(int version) throws IOException;

    List<String> getFeatures(int version) throws IOException;

    enum Vendor {
        OPENJDK
    }

}

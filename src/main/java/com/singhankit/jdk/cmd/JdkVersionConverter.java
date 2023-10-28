package com.singhankit.jdk.cmd;

import com.singhankit.jdk.core.JdkClient;
import com.singhankit.jdk.core.JdkClient.Vendor;
import picocli.CommandLine.ITypeConverter;
import picocli.CommandLine.TypeConversionException;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @author Ankit Singh
 */
class JdkVersionConverter implements ITypeConverter<Integer> {
    private final JdkClient jdkClient = JdkClient.of(Vendor.OPENJDK);

    @Override
    public Integer convert(String value) throws Exception {
        if(value.equalsIgnoreCase("<latest>")) {
            return getLatestVersion();
        } else {
            try {
                int i = Integer.parseInt(value);
                if(i <= 11) throw validationError("<latest>");
                int latestVersion = getLatestVersion();
                if(i > latestVersion) throw validationError(String.valueOf(latestVersion));
                return i;
            } catch(NumberFormatException ex) {
                throw validationError("<latest>");
            }
        }
    }

    private TypeConversionException validationError(String v) {
        return new TypeConversionException("Valid version is in range [12.." + v + "]");
    }

    private int getLatestVersion() throws IOException {
        try {
            return Integer.parseInt(jdkClient.latestJdk().split(" ", 2)[0]);
        } catch(UnknownHostException | SocketTimeoutException ex) {
            throw new TypeConversionException("Please check internet connection, not able to access the resources..");
        }
    }
}

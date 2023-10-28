package com.singhankit.jdk.cmd;

import com.singhankit.jdk.core.JdkClient;
import com.singhankit.jdk.core.JdkClient.Vendor;
import picocli.CommandLine.ITypeConverter;
import picocli.CommandLine.TypeConversionException;

/**
 * @author Ankit Singh
 */
class JdkVersionConverter implements ITypeConverter<Integer> {
    private final JdkClient jdkClient = JdkClient.of(Vendor.OPENJDK);

    @Override
    public Integer convert(String value) throws Exception {
        if(value.equalsIgnoreCase("<latest>")) {
            return Integer.parseInt(jdkClient.latestJdk().split(" ", 2)[0]);
        } else {
            try {
                int i = Integer.parseInt(value);
                if(i <= 11) throw validationError("<latest>");
                int latestVersion = Integer.parseInt(jdkClient.latestJdk().split(" ", 2)[0]);
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
}

package org.assertj.core.api;

import org.assertj.core.internal.Objects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Powered by o<+o
 */

public class CrunchAssertions {
    public static FileAssert assertThat(String actual) throws IOException {
        String read = new String(Files.readAllBytes(Paths.get(actual)));
        return new FileAssert(read);
    }

    public static class FileAssert extends StringAssert {

        protected FileAssert(String actual) {
            super(actual);
        }

        @Override
        public StringAssert isEqualTo(Object expected) {
            String expect = null;
            try {
                expect = new String(Files.readAllBytes(Paths.get((String) expected)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Objects.instance().assertEqual(info, actual, expect);
            return myself;
        }
    }
}

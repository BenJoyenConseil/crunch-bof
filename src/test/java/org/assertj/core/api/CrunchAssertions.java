package org.assertj.core.api;

import org.assertj.core.internal.Objects;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.CrunchAssertions.FileUtil.getFileContent;

/**
 * Powered by o<+o
 */

public class CrunchAssertions {
    public static FileAssert assertThat(String actual) throws IOException {
        String read = getFileContent(actual);
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
                expect = getFileContent((String) expected);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Objects.instance().assertEqual(info, actual, expect);
            return myself;
        }
    }

    public static class FileUtil{
        public static String getFileContent(String filePath) throws IOException {
            final Path[] txtFileArray = {null};
            Files.walk(Paths.get(filePath)).forEach(path-> {
                if (Files.isRegularFile(path)) {
                    if(path.getFileName().toString().endsWith(".txt")) {
                        txtFileArray[0] = path;
                    }
                }
            });

            if(txtFileArray.length == 0)
                throw new FileNotFoundException("There is no txtFileArray ending with \".txt\" extension in the path : " + filePath);

            return new String(Files.readAllBytes(txtFileArray[0]));
        }
    }
}

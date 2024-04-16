package com.drainshawty.lab2.unit;

import com.drainshawty.lab2.math.MathFunction;
import com.drainshawty.lab2.output.CsvFunctionWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CsvFunctionWriterTest {

    @TempDir
    File csvDirectory;

    @Test
    void testWritIntoFile() throws IOException {
        File file = new File(csvDirectory, "output.csv");
        CsvFunctionWriter writer = new CsvFunctionWriter(file, ',');
        writer.write(new Square(), -3, 3, 1);
        assertEquals(
                Arrays.asList(
                        "-3.000000,9.000000",
                        "-2.000000,4.000000",
                        "-1.000000,1.000000",
                        "0.000000,0.000000",
                        "1.000000,1.000000",
                        "2.000000,4.000000",
                        "3.000000,9.000000"
                ),
                Files.readAllLines(file.toPath())
        );
    }

    private static class Square extends MathFunction {
        @Override
        public double of(double x) {return x * x;}
    }
}

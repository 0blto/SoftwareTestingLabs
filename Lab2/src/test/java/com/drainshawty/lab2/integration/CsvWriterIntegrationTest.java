package com.drainshawty.lab2.integration;

import com.drainshawty.lab2.math.MathSystem;
import com.drainshawty.lab2.output.CsvFunctionWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CsvWriterIntegrationTest {

    @TempDir
    File csvDirectory;
    @Mock
    MathSystem systemMock;

    @Spy
    MathSystem systemSpy;


    @Test
    void testLog2CalculationLnFunctionCalled() throws IOException {
        File file = new File(csvDirectory, "output.csv");
        CsvFunctionWriter csvFunctionWriter = new CsvFunctionWriter(file, ',');
        csvFunctionWriter.write(systemSpy, 2, 4, 1);
        verify(systemSpy, times(3)).of(anyDouble());
    }

    @Test
    void testMathSystemCalculationWithMockedTrigonometricFunctionValues() throws IOException {
        File file = new File(csvDirectory, "output.csv");
        CsvFunctionWriter csvFunctionWriter = new CsvFunctionWriter(file, ',');
        when(systemMock.of(2)).thenReturn(1.189449);
        when(systemMock.of(3)).thenReturn(1.307883);
        when(systemMock.of(4)).thenReturn(1.395322);
        csvFunctionWriter.write(systemMock, 2, 4, 1);
        assertEquals(
                Arrays.asList(
                        "2.000000,1.189449",
                        "3.000000,1.307883",
                        "4.000000,1.395322"
                ),
                Files.readAllLines(file.toPath())
        );
    }
}

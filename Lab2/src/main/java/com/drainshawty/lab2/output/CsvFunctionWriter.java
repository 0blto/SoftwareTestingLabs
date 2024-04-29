package com.drainshawty.lab2.output;

import com.drainshawty.lab2.math.MathFunction;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import static java.lang.Float.NaN;

public class CsvFunctionWriter implements FunctionWriter {

    private final File file;
    private final char separator;

    public CsvFunctionWriter(File file, char separator) {
        this.file = file;
        this.separator = separator;
    }

    public CsvFunctionWriter(String filename, char separator) {
        this.file = new File(filename);
        this.separator = separator;
    }

    @Override
    public void write(
            MathFunction function,
            double from,
            double to,
            double step
    ) throws IOException {
        if (file.exists()) file.delete();
        file.createNewFile();
        PrintWriter printWriter = new PrintWriter(file);
        for (double current = from; current <= to; current += step) {
            try {
                double value = function.of(current);
                printWriter.println(String.format(Locale.ENGLISH, "%f%c%f", current, separator, value));
            } catch (ArithmeticException ex) {
                printWriter.println(String.format(Locale.ENGLISH, "%f%c%f", current, separator, NaN));
            }
        }
        printWriter.close();
    }
}

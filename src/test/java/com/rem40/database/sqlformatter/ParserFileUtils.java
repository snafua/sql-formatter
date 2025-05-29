package com.rem40.database.sqlformatter;

import org.apache.commons.io.*;

import java.io.*;

public class ParserFileUtils {

    public void writeToFile( FileWriter fileWriter,final String outFile, String data) {
        try {
            fileWriter = new FileWriter(outFile, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.write(data);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(final String outFile, String data) {
        File file = new File(outFile);
        try {
            FileUtils.writeStringToFile(file, data, "UTF-8", false);
        } catch (IOException e) {
            System.err.println("Error appending to file: " + e.getMessage());
        }
    }

    public String readFile(final String inFilename) {
        File file = new File(inFilename);
        try {
        return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return null;
    }

    public void appendToFile(final String outFile, String data) {
        File file = new File(outFile);
        try {
            FileUtils.writeStringToFile(file, data, "UTF-8", true);
        } catch (IOException e) {
            System.err.println("Error appending to file: " + e.getMessage());
        }
    }
}

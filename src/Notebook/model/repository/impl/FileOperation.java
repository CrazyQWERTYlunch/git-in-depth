package Notebook.model.repository.impl;

import java.io.*;
public class FileOperation {
    private final String fileName;
    public FileOperation(String fileName) {
        this.fileName = fileName;
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

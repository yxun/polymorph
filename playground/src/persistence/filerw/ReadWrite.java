package persistence.filerw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class ReadWrite {
    private File fileRead;
    private File fileWrite;
    
    public static class Builder {
        private File fileRead;
        private File fileWrite;

        public Builder(String fileRead, String fileWrite) {
            this.fileRead = new File(fileRead);
            this.fileWrite = new File(fileWrite);
        }

        public Builder fileRead(String file) {
            fileRead = new File(file);
            return this;
        }

        public Builder fileWrite(String file) {
            fileWrite = new File(file);
            return this;
        }

        public ReadWrite build() {
            return new ReadWrite(this);
        }
    }

    private ReadWrite(Builder builder) {
        fileRead = builder.fileRead;
        fileWrite = builder.fileWrite;
    }

    public static void Read(Boolean debug) {
        try {
            Scanner reader = new Scanner(this.fileRead);
            if (debug == True) {
                while (reader.hasNextLine()) {
                    String line = reader.nextLine();
                    System.out.println(line);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: file not found.");
            e.printStackTrace();
        }
    }

    public static void GetFileInfo() {
        if (this.fileRead.exists()) {
            System.out.println("File name: " + this.fileRead.getName());
            System.out.println("Absolute path: " + this.fileRead.getAbsolutePath());
            System.out.println("Writeable: " + this.fileRead.canWrite());
            System.out.println("Readable: " + this.fileRead.canRead());
            System.out.println("File size in bytes: " + this.fileRead.length());
        } else {
            System.out.println("The file does not exist.");
        }
    }

    public static void Create() {
        try {
            if (this.fileWrite.createNewFile()) {
                System.out.println("File created: " + this.fileWrite.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }

    public static void Write(String content) {
        try {
            FileWriter writer = new FileWriter(this.fileWrite);
            writer.write(content);
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }

    public static void Delete() {
        if (this.fileWrite.delete()) {
            System.out.println("Deleted the file: " + this.fileWrite.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
}

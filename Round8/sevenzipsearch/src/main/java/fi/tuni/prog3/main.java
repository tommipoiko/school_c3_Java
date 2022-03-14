package fi.tuni.prog3;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

public class main {

    public static void main(String args[]) throws IOException {
        
        String file_name = args[0];
        String word = args[1];
        
        SevenZFile archiveFile = new SevenZFile(new File(file_name));
        SevenZArchiveEntry entry;
        try {
            while((entry = archiveFile.getNextEntry()) != null) {
                if (!entry.getName().contains(".txt")) {
                    continue;
                }
                
                System.out.println(entry.getName());
                int bufferSize = 1024;
                char[] buffer = new char[bufferSize];
                StringBuilder out = new StringBuilder();
                Reader in = new InputStreamReader(archiveFile.getInputStream(entry), StandardCharsets.UTF_8);
                for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0; ) {
                    out.append(buffer, 0, numRead);
                }
                String text = out.toString();
                String[] rows = text.split("\n");
                int num = 1;
                for (var row : rows) {
                    if (org.apache.commons.lang3.StringUtils.containsIgnoreCase(row, word)) {
                        System.out.println(num + ": " + row);
                    }
                    num++;
                }
                System.out.println();
            }
        } finally {
            archiveFile.close();
        }
    }
}

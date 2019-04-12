package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 12.04.2019
 */

public class PackZip {
    public void achieve(Args args) throws IOException {
        String zip = args.output();
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip))) {
            File source = new File(args.directory());
            List<String> expansions = args.exclude();
            this.form(zos, source, expansions);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void form(ZipOutputStream zos, File source, List<String> expansions) throws IOException {
        byte[] buffer = new byte[4096];
        File[] files = source.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    form(zos, file, expansions);
                    continue;
                }
                if (exclude(file, expansions)) {
                    FileInputStream fis = new FileInputStream(file);
                    zos.putNextEntry(new ZipEntry(file.getPath()));
                    while (fis.read(buffer) != -1) {
                        zos.write(buffer);
                    }
                    zos.closeEntry();
                    fis.close();
                }
            }
        }
    }

    private boolean exclude(File file, List<String> expansions) {
        boolean result = true;
        if (expansions != null) {
            for (String current : expansions) {
                if (file.getName().endsWith(current)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}

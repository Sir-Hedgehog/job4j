package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Sir-Hedgehog (mailto:quaresma_08@mail.ru)
 * @version $Id$
 * @since 14.04.2019
 */

public class PackZip {
    private byte[] buffer = new byte[4096];
    private Search search = new Search();

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
        List<File> list = search.listOfOthers(source.getPath(), expansions);
        for (File file : list) {
            if (file.isDirectory()) {
                form(zos, file, expansions);
                continue;
            }
            try (FileInputStream fis = new FileInputStream(file)) {
                zos.putNextEntry(new ZipEntry(file.getPath().substring(source.getName().length() - 2)));
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

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
        List<File> list = search.listOfOthers(source.getName(), expansions);
        for (File file : list) {
            zos.putNextEntry(new ZipEntry(file.getAbsolutePath().substring(file.getName().length())));
            try (FileInputStream fis = new FileInputStream(file)) {
                while (fis.read(buffer) != -1) {
                    zos.write(buffer);
                }
                zos.closeEntry();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

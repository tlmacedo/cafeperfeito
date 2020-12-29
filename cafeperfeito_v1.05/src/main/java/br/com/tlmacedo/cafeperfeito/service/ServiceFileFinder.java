package br.com.tlmacedo.cafeperfeito.service;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class ServiceFileFinder {
    public static File finder(String dirName, String arqName, String extensao) {
        File dir = new File(dirName);
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains(arqName.replaceAll("\\D", ""));
            }
        });
//        if (files.length == 0)
//            return null;
//        System.out.printf("fileDir:%s", files[0].getName());
        for (File file : files)
            if (Pattern.compile(extensao).matcher(file.getName()).find())
                return file;
        return null;
    }
}

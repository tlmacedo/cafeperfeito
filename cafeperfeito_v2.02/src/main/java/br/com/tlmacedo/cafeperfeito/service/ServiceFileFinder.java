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
                if (extensao == ".xml")
                    return name.contains(arqName.replaceAll("\\D", ""));
                else
                    return name.contains(arqName);
            }
        });

//        if (!extensao.equals(null))
//            for (File file : files)
//                if (file.getName().endsWith(extensao))
//                    return file;
//        return files[0];
        for (File file : files)
            if (Pattern.compile(extensao).matcher(file.getName()).find())
                return file;

        return null;
    }
}

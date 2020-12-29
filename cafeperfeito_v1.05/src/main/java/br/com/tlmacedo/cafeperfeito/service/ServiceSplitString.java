package br.com.tlmacedo.cafeperfeito.service;

import java.util.ArrayList;
import java.util.List;

public class ServiceSplitString {

    public static List<String> split(String s, int tamanho) {
        List<String> parts = new ArrayList<String>();
        while (s.length() > tamanho) {
            int splitAt = tamanho - 1;
            for (; splitAt > 0 && !Character.isWhitespace(s.charAt(splitAt)); splitAt--) ;
            if (splitAt == 0)
                return parts; // can't be split
            parts.add(s.substring(0, splitAt));
            s = s.substring(splitAt + 1);
        }
        parts.add(s);
        return parts;
    }
}

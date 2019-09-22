package com.gyl.visit.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOUtil {

    public static String readStreamToString(InputStream inputStream) throws IOException {
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } finally {
            inputStream.close();
        }
    }

}

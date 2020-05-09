package com.tigerjoys.shark.miai.utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtilTest {

    public static List<String> readBylineTolist(String filePath) throws IOException {
        List<String> line = new ArrayList<>();
        try (FileReader reader = new FileReader(new File(filePath));
             BufferedReader br = new BufferedReader(reader);
        ) {
            String str = null;
            while ((str = br.readLine()) != null) {
                line.add(str);
            }
        }
        return line;
    }


    public static void createFile(String filePath, List<String> lines) throws IOException {
        try (FileWriter writer = new FileWriter(new File(filePath));
             BufferedWriter bw = new BufferedWriter(writer);) {
            for (String re : lines) {
                bw.write(re);
                bw.newLine();
            }
        }

    }


    public static void main(String[] args) throws IOException {
        List<String> lines = FileUtilTest.readBylineTolist("C:\\Users\\yangjunming.TIGERJOYS\\Desktop\\aaaa.csv");
        List<String> outList = new ArrayList<>();

        for (String re : lines) {
            //System.out.println(re);
            String[] split = re.split("\t");
            System.out.println(split[0]);
            outList.add(split[0]);
        }
        FileUtilTest.createFile("d:/afaf.txt",outList);


    }
}

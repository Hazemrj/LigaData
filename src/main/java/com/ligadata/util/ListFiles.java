package com.ligadata.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ListFiles {

    public static List<BufferedReader> getReaderList (String filePath){
        List<BufferedReader> readerList = new ArrayList<>();
        File file = new File(filePath);
        // returns an array of all files
        String[] fileList = file.list();

        assert fileList != null;
        for(String str : fileList) {
            try {
                BufferedReader lineReader = new BufferedReader(new FileReader(filePath + "\\" + str));
                readerList.add(lineReader);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return readerList;
    }
}

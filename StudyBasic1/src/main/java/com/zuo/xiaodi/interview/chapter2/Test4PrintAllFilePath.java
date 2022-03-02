package com.zuo.xiaodi.interview.chapter2;

import java.io.File;
import java.util.ArrayList;

/**
 * 找出某目录下的所有子目录以及子文件并打印到控制台上
 */
public class Test4PrintAllFilePath {
    public static void main(String[] args) {
        ArrayList<String> filePathes = new ArrayList<>();
        getAllFilePathes(new File("D:\\Develop software\\Development Projects\\StudyProjects"), filePathes);
        for (String filePath : filePathes) {
            System.out.println(filePath);
        }
    }

    private static void getAllFilePathes(File file, ArrayList<String> filePathes) {
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File list : listFiles) {
            if (list.isDirectory()) {
                filePathes.add(list.getPath());
                getAllFilePathes(list, filePathes);
            } else {
                filePathes.add(list.getPath());
            }
        }
    }
}

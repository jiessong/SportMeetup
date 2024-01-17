package com.example.sportsdating.utils;

import com.example.sportsdating.core.SportDatingApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static String readFile(String filename){
        try {
            File file = new File(SportDatingApplication.getMycontext().getFilesDir(), filename);
            if(!file.exists()){
                return "";
            }
            FileInputStream fis = SportDatingApplication.getMycontext().openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while(line!=null){
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static List<String> readFileFromAssets(String filename){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(SportDatingApplication.getMycontext().getAssets().open(filename), StandardCharsets.UTF_8)); // Read one line at a time.
            List<String> lines = new ArrayList<>();
            String line;
            while((line=bufferedReader.readLine())!=null){
                lines.add(line);
            }
            return lines;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeFile(String filename, String content){
        try{
            FileOutputStream fos = SportDatingApplication.getMycontext().openFileOutput(filename, SportDatingApplication.MODE_PRIVATE);
            fos.write(content.getBytes(StandardCharsets.UTF_8));
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

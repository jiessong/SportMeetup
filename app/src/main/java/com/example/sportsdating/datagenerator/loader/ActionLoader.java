package com.example.sportsdating.datagenerator.loader;

import com.example.sportsdating.core.SportDatingApplication;
import com.example.sportsdating.utils.FileUtil;
import com.example.sportsdating.utils.LocalStorageUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Read simulated data(user/posts,etc.) from assets folder, put each line in an array
 * and then iterate each line
 */
public class ActionLoader {
    private String fileName;
    private int index;
    private List<String> lines;

    public ActionLoader(String filename) {
        this.fileName = filename; //the file name in the asset folder
        loadStreamData(); //load data from this file
        this.index = LocalStorageUtil.getInstance().getInteger(filename); //fetch index(line number) from local storage
    }

    //Read data from the file and store each line in the array
    public void loadStreamData(){
        lines = FileUtil.readFileFromAssets(fileName);
    }

    //Return the iterator
    public ActionIterator getIterator(){
        return new AcitonLoaderIterator(this);
    }

    //Iterator design patter is used
    public class AcitonLoaderIterator implements ActionIterator<String>{

        ActionLoader actionLoader;
        public AcitonLoaderIterator(ActionLoader actionLoader){
            this.actionLoader = actionLoader;
        }
        @Override
        public boolean hasNext() {
            System.out.println("index: "+this.actionLoader.index+" lines size:"+this.actionLoader.lines.size());
            if(this.actionLoader.index < this.actionLoader.lines.size()){
                return true;
            }else{
                return false;
            }
        }

        @Override
        public String next() {
            if(hasNext()){
                String line = this.actionLoader.lines.get(actionLoader.index);
                LocalStorageUtil.getInstance().set(fileName, ++index); //save current index into local storage
                return line;
            }else{
                return null;
            }
        }
    }
}

package com.example.todolist;

import java.io.File;
import java.time.LocalDate;

public class CC {
    private String desc = "a";
    private LocalDate date;

    private String all;

    public CC(String desc, LocalDate date, String all) {
        this.desc = desc;
        this.date = date;
        this.all = all;
    }

    CC(String desc, LocalDate date){
        this.setDate(date);
        this.setDesc(desc);
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getAll() {
        return all;
    }

    public CC() {

    }
    public CC(String all){
        this.all = all;
    }

    public String getDesc(){
        return desc;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    @Override
    public String toString(){
        return this.all;
    }
    public static void create(){
        File file = new File("todolist.txt");
    }
}
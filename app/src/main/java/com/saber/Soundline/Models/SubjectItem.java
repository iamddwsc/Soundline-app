package com.saber.Soundline.Models;

import java.util.ArrayList;

public class SubjectItem {

    private String headerTitle;
    private ArrayList<SingleItem> listitems;

    public SubjectItem () {

    }

    public SubjectItem(String headerTitle, ArrayList<SingleItem> listitems) {
        this.headerTitle = headerTitle;
        this.listitems = listitems;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<SingleItem> getItems() {
        return listitems;
    }

    public void setItems(ArrayList<SingleItem> listitems) {
        this.listitems = listitems;
    }
}

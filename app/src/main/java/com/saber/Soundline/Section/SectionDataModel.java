package com.saber.Soundline.Section;

import com.saber.Soundline.Models.SingleItem;

import java.util.ArrayList;

public class SectionDataModel {

    private String headerTitle;
    private ArrayList<SingleItem> allItemsInSection;


    public SectionDataModel() {

    }

    public SectionDataModel(String headerTitle, ArrayList<SingleItem> allItemsInSection) {
        this.headerTitle = headerTitle;
        this.allItemsInSection = allItemsInSection;
    }


    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<SingleItem> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<SingleItem> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }

}

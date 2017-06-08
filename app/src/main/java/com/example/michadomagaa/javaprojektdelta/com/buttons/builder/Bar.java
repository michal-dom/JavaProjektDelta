package com.example.michadomagaa.javaprojektdelta.com.buttons.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macfr on 08.06.2017.
 */

public class Bar {

    private List<Item> buttons = new ArrayList<Item>();

    public void addItem(Item i){
        buttons.add(i);
    }

    public ArrayList<Integer> getIdList(){
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for(Item item:buttons){
            indexes.add(item.getId());
        }

        return indexes;
    }

}

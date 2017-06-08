package com.example.michadomagaa.javaprojektdelta.com.buttons.decorator;

/**
 * Created by macfr on 08.06.2017.
 */

public abstract class ItemDecorator implements Item{

    protected Item item;
    public ItemDecorator(Item i) {this.item = i;}
    public void completeLayout(){item.completeLayout();}


}

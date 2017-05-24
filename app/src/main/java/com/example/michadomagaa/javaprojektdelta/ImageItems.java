
/**
 * Created by Marcin on 24.05.2017.
 */


package com.example.michadomagaa.javaprojektdelta;
import android.graphics.Bitmap;

public class ImageItems {

        private Bitmap image;
        private String title;
        private long size;

        public ImageItems(Bitmap image, String title, long size){
            super();
            this.image = image;
            this.title = title;
            this.size = size;
        }

        public Bitmap getImage(){
            return image;
        }

        public void setImage(){
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getSize() {
            return size;
        }
}


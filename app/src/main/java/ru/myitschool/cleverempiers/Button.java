package ru.myitschool.cleverempiers;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Button {
    private String name;
    public ImageView image;
    Button(Activity activity, float x, float y, int sx, int sy, String name){
        this.name = name;
        image = new ImageView(activity);
        image.setImageResource(chooseImage(name));
        outXY(x,y);
        activity.addContentView(image, new RelativeLayout.LayoutParams(sx, sy));
    }
    private void outXY(float x, float y){
        image.setX(x);
        image.setY(y);
    }
    private int chooseImage(String s){
        switch (s) {
         case "New Game": return R.drawable.b_newgame_np;
         case "New Game Down": return R.drawable.b_newgame_p;
          case "How To Play": return R.drawable.b_howtoplay_np;
          case "How To Play Down": return R.drawable.b_howtoplay_p;
          case "Exit": return R.drawable.b_exit_np;
          case "Exit Down": return R.drawable.b_exit_p;
            default: return R.color.colorAccent;
        }
    }
    public void buttonDown(){
        image.setImageResource(chooseImage(name+" Down"));
    }
    public void buttonUp(){
        image.setImageResource(chooseImage(name));
    }
}
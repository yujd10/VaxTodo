package View;

import Model.Router;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class View {
    private View previous;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // Reading data using readLine
    String input = null;

    public void print(String message){
        System.out.println(message);
    }

    public void exit(){
        System.out.println("Au revoir.");
        System.exit(0);
    }

    public void clear(){

    }
}

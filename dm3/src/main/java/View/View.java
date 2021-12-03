package View;

import Model.Router;

import java.io.BufferedReader;
import java.io.IOException;
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

    }

    public void clear(){

    }
}

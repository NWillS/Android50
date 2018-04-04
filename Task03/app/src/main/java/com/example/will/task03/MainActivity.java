package com.example.will.task03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Integer x = 3;
        Integer y = 2;

        doIf(x,y);
        doIfElse(x,y);
        doIfElseIf(x,y);
        doWhile();
        doDoWhile();
        doFor();
        doForEx();
        doSwitch();

    }
    private void doIf(Integer x, Integer y){
//        if文
        if(x + y == 5){
            Log.i("System.out/If","if文の処理完了");
        }
    }

    private void doIfElse(Integer x, Integer y){
        //        if~else文
        if(x +  y < 4){
            Log.i("System.out/IfElseTrue","x + y =" + (x+y));

        } else {
            Log.i("System.out/IfElse","x + y =" + (x+y));
        }
    }

    private void doIfElseIf(Integer x, Integer y){
//        if~else if文
        if(x + y < 6){
            Log.i("System.out/IfElseIfTrue","x + y = " + (x+y));
        } else if(x + y == 5){
            Log.i("System.out/IfElseIf","x + y = " + (x+y));
        }
    }

    private void doWhile(){
        //        while文
        Integer i = 0;
        while(i < 5){
            Log.i("System.out/While","i = " + i);
            i++;
        }
    }

    private void doDoWhile(){

//        do while文
        Integer i = 0;
        do{
            Log.i("System.out/DoWhile","i = " + i);
            i++;
        } while (i < 5);

    }

    private void doFor(){
        //        for文
        for(Integer j = 0; j < 5; j++){
            Log.i("System.out/For","j = " + j);
        }
    }

    private void doForEx(){
        //        高速列挙　構文
        ArrayList<String> arr = new ArrayList<>(Arrays.asList("test", "one", "two")) ;
        for (String str: arr ){
            Log.i("System.out/ExFor","str = " + str);
        }
    }

    private void doSwitch(){
        Integer x = 3;
        //        switch文
        switch (x % 2) {
            case 0:
                Log.i("System.out/SwitchEven", " xは偶数");
                break;
            case 1:
                Log.i("System.out/SwitchOdd", " xは奇数");
                break;

            default:
        }
    }
}

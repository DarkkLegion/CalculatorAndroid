package com.example.calculator;
import java.util.*;

public class computeFunctions {
    public void add(){

    }
    int getPrecedence(char ch)
    {

        if (ch == '+' || ch == '-')
            return 1;
        else if (ch == '*' || ch == '/')
            return 2;
        else if (ch == '^')
            return 3;
        else
            return -1;
    }

    public double execute(String equation) {
        int len = equation.length();
        char[] eqn = equation.toCharArray();
        Stack<String> stack = new Stack<String>();
        for(int i=0;i<len-1;i++){
            if(eqn[i]=='('){
                stack.push(Character.toString(eqn[i]));
            }
            while(Character.isDigit(eqn[i])){
                stack.push(Character.toString(eqn[i]));
            }
        }

        return 0;
    }
}

package com.example.calculatrice;

public class Operation {


    public static int addition(int a, int b){
        return a+b;
    }

    public static int soustraction(int a, int b){
        return a-b;
    }

    public static int multiplication(int a, int b){
        return a*b;
    }
    public static int division(int a, int b){

        if (b == 0) throw new ArithmeticException("Impossible de diviser par 0");
        return a/b;
    }
    public static int modulo(int a, int b){

        if (b == 0) throw new ArithmeticException("Impossible de diviser par 0");
        return a%b;
    }

    public static int calcul(String operateur, int a, int b){
        int resultat =0;
        switch (operateur){
            case "+":
                resultat = addition(a,b);
                break;
             case "-":
                resultat = soustraction(a,b);
                break;
             case "*":
                resultat = multiplication(a,b);
                break;
             case "÷":
                resultat = division(a,b);
                break;

             case "%":
                resultat = modulo(a,b);
                break;

        }

        return resultat;
    }
}

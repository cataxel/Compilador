/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analizadorfinalmad;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author Martin de Jesus
 * //esta clase fue un intento para ver si funcionaba el metodo pero no funciono como tal asi que estuve haciendolo en el Com.java
 * 
 */
public class LRParser {
      private static final List<String> TERMINALS_NON_TERMINALS = Arrays.asList("id", "num", "int", "float", "char", ",", ";", "+", "-", "*", "/", "(", ")", "=", "$", "P", "Tipo", "V", "A", "Exp", "E", "Term", "T", "F");
    private static final List<String> STATES = Arrays.asList("q0", "q1", "q2", "q3", "q4", "q5", "q6", "q7", "q8", "q9", "q10", "q11", "q12", "q13", "q14", "q15", "q16", "q17", "q18", "q19", "q20", "q21", "q22", "q23", "q24", "q25", "q26", "q27", "q28", "q29", "q30", "q31", "q32", "q33", "q34", "q35", "q36", "q37", "q38", "q39", "q40", "q41", "q42", "q43", "q44");
    private static final Map<String, List<String>> PRODUCTIONS = new HashMap<>();

   static {
    PRODUCTIONS.put("P0", Arrays.asList("P'", "P"));
    PRODUCTIONS.put("P1", Arrays.asList("P", "Tipo", "Id", "V"));
    PRODUCTIONS.put("P2", Arrays.asList("P", "A"));
    PRODUCTIONS.put("P3", Arrays.asList("Tipo", "int"));
    PRODUCTIONS.put("P4", Arrays.asList("Tipo", "float"));
    PRODUCTIONS.put("P5", Arrays.asList("Tipo", "char"));
    PRODUCTIONS.put("P6", Arrays.asList("V", ",", "id", "V"));
    PRODUCTIONS.put("P7", Arrays.asList("V", ";", "P"));
    PRODUCTIONS.put("P8", Arrays.asList("A", "id", "=", "Exp", ";"));
    PRODUCTIONS.put("P9", Arrays.asList("Exp", "+", "Term", "E"));
    PRODUCTIONS.put("P10", Arrays.asList("Exp", "-", "Term", "E"));
    PRODUCTIONS.put("P11", Arrays.asList("Exp", "Term", "E"));
    PRODUCTIONS.put("P12", Arrays.asList("E", "+", "Term", "E"));
    PRODUCTIONS.put("P13", Arrays.asList("E", "-", "Term", "E"));
    PRODUCTIONS.put("P14", Arrays.asList("E", "€"));
    PRODUCTIONS.put("P15", Arrays.asList("Term", "F", "T"));
    PRODUCTIONS.put("P16", Arrays.asList("T", "*", "F", "T"));
    PRODUCTIONS.put("P17", Arrays.asList("T", "/", "F", "T"));
    PRODUCTIONS.put("P18", Arrays.asList("T", "€"));
    PRODUCTIONS.put("P19", Arrays.asList("F", "id"));
    PRODUCTIONS.put("P20", Arrays.asList("F", "num"));
    PRODUCTIONS.put("P21", Arrays.asList("F", "(", "Exp", ")"));
}

    private Stack<String> stack = new Stack<>();

    public LRParser() {
        stack.push("$");
        stack.push("q0");
    }

    public void parse(List<String> tokens) {
        int currentIndex = 0;
        while (currentIndex < tokens.size()) {
            String currentToken = tokens.get(currentIndex);
            String currentState = stack.peek();
            String action = getAction(currentState, currentToken);
            if (action.startsWith("q")) {
                stack.push(currentToken);
                stack.push(action.substring(1));
                currentIndex++;
            } else if (action.startsWith("P")) {
                List<String> production = PRODUCTIONS.get(action.substring(1));
                for (int i = 0; i < production.size() * 2; i++) {
                    stack.pop();
                }
                String nonTerminal = production.get(0);
                currentState = stack.peek();
                String nextState = getGoto(currentState, nonTerminal);
                stack.push(nonTerminal);
                stack.push(nextState);
            } else if (action.equals("Aceptar")) {
                System.out.println("Cadena aceptada!");
                break;
            } else {
                System.out.println("Error: Error sintactico:" + "se esperaba: " + currentToken);
                break;
            }
        }
    }

    private String getAction(String state, String token) {
        int column = TERMINALS_NON_TERMINALS.indexOf(token);
      
        int row = STATES.indexOf(state);
          System.out.println("column: " + column +"row: "+ row);
            System.out.println("state: " + state +"token: "+ token);
        return TABLE[row][column];
    }

    private String getGoto(String state, String nonTerminal) {
        int column = TERMINALS_NON_TERMINALS.indexOf(nonTerminal);
        int row = STATES.indexOf(state);
        return TABLE[row][column];
    }

    private static final String[][] TABLE = {
     //"id", "num", "int", "float", "char", ",", ";",  "+",  "-",  "*",  "/",  "(", ")",  "=", "$", "P", "Tipo", "V", "A", "Exp", "E", "Term", "T", "F"]
        {"q7",  "",   "q4",   "q5",    "q6",  "",  "",   "",   "",   "",   "",   "",  "",   "",  "", "q1", "q2",    "","q3",  "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "",  "",   "",   "",   "",   "",   "",  "",   "", "P0", "",   "",     "", "",   "",   "",    "",   "",  "" },
        {"q8",  "",    "",     "",      "",   "",  "",   "",   "",   "",   "",   "",  "",   "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "",  "",   "",   "",   "",   "",   "",  "",   "", "P2", "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"P3",  "",    "",     "",      "",   "",  "",   "",   "",   "",   "",   "",  "",   "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"P4",  "",    "",     "",      "",   "",  "",   "",   "",   "",   "",   "",  "",   "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"P5",  "",    "",     "",      "",   "",  "",   "",   "",   "",   "",   "",  "",   "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "",  "",   "",   "",   "",   "",   "",  "",  "q9", "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "", "q11","q12", "",   "",   "",   "",   "",  "",   "", "P1",  "",   "",  "q10","",   "",   "",    "",   "",  ""},
        {"q18","q19",  "",     "",      "",   "",  "",  "q14","q15", "",   "", "q20", "",   "",  "",  "",   "",     "", "",   "q13",   "",    "q16", "","q17"},
        {"P1",  "",    "",     "",      "",   "",  "",   "",   "",   "",   "",   "",  "",   "", "P1",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"q21", "",    "",     "",      "",   "",  "",   "",   "",   "",   "",   "",  "",   "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"q7",  "",    "",     "",      "",   "",  "",   "",   "",   "",   "",   "",  "",   "", "P7","q22","q2",    "","q3",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "","q23",  "",   "",   "",   "",   "",  "",   "", "P8", "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"q18","q19",  "",     "",      "",   "",  "",   "",   "",   "",   "", "q20", "",   "",  "",  "",   "",     "", "",   "",   "",   "q24", "", "q17"},
        {"q18","q19",  "",     "",      "",   "",  "",   "",   "",   "",   "", "q20", "",   "",  "",  "",   "",     "", "",   "",   "",   "q25", "", "q17"},
        {"",    "",    "",     "",      "",   "", "P14","q27","q28", "",   "",   "", "P14", "",  "",  "",   "",     "", "",   "", "q26",   "",   "",  ""},
        {"",    "",    "",     "",      "",   "", "P18","P18","P18","q30","q31", "", "P18", "",  "",  "",   "",     "", "",   "",   "",    "", "q29", ""},
        {"",    "",    "",     "",      "",   "", "P19","P19","P19","P19","P19", "", "P19", "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "", "P20","P20","P20","P20","P20", "", "P20", "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"q18","q19",  "",     "",      "",   "",  "",  "q14","q15", "",   "", "q20", "",   "",  "",  "",   "",     "", "",   "q32", "",   "q16","q29","q17"},
        {"",    "",    "",     "",      "", "q11","q12", "",   "",   "",   "",   "",  "",   "",  "",  "",   "",   "q33","",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "",  "",   "",   "",   "",   "",   "",  "",   "", "P7", "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "",  "",   "",   "",   "",   "",   "",  "",   "", "P8", "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "", "P14","q27","q28", "",   "",   "", "P14", "",  "",  "",   "",     "", "",   "", "q35",   "",   "",  ""},
        {"",    "",    "",     "",      "",   "", "P14","q27","q28", "",   "",   "", "P14", "",  "",  "",   "",     "", "",   "", "q36",   "",   "",  ""},
        {"",    "",    "",     "",      "",   "", "P11", "",   "",   "",   "",   "", "P11", "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"q18","q19",  "",     "",      "",   "",  "",   "",   "",   "",   "", "q20", "",   "",  "",  "",   "",     "", "",   "",   "",  "q36",  "q29", "q17"},
        {"q18","q19",  "",     "",      "",   "",  "",   "",   "",   "",   "", "q20", "",   "",  "",  "",   "",     "", "",   "",   "",  "q37",  "", "q17"},
        {"",    "",    "",     "",      "",   "", "P15","P15","P15", "",   "",   "", "P15", "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"q18","q19",  "",     "",      "",   "",  "",   "",   "",   "",   "", "q20", "",   "",  "",  "",   "",     "", "",   "",   "",    "", "q39", ""},
        {"q18","q19",  "",     "",      "",   "",  "",   "",   "",   "",   "", "q20", "",   "",  "",  "",   "",     "", "",   "",   "",    "", "q40", ""},
        {"",    "",    "",     "",      "",   "",  "",   "",   "",   "",   "",   "", "q40", "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "",  "",   "",   "",   "",   "",   "",  "",   "", "P6", "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "", "P9",  "",   "",   "",   "",   "", "P9",  "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "", "P10", "",   "",   "",   "",   "", "P10", "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "", "P14","q27","q28", "",   "",   "", "P14", "",  "",  "",   "",     "", "",   "", "q42",   "",   "",  ""},
        {"",    "",    "",     "",      "",   "", "P14","P17","q28", "",   "",   "", "P14", "",  "",  "",   "",     "", "",   "", "q43",   "",   "",  ""},
        {"",    "",    "",     "",      "",   "", "P18","P18","P18","q30","q31", "", "P18", "",  "",  "",   "",     "", "",   "",   "",    "", "q44", ""},
        {"",    "",    "",     "",      "",   "", "P18","P18","P18","q30","q31", "", "P18", "",  "",  "",   "",     "", "",   "",   "",    "", "q45", ""},
        {"",    "",    "",     "",      "",   "", "P21","P21","P21","P21","P21", "", "P21", "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "", "P12", "",   "",   "",   "",   "", "P12", "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "", "P13", "",   "",   "",   "",   "", "P12", "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "", "P16","P16","P16", "",   "",   "", "P16", "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""},
        {"",    "",    "",     "",      "",   "", "P17","P17","P17", "",   "",   "", "P17", "",  "",  "",   "",     "", "",   "",   "",    "",   "",  ""}
    };

   public static void main(String[] args) {
    List<String> tokens = Arrays.asList("float", "id", ",", "id", ";", "id", "=", "(", "num", "+", "num", ")", ";", "$");
    LRParser parser = new LRParser();
    parser.parse(tokens);
}

}

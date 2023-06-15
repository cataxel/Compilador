package analizadorfinalmad;

import compilerTools.Directory;
import compilerTools.Functions;
import compilerTools.Token;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class Com extends javax.swing.JFrame {

    private Directory directorio;
    //Cosas
    private ArrayList<Token> tokens;
    java.util.Stack<String> pila = new java.util.Stack<String>();
    java.util.Stack<String> pilaaux = new java.util.Stack<>(); //Pila auxiliar
    int renglones = 0, columnas = 0;
    private DefaultTableModel modeloTabla;
    //Variables que uso para contar cosas NO SON MUY NECESARIAS

    private String title;
    private Timer timerKeyReleased;
    int valorAnterior = 1;
    int variable = 1;
    int errorFlag = 0; // Bandera para marcar la presencia de errores
    Boolean banP = true; //Bandera para ver si ya se inicio la Pila

    String encabezadosRenglones[] = {"prog", "dec", "sigid", "modulos", "argumentos", "lista_argumentos", "sentencias", "else", "tipo", "leer", "L", "L'", "R", "R'", "E", "E'", "T", "T'", "F","$"};
    String encabezadosColumnas[] = {"Inicio", "id","num", "(",")","litCad","litCar","+","-","*","/","=","<",">","<=","@","%","!=", "#","verdadero","falso","si","sino","hacer","mientras","para","mensaje","lectura","llamar","{","}","ent","texto","flot","dbl", "char", "bool",",","metodo", "?",";","ERROR","$","\""};
    
    // Tabla de respaldo por si se desmadra
    String matriz[][] = {
        //{                             "Inico" 0,                                      "id",1                           "num",2	   "(",3	   ")"4		"litCad",5          "litcar",6	     "+",7		 "-", 8		     "*",9		 "/",10 	 "=",11 	    "<",12	      ">",13	        "<=", 14        "@",15	        "%",16          "!=",17       "#",18           "verdadero",19   "falso",21      "si",21                                         "sino",22                    "hacer",23                                                     "mientras",24                                    "para",25                                      "mensaje",26                        "lectura",27                "llamar",28             "{",29               "}",30         "ent",31                	"texto",32                          "flot",33                       "dbl",34                        "char",35                           "bool",36                       ",",37              "metodo",38                                         "?",39         ";",40            "ERROR",41                                   "$",41                      "" "42;   
        /*prog                    0*/{"Inicio id { dec modulos sentencias } ",          "saltar ",                      "saltar ",        "saltar ",      "saltar ",     "saltar ",         "saltar ",       "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "saltar ",       "saltar ",      "saltar ",                                      "saltar ",                  "saltar ",                                                     "saltar ",                                        "saltar ",                                     "saltar ",                          "saltar ",                  "saltar ",              "saltar ",          "saltar ",	"saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                          "saltar ",                                          "saltar ",	"saltar ",       "saltar ",                   "sacar ",                   "sacar "},
        /*dec                     1*/{"saltar ",                                        "0 ",                           "saltar ",        "saltar ",      "saltar ",     "saltar ",         "saltar ",       "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "saltar ",       "saltar ",      "0 ",                                           "saltar ",                  "0 ",                                                          "0 ",                                             "0 ",                                          "0 ",                               "saltar ",                  "0 ",                   "saltar ",          "0 ",           "ent id sigid ; dec ",          "texto id sigid ; dec ",            "flot id sigid ; dec ",         "dbl id sigid ; dec ",          "char id sigid ; dec ",             "bool id sigid ; dec ",         "saltar",                           "0 ",                                               "saltar",	"saltar ",	 "saltar ",                   "0 ",                       "sacar "},
        /*sigid                   2*/{"saltar ",                                        "saltar ",                      "saltar ",        "saltar ",      "saltar ",     "saltar ",         "saltar ",       "saltar ",          "saltar ",          "saltar ",         "saltar ",       "= L sigid ",      "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "saltar ",       "saltar ",      "sacar ",                                       "saltar ",                  "sacar ",                                                      "sacar ",                                         "sacar ",                                      "sacar ",                           "sacar ",                   "saltar ",              "saltar ",          "saltar ",      "0 ",                           "0 ",                               "0 ",                           "0 ",                           "0 ",                               "0 ",                           ", id siglist ",                    "saltar ",                                          "saltar ",	"0 ",	         "saltar ",                   "sacar ",                   "sacar "},
        /*modulos                 3*/{"saltar ",                                        "0 ",                           "saltar ",        "saltar ",      "saltar ",     "saltar ",         "saltar ",       "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "saltar ",       "saltar ",      "saltar ",                                      "saltar ",                  "0 ",                                                          "0 ",                                             "0 ",                                          "0 ",                               "0 ",                       "0 ",                   "saltar ",          "0 ",           "0 ",                           "0 ",                               "0 ",                           "0 ",                           "0 ",                               "0 ",                           "saltar ",                          "metodo id ( argumentos ) { dec sentencias } ",	"saltar ",	"saltar ",	 "saltar ",                   "0 ",                       "sacar "},
        /*argumentos              4*/{"saltar ",                                        "0 ",                           "saltar ",        "saltar ",      "0 ",          "saltar ",         "saltar ",       "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "saltar ",       "saltar ",      "saltar ",                                      "saltar ",                  "saltar ",                                                     "saltar ",                                        "saltar ",                                     "saltar ",                          "saltar ",                  "saltar ",              "sacar ",          "saltar ",	"ent id lista_argumentos ",	"texto id lista_argumentos ",       "flot id lista_argumentos ",    "dbl id lista_argumentos ",     "char id lista_argumentos ",	"bool id lista_argumentos ",	"saltar ",                          "saltar ",                                          "saltar ",	"saltar ",	 "saltar ",                   "sacar ",                   "sacar "},
        /*lista_argumentos        5*/{"saltar ",                                        "saltar ",                      "saltar ",        "saltar ",      "0 ",          "saltar ",         "saltar ",       "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "saltar ",       "saltar ",      "saltar ",                                      "saltar ",                  "saltar ",                                                     "saltar ",                                        "saltar ",                                     "saltar ",                          "saltar ",                  "saltar ",              "saltar ",          "saltar ",	"saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                      "saltar ",                          "saltar ",                      ", tipo id lista_argumentos ",      "saltar ",                                          "saltar ",	"saltar ",	 "saltar ",                   "0 ",                       "sacar "},
        /*semtencias              6*/{"saltar ",                                        "id = leer L ; sentencias ",    "saltar ",        "saltar ",      "saltar ",     "saltar ",         "saltar ",       "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "saltar ",       "saltar ",      "si ( L ) { sentencias } else sentencias ",     "saltar ",                  "hacer { sentencias } mientras ( L ) ; sentencias ",           "mientras ( L ) { sentencias } sentencias ",      "para ( L ) { sentencias } sentencias ",       "mensaje ( L ) ; sentencias ",	"saltar ",                  "saltar ",              "saltar ",          "0 ",           "saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                          "saltar ",                                          "saltar ",	"saltar ",	 "saltar ",                   "0 ",                       "sacar "},
        /*else                    7*/{"saltar ",                                        "0 ",                           "saltar ",        "saltar ",      "saltar ",     "saltar ",         "saltar ",       "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "saltar ",       "saltar ",      "0 ",                                           "sino { sentencias } ",     "0 ",                                                          "0 ",                                             "0 ",                                          "0 ",                               "saltar ",                  "saltar ",              "saltar ",          "0 ",           "saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                          "saltar ",                                          "saltar ",	"saltar ",	 "saltar ",                   "sacar ",                   "sacar "},
        /*tipo                    8*/{"saltar ",                                        "saltar ",                      "saltar ",        "saltar ",      "saltar ",     "saltar ",         "saltar ",       "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "saltar ",       "saltar ",      "saltar ",                                      "saltar ",                  "saltar ",                                                     "saltar ",                                        "saltar ",                                     "saltar ",                          "saltar ",                  "saltar ",              "saltar ",          "saltar ",	"ent ",                         "texto ",                           "flot ",                        "dbl ",                         "char ",                            "bool ",                        "saltar ",                          "saltar ",                                          "saltar ",	"saltar ",	 "saltar ",                   "sacar ",                   "sacar "}, 
        /*leer                    9*/{"saltar ",                                        "saltar ",                      "0 ",             "saltar ",      "saltar ",     "0 ",              "0 ",            "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "0 ",            "0 ",           "saltar ",                                      "saltar ",                  "saltar ",                                                     "saltar ",                                        "saltar ",                                     "saltar ",                          "lectura ( litCad ) ",      "saltar ",              "saltar ",          "saltar ",	"saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                          "saltar ",                                          "saltar ",	"saltar ",	 "saltar ",                   "sacar ",                   "sacar "},
        /*L                      10*/{"saltar ",                                        "R L' ",                        "R L' ",          "R L' ",        "saltar ",     "R L' ",           "R L' ",         "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "R L' ",         "R L' ",        "saltar ",                                      "saltar ",                  "saltar ",                                                     "saltar ",                                        "saltar ",                                     "saltar ",                          "saltar ",                  "saltar ",              "saltar ",          "saltar ",	"saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                          "saltar ",                                          "? L ",         "saltar ",	 "saltar ",                   "0 ",                       "sacar "},
        /*L'                     11*/{"saltar ",                                        "saltar ",                      "saltar ",        "saltar ",      "0 ",          "saltar ",         "saltar ",       "0 ",               "0 ",               "0 ",              "0 ",            "0 ",              "0 ",            "0 ",              "0 ",            "@ R L' ",     "% R L' ",      "0 ",          "0 ",           "0 ",            "0 ",           "sacar ",                                       "saltar ",                  "sacar ",                                                      "sacar ",                                         "sacar ",                                      "sacar ",                           "sacar ",                   "saltar ",              "0 ",               "0 ",           "sacar ",                       "sacar ",                           "sacar ",                       "sacar ",                       "sacar ",                           "sacar ",                       "saltar ",                          "saltar ",                                          "0 ",           "0 ",            "saltar ",                   "sacar ",                   "sacar "},
        /*R                      12*/{"saltar ",                                        "E R' ",                        "E R' ",          "E R' ",        "saltar ",     "E R' ",           "E R' ",         "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "E R' ",         "E R' ",        "saltar ",                                      "saltar ",                  "saltar ",                                                     "saltar ",                                        "saltar ",                                     "saltar ",                          "saltar ",                  "saltar ",              "saltar ",          "saltar ",	"saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                          "saltar ",                                          "saltar ",	"saltar ",	 "saltar ",                   "0 ",                       "sacar "},
        /*R'                     13*/{"saltar ",                                        "saltar ",                      "saltar ",        "saltar ",      "0 ",          "saltar ",         "saltar ",       "0 ",               "0 ",               "0 ",              "0 " ,           "0 ",              "< E ",          "> E ",            "<= E ",         "0 ",          "0 ",           "!= E ",       "# E ",         "0 ",            "0 ",           "sacar ",                                       "saltar ",                  "sacar ",                                                      "sacar ",                                         "sacar ",                                      "sacar ",                           "sacar ",                   "saltar ",              "0 ",               "0 ",           "sacar ",                       "sacar ",                           "sacar ",                       "sacar ",                       "sacar ",                           "sacar ",                       "saltar ",                          "saltar ",                                          "0 ",           "0 ",	         "saltar ",                   "sacar",                    "sacar "},
        /*E                      14*/{"saltar ",                                        "T E' ",                        "T E' ",          "T E' ",        "saltar ",     "T E' ",           "T E' ",         "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "T E' ",         "T E' ",        "saltar ",                                      "saltar ",                  "saltar ",                                                     "saltar ",                                        "saltar ",                                     "saltar ",                          "saltar ",                  "saltar ",              "saltar ",          "saltar ",	"saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                          "saltar ",                                          "saltar ",	"saltar ",	 "saltar ",                   "0 ",                       "sacar "},
        /*E'                     15*/{"saltar ",                                        "saltar ",                      "saltar ",        "saltar ",      "0 ",          "saltar ",         "saltar ",       "+ T E' ",          "- T E' ",          "0 ",              "0 ",            "0 ",              "0 ",            "0 ",              "0 ",            "0 ",          "0 ",           "0 ",          "0 ",           "0 ",            "0 ",           "sacar ",                                       "saltar ",                  "sacar ",                                                      "sacar ",                                         "sacar ",                                      "sacar ",                           "sacar ",                   "saltar ",              "0 ",               "0 ",           "sacar ",                       "sacar ",                           "sacar ",                       "sacar ",                       "sacar ",                           "sacar ",                       "saltar ",                          "saltar ",                                          "0 ",           "0 ",	         "saltar ",                   "sacar ",                   "sacar "},
        /*T                      16*/{"saltar ",                                        "F T' ",                        "F T' ",          "F T' ",        "saltar ",     "F T' ",           "F T' ",         "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "F T' ",         "F T' ",        "saltar ",                                      "saltar ",                  "saltar ",                                                     "saltar ",                                        "saltar ",                                     "saltar ",                          "saltar ",                  "saltar ",              "saltar ",          "saltar ",	"saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                          "saltar ",                                          "saltar ",	"saltar ",	 "saltar ",                   "0 ",                       "sacar "},
        /*T'                     17*/{"saltar ",                                        "saltar ",                      "saltar ",        "saltar ",      "0 ",          "saltar ",         "saltar ",       "0 ",               "0 ",               "* F T' ",         "/ F T' ",       "0 ",              "0 ",            "0 ",              "0 ",            "0 ",          "0 ",           "0 ",          "0 ",           "0 ",            "0 ",           "sacar ",                                       "saltar ",                  "sacar ",                                                      "sacar ",                                         "sacar ",                                      "sacar ",                           "sacar ",                   "saltar ",              "0 ",               "0 ",           "sacar ",                       "sacar ",                           "sacar ",                       "sacar ",                       "sacar ",                           "sacar ",                       "saltar ",                          "saltar ",                                          "0",            "0 ",            "saltar ",                   "sacar ",                   "sacar "},
        /*F                      18*/{"saltar ",                                        "id ",                          "num ",           "( L ) ",       "saltar ",     "litCad ",         "litCar ",       "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "verdadero ",    "falso ",       "saltar ",                                      "saltar ",                  "saltar ",                                                     "saltar ",                                        "saltar ",                                     "saltar ",                          "saltar ",                  "saltar ",              "saltar ",          "saltar ",	"saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                          "saltar ",                                          "saltar ",	"saltar ",	 "saltar ",                   "0 ",                   "sacar "},
        /*$                      19*/{"saltar ",                                        "saltar ",                      "saltar ",        "saltar ",      "saltar ",     "saltar ",         "saltar ",       "saltar ",          "saltar ",          "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",         "saltar ",       "saltar ",     "saltar ",      "saltar ",     "saltar ",      "saltar ",       "saltar ",      "saltar ",                                      "saltar ",                  "saltar ",                                                     "saltar ",                                        "saltar ",                                     "saltar ",                          "saltar ",                  "saltar ",              "saltar ",          "saltar ",	"saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                      "saltar ",                          "saltar ",                      "saltar ",                          "saltar ",                                          "saltar ",      "saltar ",	 "saltar ",                   "acepta ",                   "sacar "}};
    
    
    
    NumeroLinea numeroLinea;

    public Com() {
        initComponents();
          colors();
        Inicializa();
        cosasVisuales();
        Inicial();

    }

    private void Inicial() {

        title = "Compiler";

        // Coloca la ventana en el centro de la pantalla.
        setLocationRelativeTo(null);

        //Numero de linea
        numeroLinea = new NumeroLinea(escritura);
        jScrollPane1.setRowHeaderView(numeroLinea);

        // Crea un nuevo objeto Directory.
        // "this" se refiere al objeto actual, "escritura" es una referencia a algún tipo de componente de texto, "title" es el título y ".ldas"  la extensión de un tipo de archivo.
        directorio = new Directory(this, escritura, title, ".mad");

        // Agrega un escuchador de eventos a la ventana. Este escuchador responde a cuando la ventana se está cerrando.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Antes de que la ventana se cierre, se llama a la función Exit() del objeto directorio.
                directorio.Exit();
                // Después se cierra la aplicación.
                System.exit(0);
            }
        });

        // Se llama a una función que parece establecer un número de línea en el componente de texto "escritura".
        //Functions.setLineNumberOnJTextComponent(escritura);
        // Inicializa un nuevo temporizador que se detiene después de 0.3 segundos.
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop();
        });

        // Llama a una función que parece insertar un asterisco en el nombre. 
        // La función también reinicia el temporizador cuando se libera una tecla.
        Functions.insertAsteriskInName(this, escritura, () -> {
            timerKeyReleased.restart();
        });

        // Configura un componente de texto "escritura" para autocompletar basado en un conjunto de cadenas vacío.
        // La función también reinicia el temporizador cuando se libera una tecla.
    }

    public void Inicializa() {
        tokens = new ArrayList<>();

    }

    private void analisisLexico() {
        Lexer lexer = null;// Creamos un objeto lexer
        try {
            File sourceCodeFile = new File("code.encrypter");// Creamos un nuevo archivo llamado "code.encrypter" 

            FileOutputStream fileOutputStream = new FileOutputStream(sourceCodeFile);// Creamos un flujo de salida para escribir datos al archivo
            byte[] bytesOfText = escritura.getText().getBytes();// Obtenemos el texto de la variable escritura y lo convertimos a bytes      

            fileOutputStream.write(bytesOfText); // Escribimos los bytes al archivo

            BufferedReader fileInputReader = new BufferedReader(new InputStreamReader(new FileInputStream(sourceCodeFile), "UTF8"));// Creamos un flujo de entrada para leer datos del archivo,

            lexer = new Lexer(fileInputReader);// Inicializamos el lexer con el contenido del archivo

            while (true) { // Iteramos a través de cada token producido por el lexer hasta que no haya más
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                // Añadimos cada token a la lista de tokens
                tokens.add(token);
            }
        } catch (FileNotFoundException ex) {
            // En caso de que el archivo no pueda ser encontrado, se muestra un mensaje de error
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            // En caso de un error de entrada/salida, se muestra un mensaje de error
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    private void analisisLexicoEerr() {

        // Recorrer todos los tokens
        for (int i = 0; i < tokens.size(); i++) {

            // Si el token es de tipo ERROR
            if ("ERROR".equals(tokens.get(i).getLexicalComp())) {

                // En función del tipo de error, añadir el mensaje correspondiente
                String errorMsg = "Error Lexico linea " + tokens.get(i).getLine() + ": token [ " + tokens.get(i).getLexeme() + " ] ";
                if (tokens.get(i).getLexeme().matches("[\"]")) {
                    errorMsg += "se esperaba comilla doble de cierre ";
                    errorFlag = 1;
                } else if (tokens.get(i).getLexeme().matches("[\']")) {
                    errorMsg += "se esperaba comilla simple de cierre ";
                    errorFlag = 1;
                } else if (tokens.get(i).getLexeme().matches("'[^']*'")) {
                    errorMsg += "se esperaba solo un caracter entre las comillas simples ";
                    errorFlag = 1;
                } else {
                    errorMsg += "no es valido ";
                    errorFlag = 1;
                }

                // Añadir la línea y columna del error
                errorMsg += "[" + tokens.get(i).getLine() + ", " + tokens.get(i).getColumn() + "]\n";

                // Actualizar el contenido del campo mensajes
                mensajes.setText(mensajes.getText() + errorMsg);
            }
            //AnalisisSintactico(tokens.get(i).getLexicalComp());
        }
    }

    public void AnalisisSintacticoInicioPila() {

        // Si la bandera 'ban' es verdadera.
        if (banP) {

            pila.push("$"); // Coloca en la pila el símbolo de fin de archivo o de fin de entrada "$".          
            pila.push("prog "); // Añade a la pila el símbolo no terminal "prog".

            // banP = false;  // Asigna a la bandera 'banP' el valor de false.
            // Imprime en consola el contenido de la pila.
            // Crear una nueva tabla para mostrar la pila y las acciones
            modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("Pila");
            modeloTabla.addColumn("Token");
            modeloTabla.addColumn("Accion");
            modeloTabla.addColumn("Indice");
            jTable1.setModel(modeloTabla);

            System.out.println(pila + "");
            modeloTabla.addRow(new Object[]{pila.toString(), " ", " "});

        }

    }

    boolean banpr = true;

    public void AnalisisSintactico(String token, int linea,String caracter) {

        String accion;
        String indice = Renglon() + "-" + Columna(token);
        accion = matriz[Renglon()][Columna(token)];

        try {

            switch (pila.peek()) {

                case "{":
                    if (accion.equals("ent id lista_argumentos ")) {
                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba un { \n" + "atras del token: " + token + "\n");

                        break;
                    }
            }

            if (token.equals(pila.peek())) {
                accion = "Concuerda";
                if (token.equals("$")) {
                    //Aqui le queria poner unas cosillas  
                    
                    
                    pila.pop();
                    modeloTabla.addRow(new Object[]{pila.toString(), pila.peek(), accion, indice});
                    
                    return;
                } else // Si el token de entrada no es el símbolo de fin de entrada, procesa el símbolo en la cima de la pil
                {
                    
                    modeloTabla.addRow(new Object[]{pila.toString(), pila.peek(), accion, indice});
                    
                }
                pila.pop();
                 modeloTabla.addRow(new Object[]{pila.toString(), pila.peek(), accion, indice});

            }else if(terminal(pila.peek(),token)){
                
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba " +pila.peek()+" \n");
                pila.pop();
                modeloTabla.addRow(new Object[]{pila.toString(), pila.peek(), accion, indice});
                this.AnalisisSintactico(token, linea,caracter);//Sigue con el mismo toke  
            }else if (accion == "saltar ") {
                
                
                 if (caracter.contains("\"")) {

                            mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba cierre de comillas\n");

                          
                                 
                        }
                 
                 
                 if (caracter.contains("\'")) {

                            mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba cierre de comilla simple\n");

                           
                        }

                switch (pila.peek()) {
                    case "prog ":

                        if (banpr) {

                            mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba la palabra 'Inicio' \n");

                            banpr = false;
                        }
                        break;

                    case "id":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba un id \n");
                        break;

                    case "{":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba una llabe de apertura '{' \n");
                        break;

                    case "}":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba una llabe de cierre '}' \n");
                        break;

                    case "(":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba una parentecis de apertura '(' \n");
                        break;

                    case ")":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba una parentecis de cierre ')' \n");
                        break;

                    case "L":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaban argumentos \n");
                        break;

                    case "T'":
                        if (token.equals(",")) {
                            mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": no puede seguir un ' , ' \n");
                        } else {
                            mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaban algun operador o comparacion \n");
                        }
                        break;

                    case ";":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba ;  \n");

                        break;

                    case "sigid":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba ;  \n");

                        break;
                        
                        case "=":
                        if(!token.equals(pila.peek())){
                            mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba =  \n");
                        }
                        break;
                        
                        case "sentencias":
                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba una sentencia  \n");

                        break;
                }

                System.out.println(pila.toString());
                modeloTabla.addRow(new Object[]{pila.toString(), token, accion, indice});

                return;

            } else if (accion == "sacar ") {

                switch (pila.peek()) {
                    case "prog ":

                        if (banpr) {

                            mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba la palabra 'Inicio' \n");

                            banpr = false;
                        }
                        break;

                    case "id":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba un id \n");
                        break;

                    case "{":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba una llabe de apertura '{' \n");
                        break;

                    case "}":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba una llabe de cierre '}' \n");
                        break;

                    case "(":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba una parentecis de apertura '(' \n");
                        break;

                    case ")":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba una parentecis de cierre ')' \n");
                        break;

                    case "L":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaban argumentos \n");
                        break;

                    case "T'":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaban ; \n");

                        break;

                    case "sigid":

                        mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba ;  \n");

                        break;
                    case "dec":
                        // verifica si el token no es un tipo de dato
                        if (!tipo(token)) {
                            mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba tipo  \n");
                        }
                        break;

                }

                modeloTabla.addRow(new Object[]{pila.toString(), token, accion, indice});
                System.out.println(pila.toString());

                System.out.println("ERROR: Antes del pop habia:  " + token);
                pila.pop();
                System.out.println("ERROR: Despues del pop habia:  " + token);
                this.AnalisisSintactico(token, linea,caracter);//Sigue con el mismo token   

            } else if (accion == "0 ") {

                modeloTabla.addRow(new Object[]{pila.toString(), token, accion, indice});
                System.out.println(pila.toString());
                pila.pop();
                this.AnalisisSintactico(token, linea,caracter);//Sigue con el mismo toke  

            } else {
                ///PILA AUXILIAR

                String palabras = ""; //Almacena las palabras de la cadena
                //modeloTabla.addRow(new Object[]{pila.toString(), token, accion, indice});
                //System.out.println(pila.toString());
                
                pila.pop();
                for (int j = 0; j < accion.length(); j++) //Si el caracter no es un espacio en blanco entonces se concatena pal
                {

                    if (accion.charAt(j) != ' ') {
                        palabras += accion.charAt(j) + "";
                    } //Si es un espacio en blanco indica que se completo una palabra y entonces se agrega la pila auxiliar
                    else {
                        pilaaux.push(palabras); //Agrega la palabra a la pila auxiliar
                        palabras = "";
                    }
                }
                do {
                    pila.push(pilaaux.pop()); //Agrega los elementos de la pila auxiliar a la pila original
                } while (!pilaaux.isEmpty());

                modeloTabla.addRow(new Object[]{pila.toString(), token, accion, indice});
                System.out.println(pila.toString());

                this.AnalisisSintactico(token, linea,caracter);//Sigue con el mismo token

            }

        } catch (Exception e) {
            System.out.println("cdscsdx");
        }

    }

    private boolean tipo(String token) {
        List<String> tipos = Arrays.asList("ent", "texto", "flot", "dbl", "char", "bool");
        boolean resultado = tipos.contains(token);
        return resultado;
    }

    private void llenarPila() {
        tokens.forEach(token -> {

            variable = token.getLine();

            //Imprimir el token en mi JTextpanel de mi analizador lexico 
            if (token.getLexicalComp() == "pReservada" || token.getLexicalComp() == "tipoDa") {

                AnalisisSintactico(token.getLexeme(), token.getLine(),token.getLexeme());
            }  else if (token.getLexicalComp() == "ERROR") {
                 AnalisisSintactico(token.getLexicalComp(), token.getLine(),token.getLexeme());
            }else {

                AnalisisSintactico(token.getLexicalComp(), token.getLine(),token.getLexeme());

            }
        });
        
         mensajes.setText(mensajes.getText() + "Analisis terminado...");
    }
    private boolean terminal(String Ptoken,String token){
        for (String encabezadosColumna : encabezadosColumnas) {
            if(encabezadosColumna.equals(Ptoken)){
                return !Ptoken.equals(token);
            }
        }
        return false;
    }
    private void llenarJPnaleTokens() {
        tokens.forEach(token -> {

            variable = token.getLine();

            //solucion rapida para imprimir el salto de linea casda que se encuentre cambio en la linea 
            if (variable != valorAnterior) {
                lexico.setText(lexico.getText() + "\n");// Imprimir salto de línea
                valorAnterior = token.getLine();
            }
            if (token.getLexicalComp() == "pReservada" || token.getLexicalComp() == "tipoDa") {

                lexico.setText(lexico.getText() + " " + token.getLexeme());
                //Imprimir el token en mi JTextpanel de mi analizador lexico 
            } else if (token.getLexicalComp() == "ERROR") {
                lexico.setText(lexico.getText() + " ");
            } else {

                lexico.setText(lexico.getText() + " " + token.getLexicalComp());
            }

        });
    }

    //Metodo que busca un elemento dentro del encabezadosRenglones y devuelve el indice o posicion donde lo encontro
    public int Renglon() {
        for (int i = 0; i < encabezadosRenglones.length; i++) {
            if (encabezadosRenglones[i].equals(pila.peek())) {
                renglones = i;
                break;
            }
        }
        return renglones;
    }

    //ESTE METODO ES DE MARTIN SABRA DIOS DE DONDE SE SACO ESA SOLUCION PROVICIONAL XD
    public void FaltaPila(int linea) {
        switch (pila.peek()) {
            case "id":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente token un id \n");
                break;

            case "{":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente token un { \n");
                break;

            case "modulos":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente un modulos o dec o sentencias o } \n");
                break;

            case "}":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente token un } \n");
                break;

            case "dec":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente un dec o sentencias o } \n");
                break;

            case "sentencias":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente sentencias o } \n");
                break;

            case "(":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente token un ( \n");
                break;

            case ")":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente token un ) \n");
                break;

            case "argumentos":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente token un argumentos o ) \n");
                break;

            case "sigid":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente token un = o , o ; \n");
                break;

            case "L":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente token un operador \n");
                break;

            case "L'":
            case "R'":
            case "E'":
            case "T'":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente token un ; si es declaracion o un == para los metodos si y sino o en casos de cierre un ) para las sentencias \n");
                break;

            case "E":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente token un operador o num\n");
                break;

            case ";":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente token un ;\n");
                break;

            case "else":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente token  sino\n");
                break;

            case "=":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente token  = \n");
                break;

            case "leer":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Error sintactico linea " + linea + ": Se esperaba como siguiente token  num \n");
                break;

            case "$":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Analisis Sintactico Correcto\n");
                break;

            case "":
                mensajes.setText("");
                mensajes.setText(mensajes.getText() + "Analisis Sintactico Incorrecto\n");
                break;
        }
    }

    //Metodo que busca la posicion del token dentro de encabezadosColumnas y devuelve su posicion
    public int Columna(String token) {
        for (int i = 0; i < encabezadosColumnas.length; i++) {
            if (token.equals(encabezadosColumnas[i])) {
                columnas = i;
                break;
            }
        }
        return columnas;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        mensajes = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        escritura = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        lexico = new javax.swing.JTextPane();
        bRun = new javax.swing.JButton();
        bNuevo = new javax.swing.JButton();
        bAbrir = new javax.swing.JButton();
        bGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane3.setViewportView(mensajes);

        jTabbedPane1.addTab("Errores", jScrollPane3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTable1);

        jTabbedPane1.addTab("Pila", jScrollPane4);

        jScrollPane1.setViewportView(escritura);

        jScrollPane2.setViewportView(lexico);

        bRun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/boton-de-play.png"))); // NOI18N
        bRun.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bRun.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/jugar (1).png"))); // NOI18N
        bRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRunActionPerformed(evt);
            }
        });

        bNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar-archivo.png"))); // NOI18N
        bNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bNuevo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/agregar-archivo (1).png"))); // NOI18N
        bNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNuevoActionPerformed(evt);
            }
        });

        bAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/caja (1).png"))); // NOI18N
        bAbrir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bAbrir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/caja (2).png"))); // NOI18N
        bAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAbrirActionPerformed(evt);
            }
        });

        bGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/disquete (1).png"))); // NOI18N
        bGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bGuardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salvar (1).png"))); // NOI18N
        bGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CODIGO");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LEXICO");

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Nuevo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Abrir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Guardar");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Run");

        jMenuItem4.setText("Compilar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Salir");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(bNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bAbrir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bRun))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bAbrir)
                    .addComponent(bNuevo)
                    .addComponent(bGuardar)
                    .addComponent(bRun))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(15, 15, 15)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRunActionPerformed
        StyledDocument doc = escritura.getStyledDocument();
            try{
                if (doc.getLength() > 0 && doc.getText(doc.getLength() - 1, 1).equals("$")) {
                // No se agrega otro símbolo de dólar ($) si ya existe
            } else {
                // Inserta el símbolo de dólar ($) al final del texto
                doc.insertString(doc.getLength(), "$", null);
               
            }
            }catch(BadLocationException e){
                
            }
        pila.clear();
        pilaaux.clear();
        banP = true;
        banpr = true;
        renglones = 0;
        columnas = 0;
        mensajes.setText("");
        
        
        if (escritura.getText().isEmpty()){
         mensajes.setText("No hay nada que analizar");
        
        }else {
          borrarComponentes();
        analisisLexico();
        llenarJPnaleTokens();
        analisisLexicoEerr();
        AnalisisSintacticoInicioPila();
        llenarPila();
        //noDuplicados();
        
        }

        
        
      
    }//GEN-LAST:event_bRunActionPerformed

    private void bNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNuevoActionPerformed
        directorio.New();
        borrarComponentes();// TODO add your handling code here:
    }//GEN-LAST:event_bNuevoActionPerformed

    private void bAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAbrirActionPerformed
        if (directorio.Open()) {

            borrarComponentes();
        }
    }//GEN-LAST:event_bAbrirActionPerformed

    private void bGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarActionPerformed
        if (directorio.Save()) {
            borrarComponentes();
        }
    }//GEN-LAST:event_bGuardarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        directorio.New();
        borrarComponentes();// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (directorio.Open()) {

            borrarComponentes();
        }   // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        pila.clear();
        pilaaux.clear();
        banP = true;
        banpr = true;
        renglones = 0;
        columnas = 0;

        borrarComponentes();
        analisisLexico();
        llenarJPnaleTokens();
        analisisLexicoEerr();
        AnalisisSintacticoInicioPila();
        llenarPila();
        //imprimirConsola();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    public void cosasVisuales() {

    }

    
    public void noDuplicados(){
    
    String[] lines = mensajes.getText().split("\n");
                Set<String> set = new LinkedHashSet<String>(Arrays.asList(lines));

                String noDuplicateText = String.join("\n", set);
                mensajes.setText(noDuplicateText);
    
    }
    
    private void borrarComponentes() {

        lexico.setText("");
        valorAnterior = 1;
        variable = 1;
        mensajes.setText("");
        tokens.clear();
        // errors.clear();
        // identProd.clear();
        // identificadores.clear();
    }
    
    
     // ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // COLOREAR LAS COSAS
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //METODO PARA ENCONTRAR LAS ULTIMAS CADENAS
    private int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            //  \\W = [A-Za-Z0-9]
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    //METODO PARA ENCONTRAR LAS PRIMERAS CADENAS 
    private int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }   
 //METODO PARA PINTAS LAS PALABRAS RESEVADAS
    private void colors() {

        final StyleContext cont = StyleContext.getDefaultStyleContext();

        //COLORES 
        final AttributeSet attred = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(225, 123, 13));//naranja
        final AttributeSet attgreen = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(48, 101, 59)); //verde
        final AttributeSet attblue = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(0, 0, 0)); //Blanco
        final AttributeSet attblack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(145, 55, 76));
        final AttributeSet blanco = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(0, 0, 0)); //Blanco
        //STYLO 
        DefaultStyledDocument doc = new DefaultStyledDocument() {
            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                        if (text.substring(wordL, wordR).matches("(\\W)*()")) { //Azul 
                            setCharacterAttributes(wordL, wordR - wordL, attblue, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(para|verdadero|falso|mensaje|lectura)")) { //verde
                            setCharacterAttributes(wordL, wordR - wordL, attgreen, false);
                        } else if (text.substring(wordL, wordR).matches("(\\W)*(Inicio|metodo|si|sino|hacer|mientras)")) { // blanco
                            setCharacterAttributes(wordL, wordR - wordL, attred, false);
                        } 
                         else if (text.substring(wordL, wordR).matches("(\\W)*($)")) { // blanco
                            setCharacterAttributes(wordL, wordR - wordL, blanco, false);
                        
                         }else {
                            setCharacterAttributes(wordL, wordR - wordL, attblack, false);
                        }
                        wordL = wordR;

                    }
                    wordR++;
                }
            }

            public void romeve(int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) {
                    before = 0;
                }
            }
        };

        JTextPane txt = new JTextPane(doc);
        String temp = escritura.getText();
        escritura.setStyledDocument(txt.getStyledDocument());
        escritura.setText(temp);

    }

    // ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    
    

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Com().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAbrir;
    private javax.swing.JButton bGuardar;
    private javax.swing.JButton bNuevo;
    private javax.swing.JButton bRun;
    private javax.swing.JTextPane escritura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane lexico;
    private javax.swing.JTextPane mensajes;
    // End of variables declaration//GEN-END:variables
}

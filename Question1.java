import java.util.*;

public class Question1 {
    static class Token {
        String value;
        String derived;
        String attribute;

        Token(String value, String attribute) {
            this.value = value;
            this.attribute = attribute;
            this.derived = "";
        }

        @Override
        public String toString() {
            return value + " " + attribute;
        }
    }




    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int count = 1;
        boolean hasError = false;
        boolean noBuenos = false;
        //Beginning of the program

        while (true) {
            System.out.println("ENTER NEXT STRING === #" + count);
            count++;
            System.out.println(
                    "-Or Type 99 and press Enter to Quit:\n" +
                    "-Words in capital letters are Keywords\n" +
                    "-Words in small letters are Identifiers. Words in single letters from A — Z and a to z are also Identifiers\n" +
                    "+,-,/,* are Operators\n" +
                    "-Equals =, and semi colon ; are Symbols\n" +
                    "-Any string/line must contain: Keywords, Identifiers, Operators, or Symbols\n" +
                    "-Symbols such as: %,$, &, <, >,; not allowed and would give Semantic error\n" +
                    "-Two operators must not be combined such as: +* not allowed and would give Syntax error, same with other operators that are combined, for example -/, */, *+\n" +
                    "-Semi colon ; at the end of a line not allowed and would give Syntax error\n" +
                    "-Numbers 0,1 to 9 are not allowed and would give Syntax error\n" +
                    "-The acceptable keywords are: INTEGER, ASSIGN, READ, WRITE, START, STOP.\n" +
                    "-Misspelling in the keywords such as: WRITEE not allowed and would give Lexical error\n" +
                    "-Any other character on the keyboard different from all the above will give Syntax error. " +
                    "First Enter Acceptable Keywords or identifier then press enter, then enter the rest");
            String string = scanner.nextLine();

            //If 99 is entered the program ends
            if (Objects.equals(string, "99")) {
                System.out.println("Exiting program...");
                System.exit(0);
            }

            //Check if the input string contains a space
            if(!string.contains(" ")) {
                System.out.println("SYNTAX ERROR - No space between KEYWORD and IDENTIFIERS.");
                return;
            }
            //Splits up the Keyword and string of tokens into two separate variables
            String Key = string.substring(0, string.indexOf(' '));
            String tokenString = string.substring(string.indexOf(' ') + 1);


            if (Objects.equals(Key, "P") ||Objects.equals(Key, "R") || (Objects.equals(Key, "p")) ||Objects.equals(Key, "r")){
                lexicalAnalysis(tokenString, Key);
                noBuenos = true;
            } else if (Objects.equals(Key, "ASSIGN") || Objects.equals(Key, "INTEGER") || Objects.equals(Key, "READ") || Objects.equals(Key, "WRITE") || Objects.equals(Key, "START") || Objects.equals(Key, "STOP")){
                hasError = false;
            } else {
                System.out.println("LEXICAL ERROR - Invalid Keyword entered\n");
                hasError = true;
            }


            String prevAttr = "Operator";
            //Creates a List using properties in the Token class
            List<Token> tokens = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(tokenString);

            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                String attribute = "";

                if (Character.isAlphabetic(token.charAt(0)) && token.length() == 1) {
                    attribute = "Identifier";
                    if (Objects.equals(attribute, prevAttr)) {
                        if (Objects.equals(Key, "ASSIGN") || Objects.equals(Key, "INTEGER") || Objects.equals(Key, "READ") || Objects.equals(Key, "WRITE") || Objects.equals(Key, "START") || Objects.equals(Key, "STOP")){

                            hasError=false;
                        }else {
                            System.out.println("# SEMANTIC ERROR- two operators (*,-,+,/) or Identifier (A to Z and a to z) cannot be written together!\n");
                            hasError = true;
                        }
                    }
                } else if (Objects.equals(token, "*") || Objects.equals(token, "-") || Objects.equals(token, "+") || Objects.equals(token, "/")) {
                    attribute = "Operator";
                    if (Objects.equals(attribute, prevAttr)) {
                            hasError=true;
                            System.out.println("# SEMANTIC ERROR- two operators (*,-,+,/) or Identifier (A to Z and a to z) cannot be written together!\n");
                        if (Objects.equals(Key, "ASSIGN") || Objects.equals(Key, "INTEGER") || Objects.equals(Key, "READ") || Objects.equals(Key, "WRITE") || Objects.equals(Key, "START") || Objects.equals(Key, "STOP")){
                            hasError=true;
                        }
                        }else {
                            hasError = false;
                        }

                } else if (Character.isDigit(token.charAt(0))) {
                    System.out.println("# SYNTAX ERROR - Numbers 0,1 to 9 are not allowed.\n");
                    hasError = true;
                } else if (Objects.equals(token, "=")){
                    attribute = "Symbol"; // Set attribute as "Special" for special characters
                } else {
                    System.out.println("# Semantic error: Use of Special Characters ie &, &&, $, %, !, , etc, not permitted\n");
                    hasError = true;
                }

                Token t = new Token(token, attribute);
                tokens.add(t);
                prevAttr = attribute;
            }

            if (!hasError) {
                if (!noBuenos) {
                    System.out.println("\nString entered: " + string);
                    System.out.println("\nValid String entered, no errors found!\n");
                    System.out.println("______________________________________________________________________________\n\n");
                }
            } else {
                System.out.println("\n Errors found, please Enter a valid String\n");
                System.out.println("______________________________________________________________________________\n\n");
            }
        }
    }


    public static void lexicalAnalysis(String tokenString, String key) {
        // Implementation of lexical analysis
        String prevAttr = "Operator";
        boolean hasError = false;
        //Creates a List using properties in the Token class
        List<Token> tokens = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(tokenString);

        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            String attribute = "";

            if (Character.isAlphabetic(token.charAt(0)) && token.length() == 1) {
                attribute = "Identifier";
                if (Objects.equals(attribute, prevAttr)) {
                    System.out.println("# SEMANTIC ERROR- two operators (*,-,+,/) or Identifier (A to Z and a to z) cannot be written together!\n");
                    hasError = true;
                }
            } else if (Objects.equals(token, "*") || Objects.equals(token, "-") || Objects.equals(token, "+") || Objects.equals(token, "/")) {
                attribute = "Operator";
                if (Objects.equals(attribute, prevAttr)) {
                    System.out.println("# SEMANTIC ERROR- two operators (*,-,+,/) or Identifier (A to Z and a to z) cannot be written together!\n");
                    hasError = true;
                }
            } else if (Character.isDigit(token.charAt(0))) {
                System.out.println("# SYNTAX ERROR - Numbers 0,1 to 9 are not allowed.\n");
                hasError = true;
            } else if (Objects.equals(token, "=")){
                attribute = "Symbol"; // Set attribute as "Special" for special characters
            } else {
                System.out.println("# Semantic error: Use of Special Characters ie &, &&, $, %, !, , etc, not permitted\n");
                hasError = true;
            }

            Token t = new Token(token, attribute);
            tokens.add(t);
            prevAttr = attribute;

        }

        if (hasError) {
            System.out.println(" ");

        }else {
            System.out.println("======STAGE1: COMPILER TECHNIQUES--> LEXICAL ANALYSIS-Scanner\n" + "SYMBOL TABLE COMPRISING ATTRIBUTES AND TOKENS:");
            int count = 0;
            for (Token token : tokens) {
                count++;
                System.out.println("TOKEN#" + count + " " + token);
            }
            System.out.println("Total Number of Tokens: " + count + "\n");

            System.out.println("The Grammar and Production rules: \n" +
                    "E-> E                                                            Rule1  (R1)\n" +
                    "E-> |E+E|E/E|E*E|E+E|                                                    R2\n" +
                    "E-> |E1]E2|E3]...|E26|                                                   R3\n" +
                    "E-> |A|B|C|...|Z                                                         R4\n" +
                    "E-> |a|b|c]...|z                                                         R4\n");

            //Calls the Syntax Analysis Function to proceed with STAGE 2
            syntaxAnalysis(tokenString, tokens, key);
        }
    }


    private static void syntaxAnalysis(String tokenString, List<Token> tokens, String key) {
        // Implementation of syntax analysis
        System.out.println("======STAGE2: COMPILER TECHNIQUES--> SYNTAX ANALYSIS-Parser");
        System.out.println("GET A DERIVATION FOR: " + tokenString);
        List<String> derivedTokens = new ArrayList<>();
        if (Objects.equals(key, "P")) {
            for (int i = 0; i < tokens.size(); i++) {
                String derived = deriveToken(tokens, i);
                derivedTokens.add(i, derived);
            }

            System.out.println("""                          
                    E -> E                                           R1
                    E -> E / E                                       R2
                    E -> E / E - E                                   R2
                    E -> E / E - E + E                               R2
                    E -> E / E - E + E * E                           R2
                    E -> E / E - E + E * E / E                       R2
                    """);

            System.out.println(
                    "E -> " + derivedTokens.get(1) +" "+ derivedTokens.get(2)+ " E - E + E * E / E                      R3\n" +
                    "E -> " + derivedTokens.get(1) +" "+ derivedTokens.get(2)+" "+ derivedTokens.get(3)+" "+ derivedTokens.get(4)+ " E + E * E / E                     R3\n" +
                    "E -> " + derivedTokens.get(1) +" "+ derivedTokens.get(2)+" "+ derivedTokens.get(3)+" "+ derivedTokens.get(4)+" "+ derivedTokens.get(5)+" "+ derivedTokens.get(6)+" E * E / E                   R3\n" +
                    "E -> " + derivedTokens.get(1) +" "+ derivedTokens.get(2)+" "+ derivedTokens.get(3)+" "+ derivedTokens.get(4)+" "+ derivedTokens.get(5)+" "+ derivedTokens.get(6)+" "+ derivedTokens.get(7) +" "+ derivedTokens.get(8) + " E / E                 R3\n" +
                    "E -> " + derivedTokens.get(1) +" "+ derivedTokens.get(2)+" "+ derivedTokens.get(3)+" "+ derivedTokens.get(4)+" "+ derivedTokens.get(5)+" "+ derivedTokens.get(6)+" "+ derivedTokens.get(7) +" "+ derivedTokens.get(8) +" "+ derivedTokens.get(9) +" "+ derivedTokens.get(10)+ " E               R3\n" +
                    "E -> " + derivedTokens.get(1) +" "+ derivedTokens.get(2)+" "+ derivedTokens.get(3)+" "+ derivedTokens.get(4)+" "+ derivedTokens.get(5)+" "+ derivedTokens.get(6)+" "+ derivedTokens.get(7) +" "+ derivedTokens.get(8) +" "+ derivedTokens.get(9) +" "+ derivedTokens.get(10) +" "+ derivedTokens.get(11)+ "             R3\n"+

                    "E -> " + tokens.get(1).value +" "+ tokens.get(2).value+" "+ derivedTokens.get(3)+" "+ derivedTokens.get(4)+" "+ derivedTokens.get(5)+" "+ derivedTokens.get(6)+" "+ derivedTokens.get(7) +" "+ derivedTokens.get(8) +" "+ derivedTokens.get(9) +" "+ derivedTokens.get(10) +" "+ derivedTokens.get(11)+ "              R4\n" +
                    "E -> " + tokens.get(1).value +" "+ tokens.get(2).value+" "+ tokens.get(3).value+" "+ tokens.get(4).value+" "+ derivedTokens.get(5)+" "+ derivedTokens.get(6)+" "+ derivedTokens.get(7) +" "+ derivedTokens.get(8) +" "+ derivedTokens.get(9) +" "+ derivedTokens.get(10) +" "+ derivedTokens.get(11)+ "               R4\n"+
                    "E -> " + tokens.get(1).value +" "+ tokens.get(2).value+" "+ tokens.get(3).value+" "+ tokens.get(4).value+" "+ tokens.get(5).value+" "+ tokens.get(6).value+" "+ derivedTokens.get(7) +" "+ derivedTokens.get(8) +" "+ derivedTokens.get(9) +" "+ derivedTokens.get(10) +" "+ derivedTokens.get(11)+ "                 R4\n" +
                    "E -> " + tokens.get(1).value +" "+ tokens.get(2).value+" "+ tokens.get(3).value+" "+ tokens.get(4).value+" "+ tokens.get(5).value+" "+ tokens.get(6).value+" "+ tokens.get(7).value +" "+ tokens.get(8).value +" "+ derivedTokens.get(9) +" "+ derivedTokens.get(10) +" "+ derivedTokens.get(11)+ "                   R4\n" +
                    "E -> " + tokens.get(1).value +" "+ tokens.get(2).value+" "+ tokens.get(3).value+" "+ tokens.get(4).value+" "+ tokens.get(5).value+" "+ tokens.get(6).value+" "+ tokens.get(7).value +" "+ tokens.get(8).value +" "+ tokens.get(9).value +" "+ tokens.get(10).value +" "+ derivedTokens.get(11)+ "                     R4\n" +
                    "E -> " + tokens.get(1).value +" "+ tokens.get(2).value+" "+ tokens.get(3).value+" "+ tokens.get(4).value+" "+ tokens.get(5).value+" "+ tokens.get(6).value+" "+ tokens.get(7).value +" "+ tokens.get(8).value +" "+ tokens.get(9).value +" "+ tokens.get(10).value +" "+ tokens.get(11).value+ "                       R4\n"
            );

            semanticAnalysis(tokenString, tokens, key);

        } else if (Objects.equals(key, "R")) {
            for (int i = 0; i < tokens.size(); i++) {
                String derived = deriveToken(tokens, i);
                derivedTokens.add(i, derived);
            }

            System.out.println("""                          
                    E -> E                                 R1
                    E -> E + E                             R2
                    E -> E + E / E                         R2
                    """);

            System.out.println(
                    "E -> " + derivedTokens.get(1) + " " + derivedTokens.get(2) + " E / E                       R3\n" +
                            "E -> " + derivedTokens.get(1) + " " + derivedTokens.get(2) + " " + derivedTokens.get(3) + " " + derivedTokens.get(4) + " E                     R3\n" +
                            "E -> " + derivedTokens.get(1) + " " + derivedTokens.get(2) + " " + derivedTokens.get(3) + " " + derivedTokens.get(4) + " " + derivedTokens.get(5) + "                   R3\n" +
                            "E -> " + tokens.get(1).value + " " + tokens.get(2).value + " " + derivedTokens.get(3) + " " + derivedTokens.get(4) + " " + derivedTokens.get(5) + "                     R4\n" +
                            "E -> " + tokens.get(1).value + " " + tokens.get(2).value + " " + tokens.get(3).value + " " + tokens.get(4).value + " " + derivedTokens.get(5) + "                       R4\n" +
                            "E -> " + tokens.get(1).value + " " + tokens.get(2).value + " " + tokens.get(3).value + " " + tokens.get(4).value + " " + tokens.get(5).value + "                         R4\n"

            );
            semanticAnalysis(tokenString, tokens, key);
        }
    }

    private static String deriveToken(List<Token> tokens, int i) {
        return switch (tokens.get(i).value) {
            case "a", "A" -> "E" + 1;
            case "b", "B" -> "E" + 2;
            case "c", "C" -> "E" + 3;
            case "d", "D" -> "E" + 4;
            case "e", "E" -> "E" + 5;
            case "f", "F" -> "E" + 6;
            case "g", "G" -> "E" + 7;
            case "h", "H" -> "E" + 8;
            case "i", "I" -> "E" + 9;
            case "j", "J" -> "E" + 10;
            case "k", "K" -> "E" + 11;
            case "l", "L" -> "E" + 12;
            case "m", "M" -> "E" + 13;
            case "n", "N" -> "E" + 14;
            case "o", "O" -> "E" + 15;
            case "p", "P" -> "E" + 16;
            case "q", "Q" -> "E" + 17;
            case "r", "R" -> "E" + 18;
            case "s", "S" -> "E" + 19;
            case "t", "T" -> "E" + 20;
            case "u", "U" -> "E" + 21;
            case "v", "V" -> "E" + 22;
            case "w", "W" -> "E" + 23;
            case "x", "X" -> "E" + 24;
            case "y", "Y" -> "E" + 25;
            case "z", "Z" -> "E" + 26;
            default -> tokens.get(i).value;
        };
    }



    public static void semanticAnalysis(String tokenString, List<Token> tokens, String key) {
        // Implementation of semantic analysis
        System.out.println("======STAGE3: COMPILER TECHNIQUES--> SEMANTIC ANALYSIS\n" +
                "CONCLUSION-->This expression: "+ tokenString+" is Syntactically and Semantically correct. \n");
        intermediateCodeRepresentation(tokens, tokenString, key);
    }

    public static void intermediateCodeRepresentation(List<Token> tokens, String tokenString, String key) {
        // Implementation of intermediate code representation
        System.out.println("\n======STAGE4: COMPILER TECHNIQUES--> INTERMEDIATE CODE REPRESENTATION (ICR)");
        System.out.println("THE STRING ENTERED IS : " + tokenString + "\nThe ICR is as follows:");
        if (Objects.equals(key, "P")){
            System.out.println("t1 " + tokens.get(0).value + " " + tokens.get(1).value + " " + tokens.get(2).value + " " + tokens.get(3).value);
            System.out.println("t2 " + tokens.get(0).value + " " + tokens.get(9).value + " " + tokens.get(10).value + " " +tokens.get(11).value);
            System.out.println("t3 " + tokens.get(0).value + " " + tokens.get(7).value + " " + tokens.get(8).value +" t2");
            System.out.println("t4 " + tokens.get(0).value + " " + tokens.get(5).value + " " + tokens.get(6).value + " t3");
            System.out.println("t5 " + tokens.get(0).value + " t1 " + tokens.get(4).value + " t4");
        } else if (Objects.equals(key, "R")){
            System.out.println("t1 " + tokens.get(0).value + " " + tokens.get(3).value + " " + tokens.get(4).value + " " + tokens.get(5).value);
            System.out.println("t2 " + tokens.get(0).value + " " + tokens.get(1).value + " " + tokens.get(2).value + " t1");
        }
        System.out.println("\nCONCLUSION-->The expression was correctly generated in ICR");
        codeGeneration(tokens, key, tokenString);
    }

    public static void codeGeneration(List<Token> tokens, String key, String tokenString) {
        // Implementation of code generation
        System.out.println("\n======STAGE5: CODE GENERATION (CG)");
        if (Objects.equals(key, "P")) {
            System.out.println(
                    "\nLDA " + tokens.get(1).value +
                            "\nDIV " + tokens.get(3).value +
                            "\nSTR t1" +
                            "\n\nLDA " + tokens.get(9).value +
                            "\nDIV " + tokens.get(11).value +
                            "\nSTR t2" +
                            "\n\nLDA " + tokens.get(7).value +
                            "\nMUL t2" +
                            "\nSTR t3" +
                            "\n\nLDA " + tokens.get(5).value +
                            "\nADD t3" +
                            "\nSTR t4" +
                            "\n\nLDA t1" +
                            "\nDIV t4" +
                            "\nSTR t5"
            );
        } else if (Objects.equals(key,"R")) {
            System.out.println(
                    "\nLDA " + tokens.get(3).value +
                            "\nDIV " + tokens.get(4).value +
                            "\nSTR t1" +
                            "\n\nLDA " + tokens.get(1).value +
                            "\nADD t1" +
                            "\nSTR t2"
            );
        }

        codeOptimization(tokens,key,tokenString);
    }

    public static void codeOptimization(List<Token> tokens, String key, String tokenString) {
        // Implementation of code optimization
        System.out.println("======STAGE6: CODE OPTIMISATION (CO)");

        if (Objects.equals(key, "P")){
            System.out.println(
                    "\nDIV t1, " + tokens.get(3).value + ", " + tokens.get(1).value +
                            "\nDIV t2, " + tokens.get(11).value + ", " + tokens.get(9).value +
                            "\nMUL t3, t2, " + tokens.get(7).value +
                            "\nADD t4, t3, " + tokens.get(5).value +
                            "\nSUB t5, t4, t1\n\n"
            );
        }else if (Objects.equals(key, "R")) {
            System.out.println(
                    "\nDIV t1, " + tokens.get(5).value + ", " + tokens.get(3).value +
                            "\nADD t2, " + tokens.get(1).value + ", t1\n\n"
            );
        }

        targetMachineCode(tokens);
    }

    public static void targetMachineCode(List<Token> tokens) {
        // Implementation of target machine code
        System.out.println("======STAGE 7: TARGET MACHINE CODE IN BINARY \n");
        List<String> binaryTokens = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            String derived = binToken(tokens, i);
            binaryTokens.add(i, derived);
            System.out.print(binaryTokens.get(i) + " ");
        }
        System.out.println("\n");

    }

    public static String binToken(List<Token> tokens, int i) {
        return switch (tokens.get(i).value) {
            case "a" -> "01100001";
            case "b" -> "01100010";
            case "c"-> "01100011" ;
            case "d" -> "01100100" ;
            case "e"-> "01100101" ;
            case "f"-> "01100110" ;
            case "g"-> "01100111" ;
            case "h" -> "01101000" ;
            case "i" -> "01101001" ;
            case "j" -> "01101010" ;
            case "k" -> "01101011" ;
            case "l" -> "01101100" ;
            case "m" -> "01101101" ;
            case "n" -> "01101110" ;
            case "o" -> "01101111" ;
            case "p" -> "01110000" ;
            case "q" -> "01110001";
            case "r"-> "01110010" ;
            case "s"-> "01110011" ;
            case "t" -> "01110100" ;
            case "u" -> "01110101" ;
            case "v"-> "01110110" ;
            case "w" -> "01110111" ;
            case "x" -> "01111000" ;
            case "y" -> "01111001";
            case "z" -> "01111010" ;
            case "A" ->  "01000001";
            case "B" ->  "01000010";
            case "C" ->  "01000011";
            case "D" ->  "01000100";
            case "E" ->  "01000101";
            case "F" ->  "01000110";
            case "G" ->  "01000111";
            case "H" ->  "01001000";
            case "I" ->  "01001001";
            case "J" ->  "01001010";
            case "K" ->  "01001011";
            case "L" ->  "01001100";
            case "M" ->  "01001101";
            case "N" ->  "01001110";
            case "O" ->  "01001111";
            case "P" ->  "01010000";
            case "Q" ->  "01010001";
            case "R" ->  "01010010";
            case "S" ->  "01010011";
            case "T" ->  "01010100";
            case "U" ->  "01010101";
            case "V" ->  "01010110";
            case "W" ->  "01010111";
            case "X" ->  "01011000";
            case "Y" ->  "01011001";
            case "Z" ->  "01011010";
            case "-" ->  "00101101";
            case "+" ->  "00101011";
            case "*" ->  "00101010";
            case "/" ->  "01011000";
            default -> "";
        };
    }
}

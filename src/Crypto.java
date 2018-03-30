import java.util.Scanner;

public class Crypto {
    public static void main(String[] arg){
        String encrypted_text;

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Crypto! Enter the text you want to encrypt: ");
        String input = sc.nextLine();
        System.out.println("Enter the number by which you want it to be shifted: ");
        int key = sc.nextInt();
        System.out.println("How many letters in a group?");
        int letPerGroup = sc.nextInt();

        //Encrpyt String
        encrypted_text = encryptString(input, key, letPerGroup);

        System.out.println("The encrypted message is: " + encrypted_text);
    }

    public static String normalizeText(String message){

        //remove all spaces and punctuations
        // "\\W" regex for non word.
        message = message.replaceAll("\\W", "");
        //turn all lower case to upper case
        message = message.toUpperCase();
        //return the result
        return message;
    }

    public static String ceasarify(String text, int key){
        String original = shiftAlphabet(0);
        String shifted_code = shiftAlphabet(key);
        String cipher_text = "";

        int i = 0;
        while(i < text.length()){
            char ch = text.charAt(i);
            int index = original.lastIndexOf(ch);
            cipher_text = cipher_text + shifted_code.charAt(index);
            i++;
        }

        return cipher_text;
    }

    public static String groupify(String text, int code){
        String code_groups = "";
        int pos = code;

        //pad 'x' to fill the remaining spaces
        while(text.length()%code != 0){
            text = text + "x";
        }

        while (pos < text.length()){
            text = text.substring(0,pos) + " " + text.substring(pos, text.length());
            pos = pos + code + 1;
        }
        return text;
    }

    public static String encryptString(String text, int key, int letPerGroup){
        String normalized_text, cipher_text, code_groups;
        //Part 1: Normalize Text
        normalized_text = normalizeText(text);
        //System.out.println(normalized_text);

        //Part 2: Caesar Cipher
        cipher_text = ceasarify(normalized_text, key);
        //System.out.println(cipher_text);

        //Part 3: Code Groups
        code_groups = groupify(cipher_text, letPerGroup);
        //System.out.println(code_groups);


        return code_groups;
    }

    //function to shift the alphabets by the key
    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }
}

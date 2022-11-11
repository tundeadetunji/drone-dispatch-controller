package com.tundeadetunji.dispatchcontroller.business.utility;

import lombok.Data;

import java.util.Iterator;
import java.util.Set;

/**
 * The logic in this file is used to validate the name and code of
 * medication when attempting to load medication onto drone.
 */

@Data
public class Utility {

    public static boolean nameIsValid(String word){
        for (Character character : word.toCharArray()){
            if (!(character == 'a' || character == 'b' || character == 'c' || character == 'd'
                    || character == 'e' || character == 'f' || character == 'g' || character == 'h'
                    || character == 'i' || character == 'j' || character == 'k' || character == 'l'
                    || character == 'm' || character == 'n' || character == 'o' || character == 'p'
                    || character == 'q' || character == 'r' || character == 's' || character == 't'
                    || character == 'u' || character == 'v' || character == 'w' || character == 'x'
                    || character == 'y' || character == 'z' || character == 'A' || character == 'B'
                    || character == 'C' || character == 'D' || character == 'E' || character == 'F'
                    || character == 'G' || character == 'H' || character == 'I' || character == 'J'
                    || character == 'K' || character == 'L' || character == 'M' || character == 'N'
                    || character == 'O' || character == 'P' || character == 'Q' || character == 'R'
                    || character == 'S' || character == 'T' || character == 'U' || character == 'V'
                    || character == 'W' || character == 'X' || character == 'Y' || character == 'Z'
                    || character == '0' || character == '1' || character == '2' || character == '3'
                    || character == '4' || character == '5' || character == '6' || character == '7'
                    || character == '8' || character == '9' || character == '-' || character == '_')){
                return false;
            }
        }
        return true;
    }
    public static boolean codeIsValid(String word){
        for (Character character : word.toCharArray()){
            if (!(character == 'a' || character == 'b' || character == 'c' || character == 'd'
                    || character == 'e' || character == 'f' || character == 'g' || character == 'h'
                    || character == 'i' || character == 'j' || character == 'k' || character == 'l'
                    || character == 'm' || character == 'n' || character == 'o' || character == 'p'
                    || character == 'q' || character == 'r' || character == 's' || character == 't'
                    || character == 'u' || character == 'v' || character == 'w' || character == 'x'
                    || character == 'y' || character == 'z' || character == 'A' || character == 'B'
                    || character == 'C' || character == 'D' || character == 'E' || character == 'F'
                    || character == 'G' || character == 'H' || character == 'I' || character == 'J'
                    || character == 'K' || character == 'L' || character == 'M' || character == 'N'
                    || character == 'O' || character == 'P' || character == 'Q' || character == 'R'
                    || character == 'S' || character == 'T' || character == 'U' || character == 'V'
                    || character == 'W' || character == 'X' || character == 'Y' || character == 'Z'
                    || character == '0' || character == '1' || character == '2' || character == '3'
                    || character == '4' || character == '5' || character == '6' || character == '7'
                    || character == '8' || character == '9' || character == '_')){
                return false;
            }
        }
        return true;
    }

}

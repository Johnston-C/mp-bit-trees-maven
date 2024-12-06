package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import edu.grinnell.csc207.util.BrailleAsciiTables;

/**
 * A class that provides an interface to convert strings between various formats.
 * Supports ASCII to braille, braille to ASCII, and braille to unicode
 * conversions. Arguemnts are expected on the command line and to be
 * reperesentations of english letters, uppercase or lowercase, or the
 * space character.
 *
 * @author Cade Johnston
 * @author Samuel A. Rebelsky
 */
public class BrailleASCII {
  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * A program to convert between representations of letters.
   *
   * @param args
   *   The command line arguments.
   *   args[0] is the conversion to be used.
   *   args[1] is the string to convert.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    try {
      if (args.length != 2) {
        pen.println("Wrong number of arguments (2 expected)."
            + " If your string contains ' ', put parentheses around it.");
      } else if (args[0].toLowerCase().equals("braille")) {
        for (int i = 0; i < args[1].length(); i++) {
          pen.print(BrailleAsciiTables.toBraille(args[1].charAt(i)));
        } // for
        pen.println("");
      } else if (args[0].toLowerCase().equals("ascii")) {
        if ((args[1].length() % 6) != 0) {
          pen.println("Invalid length of input string (must be a multiple of 6 bits).");
        } else {
          for (int i = 0; i < args[1].length(); i += 6) {
            pen.print(BrailleAsciiTables.toAscii(args[1].substring(i, i + 6)));
          } // for[i]
          pen.println("");
        } // if / else
      } else if (args[0].toLowerCase().equals("unicode")) {
        if ((args[1].length() % 6) != 0) {
          pen.println("Invalid length of input string (must be a multiple of 6 bits).");
        } else {
          for (int i = 0; i < args[1].length(); i += 6) {
            pen.print(Character.toChars(Integer.parseInt(BrailleAsciiTables.toUnicode(args[1].
                substring(i, i + 6)), 16)));
          } // for[i]
          pen.println("");
        } // if / else
      } else {
        pen.println("Invalid conversion type ('braille', 'ascii', or 'unicode' expected).");
      } // if / else if / else if / else if / else
      pen.close();
    } catch (IndexOutOfBoundsException e) {
      pen.println("\nUnexpected character in input, could not translate.");
    } // try / catch
  } // main(String[]

} // class BrailleASCII

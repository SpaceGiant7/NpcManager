package Utilities;

public class WordWrapper {

    public static String wrap( int width, String string ) {
        if ( string.length() == 0 || width <= 0 )
            return string;

        String[] words = string
                .replaceAll( "[\t\n]", " " )
                .split( " " );
        String returnString = "";
        int charCounter = 0;
        for ( String s : words ) {
            if ( s.length() >= width ) {
                if ( !returnString.isEmpty() )
                    returnString += "\n";

                returnString += s + " ";
                charCounter = s.length();
            }
            else if ( charCounter + s.length() <= width ) {
                returnString += s + " ";
                charCounter += s.length() + 1;
            }
            else {
                returnString = returnString.substring( 0, returnString.length() - 1 );
                returnString += "\n" + s + " ";
                charCounter = s.length() + 1;
            }
        }
        return returnString.substring( 0, returnString.length() - 1 );
    }
}

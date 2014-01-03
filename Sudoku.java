{\rtf1\ansi\ansicpg1252\cocoartf1265
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
\paperw11900\paperh16840\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural

\f0\fs24 \cf0 \
class Sudoku\
\{\
  static int count = 0, totalcount = 0, iterations = 0;\
      static int[][] in =\
      \{\
            \{ 5, 0, 0, 8, 0, 0, 0, 0, 0 \},\
            \{ 8, 0, 0, 0, 0, 0, 5, 0, 0 \},\
            \{ 0, 0, 3, 0, 5, 2, 0, 0, 1 \},\
            \{ 1, 4, 0, 0, 6, 0, 0, 0, 0 \},\
            \{ 0, 3, 0, 0, 2, 0, 0, 1, 0 \},\
            \{ 0, 0, 0, 0, 7, 0, 0, 3, 6 \},\
            \{ 3, 0, 0, 6, 8, 0, 2, 0, 0 \},\
            \{ 0, 0, 7, 0, 0, 0, 0, 0, 4 \},\
            \{ 0, 0, 0, 0, 0, 4, 0, 0, 5 \}\
      \};\
      static String[][] sin = new String[9][9]; //the array of possibilities\
      static //initialises each possibility to default value\
      \{\
            for(int i=0; i < 9; i++)\
            \{\
              for(int j=0; j < 9; j++)\
                  \{\
                    sin[i][j] = "" + in[i][j];\
                        if(in[i][j] == 0)\
                        \{\
                          sin[i][j] = "123456789";\
                        \}\
                  \}\
            \}\
      \}\
      public static void main(String[] args)// eliminates possibilities\
      \{\
            printMe();\
            do\
            \{\
                  count = 0;\
                  iterations++;\
                  int i;\
                  for(i=0; i<9; i++)\
                  \{\
                	  	doRow(i);\
                        doColumn(i);\
                        doCell(i);\
                  \}\
                  printMe();\
            \} while( count > 0 );\
            printMe();\
      \}\
      public static void doRow(int i)// converts row to a line\
      \{\
        String[] line = new String[9];\
            for(int j=0; j < 9; j++)\
            \{\
              line[j] = sin[i][j];\
            \}\
            doLine(line);\
            for(int j=0; j < 9; j++)\
            \{\
                  sin[i][j] = line[j];\
            \}    \
      \}\
      public static void doColumn(int i)//converts column to a line\
      \{\
        String[] line = new String[9];\
            for(int j=0; j < 9; j++)\
            \{\
              line[j] = sin[j][i];\
            \}\
            doLine(line);\
            for(int j=0; j < 9; j++)\
            \{\
                  sin[j][i] = line[j];\
            \}\
      \}\
      public static void doCell(int i)// converts 3x3 grid to a line\
      \{\
        String[] line = new String[9];\
            int fromRow, fromCol, uptoRow, uptoCol;\
            int j, k, l = 0;\
            fromRow = (i / 3)*3;\
            uptoRow = fromRow + 2;\
            fromCol = (i % 3)*3;\
            uptoCol = fromCol + 2;\
           \
            l = 0;\
            for(j=fromRow; j <= uptoRow; j++)\
            \{\
              for(k=fromCol; k <= uptoCol; k++)\
                  \{\
                        line[l++] = sin[j][k];\
                  \}\
            \}\
            doLine(line);\
            l = 0;\
            for(j=fromRow; j <= uptoRow; j++)\
            \{\
              for(k=fromCol; k <= uptoCol; k++)\
                  \{\
                        sin[j][k] = line[l++];\
                  \}\
            \}\
      \}\
      public static void doLine(String[] line)// solves the converted line\
      \{\
    	  /*\
    	   * \
    	   */\
            String sComposite;\
            int i, j, k, l, m, o;\
            for(i=0; i<9; i++)\
            \{\
                  if(line[i].length() == 1)\
                  \{\
                    sComposite = line[i];\
                    for(o=0; o<9; o++)\
                        \{\
                          if(o!=i)\
                              \{\
                                line[o] = remove(line[o],sComposite);\
                              \}\
                        \}\
                  \}\
                  for(j=0; j<9; j++)\
                  \{\
                    if(j!=i)\
                        \{\
                          sComposite = line[i] + line[j];\
                              sComposite = reduce(sComposite);\
                              if(sComposite.length() == 2)\
                              \{\
                                    for(o=0; o<9; o++)\
                                    \{\
                                          if( (o!=i) && (o!=j) )\
                                          \{\
                                                line[o] = remove(line[o],sComposite);\
                                          \}\
                                    \}                              \
                              \}\
                              for(k=0; k<9; k++)\
                              \{\
                                    if( (k!=i) && (k!=j) )\
                                    \{\
                                          sComposite = sComposite + line[k];\
                                          sComposite = reduce(sComposite);\
                                          if(sComposite.length() == 3)\
                                          \{\
                                                for(o=0; o<9; o++)\
                                                \{\
                                                      if( (o!=i) && (o!=j) && (o!=k) )\
                                                      \{\
                                                            line[o] = remove(line[o],sComposite);\
                                                      \}\
                                                \}                              \
                                          \}\
                                          for(l=0; l<9; l++)\
                                          \{\
                                            if( (l!=i) && (l!=j) && (l!=k) )\
                                                \{\
                                                      sComposite = sComposite + line[l];\
                                                      sComposite = reduce(sComposite);\
                                                      if(sComposite.length() == 4)\
                                                      \{\
                                                            for(o=0; o<9; o++)\
                                                            \{\
                                                                  if( (o!=i) && (o!=j) && (o!=k) && (o!=l) )\
                                                                  \{\
                                                                        line[o] = remove(line[o],sComposite);\
                                                                  \}\
                                                            \}                              \
                                                      \}\
                                                      for(m=0; m<9; m++)\
                                                      \{\
                                                        if( (m!=i) && (m!=j) && (m!=k) && (m!=l) )\
                                                            \{\
                                                                  sComposite = sComposite + line[m];\
                                                                  sComposite = reduce(sComposite);\
                                                                  if(sComposite.length() == 5)\
                                                                  \{\
                                                                        for(o=0; o<9; o++)\
                                                                        \{\
                                                                              if( (o!=i) && (o!=j) && (o!=k) && (o!=l) && (o!=m) )\
                                                                              \{\
                                                                                    line[o] = remove(line[o],sComposite);\
                                                                              \}\
                                                                        \}                              \
                                                                  \}\
                                                            \}\
                                                      \}\
                                                \}\
                                          \}\
                                    \}\
                              \}\
                        \}\
                  \}\
            \}\
      \}\
      public static String remove(String s, String s2Remove)\
      \{\
        int i;\
            String rv = "";\
            for(i=0; i < s.length(); i++)\
            \{\
              char c = s.charAt(i);\
                  if(s2Remove.indexOf(c) >= 0)\
                  \{\
                        count++;\
                        totalcount++;\
                  \}\
                  else\
                  \{\
                    rv = rv + c;\
                  \}\
            \}\
            return rv;\
      \}\
      public static boolean isSubsetOf(String s1, String s2)\
      \{\
        if(s1.equals(s2))\
              return true;\
            int l2 = s2.length();\
            int subcount = 0;\
            for(int i=0; i < l2; i++)\
            \{\
              char c2 = s2.charAt(i);\
                  if(s1.indexOf(c2) >= 0)\
                  \{\
                    subcount++;\
                  \}\
            \}\
            if(subcount == l2)\
              return true;\
            return false;\
      \}\
      public static String reduce(String pString)\
      \{\
        String rv = "";\
            int i;\
            char ch;\
            for(i=0; i < pString.length(); i++)\
            \{\
              ch = pString.charAt(i);\
                  if( rv.indexOf(ch) >= 0 )\
                  \{\
                  \}\
                  else\
                  \{\
                    rv = rv + ch;\
                  \}\
            \}\
            return rv;\
      \}    \
      public static void printMe()\
      \{\
        int i, j;\
            for(i=0; i < 9; i++)\
            \{\
              for(j=0; j < 9; j++)\
                  \{\
                    System.out.print(rpad(sin[i][j],8));\
                        System.out.print(" ");\
                  \}\
                  System.out.println();\
            \}\
            System.out.println("totalcount: " + totalcount + ", iterations: " + iterations + ", count: " + count + " ------------------------");\
            //System.out.println();\
      \}\
      public static String rpad(String p, int w)\
      \{\
        String s = "" + p;\
            int l = w - p.length();\
            while(l > 0)\
            \{\
              s = s + " ";\
                  l--;\
            \}\
            return s;\
      \}\
\}\
 }
{\rtf1\ansi\ansicpg1252\cocoartf1265
{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
\paperw11900\paperh16840\margl1440\margr1440\vieww17980\viewh11440\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural

\f0\fs24 \cf0 class Square\
\{\
private int value;\
private String possible=\'93123456789\'94;\
private Grid grid, Row row, Column column;\
int[] locations = new int[3]; //grid ; row ; column\
private foundValue;\
\
public void setValue(int value)\
\{\
this.value=value;\
possible=\'93\'94;\
foundValue=true;\
grid.removeUnsolved(locations[0]+1);\
row.removeUnsolved(locations[1]+1);\
column.removeUnsolved(locations[2]+1);\
refreshPossStrings(value);\
\}\
\
public void refreshPossStrings(char value)\
\{\
refreshPossStrings(grid,value);\
refreshPossStrings(row,value);\
refreshPossStrings(column,value);\
\}\
\
public void refreshPossStrings(char[] valueArr)\
\{\
int length=valueArr.length;\
for(int i=0; i<length; i++)\
\{\
	refreshPossStrings(valueArr[i]);\
\}\
\}\
\
public void refreshPossStrings(GRC grc, char value)\
\{\
String unsolved=grc.getUnsolved();\
int length=unsolved.length();\
Square square;\
for(int i=0;i<length;i++)\
	\{\
	 square=grc.getSquare(unsolved.charAt(i));\
	 square.removePossible(value);\
	\}\
\}\
\
public String getPossible()\
\{\
return possible;\
\}\
\
public void removePossible(String toRemove)\
\{\
int length=toRemove.length();\
for(int i=0;i<length;i++)\
removePossible(toRemove.charAt(i));\
\}\
\
public void removePossible(char number)\
\{\
possible=Stringfuncs.removeString(possible, number);\
checkSolved();\
\}\
\
public void checkSolved()\
\{\
if(possible.length()==1)\
\{\
	setValue(possible.charAt(0));\
\}\
\}\
\
public void setPossible(String possible)\
\{\
this.possible=possible;\
\}\
\
public boolean foundValue()\
\{\
return foundValue; \
\}\
\
\}\
\
class GRC\
\{\
private Square[] container = new Square[10];\
private String unsolved;\
private String solved; //see if unsolved + solved + methods need another class.\
private int number;\
\
public GRC(int number)\
\{\
this.number=number;\
refreshUnsolved();\
\}\
\
public void removeUnsolved(char ch)\
\{\
unsolved=Stringfuncs.removeString(unsolved, ch);\
refreshSolved();\
\}\
\
public Square getSquare(int n)\
\{\
return container[(n-1)];\
\}\
\
public void reduce() //reduces all the possible String values at the beginning\
\{\
int length =  unsolved.length;\
for( int i=0; i<length; i++)\
	\{\
	container[unsolved.charAt(i)].setPossible(getPossible().remove(solved)); // do it character at a time\
	\}\
\}\
\
//eliminate one\'92s, reduce again?\
//eliminate two\'92s\
//eliminate three\'92s\
//eliminate four\'92s\
//eliminate five\'92s\
\
public void refreshUnsolved() //finds the square\'92s whose values haven\'92t been found yet\
\{\
for(int i=0; i<10; i++)\
	\{\
	if(!container[i].foundValue())\
	unsolved = \'93\'94 + unsolved + i;\
	\}\
refreshSolved();\
\}\
\
public String getUnsolved()\
\{\
return unsolved;\
\}\
\
public void refreshSolved()\
\{\
solved=\'93123456789\'94;\
int length=unsolved.length;\
int loop = 0, counter=0;\
while (counter<length)\
\{\
if (solved.charAt(loop)==unsolved.charAt(counter))\
	\{\
	solved.removeCharAt(loop); // is removeCharAt a legit function?\
	counter++;\
	\}\
loop++;\
\}\
\
\}\
\
\}\
\
\
class Grid extends GRC\
\{\
\}\
\
class Row extends GRC\
\{\
\}\
\
class Column extends GRC\
\{\
\}\
\
class Stringfuncs\
\{\
\
private static String removeString(String s, char toRemove)\
\{\
int length = s.length();\
for (int i=0;i<length;i++)\
\{\
	if(s.charAt(i)!=toRemove)\
	s+=s.charAt(i);\
\}\
return s.substring(length);\
\}\
\
\}}
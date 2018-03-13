if not exist "bin" mkdir bin
javac -d bin -classpath ".;.\jsoup-1.11.2.jar;.\json-20180130.jar;.\RXTXcomm.jar" .\MenuFrame.java
java  -classpath "bin;.\jsoup-1.11.2.jar;.\json-20180130.jar;.\RXTXcomm.jar" MenuFrame

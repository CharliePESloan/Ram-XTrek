if not exist "bin" mkdir bin
javac -d bin -classpath ".;.\*" .\MenuFrame.java
java  -classpath "bin;.\*" MenuFrame

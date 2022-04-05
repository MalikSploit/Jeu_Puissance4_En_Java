#How to compile 
cd Puissance4
javac -d build src/*.java

#How to execute 
java -classpath build Main

#How to generate JavaDoc
cd JavaDoc
javadoc ../src/*.java

#How to open the JavaDoc
firefox index.html
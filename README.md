Code running instructions :
1) navigate to /todoapp in your console
2) Run the following commands :
   find src/main/java -name "*.java" > sources.txt
   javac -cp "lib/*" -d out @sources.txt
   java -cp "out:lib/*" com.example.todoapp.Main

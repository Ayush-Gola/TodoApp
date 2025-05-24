Code running instructions :
1) navigate to /todoapp
2) find src/main/java -name "*.java" > sources.txt
javac -cp "lib/*" -d out @sources.txt
3) java -cp "out:lib/*" com.example.todoapp.Main

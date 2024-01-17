# Interacciones con usuarios

---

Las formas de interacción de usuarios en los programas de java son diversas y dependen del tipo de aplicación que se desarrolle. Algunas de las formas más comunes son:

- La interfaz gráfica de usuario (GUI), que consiste en elementos visuales como ventanas, botones, menús, cuadros de texto, etc. que permiten al usuario interactuar con el programa mediante el ratón y el teclado. Para crear una GUI en java se pueden usar las clases del paquete **javax.swing** o la biblioteca **JavaFX**.
- La entrada y salida estándar (E/S), que consiste en leer y escribir datos desde el teclado y la pantalla del terminal. Para usar la E/S estándar en java se pueden usar las clases **System.in** y **System.out,** que son objetos de tipo InputStream y PrintStream respectivamente.
- Los argumentos de línea de comandos, que consisten en pasar valores al programa cuando se ejecuta desde el terminal. Para acceder a los argumentos de línea de comandos en java se puede usar el parámetro String[] args del método main, que contiene un array con los valores pasados.
- Los archivos, que consisten en leer y escribir datos desde ficheros almacenados en el disco duro u otros dispositivos. Para trabajar con archivos en java se pueden usar las clases del paquete **java.io** o la API NIO.2 del paquete **java.nio.**
- Las bases de datos, que consisten en almacenar y recuperar datos desde sistemas de gestión de bases de datos (SGBD) como MySQL, Oracle, MongoDB, etc. Para conectar con bases de datos en java se pueden usar las clases del paquete java.sql o la API JDBC. _**Esto lo veremos más adelante**_ 
- Las redes, que consisten en comunicar datos entre programas que se ejecutan en diferentes máquinas conectadas por internet u otras redes. Para usar las redes en java se pueden usar las clases del paquete java.net o la API RMI. _**Esto lo veremos más adelante**_


<br>

### Ejemplos

---
1. Java Swing

<pre>
       <code>
   public class EjemploSwing {
       public static void main(String[] args) {
           JFrame frame = new JFrame("Mi Ventana");
           JButton button = new JButton("Haz clic en mí");
           frame.getContentPane().add(button);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           frame.setSize(300, 200);
           frame.setVisible(true);
      }
   }
        </code>
</pre>

   
2. JavaFX

<pre>
       <code>
   import javafx.application.Application;
   import javafx.scene.Scene;
   import javafx.scene.control.Button;
   import javafx.scene.layout.StackPane;
   import javafx.stage.Stage;
   
   public class EjemploJavaFX extends Application {
       public static void main(String[] args) {
           launch(args);
       }
   
       @Override
       public void start(Stage primaryStage) {
           Button btn = new Button();
           btn.setText("Haz clic en mí");
           StackPane root = new StackPane();
           root.getChildren().add(btn);
           Scene scene = new Scene(root, 300, 200);
           primaryStage.setTitle("Mi Ventana");
           primaryStage.setScene(scene);
           primaryStage.show();
       }
   }

        </code>
</pre>


3. System.in 

<pre>
       <code>
   public class EjemploSystemIn {
       public static void main(String[] args) {
           Scanner scanner = new Scanner(System.in);
           System.out.println("Introduce algo de texto:");
           String input = scanner.nextLine();
           System.out.println("Ingresaste: " + input);
       }
   }
        </code>
</pre>


4. System.Out  

<pre>
       <code>
   public class EjemploSystemOut {
       public static void main(String[] args) {
           System.out.println("¡Hola, mundo!");
       }
   }
        </code>
</pre>


5. Java.io  

<pre>
       <code>
      public class Lector {
   
      public static void main(String[] args) throws IOException {
         BufferedReader reader = new BufferedReader(new FileReader("archivo.txt"));
         String linea;
         while ((linea = reader.readLine()) != null) {
         System.out.println(linea);
      }
      reader.close();
        </code>
</pre>

6. Java.nio 

<pre>
   <code>
   public class LectorNIO {
      public static void main(String[] args) throws IOException {
         Path ruta = Paths.get("archivo.txt");
         List<String> lineas = Files.readAllLines(ruta);
         for (String linea : lineas) {
            System.out.println(linea);
         }
      }
   }
   </code>
</pre>

<br>

## Referencias

---


1. Java Swing  
   1. [Documentacion oficial de Java Swing](https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html)   
   2. [Java Swing tutorial](https://www.javatpoint.com/java-swing)  
   3. [Pequeña demostración de boton Swing](https://www.youtube.com/shorts/hwiAhsaXnA0)  
2. JavaFX  
   1. [Documentacion oficial de JavaFX](https://docs.oracle.com/javafx/2/api/index.html)
   2. [JavaFX tutorial](https://www.javatpoint.com/javafx-tutorial)
   3. [Pequeña demostración de aplicación JavaFX](https://www.youtube.com/shorts/JNL-4CwdcFk)
3. System.in
   1. [Documentacion oficial de Java.Util.Scaner](https://docs.oracle.com/javase/8/docs/api/java/lang/System.html))
   2. [Scanner tutorial](https://www.javatpoint.com/Scanner-classl)
   3. [Pequeña demostración de aplicación Java.Util.Scaner](https://docs.oracle.com/javase/8/docs/api/java/lang/System.html)
4. System.Out
   1. [Documentacion oficial de Java.lang.System](https://docs.oracle.com/javase/8/docs/api/java/lang/System.html)
   2. [JavaFX tutorial](https://www.javatpoint.com/java-console-class)
   3. [Pequeña demostración de aplicación Java.lang.System](https://www.youtube.com/watch?v=mdhvTB8JkwA)
5. Java.io
   1. [Documentacion oficial de Java.io](https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html)
   2. [Java I/O tutorial](https://www.geeksforgeeks.org/java-io-tutorial/)
   3. [Curso para la API Java I/O](https://www.youtube.com/playlist?list=PLTd5ehIj0goOxCwlYFWTKCYH1KeUx1qB1)
6. Java.nio
   1. [Documentacion oficial de Java.nio](https://docs.oracle.com/javase/8/docs/api/java/nio/package-summary.html)
   2. [Java.nio tutorial](https://www.javatpoint.com/javafx-tutorial)
   3. [Pequeña demostración de aplicación Java.nio](https://www.youtube.com/watch?v=LPU0fZ0-bUU&ab_channel=AprendoInform%C3%A1tica)


<br> 

[Pequeño Chiste](https://www.youtube.com/shorts/7jlu4Fv7Q8o)  
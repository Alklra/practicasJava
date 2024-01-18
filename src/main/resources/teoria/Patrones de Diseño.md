# Patrones de Diseño 

---

Los patrones de diseño en software son soluciones generales y reutilizables a problemas comunes que se presentan en el desarrollo de software. Los patrones de diseño no son algoritmos específicos ni fragmentos de código, sino descripciones o plantillas de cómo resolver un problema que se puede adaptar a diferentes situaciones. 

## Categorías

---

Los patrones de diseño se clasifican en tres categorías principales: creacionales, estructurales y de comportamiento. Los patrones creacionales se ocupan de la forma de crear objetos o clases, los patrones estructurales se ocupan de la forma de organizar o componer objetos o clases, y los patrones de comportamiento se ocupan de la forma de coordinar o comunicar objetos o clases.

Los patrones de diseño se clasifican en tres tipos diferentes dependiendo del tipo de problema que resuelven. Estos pueden ser:

- Creacionales: Estos patrones se encargan de la creación de objetos. Su objetivo es abstraer el proceso de instanciación y ocultar los detalles de cómo se crean y se inicializan los objetos. Algunos ejemplos de patrones creacionales son **el Factory Method, el Singleton, el Prototype y el Builder**.
- Estructurales: Estos patrones se ocupan de la composición y la estructura de las clases y los objetos. Su objetivo es facilitar el diseño de estructuras que sean capaces de adaptarse a nuevas situaciones sin modificar su código fuente. Algunos ejemplos de patrones estructurales son **el Adapter, el Bridge, el Composite y el Decorator**.
- De comportamiento: Estos patrones se centran en la comunicación y la responsabilidad entre los objetos. Su objetivo es definir cómo se coordinan y cooperan los objetos para realizar una tarea. Algunos ejemplos de patrones de comportamiento son **el Observer, el Strategy, el Command y el State**

<br>

## Ejemplo

---

- Factory Method: Este patrón define una interfaz para crear un objeto, pero deja que las subclases decidan qué clase instanciar. De esta forma, se delega la responsabilidad de la creación de objetos a las subclases. Un ejemplo de este patrón es el siguiente:

```java
// Interfaz que define el método de fábrica
public interface Animal {
    void makeSound();
}

// Clase concreta que implementa la interfaz
public class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}

// Clase concreta que implementa la interfaz
public class Cat implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}

// Clase abstracta que usa el método de fábrica
public abstract class AnimalFactory {
    // Método de fábrica que devuelve un objeto Animal
    public abstract Animal createAnimal();

    // Método que usa el objeto Animal creado
    public void makeAnimalSound() {
        Animal animal = createAnimal();
        animal.makeSound();
    }
}

// Clase concreta que extiende la clase abstracta
public class DogFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        // Crea y devuelve un objeto Dog
        return new Dog();
    }
}

// Clase concreta que extiende la clase abstracta
public class CatFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        // Crea y devuelve un objeto Cat
        return new Cat();
    }
}

// Clase principal que usa el patrón
public class Main {
    public static void main(String[] args) {
        // Crea un objeto DogFactory
        AnimalFactory dogFactory = new DogFactory();
        // Usa el método makeAnimalSound
        dogFactory.makeAnimalSound(); // Imprime "Woof!"

        // Crea un objeto CatFactory
        AnimalFactory catFactory = new CatFactory();
        // Usa el método makeAnimalSound
        catFactory.makeAnimalSound(); // Imprime "Meow!"
    }
}

```


- Adapter: Este patrón permite que dos interfaces incompatibles trabajen juntas. Para ello, se crea una clase adaptadora que implementa la interfaz que se desea usar y contiene una referencia a la interfaz que se necesita adaptar. De esta forma, se convierten las llamadas de una interfaz a otra. Un ejemplo de este patrón es el siguiente:


<br>

```java
// Interfaz que se desea usar
public interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Interfaz que se necesita adaptar
public interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

// Clase concreta que implementa la interfaz que se necesita adaptar
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // No hace nada
    }
}

// Clase concreta que implementa la interfaz que se necesita adaptar
public class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // No hace nada
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}

// Clase adaptadora que implementa la interfaz que se desea usar
public class MediaAdapter implements MediaPlayer {
    // Referencia a la interfaz que se necesita adaptar
    private AdvancedMediaPlayer advancedMediaPlayer;

    // Constructor que crea el objeto adecuado según el tipo de audio
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer = new Mp4Player();
        }
    }

    // Método que convierte la llamada de una interfaz a otra
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}

// Clase concreta que implementa la interfaz que se desea usar
public class AudioPlayer implements MediaPlayer {
    // Referencia a la clase adaptadora
    private MediaAdapter mediaAdapter;

    // Método que reproduce el audio según el tipo
    @Override
    public void play(String audioType, String fileName) {
        // Si el tipo es mp3, reproduce el audio directamente
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file: " + fileName);
        }
        // Si el tipo es vlc o mp4, usa la clase adaptadora
        else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        // Si el tipo es otro, muestra un mensaje de error
        else {
            System.out.println("Invalid media type: " + audioType);
        }
    }
}

// Clase principal que usa el patrón
public class Main {
    public static void main(String[] args) {
        // Crea un objeto AudioPlayer
        AudioPlayer audioPlayer = new AudioPlayer();

        // Reproduce diferentes tipos de audio
        audioPlayer.play("mp3", "song.mp3"); // Imprime "Playing mp3 file: song.mp3"
        audioPlayer.play("mp4", "video.mp4"); // Imprime "Playing mp4 file: video.mp4"
        audioPlayer.play("vlc", "movie.vlc"); // Imprime "Playing vlc file: movie.vlc"
        audioPlayer.play("avi", "clip.avi"); // Imprime "Invalid media type: avi"
    }
}


```

<br>

- El patrón singleton se usa para asegurar que una clase solo tenga una instancia, y que esta instancia sea accesible globalmente. Para implementar este patrón, se necesita:

    - Un constructor privado que evite la creación de objetos desde otras clases.
    - Un atributo privado y estático de la misma clase que almacene la única instancia.
    - Un método público y estático que devuelva la instancia de la clase, y que la cree si no existe.

<br>

```java
// Clase que implementa el patrón singleton
public class Singleton {
  // Atributo privado y estático que guarda la única instancia
  private static Singleton instance;
  // Constructor privado que evita la creación de objetos desde otras clases
  private Singleton() {
  }
  // Método público y estático que devuelve la instancia de la clase
  public static Singleton getInstance() {
    // Si la instancia no existe, la crea
    if (instance == null) {
      instance = new Singleton();
    }
    // Devuelve la instancia
    return instance;
  }
}

// Clase principal que usa el patrón
public class Main {
  public static void main(String[] args) {
    // Obtiene la instancia de la clase Singleton
    Singleton singleton = Singleton.getInstance();
    // Usa la instancia para hacer algo
    System.out.println(singleton);
  }
}

```

<br>

- Observer: Este patrón define una dependencia de uno a muchos entre objetos, de modo que cuando un objeto cambia de estado, se notifica a todos sus observadores. De esta forma, se logra una comunicación indirecta entre los objetos, evitando el acoplamiento. Un ejemplo de este patrón es el siguiente:

<br>

```java
// Clase concreta que implementa la interfaz del sujeto
public class ChatRoom implements Subject {
    // Lista de observadores
    private List<Observer> observers;
    // Mensaje del chat
    private String message;

    // Constructor que inicializa la lista de observadores
    public ChatRoom() {
        observers = new ArrayList<>();
    }

    // Método que registra un observador en la lista
    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    // Método que elimina un observador de la lista
    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    // Método que notifica a todos los observadores registrados
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // Método que cambia el mensaje y llama al método de notificación
    public void postMessage(String message) {
        this.message = message;
        System.out.println("New message: " + message);
        notifyObservers();
    }
}

// Clase principal que usa el patrón
public class Main {
    public static void main(String[] args) {
        // Crea un objeto ChatRoom
        ChatRoom chatRoom = new ChatRoom();

        // Crea tres objetos User con el mismo ChatRoom
        User user1 = new User("Alice", chatRoom);
        User user2 = new User("Bob", chatRoom);
        User user3 = new User("Charlie", chatRoom);

        // Registra los usuarios en el ChatRoom
        chatRoom.register(user1);
        chatRoom.register(user2);
        chatRoom.register(user3);

        // Cambia el mensaje del ChatRoom
        chatRoom.postMessage("Hello, world!"); // Imprime "New message: Hello, world!"
        // Los usuarios reciben el mensaje
        user1.update("Hello, world!"); // Imprime "Alice received: Hello, world!"
        user2.update("Hello, world!"); // Imprime "Bob received: Hello, world!"
        user3.update("Hello, world!"); // Imprime "Charlie received: Hello, world!"
    }
}


```

<br>

## Codigo sin patrón

---

El código sin el patrón Observer, te puedo decir que dependería mucho del contexto y del problema que se quiera resolver. Sin embargo, una forma posible de hacerlo sería que el sujeto observable tuviera una referencia directa a cada uno de los observadores, y que los llamara individualmente cada vez que cambiara su estado. Esto implicaría que el sujeto observable tendría que conocer los detalles de cada observador, lo que aumentaría el acoplamiento y la complejidad del código. Un ejemplo de cómo se vería el código sin el patrón Observer es el siguiente:

<br>
 
```java
// Clase que representa el sujeto observable
public class ChatRoom {
    // Referencias directas a los observadores
    private User user1;
    private User user2;
    private User user3;
    // Mensaje del chat
    private String message;

    // Constructor que asigna los observadores
    public ChatRoom(User user1, User user2, User user3) {
        this.user1 = user1;
        this.user2 = user2;
        this.user3 = user3;
    }

    // Método que cambia el mensaje y llama a los observadores
    public void postMessage(String message) {
        this.message = message;
        System.out.println("New message: " + message);
        // Llama a los observadores directamente
        user1.update(message);
        user2.update(message);
        user3.update(message);
    }
}

// Clase que representa los observadores
public class User {
    // Nombre del usuario
    private String name;

    // Constructor que asigna el nombre
    public User(String name) {
        this.name = name;
    }

    // Método que recibe el mensaje y lo muestra
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

// Clase principal que usa el código sin el patrón
public class Main {
    public static void main(String[] args) {
        // Crea tres objetos User
        User user1 = new User("Alice");
        User user2 = new User("Bob");
        User user3 = new User("Charlie");

        // Crea un objeto ChatRoom con los tres User
        ChatRoom chatRoom = new ChatRoom(user1, user2, user3);

        // Cambia el mensaje del ChatRoom
        chatRoom.postMessage("Hello, world!"); // Imprime "New message: Hello, world!"
        // Los usuarios reciben el mensaje
        user1.update("Hello, world!"); // Imprime "Alice received: Hello, world!"
        user2.update("Hello, world!"); // Imprime "Bob received: Hello, world!"
        user3.update("Hello, world!"); // Imprime "Charlie received: Hello, world!"
    }
}


```

<br>

Como puedes ver, este código es más rígido y menos flexible que el que usa el patrón Observer, ya que el ChatRoom tiene que saber cuántos y qué usuarios hay, y no puede añadir o eliminar usuarios fácilmente. Además, si se quisiera usar otro tipo de observador, habría que modificar el código del ChatRoom para adaptarlo. Por eso, el patrón Observer es una buena práctica que mejora el diseño y la calidad del software.

<br>



```java
// Clase que representa el vehículo
public class Vehiculo {
    // Atributo que guarda el tipo de vehículo
    private String tipo;
    // Atributo que guarda la instancia concreta del vehículo
    private MotorVehicle vehiculo;

    // Constructor que recibe el tipo de vehículo y crea la instancia concreta
    public Vehiculo(String tipo) {
        this.tipo = tipo;
        // Según el tipo, crea una instancia de la clase concreta
        if (tipo.equals("moto")) {
            vehiculo = new Motorcycle();
        } else if (tipo.equals("coche")) {
            vehiculo = new Car();
        } else if (tipo.equals("camion")) {
            vehiculo = new Truck();
        } else {
            // Si el tipo no es válido, muestra un mensaje de error
            System.out.println("Tipo de vehículo inválido");
        }
    }

    // Método que devuelve la instancia concreta del vehículo
    public MotorVehicle getVehiculo() {
        return vehiculo;
    }
}

// Clase principal que usa el código sin el patrón
public class Main {
    public static void main(String[] args) {
        // Crea un objeto Vehiculo con el tipo "moto"
        Vehiculo moto = new Vehiculo("moto");
        // Obtiene la instancia concreta del vehículo
        MotorVehicle vehiculoMoto = moto.getVehiculo();
        // Usa el método build de la instancia
        vehiculoMoto.build(); // Imprime "Build Motorcycle"

        // Crea un objeto Vehiculo con el tipo "coche"
        Vehiculo coche = new Vehiculo("coche");
        // Obtiene la instancia concreta del vehículo
        MotorVehicle vehiculoCoche = coche.getVehiculo();
        // Usa el método build de la instancia
        vehiculoCoche.build(); // Imprime "Build Car"
    }
}


```

<br>


Como puedes ver, este código es más simple que el que usa el patrón factory method, pero también tiene varias desventajas. Por ejemplo, la clase Vehiculo tiene que conocer todos los tipos posibles de vehículos y sus clases concretas, lo que aumenta el acoplamiento y la complejidad del código. Además, si se quisiera añadir un nuevo tipo de vehículo, habría que modificar el código de la clase Vehiculo para incluirlo, lo que viola el principio de abierto-cerrado. Por eso, el patrón factory method es una buena práctica que mejora el diseño y la calidad del software.

Espero que este ejemplo te haya ayudado a entender cómo se vería la funcionalidad que hace el factory method sin implementar el patrón de manera monolítica. 

### Referencias

---

- Aquí puedes ver todos los [patrones de diseño](https://java-design-patterns.com/patterns/) y muy sencillo como aplicarlo 
patrones de diseño
- [Implementación de patrones de diseño](https://www.youtube.com/playlist?list=PLvimn1Ins-41Uiugt1WbpyFo1XT1WOquL)

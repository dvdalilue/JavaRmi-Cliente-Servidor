RMI Java - Client/Server
========================

Repositorio del proyecto 2 de [CI-4835] Redes de Computadoras

Resumen - Primeros Pasos
-------

En primera instancia se desea crear una aplicacion cliente-servidor
similar al proyecto 1 de esta misma materia en este mismo
periodo. Pero en esta ocasion se hacer uso de RCP orientado a la
comunicacion en `Java`. Para ello se deben seguir una serie de pasos,
ademas de eso se deben usar las librerias `java.rmi.*` y en primer
lugar, se debe crear una interfaz definida por el `cliente` para que
sea implementada por el `servidor`. Luego de que se a creado la
interfaz, la clase servidor que la implemente y cliente que haga la
llamada a procedimiento remota (RCP), se compilan ambos con `javac
file.java` y se debe crear el `Stub` del server, para ello se usa el
comando `rmic fileServerImpl` sin .java. Despues de esto se debe crear
un archivo `fileServerImpl_Stub.class`. El stub se encarga de todo el
proceso de empaquetar y guardar bien la informacion a mandar(marshal).

Un codigo ejemplo para la interfaz que implementa el server:

```java
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiServer extends Remote {

    public String getMessage() throws RemoteException;

    public long add(long a, long b) throws RemoteException;
}
```

Se puede crear un Makefile, como por ejemplo:

```bash
# Compilers
JAVAC = javac
RMIC = rmic

# Sources
SERVER = RmiServerImpl.java
CLIENT = RmiClient.java

all: RmiServer RmiClient
	$(RMIC) RmiServerImpl

RmiServer: $(SERVER)
	$(JAVAC) $<

RmiClient: $(CLIENT)
	$(JAVAC) $<

clean:
	\rm -f *.class *.*~
```

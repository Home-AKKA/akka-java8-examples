
# akka-java8-example

* https://github.com/opencredo/akka-java8-examples

Пример кода из блога [https://opencredo.com/improved-akka-java-8](https://opencredo.com/improved-akka-java-8)

Несколько очень простых примеров использования интерфейса Akka в Java-8.
Имплементация бара (взаимодействие с пользователем).



## Running examples

```
mvn package exec:java -P simple
```
Простейшая имплементация с использованием простого `AbstractActor`
 
Ожидается мало взаимодействий.
Чтобы остановить приложение нажмите `Ctrl-C`.

```
mvn package exec:java -P become
```
Actor-бармена который готов когда кофейная машина разогревается...


```
mvn package exec:java -P future
```
Имплементация с использованием `CompletableFuture` 

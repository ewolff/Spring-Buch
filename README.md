# Spring-Buch-Beispiel: Lies Mich

## Einführung

Willkommen zu den Beispielanwendungen aus dem Spring-3-Buch!

Die Beispiele sind in verschiedenen Unterverzeichnissen
organisiert. Dadurch können Sie die Beispiel jeweils individuell
ausführen und mit ihnen experimentieren.

Viel Spaß dabei!

## Zwei Worte zu Maven

Die Beispiele basieren auf dem Build-Tool Maven. Das hat
verschiedene Vorteile:

* Es müssen keine abhängigen Bibliotheken installiert
werden

* Es ist auch kein Application- oder Web-Server zu installieren,
	auch das wird von Maven erledigt.

Zur Installation von Maven müssen Sie folgendes

* Installieren Sie maven 2.2.1 von <http://maven.apache.org/download.html>

* Fügen Sie das maven bin Verzeichnis in den Pfad ein.  Kontrolle: In
	einer Eingabeaufforderung müssen Sie nun `mvn` ausführen
	können.


## Maven-Projekte
Die Maven-Projekte haben ein festes Layout:

* In `src/test` liegt der Anteil des
Projekts, der für die Tests notwendig ist.

* In `src/main` liegt der Anteil des
	Projekts, der für die Funktionalitäten benötigt wird.

Diese beiden Verzeichnisse haben identische Unterverzeichnisse:

* In `java` liegt der Java-Code.

* Und in `resources` die übrigen Ressourcen.

Meinstens haben die Projekte Tests, die die Benutzung der
Spring-Features verdeutlicht.

Allgemeine Einstellungen sind in dem `pom.xml` in diesem
Verzeichnis.

Sie können alle Projekt mit `mvn install` in dem
Hauptverzeichnis, in dem auch diese Datei liegt, compilieren, testen
und in Ihr lokales Maven-Repository kopieren. Dies geht auch für
einzelne Projekte, wenn Sie mvn install in dem Unterverzeichnis
ausführen.

Web-Anwendungen kann man mit `mvn jetty:run` in dem jeweiligen
Verzeichnis starten. Einige Projekt haben Tests in einem Package
remotetest. Diese Test setzen voraus, dass der Server läuft und
werden bei einem mvn install nicht ausgeführt. Sie müssen
also nach dem Start des Servers manuell gestartet

##  Eclipse-Projekte erzeugen

Ich empfehle die Nutzung der Spring Tool Suite, da diese
Eclipse-Distribution schon einige wichtige Plug Ins integriert
hat. Sie können STS unter <https://spring.io/tools>
runterladen. Alternativ kann man ein Eclipse mit mindestens der
m2eclipse-Maven-Integration nutzen.

Legen Sie einen neuen Eclipse-Workspace an. Dazu kann man *File ->
Switch Workspace...* verwenden und in dem Dialog dann ein neues
Verzeichnis eingeben.

Anschließend müssen Sie im File-Menü den Eintrag
*Import*. In dem erscheinenden Dialog wählen Sie unter *General* den Punkt
*Maven Projects*. Dann drücken Sie den *Next*-Button.
Als *Root*-Verzeichnis wählen Sie das Verzeichnis mit den
ausgepackten Beispielen. Dann drücken Sie den *Finish*-Button. Der
Workspace baut sich dann auf.

Web-Anwendungen kann man dann in Eclipse einfach mit einem rechten
Mausklick auf das Projekt starten. Dort wählt man *Run As* und dann *Run
On Server*. Man muss dann nur noch einen Server auswählen, auf dem die
Anwendungen laufen soll.

## [beispielanwendung](beispielanwendung)

Dieses Projekt enthält die Basis-Funktionalitäten der
Beispielanwendung, also den Geschäftslogik-Kern. Dazu zählt
auch die Persistenz. Das Projekt enthält Tests für die
Funktionalitäten. Dadurch kann man sehen, dass die Anwendung
tatsächlich funktioniert. Die Tests werden während der
Installation ausgeführt. Durch die Installation steht der
Geschäftslogik-Kern den andern Beispielen zur Verfügung.

## [beispielanwendung-annotations](beispielanwendung-annotations)

Im Prinzip wie die Beispielanwendung - aber mit Annotationen statt
XML-Konfiguration. 

## [beispielanwendung-jsr330](beispielanwendung-jsr330)

Im Prinzip wie die Beispielanwendung - aber mit JSR330-Annotationen statt
XML-Konfiguration.

## [beispielanwendung3](beispielanwendung3)

Eine vollständige Spring-3-Anwendung zum Beispiel mit REST.

## [el](el)

Einige Beispiele für die Spring Expression Language (SpEL).

## [web](web)

Das Web-Projekt ist ein Web-Frontend auf Basis von Spring MVC.  Damit
dieses Projekt funktioniert, muss vorher die Beispielanwendung
installiert worden sein. Es kann dann mit jetty:run gestartet
werden. Die URL ist <http://localhost:8080/web>.

## [jsf](jsf)

Das JSF-Projekt ist ein Web-Frontend auf Basis von JSF mit Integration
in Spring. Damit dieses Projekt funktioniert, muss vorher die
Beispielanwendung installiert worden sein. Es kann dann mit `mvn
jetty:run` gestartet werden.

Die URL ist <http://localhost:8080/jsf>.

## [springsecurity](springsecurity)

Das Spring-Security-Beispiel setzt auf dem Web-Beispiel auf.  Das
Spring-Security-Projekt lädt das Web-Projekt und ergänzt es um den
Sicherheits-Aspekt mit Hilfe von Spring Security.

Man kann es dann mit `mvn jetty:run` starten.

Die Anwendung steht dann unter der URL <http://localhost:8080/acegi/>
zur Verfügung.

Leider unterstützt die Maven-Eclipse-Integration die
Ergänzung von Web-Anwendungen nicht, man kann die Anwendung in
Eclipse also nicht starten.

## [webflow](webflow)

Dieses Projekt benutzt Spring Web Flow zum Aufbaue eines etwas
komplexeren Bestell-Prozesses. Man kann es mit `mvn jetty:run` starten.

Die Anwendung steht dann unter der URL <http://localhost:8080/webflow/>
zur Verfügung.

## [remoting](remoting)

Das Remoting-Projekt zeigt die Remoting-Technologien aus Spring.
Dabei wird Hession, Burlap und der HttpInvoker von Spring
gezeigt. Dazu muss die Beispielanwendung im Maven-Repository
installiert sein. Dann muss man den Web-Server mit `mvn jetty:run`
starten. Dann kann man auch die Tests aus dem Package remotetest
ausführen.

## [webservice](webservice)

Dieses Projekt nutzt Spring Web Services für die Implementierung eines
Web Service. Man kann den Web Service mit `mvn jetty:run`
starten. Dann kann man auch die Tests aus dem Package remotetest
ausführen.


## [webservice-annnotations](webservice-annotations)

Wie webservice - allerdings werden hier Annotationen für die
Implementierung der Web Services genutzt.

## [enterprise](Enterprise)

Dieses Projekt zeigt die Verwendung der Enterprise-Features wie JMS,
Timer und JMX. JMS und Timer werden durch `mvn test` laufen
gelassen. Bei JMX muss man mit `mvn exec:exec` die Anwendung
starten. Anschließend kann man die `jconsole` die
JDK-Management-Console starten und sich die JMX-Beans anschauen. Sie
ändern auch regelmäßig ihre Werte.

## [aop2](aop2)

In diesem Projekt werden die AOP-Features von Spring 2 gezeigt.  Dazu
sind entsprechende Tests vorhanden, die man mit `mvn test`
ausprobieren kann.

## [beispiele](beispiele)

In diesem Projekt werden verschiedene kleiner Beispiele mit Spring
gezeigt. Dazu sind entsprechende Tests vorhanden, die man mit `mvn
test` ausprobieren kann.

## [aop-benchmark](aop-benchmark)

Dieses Projekt enthält den Code für den AOP-Benchmark. Er wird mit
	`mvn test` gestartet.

## [feedback](Feedback)
Feedback bitte an [Eberhard Wolff](mailto:eberhard.wolff@gmail.com).

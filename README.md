# V2TOSAD Groep 2 : Business Rule Generator
#### 1.0-SNAPSHOT
Applicatie voor het genereren van PL/SQL code op basis van gecreëerde Business Rules. Een Java-applicatie die draaiende gehouden wordt door Tomcat. Website haalt informatie van de Java klassen d.m.v. REST-Services.\
\
Applicatie is getest binnen Chrome.

## Getting Started: De applicatie draaien.
De applicatie kan opgestart worden door deze te importeren in IntelliJ IDEA van Jetbrains.
Er moeten alleen een paar dingen zelf gedaan worden. Omdat ojdbc7.jar van Oracle niet opensource is mag
deze niet gehost worden hier. Deze kan [hier](https://www.oracle.com/technetwork/database/features/jdbc/jdbc-drivers-12c-download-1958347.html/)
gedownload worden.
1. Plaats ojdbc7.jar dan in src/main en dan wordt deze opgepakt door Maven.
2. Plaats ojdbc7.jar in de lib folder van je Tomcat installatie.

## Getting Started: Aan de CSS van deze applicatie knoeien
De front-end van de website is gemaakt met Bootstrap en Font Awesome 4. In plaats van een CDN te gebruiken kozen wij
ervoor om de Bootstrap en Font Awesome en zijn vereisten zelf te compilen. Hiervoor hebben we Yarn (voor de dependency's)
en Gulp (het compilen van de css, font en js bestanden)./
/
Vereisten: NPM en Yarn moeten geïnstalleerd zijn.

1. Open je CMD/PowerShell in de webfolder.
2. Voer `yarn` uit en deze download alle node dependency's van het project
3. Herstart je CMD. Heropen deze in dezelfde map en voer `gulp` uit.
4. Alle scss bestanden van alle dependency's (incl je eigen scss) zijn gecompiled naar web/public/dist. Als je je
eigen scss (in web/assets/styles/main.scss) wilt aanpassen voer dan eerst `gulp watch` uit. Elke keer wanneer jij iets
 opslaat dan compilet gulp meteen de nieuwe scss bestanden. Het enige wat jij hoeft te doen is Tomcat herladen.

---

V2TOSAD Groep 3, V2C\
Hogeschool Utrecht

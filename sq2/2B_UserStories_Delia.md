# User story

---

## Error handling

Wer: User
Was: Backend nicht gestartet
Warum: Um auf ein nicht funktionierenden teil hinzuweisen

| Akzeptanzkriterien |  
| :--- |  
| 1. User kann die Frontend andwendung starten und über einen Browser erreichen. |
| 2. User kann einen neune Task erstellen. | 
| 3. Nach dem der Task abgeschickt wurde, kommt die Fehlermeldung das keine antwort vom Backend kommt (Not running). |
| 4. Nach einschalten des Backends verschwindet die Fehlermeldung nach aktualisierung des Browsers. |

Wer: User
Was: Fehlermeldung bei fehlerhaften Datenverbindungen anzeigen
Warum: Um den User über Verbindungsprobleme zu informieren

| Akzeptanzkriterien |  
| :--- |  
| 1. Der User kann die Frontend-Anwendung starten und über einen Browser erreichen. |
| 2. Der User kann auf Seiten zugreifen, die Daten vom Backend benötigen. |
| 3. Bei einer fehlerhaften Datenverbindung (z.B. Netzwerkprobleme) wird eine entsprechende Fehlermeldung angezeigt. |
| 4. Die Fehlermeldung verschwindet, wenn die Datenverbindung wiederhergestellt wird und die Seite aktualisiert wird. |

Wer: User
Was: Abrufen der Datenbank
Warum: Um gespeicherte Tasks zu sehen

| Akzeptanzkriterien |  
| :--- |  
| 1. Der User Startet normal die Anwendung (Frontend und Backend). |
| 2. Der User sieht durch eine Fehler Meldung, dass die Datenbank nicht angeschlossen ist. | 
| 3. Der User muss die Datenbank starten. |
| 4. Die Fehlermeldung verschwindet, wenn die Datenbank wieder verbunden ist und die Seite aktualisiert wird. |


## Unit tests

Wer: Entwickler
Was: Unit tests für Datenverarbeitungskomponenten schreiben
Warum: Um die korrekte verarbeitung von Daten zu gewährleisten

| Akzeptanzkriterien |  
| :--- |  
| 1. Es existieren Unit-Tests für alle Datenverarbeitungskomponenten. |
| 2. Jeder Testfall deckt verschiedene Szenarien und Randfälle ab. | 
| 3. Alle Tests werden automatisch bei jedem Build ausgeführt. |
| 4. Alle Tests müssen am ende Erfolgreich sein. |

Wer: Entwickler
Was: Unit tests für API-Endpunkte schreiben
Warum: Um die funktionalität der API zu gewährleisten

| Akzeptanzkriterien |  
| :--- |  
| 1. Es existieren Unit tests für alle API-Endpunkte. |
| 2. Jeder Testfall überprüft die korrekte Antwort und Fehlerbehandlung der Endpunkte. | 
| 3. Alle Tests werden automatisch bei jedem Build ausgeführt. |
| 4. Tests müssen am ende Erfolgreich sein. |

Wer: Entwickler
Was: Unit Tests für UI-Komponenten schreiben
Warum: Um die funktionalität der UI-Komponenten zu gewährleisten

| Akzeptanzkriterien |  
| :--- |  
| 1. Es existieren Unit Tests für alle wesentlichen UI-Komponenten. |
| 2. Jeder Testfall deckt die Interaktionen und Zustände der UI.Komponenten ab. | 
| 3. Alle Tests werden automatisch bei jedem Build ausgeführt. |
| 4. Tests müssen am ende Erfolgreich sein. |
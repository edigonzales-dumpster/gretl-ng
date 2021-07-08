# gretl-ng

## todo
- AntLogAdaptor:
  * Funktioniert, aber glücklich ist anders. 
  * Vielleicht sollte noch ein Logger geschrieben werden, damit man bei den Klassen (die nicht Task) extenden sieht, woher die Logmeldung stammt.
  * Bei ili2pg wird immer die üblichen Infos (wie bei CLI-Aufruf) geloggt. Richtig verstanden habe ich es noch nicht. Es scheint, als käme das auf STDERR. Das Handling / die Steuerung mit STDOUT scheint zu funktionieren. Irgendwie habe ich das Gefühl, dass mir dieses Verhalten schon im Original-Gretl aufgefallen ist. D.h. auch unterschiedlichens Verhalten Lokal vs. Jenkins (der nur STDOUT in den ConsoleOutput schreibt?)
- ant dependency with gradle
- ilivalidator task (ganz simpel)
  * filecollection? aka *-validation (identisch zu ili2db filecollection etc. ?)
  * failonerror
  * throw exception (ganz grundsätzlich)
- ili2db
- deploy to sonatype / mavenCentral
- integration tests (jar und docker)
- iox-ili: reader / validation problem (> v X.X)
- db2db task

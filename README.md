Nichtsquentielle Programmierung

---------------------------------------------------------------------

Water.java

Sollen Sie ein Java Programm schreiben, dass die Bindung von
Wassermolekülen mit Hilfe von Threads simuliert.
Es sollen Wasserstoff und Sauerstoff Threads produziert werden, die mit Hilfe eines Barrier zu
einem Molekül synchronisiert werden.
Wenn ein Sauerstoff Thread an die Barrier ankommt und kein Wasserstoff Thread da ist, muss
es warten, bis zwei Wasserstoff Threads ankommen.
Wenn ein Wasserstoff Thread an die Barrier ankommt und keine weiteren Threads da sind,
muss es auf ein Wasserstoff und ein Sauerstoff Thread warten.
Die Synchronisation besteht daraus, dass nur Thread-Gruppen, die aus einem Sauerstoff und
zwei Wasserstoff Threads bestehen, die Barrier verlassen können. Sie müssen dafür eine
korrekte Wasserstoff und Sauerstoff Klasse von Thread-Objekten definieren, die mit diesen
Einschränkungen korrekt funktionieren. 

------------------------------------------------------------------------

Kita.java

Nehmen wir an, in einer private Kita gelten strenge Regeln bezüglich der minimalen Anzahl von
Erzieherinnen, die für die Pflege der Babies tagsüber notwendig sind.
1) Pro Erzieherin dürfen maximal 5 Babies aufgenommen werden.
2) Neue Babies werden nicht akzeptiert, wenn nicht genug Erzieherinnen bereits eingetroffen
sind.
3) Die Erzieherinnen dürfen nicht einfach weggehen und eine Überzahl von Babies den
restlichen Erzieherinnen überlassen.
Definieren Sie eine Kita-Klasse in Java, indem Sie mit Hilfe von Java-Semaphoren die
Aufnahme und das Abholen der Babies sowie das Eintreffen und die Entlastung der
Erzieherinnen synchronisieren.
Sowohl die Erzieherinnen als auch die Eltern mit Babies sollen mit Hilfe von Threads simuliert
werden.

--------------------------------------------------------------------------------

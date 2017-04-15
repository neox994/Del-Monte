# Del-Monte
HomeWork3

Potrebno je kreirati aplikaciju koja koristi jednostavnu bazu podataka za kreiranje korisničkih zabilješki(neka vrsta To do liste). Nužno je omogućiti korisniku kreiranje zabilješki, spremanje u bazu, pregled zabilješki u obliku liste te njihovo brisanje. Koristiti primjere u vježbi te naučeno na proteklim vježbama. Kreirati ListActivity prema slici koji će služiti kao početni zaslon, Listu popuniti iz baze. Kreirati vlastitu klasu Task koja će predstavljati zadatak, potrebno najmanjeje imati atribute za naslov, tekst zadatka, i sliku koja predstavlja prioritet (crveno, žuto zeleno). Definirati izgled elementa liste u XML-u Kreirati DBHelper klasu po uzoru na LV i kreirati bazu i u njoj tablicu za pohranu. Kreirati dodatni Activity za unos zadataka koji se pokreće klikom na gumb iz prvog Activitya. Implementirati brisanje na dugi klik na element liste.
Tasky je aplikacija koja koristi bazu podataka i  omogućava korisniku unos određenih zadataka uz kratak opis i kategoriju, (vrsta To Do Liste). Aplikacija je napravljena uz pomoć prijašnjih LV-ova te raznih materijala sa interneta. Pošto sam sa spinnerima već radio taj dio koda je išao poprilično lagano, prvi problem je bio kreiranje baze podataka i DBHelper-a. Problem je riješem pomoću sljedećeg linka : http://mobilesiri.com/android-sqlite-database-tutorial-using-android-studio/
Drugi problem se pojavio prilikom brisanja podataka iz baze link:
http://stackoverflow.com/questions/7510219/deleting-row-in-sqlite-in-android
Aplikacija radi bez ikakvih problema i neruši se. Prilikom ne unošenja teksta u potrebna polja izbacuje se poruka i prilokom brisanja podataka iz baze također se izbaci poruka da je zadatak obrisan.
Malo sam poradio na dizajnu pa ova aplikaciju puno bolje izgleda od prijašnjih.

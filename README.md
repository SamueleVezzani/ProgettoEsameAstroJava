🚀 AstroJava
Un videogioco 2D sviluppato in Java (JavaFX) che si ispira alle meccaniche puzzle-arcade del gioco mobile "Tomb of the Mask".
Il giocatore controlla una navicella spaziale all'interno di labirinti insidiosi. La particolarità del movimento è che, una volta scelta una direzione, la navicella si ferma solo quando incontra un ostacolo (un muro o un trampolino).

🎮 Meccaniche di Gioco
Movimento a scorrimento: Usa W, A, S, D o le Frecce Direzionali per muoverti. La navicella viaggia dritta fino al prossimo ostacolo.

Ostacoli e Pericoli: Evita i blocchi mortali (fuoco/trappole) altrimenti perderai una vita e ricomincerai il livello.

Trampolini (Boost): Speciali blocchi angolati che deviano istantaneamente la navicella in un'altra direzione, conferendo un "Boost" (velocità raddoppiata) che si esaurisce solo al prossimo impatto con un muro.

Punteggio e Portale: Raccogli le stelle piccole e grandi per aumentare il punteggio. Raggiungi il portale finale per passare al livello successivo.

📂 Struttura del Progetto (Architettura MVC-Like)
Il progetto è strutturato in pacchetti ben definiti per separare la logica di gioco dall'interfaccia utente:

1. org.example.progettoesameastrojava.gestionegenerale
Gestisce l'avvio e il flusso del programma:

Launcher.java: La classe main dell'applicazione JavaFX. Si occupa di precaricare gli asset e inizializzare la finestra.

SceneManager.java: Gestisce gli scambi di scena (View) permettendo transizioni fluide tra il Menu Principale e il Gioco, bloccando in automatico il ciclo di gioco quando necessario.

2. org.example.progettoesameastrojava.schermatevisive
Contiene la UI (Interfaccia Utente) vera e propria:

MenuScreen.java: La schermata iniziale con i pulsanti per Giocare o Uscire.

GameScreen.java: Gestisce il layout durante la partita. Contiene l'HUD (Punteggio e Vite), il Canvas di gioco auto-ridimensionante e i pannelli in sovrimpressione per il Game Over e la Vittoria.

3. org.example.progettoesameastrojava.GameEngine
Il cuore pulsante del gioco:

GameLoop.java: Estende AnimationTimer. Processa l'input da tastiera, aggiorna la logica a 60 FPS, carica dinamicamente le mappe da file di testo (.txt) e gestisce le transizioni di livello.

Renderer.java: Disegna tutti gli elementi grafici sul Canvas. Implementa una telecamera fluida (Lerp) che segue costantemente la navicella del giocatore.

AssetManager.java: Classe di utilità per caricare e mantenere in memoria (HashMap) tutti gli sprite grafici una singola volta, ottimizzando le risorse.

4. org.example.progettoesameastrojava.GameEngine.Entities
Player.java: Gestisce tutta la fisica, la logica e lo stato del giocatore (Posizione, Velocità, Vite, Punteggio). Qui avvengono i complessi controlli di collisione predittiva sulla griglia (Tile-based), la logica di raccolta oggetti e il sistema di rimbalzi perfetti sui trampolini.

🗺️ Sistema delle Mappe
I livelli sono generati tramite file testuali posizionati in src/main/resources/maps/. La prima riga indica le dimensioni della griglia (es. 30 30), seguita da una matrice di numeri dove ogni intero rappresenta un "Tile":

0 = Spazio vuoto

1 = Muro

2 = Spawn del giocatore

3 = Ostacolo mortale

4 = Portale di fine livello

5 / 6 = Stelle e Stelline (Punti)

7 a 10 = Trampolini direzionali

🛠️ Requisiti
Java 17 o superiore

JavaFX SDK

Maven (per la gestione delle dipendenze e la build)

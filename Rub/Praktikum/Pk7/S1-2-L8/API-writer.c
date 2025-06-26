int main (){
    //Kindprozess starten, der api.sh ausführt
    //
    while(getppid == 0){
        sleep(5);
        fork();
    }
    //Ausgabe vom Kind auffangen
    //
    //Shared memory erstellen/öffnen
    //
    //Zeilen auswerten und in den shared memory schreiben
}
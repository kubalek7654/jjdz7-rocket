package teamrocket.menu;

import teamrocket.App;
import teamrocket.model.Event;

import teamrocket.model.EventService;
import teamrocket.model.Game;
import teamrocket.model.UserService;
import teamrocket.util.Util;

import javax.imageio.stream.ImageInputStream;
import java.io.IOException;
import java.util.Scanner;

import static teamrocket.model.UserService.list;

public class Menu {

     private boolean exit;

    public void menuStart() throws IOException {
        printMainMenuInfo();
        do {
            int choice = Util.readUserInputInteger();
            decide(choice);
        } while (!exit);
    }

    private void printMainMenuInfo() {
        System.out.println(" ##### Menu Aplikacji #####  ");
        System.out.println("1: ##### Gry Planszowe  #####");
        System.out.println("2: ##### Wydarzenia     #####");
        System.out.println("3: ##### Ulubuione      #####");
        System.out.println("0: ##### Wyjście        #####");
    }

    private void printGameMenuInfo() {
        System.out.println("### Menu Gier Planszowych ###");
        System.out.println("1: Pokaż wszystkie gry");
        System.out.println("2: Szukaj gry po jej nazwie ");
        System.out.println("3: Filtruj gry po typie ");
        System.out.println("4: Filtruj gry po liczbie graczy");
        System.out.println("9: Wróć do głonwego menu");
        System.out.println("0: Wyjście");
    }

    private void printEventMenuInfo(){
        System.out.println("1: Pokaż wszystkie wydarzenia ");
        System.out.println("2: Filtruj wydarzenia po typie");
        System.out.println("3: Filtruj wydarzenia po dacie");
        System.out.println("4: Dodaj nowe wydarzenie ");
        System.out.println("9: Wróć do głonwego menu");
        System.out.println("0: Wyjście");
    }

    private void printFavouritesMenuInfo(){
        System.out.println("1: Dodaj grę do ulubionych ");
        System.out.println("2: Pokaż ulubione gry");
        System.out.println("9: Wróć do głonwego menu");
        System.out.println("0: Wyjście");
    }

    private void printBadChoice() {
        System.out.println("Niepoprawna opcja. Podaj parametr ponownie.");
    }

    private void decide(int choice) throws IOException {
        switch (choice) {
            case 1:
                do {  printGameMenuInfo();
                    int gameChoice = Util.readUserInputInteger();
                    decideGame(gameChoice);
                } while (!exit);
                break;

            case 2:
                do {
                    printEventMenuInfo();
                    int eventChoice = Util.readUserInputInteger();
                    decideEvent(eventChoice);
                } while (!exit);
                break;

            case 3:
                do {
                    printFavouritesMenuInfo();
                    int favouritesChoice = Util.readUserInputInteger();
                    decideFavourites(favouritesChoice);
                } while (!exit);
                break;
            case 0:
                exit = true;
                break;
            default:
                printBadChoice();
        }
    }


    private void decideGame(int gameChoice) {
        Game.createArrayFromRepo();
        switch (gameChoice) {
            case 1:
                Game.showAllGames();
                printGameMenuInfo();
                break;
            case 2:
                Game.searchGameByName();
                break;
            case 3:
                Game.filterByGameType();
                break;
            case 4:
                Game.filterByNumberOfPlayers();
                break;
            case 9:
                break;
            case 0:
                exit = true;
                break;
            default:
                printBadChoice();
        }
    }

    private void decideEvent(int eventChoice) throws IOException {
        Event.createArrayFromRepo();
        switch (Util.readUserInputInteger()) {
            case 1:
                Event.showAllEvents();
                return;
            case 2:
                Event.filterEventsByType();
                break;
            case 3:
                Event.filterEventByDate();
                break;
            case 4:
                EventService eventService = new EventService();
                eventService.addEvent(eventService.getEventFromConsole(), eventService.getEventID());
                break;
            case 9:
                menuStart();
            case 0:
                exit = true;
                break;
            default:
                printBadChoice();
        }

    }

    private void decideFavourites(int favouritesChoice) throws IOException {
        switch (Util.readUserInputInteger()) {
            case 1:
                UserService.addUser(UserService.getUserFromConsole());
                break;
            case 2:
                list(UserService.getUsers());
                break;
            case 9:
                menuStart();
            case 0:
                exit = true;
                break;
            default:
                printBadChoice();
        }

    }
}



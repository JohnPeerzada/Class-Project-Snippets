#define MYSQLPP_MYSQL_HEADERS_BURIED
#include <mysql++/mysql++.h>
#include <string>
#include <iostream>
#include <iomanip>
#include "Film.h"


int main(int argc, char* argv[]) {
    // Connect to database with: database, server, userID, password
    int option = 0;
    while(option != -1) {
        std::cout << "A Query Application for Actor Data" << std::endl;
        std::cout << "1. Find actors by first name / letter" << std::endl;
        std::cout << "2. Find actors by name and actorId" << std::endl;
        std::cout << "3. Find how many actors with same first name / letter" << std::endl;
        std::cout << "4. Assign an actor with a Movie genre" << std::endl;
        std::cout << "5. Exit" << std::endl;\
        std::cout << "Enter a menu number >>> " << std::endl;
        std::cin >> option;
        if (option == 1) {
            std::cout << "Enter actor name or letters >>> " << std::endl;
            Query1(option);
        }

        if (option == 2) {
            std::cout << "Enter actor name >>> " << std::endl;
            Query2(option);
        }

        if (option == 3) {
            std::cout << "Enter actor name or letters to see number of actors with same names / same letters >>> " << std::endl;
            Query3(option);
        }

        if (option == 4) {
            std::cout << "Enter actor id 1-16 >>> " << std::endl;
            Query4(option);
        }

        if(option > 5 || option < 1) {
            std::cout << "Invalid Input, Try again..." << std::endl;
            option = 0;
        }

        if (option == 5) {
            option = -1;
        }
    }

    return 0;
}

#ifndef FILM_H
#define FILM_H
#define MYSQLPP_MYSQL_HEADERS_BURIED
#include <mysql++/mysql++.h>
#include <string>
#include <iostream>

class Film {
   public: 
      Film();
      Film(int actorId, int categoryId, std::string firstName, std::string lastName);
      void Query1(int option);
      void Query2(int option);
      void Query3(int option);
      void Query4(int option);
      int getActorId();
      int getCategoryId();
      std::string getFirstname();
      std::string getLastName();
   private:
      int actorId;
      int categoryId;
      std::string firstName;
      std::string lastName;
};

#endif
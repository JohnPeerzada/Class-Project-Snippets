#define MYSQLPP_MYSQL_HEADERS_BURIED
#include <mysql++/mysql++.h>
#include <string>
#include <iostream>
#include "Film.h"

// "Copyright 2022 John Peerzada"  [legal/copyright] [5]
    Film::Film() {
        this->actorId = 0;
        this->categoryId = 0;
        this->firstName = "";
        this->lastName = "";
    }

    Film::Film(int actorId, int categoryId, std::string firstName, std::string lastName) {
        this->actorId = actorId;
        this->categoryId = categoryId;
        this->firstName = firstName;
        this->lastName = lastName;
    }

    void Film::Query1(int option) {
        mysqlpp::Connection myDB;
        if (myDB.connect("cse278F2022", "localhost", "cse278F2022",
                "raspberrySeltzer")) {
            // Create a query
            mysqlpp::Query query = myDB.query();
            std::cin >> firstName;
            query << "select First_name, last_name "
            << "FROM actor "
            << "WHERE first_name LIKE" << firstName << "order by last_name";
            query.parse();
            mysqlpp::StoreQueryResult result = query.store();
            // Always check for errors
            if(result) {
                std::cout  << std::left <<std::setw(40) << "First Name"
                        << std::setw(40)<< "Last Name"
                        << std::endl;
                std::string line(80,'-');
                std::cout << line <<std::endl;
                for (const auto & row : result) {

                    std::cout <<std::left<<std::setw(40)<< row[0].c_str()
                        << std::setw(40) << row[1]
                        << std::endl;
                }  // done printing results
            }
            else {
                std::cerr << "Query failed: " << query.error() << std::endl;
            }

        } else {
            std::cerr << "Connection failed: " << myDB.error() << std::endl;
        }
    }

    void Film::Query2(int option) {
        mysqlpp::Connection myDB;
        if (myDB.connect("cse278F2022", "localhost", "cse278F2022",
                "raspberrySeltzer")) {
            // Create a query
            mysqlpp::Query query = myDB.query();
            std::cin >> firstName;
            std::cout << "How many names do you want to see >>> " << std::endl;
            std::cin >> actorId;

            query << "select First_name, Actor_id "
            << "FROM actor "
            << "where actor_id < " << actorId << "AND first_name like " << firstName;
            query.parse();
            mysqlpp::StoreQueryResult result = query.store();
            // Always check for errors
            if(result) {
                std::cout  << std::left <<std::setw(40) << "First Name"
                        << std::setw(40)<< "Actor Id"
                        << std::endl;
                std::string line(80,'-');
                std::cout << line <<std::endl;
                for (const auto & row : result) {

                    std::cout <<std::left<<std::setw(40)<< row[0].c_str()
                        << std::setw(40) << row[1]
                        << std::endl;
                }  // done printing results
            }
            else {
                std::cerr << "Query failed: " << query.error() << std::endl;
            }

        } else {
            std::cerr << "Connection failed: " << myDB.error() << std::endl;
        }
    }

    void Film::Query3(int option) {
        mysqlpp::Connection myDB;
        if (myDB.connect("cse278F2022", "localhost", "cse278F2022",
                "raspberrySeltzer")) {
            // Create a query
            mysqlpp::Query query = myDB.query();
            std::cin >> firstName;
            query << "select count(first_name) "
            << "FROM actor "
            << "where first_name like" << firstName << "order by count(*)";
            query.parse();
            mysqlpp::StoreQueryResult result = query.store();
            // Always check for errors
            if(result) {
                std::cout  << std::left <<std::setw(40) << "Number of People that start with " << firstName << std::endl;
                std::string line(40 ,'-');
                std::cout << line <<std::endl;
                for (const auto & row : result) {

                    std::cout <<std::left<<std::setw(40)<< row[0].c_str()
                        << std::endl;
                }  // done printing results
            }
            else {
                std::cerr << "Query failed: " << query.error() << std::endl;
            }

        } else {
            std::cerr << "Connection failed: " << myDB.error() << std::endl;
        }
    }

    void Film::Query4(int option) {
        mysqlpp::Connection myDB;
        if (myDB.connect("cse278F2022", "localhost", "cse278F2022",
                "raspberrySeltzer")) {
            // Create a query
            mysqlpp::Query query = myDB.query();

            std::cin >> actorId;; 
            std::cout << "Enter category 1-16 to assign a actor with a Genre" << std::endl;
            std::cin >> categoryId;

            query << "select actor.first_name, category.name "
            << "from actor,category "
            << "WHERE actor.actor_id =" << actorId << "AND category.category_id = " << categoryId;
            query.parse();
            mysqlpp::StoreQueryResult result = query.store();
            // Always check for errors
            if(result) {
                std::cout  << std::left <<std::setw(40) << "First Name"
                        << std::setw(40)<< "Last Name"
                        << std::endl;
                std::string line(80,'-');
                std::cout << line <<std::endl;
                for (const auto & row : result) {

                    std::cout <<std::left<<std::setw(40)<< row[0].c_str()
                        << std::setw(40) << row[1]
                        << std::endl;
                }  // done printing results
            }
            else {
                std::cerr << "Query failed: " << query.error() << std::endl;
            }

        } else {
            std::cerr << "Connection failed: " << myDB.error() << std::endl;
        }
    }

    int Film::getActorId() {
        return actorId;
    }

    int Film::getCategoryId() {
        return categoryId;
    }

    std::string Film::getFirstname() {
        return firstName;
    }

    std::string Film::getLastName() {
        return lastName;
    }
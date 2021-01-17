# The E-Bartender App

The <b>E-Bartender</b> is a Spring Boot web application that gives each registered user a personal bar (i.e. an inventory of ingredients with CRUD functionality) and then allows the user to search for cocktails whose ingredients match his/her bar (or are only one or two ingredients away from doing so).

The app uses <b>TheCocktailDB</b>'s API (https://www.thecocktaildb.com/) to retrieve ingredient and cocktail data. In order to minimize the number of expensive calls to the API, the cocktail data is also saved in the same MySQL database that contains user and ingredient information. After that, the API gets called judiciously to update cocktail and ingredient information.

The E-Bartender uses Thymeleaf's template engine and a tiny bit of JavaScript (mostly Bootstrap).

It is deployed at http://e-bartender.net/ using Amazon Web Services's Elastic Beanstalk.

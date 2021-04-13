# Loteria

Lotería is a traditional game of chance, similar to bingo, but using images on a deck of cards instead of numbered ping pong balls. Every image has a name and an assigned number, but the number is usually ignored. Each player has at least one tabla, a board with a randomly created 4 x 4 grid of pictures with their corresponding name and number. Players choose a tabla to play with, from a variety of previously created tablas, each with a different selection of images.

Lotería is the Spanish word for lottery. The deck is composed with a set of 54 different cards with a picture on it. To start, the caller (cantor, or singer) shuffles the deck. One by one, the caller picks a card from the deck and announces it to the players by its name, sometimes using a verse before reading the card name. Each player locates the matching pictogram of the card just announced on their board and marks it off with a chip or other kind of marker. 

Taken from wikipedia, you can read more about the game [here](https://en.wikipedia.org/wiki/Loter%C3%ADa).

## How works

It's based on the Producer–consumer problem, you can read more about [here](https://en.wikipedia.org/wiki/Producer%E2%80%93consumer_problem).

This program uses thread two thread that consumes the card that it's taken for the producer thread from the card stack.

The producer thread notify both thread when a card has been taken from the stack then the threads reads the stack and the last one in read the card notify the producer to take a new card from the stack, when a consumer mark all its cards with beans in the table the game stop.

![](https://i.imgur.com/Y9OTOTp.png)
![](https://i.imgur.com/8EioARP.png)

**Generate your own board**

You can choose randomly the table which you are gonna play.



**You can register a user to play**

You can use a user to play, all the data is saved in a MongoDB database.

*Login screen*

![](https://i.imgur.com/ljiyg0s.png)

*Sign up screen*

![](https://i.imgur.com/JZnumdM.png)

## Diagrams

You can see how the thread part is implemented in the following diagram.

![](https://i.imgur.com/nQp5X3r.png)

You can find some descriptions about the code in the following documents (they are in spanish):

* [UML Docs](https://drive.google.com/file/d/1Zzzof6Ge7B-RerlQcHn705OND6Vh_fVo/view?usp=sharing)
* [Thread Docs](https://drive.google.com/file/d/1ns8m5U3h6fPApQtkqz7pjKi4Pnp1I83g/view?usp=sharing)


## Dependencies

You need the following dependencies:

* MongoDB Java Driver 3.12.0. [Download](https://mvnrepository.com/artifact/org.mongodb/mongo-java-driver/3.12.0)
* JavaFX 11.  [Download](https://gluonhq.com/products/javafx/)





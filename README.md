# tictactoe
Launch terminal in present directory.
Execute the below commands to run in docker

'cd neo'
'mvn clean install'
'docker build -t neo .'
'docker run -p 8080:8080 neo'

The application will start running on http://localhost:8080
Following are the APIs

1.To create a game:
curl --location --request POST 'http://localhost:8080/game' \
--header ': ' \
--header 'Content-Type: application/json' \
--data-raw '{
	"name" : "Arjun",
	"character" : "o"
}'

Above will return the Name of the player, character and Game ID in ASCII. Eg:
Player Name: Arjun
Player Character: o
Game UID: 0

2.To see the current status board of a game '0' where '0' is the the Game id:

curl --location --request GET 'http://localhost:8080/game/0'

Above will return the Board status. Eg:
Player Name: Arjun
Player Character: o

      A B C
   A | | | |
   B | | |x|
   C | | | |

Match Ongoing!

3. Make a move in the game '0', where 0 is the game id:

curl --location --request POST 'http://localhost:8080/game/0/move' \
--header 'Content-Type: application/json' \
--data-raw '{
	"row": "B",
	"column":"A"
}'

Above will return the Board status making the current move. Eg:
Player Name: Arjun
Player Character: o

      A B C
   A | | | |
   B |o| |x|
   C | |x| |

Match Ongoing!


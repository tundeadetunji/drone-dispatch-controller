# dispatch-controller

SpringBoot 2.7.4 (or 2.7.5)

w/H2 (InMemory Database)

10 Drones, 5 Medications preloaded

Battery Information is logged every 1 minute

InMemory Database details:

URL

http://localhost:8080/h2-console 

username

sa

password




### To register a drone:

url

http://localhost:8080/api/register

method

POST

sample request body
`{
    "serial":"abc",
    "model":"Lightweight",
    "weightLimit":200
}`

response
`{
    "id": 11,
    "serial": "abc",
    "model": "Lightweight",
    "weightLimit": 200.0,
    "batteryCapacity": 100.0,
    "state": "IDLE",
    "loadedMedication": []
}`



### To view a drone:

url

http://localhost:8080/api/drone

method

GET

sample request body
`{
    "id":1
}`

response
`{
    "id": 1,
    "serial": "drone1",
    "model": "Lightweight",
    "weightLimit": 500.0,
    "batteryCapacity": 100.0,
    "state": "IDLE",
    "loadedMedication": []
}`



### To view all drones:

url

http://localhost:8080/api/drones

method

GET



### To get battery capacity of a drone:

url

http://localhost:8080/api/battery

method

GET

sample request body
`{
    "id":1
}`

response
`{
    "id": 1,
    "serial": "drone1",
    "batteryCapacity": 100.0
}`



### To view available drones for loading (in IDLE or LOADING states) 
* returns drones that are either idle or loaded but not to weight limit (loading):

url

http://localhost:8080/api/available

method

GET



### To load a drone with medication:

url

http://localhost:8080/api/load

method

POST

sample request body
`{
    "name": "med1",
    "weight":100,
    "code": "code",
    "image": "image",
    "droneId": 1
}`

response
`{
    "id": 1,
    "serial": "drone1",
    "model": "Lightweight",
    "weightLimit": 500.0,
    "batteryCapacity": 100.0,
    "state": "LOADING",
    "loadedMedication": [
        {
            "id": 6,
            "name": "med1",
            "weight": 100.0,
            "code": "CODE",
            "image": "image",
            "droneId": 1
        }
    ]
}`



### To view a drone's list of loaded medications:

url

http://localhost:8080/api/loaded

method

GET

sample request body
`{
    "id":1
}`

response
`[
    {
        "id": 6,
        "name": "med1",
        "weight": 100.0,
        "code": "code",
        "image": "image",
        "droneId": 1
    }
]`



### To get list of saved medications:

url

http://localhost:8080/api/medications

method

GET


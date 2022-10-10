# ProvaTecnicaOMC-Java
 
Projecte back-end per a la prova tècnica de la empresa OhMyCode!, realitzada durant els dies 28 de setembre a 13 de octubre.

## Descripció

Es tracta de tota la part lògica del projecte, el qual rep les trucades que es fan a la API i es distribueix la informació que es sol·licita si les condicions es compleixen.

## Instal·lació i Ús

Primer de tot, cloneu el repositori dins de la vostra màquina local, o descarregueu el arxiu .zip amb els continguts del projecte i descomprimiu-lo on es desitgi. Tot seguit, obriu el editor de codi que s'estigui utilitzant (en el meu cas ha sigut Eclipse amb el plug-in de Spring), i inicieu el projecte com a Spring Boot App.

A continuació es deixen alguns links per a verificar el correcte funcionament de la API:

**GET -** http://localhost:8000/todos/all 

**GET -** http://localhost:8000/todos/1 

**GET -** http://localhost:8000/users/1/todos

**POST -** http://localhost:8000/users/1/todos/add

**PUT -** http://localhost:8000/users/1/todos/edit

**DELETE -** http://localhost:8000/todos/2/delete

<br />

***Nota:*** Per a les trucades POST i PUT, tenen que tenir un 'body' específic, que es disporarà a continuació:

### PUT
```
{
    "title": "El titol de la tasca",
    "completed": true/false
}
```
# hAPI friends

## Description du projet

hAPI-friends est un projet d'API réseau social basique effectué dans le cadre du cours Spring de M1 III, Université Catholique de Lille.

## Membres du groupe

Les membres du groupe (GROUPE 1 sur le fichier Excel) sont les suivants :
- Johann De Almeida
- Sébastien Guidez
- Gianni Giudice

## Fonctionnalités


Sur hapi-friends, il est possible d'effectuer les actions suivantes :

### Sécurité

- Créer un compte en envoyant à la route **/sign-up** un .json de cette forme : (**POST**)

```json
{
    "id": 4,
    "pseudo": "test",
    "password": "test",
    "surname": "Test",
    "firstname": "Test",
    "email": "test.test@lacatholille.fr",
    "mob_number": null,
    "friends": [
    ]
}
```

- Créer un compte en envoyant à la route **/sign-up2** les paramètres suivants : (**POST**) => version alternative de sign-up

    - pseudo
    - password
    - surname
    - firstname
    - email
    - number

- Connecter son compte en envoyant à la route **/sign-in** les paramètres suivants : (**POST**)
    - pseudo
    - password

### Utilisateur

- Afficher les utilisateurs existant en base via la route **/users** (**GET**)

- Afficher un utilisateur existant en base en renseignant son id via la route **/users/{id}** (**GET**)

- Supprimer un utilisateur existant en base en renseignant son id via la route **/users/{id}** (**DELETE**)

- Modifier un utilisateur existant en base en renseignant son id via la route **/users/{id}** et en envoyant les paramètres suivants : (**PUT**)

  - pseudo
  - surname
  - firstname
  - email
  - number

- Rechercher un utilisateur existant en base en renseignant son nom via la route **/users/{name}** (**GET**)

### Message

- Afficher les messages existant en base via la route **/posts** (**GET**)

- Afficher un message existant en base en renseignant son id via la route **/posts/{id}** (**GET**)

- Créer un message en envoyant à la route **/add** les paramètres suivants : (**POST**)

    - title
    - text
    - shared
    - user_id
    
- Supprimer un message existant en base en renseignant son id via la route **/posts/{id}** (**DELETE**)

- Modifier un message existant en base en renseignant son id via la route **/posts/{id}** et en envoyant les paramètres suivants : (**PUT**)

  - title
  - text
  - shared

- Rechercher un message existant en base par titre en renseignant son titre via la route **/posts/search/{name}** (**GET**)

- Rechercher un message existant en base par contenu en renseignant son contenu via la route **/posts/searchText/{text}** (**GET**)

### Ami


- Afficher les amis d'un utilisateur existant en base en renseignant son id via la route **/friends/{owner_id}** (**GET**)

- Rechercher un ami d'un utilisateur existant en base par pseudo en renseignant l'id de l'utilisateur et le pseudo de l'ami recherché via la route **/friends/search/{owner_id}/{name}** (**GET**)

- Afficher les demandes d'ami via la route **/friends/show-requests** (**GET**)

- Faire une demande d'ami en envoyant à la route **/friends/request** les paramètres suivants : (**POST**)
    - owner_id
    - to_add_id

- Répondre à une demande d'ami en envoyant à la route **/friends/reply** les paramètres suivants : (**POST**)
    - receiver_id
    - sender_id
    - response

- Supprimer un lien d'amitié via la route **/friends/{owner_id}/{to_delete_id}** (**DELETE**)

### Sécurisation de l'API via Auth0

Toutes les routes nécessitent une authentification, mises à part les deux routes permettant l'inscription et la route permettant la connexion.

### Collection Postman

Le fichier postman est à la racine du projet et a pour nom **hapifriends.postman_collection.json**.

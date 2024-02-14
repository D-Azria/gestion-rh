# **Gestion RH**

Le projet a pour objectif de créer un système de gestion des ressources humaines permettant de suivre l'ensemble des informations liées aux employés d'une entreprise.

## **Entités, Modules et Fonctionnalités**

### Fiche de Poste
Informations : Service de l’entreprise, nom du poste, catégorie du poste, missions, conditions de travail (télétravail, hybride, présentiel), compétences requises.
Un module permettra de gérer les fiches de poste pour chaque poste de travail.
Fonctionnalités :
* Création, modification et suppression de fiches de poste.
* Attribution des compétences requises et responsabilités.

### Employé
Informations : Nom, prénom, date de naissance, adresse, mail professionnel, diplômes, compétences, contrat, fiche de poste.
Un module permettra d’enregistrer et suivre les détails des employés.
Fonctionnalités :
* Création, modification et suppression du profil de l'employé.

### Contrat
Informations : Date d'embauche, le type de contrat, salaire, date de fin
Un module permettra de gérer les contrats.
Fonctionnalités :
* Création d’un contrat de travail.
* Gestion d’avenants


## **Processus d'authentication :**

1. L'utilisateur envoie une requête POST sur /login avec les informations d'identification (email et mot de passe) dans le corps de la requête.
2. La requête est interceptée par le filtre de sécurité configuré dans la méthode securityFilterChain de WebSecurityConfiguration.
3. Le filtre de sécurité vérifie les autorisations et applique les règles de sécurité définies dans WebSecurityConfiguration, telles que la validation des autorisations, la gestion des sessions, etc.
4. Si la requête respecte les règles de sécurité définies dans WebSecurityConfiguration, elle est ensuite transmise au contrôleur AuthenticationController pour traitement.
5. Dans AuthenticationController, la méthode login(UserDto userDto) est invoquée pour gérer la requête de connexion.
6. Dans AuthenticationServiceImpl, la méthode login(UserDto userDto) est exécutée pour gérer le processus d'authentification.
7. Dans AuthenticationServiceImpl, un UsernamePasswordAuthenticationToken (authenticationToken) est créé avec les informations d'identification fournies par l'utilisateur.
8. L'AuthenticationManager est utilisé pour authentifier l'utilisateur en appelant authenticate(authenticationToken).
9. L'AuthenticationManager traite la demande d'authentification en utilisant un gestionnaire de détails utilisateur, qui est configuré pour utiliser UserDetailsService.
10. Si les informations d'identification sont correctes :
* L'AuthenticationManager renvoie un objet Authentication contenant les détails de l'utilisateur authentifié.
* Le AuthenticationServiceImpl génère un token JWT en utilisant JWTUtils.
* Les détails de l'utilisateur authentifié sont extraits et utilisés pour construire le token JWT.
* Le token JWT est retourné dans la réponse HTTP avec le code de statut 200.

Si les informations d'identification sont incorrectes ou si le compte de l'utilisateur est verrouillé :
* L'AuthenticationManager lève une exception AuthenticationException.
* AuthenticationServiceImpl intercepte l'exception et retourne une réponse HTTP avec un code de statut 403 (Forbidden), indiquant que les informations d'identification sont incorrectes ou que le compte est verrouillé.

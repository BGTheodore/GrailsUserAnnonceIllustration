package fr.fullstack.project

class User {

    String username
    Date dateCreated

    static hasMany = [annonces: Annonce]

    static constraints = {
        username nullable: false, blank: false, size: 3..25, unique: true
    }
}

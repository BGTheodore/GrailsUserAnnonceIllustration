package fr.fullstack.project

import grails.converters.JSON
import grails.converters.XML
import org.springframework.http.HttpMethod

class ApiController {

//    Annonce singleton
    def annonce()
    {
        switch (request.getMethod())
        {
            case "GET":
//                1. Verifier si l'ID est bien fourni
                if (!params.id)
                    return response.status = 400
//                2. Changer l'instance correspondante et verifier qu'elle existe
                def annonceInstance = Annonce.get(params.id)
                if (!annonceInstance)
                    return response.status = 404
//                3. Serialiser l'instance recuperee a l'appelant
            response.withFormat {
                xml { render annonceInstance as XML}
                json { render annonceInstance as JSON}
            }
                break;
            case "PUT":
                break;
            case "PATCH":
                break;
            case "DELETE":
                break;
            default:
                return response.status = 405
            break
        }
        return response.status = 406
    }

//    Liste d'annonces
    def annonces()
    {
        switch (request.getMethod())
        {
            case "GET":
                def annonceList = Annonce.list()
                response.withFormat {
                    xml { render annonceList as XML}
                    json { render annonceList as JSON}
                }
                break;
            case "POST":
                break;
        }
    }

//    Utilisateur singleton
    def user()
    {
        switch (request.getMethod())
        {
            case "GET":
//                1. Verifier si l'ID est bien fourni
                if (!params.id)
                    return response.status = 400
//                2. Changer l'instance correspondante et verifier qu'elle existe
                def userInstance = User.get(params.id)
                if (!userInstance)
                    return response.status = 404
//                3. Serialiser l'instance recuperee a l'appelant
                response.withFormat {
                    xml { render userInstance as XML}
                    json { render userInstance as JSON}
                }
                break;
            case "PUT":
                break;
            case "PATCH":
                break;
            case "DELETE":
                break;
            default:
                return response.status = 405
                break
        }
        return response.status = 406
    }

//    Liste d'utilisateurs
    def users()
    {
        switch (request.getMethod())
        {
            case "GET":
                def userList = User.list()
                response.withFormat {
                    xml { render userList as XML}
                    json { render userList as JSON}
                }
                break;
            case "POST":
                break;
        }
    }
}

package mooc_project

import fr.fullstack.project.Annonce
import fr.fullstack.project.Illustration
import fr.fullstack.project.Role
import fr.fullstack.project.User
import fr.fullstack.project.UserRole

class BootStrap {

    def init = { servletContext ->

        def adminRole = new Role(authority: "ROLE_ADMIN").save()
        def clientRole = new Role(authority: "ROLE_CLIENT").save()

        def admin = new User(username: "admin", password: "admin").save()

        UserRole.create(admin, adminRole)

        ["Alice", "Bob", "Charlie"].each{
            String username ->
                def userInstance = new User(username: username, password: "password")
                (1..5).each {
                    Integer index ->
                        def annonceInstance = new Annonce(title: "Title $username $index",
                                description: "Description de l annonce $username $index",
                                price: 100*index,
                                active: Boolean.TRUE)
                        (1..5).each {
                            annonceInstance.addToIllustrations(
                                    new Illustration(filename: "filename_$username-$index-$it")
                            )
                        }
                        userInstance.addToAnnonces(annonceInstance)
                        userInstance.save(failOnError: true)

                }

                UserRole.create(userInstance, clientRole)

                UserRole.withSession{
                    //it.flush()
                    it.clear()
                }
        }
    }
    def destroy = {
    }
}

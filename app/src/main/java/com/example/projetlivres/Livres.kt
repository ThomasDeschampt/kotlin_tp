import android.content.Intent
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class Livres {
    var titre = ""
    var auteur = ""
    var description = ""
    var photo = ""
    var posseder = false
}

class LivresDAO {

    fun creerlivre(result: QuerySnapshot?): List<Livres> {
        val livres = mutableListOf<Livres>()
        if (result != null) {
            for (document in result) {
                val livre = Livres()
                livre.titre = document.data["titre"] as String
                livre.auteur = document.data["auteur"] as String
                livre.description = document.data["description"] as String
                livre.photo = document.data["photo"] as String
                livre.posseder = document.data["posseder"] as Boolean
                livres.add(livre)
            }
        }
        return livres
    }
}








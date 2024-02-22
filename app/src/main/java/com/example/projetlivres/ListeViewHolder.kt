import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetlivres.R
import com.squareup.picasso.Picasso

class ListeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun updatelivre(livres : Livres) {
        itemView.findViewById<TextView>(R.id.titre).text = livres.titre
        itemView.findViewById<TextView>(R.id.auteur).text = livres.auteur
        itemView.findViewById<TextView>(R.id.description).text = livres.description
        Picasso.get().load(livres.photo).into(itemView.findViewById<ImageView>(R.id.photo))
    }
}
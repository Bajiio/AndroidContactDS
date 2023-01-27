package fr.isen.cousseau.androidcontactds.adapter

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.cousseau.androidcontactds.R
import fr.isen.cousseau.androidcontactds.data.model.Contact
import fr.isen.cousseau.androidcontactds.data.model.Result

class MainAdapter(private var contactList: Array<Contact>, private val onClickFun: (Contact) -> Unit): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    class MainViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val nameView: TextView = itemView.findViewById(R.id.nameView)
        val addressView: TextView = itemView.findViewById(R.id.addressView)
        val stateView: TextView = itemView.findViewById(R.id.stateView)
        val emailView: TextView = itemView.findViewById(R.id.emailView)
        val imageView : ImageView = itemView.findViewById(R.id.imageview)

        val cardLinear: ConstraintLayout = itemView.findViewById(R.id.CardLinear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view, parent, false)

        return MainViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val ItemsViewModel = contactList[position]

        holder.nameView.text=ItemsViewModel.name.first +" "+ ItemsViewModel.name.last
        holder.addressView.text = ItemsViewModel.location.street.number.toString() + ", " + ItemsViewModel.location.street.name
        holder.stateView.text = ItemsViewModel.location.state + ", " + ItemsViewModel.location.city+ ", " + ItemsViewModel.location.country
        holder.emailView.text = ItemsViewModel.email
        if (ItemsViewModel.picture != null)
        {
            Picasso.get().load(ItemsViewModel.picture.medium).into(holder.imageView)
        }

        holder.cardLinear.setOnClickListener{
            onClickFun(ItemsViewModel)
        }

    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun updateData(filter: Result?) {
        if (filter != null) {
            contactList = filter.results
        }
        notifyDataSetChanged()
    }
}
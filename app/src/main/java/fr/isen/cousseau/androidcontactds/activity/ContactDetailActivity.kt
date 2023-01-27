package fr.isen.cousseau.androidcontactds.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import fr.isen.cousseau.androidcontactds.R
import fr.isen.cousseau.androidcontactds.data.model.Contact
import fr.isen.cousseau.androidcontactds.databinding.ActivityContactDetailBinding

class ContactDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailBinding
    private lateinit var contact: Contact

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
        binding=ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contact = intent.getSerializableExtra("contact") as Contact
        title=contact.name.first + " " + contact.name.last

        binding.mailDetail.text = contact.email
        binding.nameDetail.text = contact.name.first + " " + contact.name.last
        binding.locationDetail.text = contact.location.street.number.toString() + " " + contact.location.street.name + " " +
                "\n" + contact.location.city + ", " + contact.location.state + ", " + contact.location.country
        val dateParts = contact.registered.date.split("T")

        binding.birthDetail.text = dateParts[0]
        binding.phoneDetail.text=contact.cell
        Picasso.get().load(contact.picture.large).into(binding.imageDetail)

    }
}
package fr.isen.cousseau.androidcontactds.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.cousseau.androidcontactds.R
import fr.isen.cousseau.androidcontactds.data.model.Contact
import fr.isen.cousseau.androidcontactds.databinding.ActivityContactDetailBinding
import fr.isen.cousseau.androidcontactds.databinding.ActivityMainBinding

class ContactDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailBinding
    private lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
        binding=ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contact = intent.getSerializableExtra("contact") as Contact
        title=contact.name.first + " " + contact.name.last

    }
}
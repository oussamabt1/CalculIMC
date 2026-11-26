package com.example.calculimc   // adapter au nom de package de votre projet

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var editTextPoids: EditText
    private lateinit var editTextTaille: EditText
    private lateinit var buttonCalculer: Button
    private lateinit var buttonEffacer: Button
    private lateinit var textViewImc: TextView
    private lateinit var textViewCategorie: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextPoids = findViewById(R.id.editTextPoids)
        editTextTaille = findViewById(R.id.editTextTaille)
        buttonCalculer = findViewById(R.id.buttonCalculer)
        buttonEffacer = findViewById(R.id.buttonEffacer)
        textViewImc = findViewById(R.id.textViewImc)
        textViewCategorie = findViewById(R.id.textViewCategorie)

        buttonCalculer.setOnClickListener { calculerImc() }
        buttonEffacer.setOnClickListener { effacer() }
    }

    private fun calculerImc() {
        // 1. Récupérer les saisies (la virgule est acceptée comme séparateur décimal)
        val textePoids = editTextPoids.text.toString().trim().replace(',', '.')
        val texteTaille = editTextTaille.text.toString().trim().replace(',', '.')

        // Réinitialiser les erreurs précédentes
        editTextPoids.error = null
        editTextTaille.error = null

        // 2. Vérifier que les champs sont renseignés
        if (textePoids.isEmpty() || texteTaille.isEmpty()) {
            if (textePoids.isEmpty()) editTextPoids.error = getString(R.string.erreur_poids_vide)
            if (texteTaille.isEmpty()) editTextTaille.error = getString(R.string.erreur_taille_vide)
            Toast.makeText(this, R.string.erreur_champ_vide, Toast.LENGTH_SHORT).show()
            viderResultat()
            return
        }

        // 3. Convertir en nombres décimaux (null si la conversion échoue, ex. ".")
        val poids = textePoids.toDoubleOrNull()
        val taille = texteTaille.toDoubleOrNull()

        if (poids == null || taille == null) {
            if (poids == null) editTextPoids.error = getString(R.string.erreur_valeur_invalide)
            if (taille == null) editTextTaille.error = getString(R.string.erreur_valeur_invalide)
            viderResultat()
            return
        }

        // 4. Vérifier que les valeurs sont strictement positives
        if (poids <= 0 || taille <= 0) {
            if (poids <= 0) editTextPoids.error = getString(R.string.erreur_positif)
            if (taille <= 0) editTextTaille.error = getString(R.string.erreur_positif)
            Toast.makeText(this, R.string.erreur_positif, Toast.LENGTH_SHORT).show()
            viderResultat()
            return
        }

        // 5. Calculer l'IMC
        val imc = poids / (taille * taille)

        // 7. Déterminer la catégorie et la couleur
        val categorie: Int
        val couleur: Int
        if (imc < 18.5) {
            categorie = R.string.cat_insuffisance
            couleur = R.color.imc_orange
        } else if (imc < 25) {
            categorie = R.string.cat_normale
            couleur = R.color.imc_vert
        } else if (imc < 30) {
            categorie = R.string.cat_surpoids
            couleur = R.color.imc_orange
        } else if (imc < 35) {
            categorie = R.string.cat_obesite_moderee
            couleur = R.color.imc_rouge
        } else if (imc < 40) {
            categorie = R.string.cat_obesite_severe
            couleur = R.color.imc_rouge
        } else {
            categorie = R.string.cat_obesite_morbide
            couleur = R.color.imc_rouge_fonce
        }

        // 6 + 8. Arrondir à 2 décimales et afficher
        val imcTexte = String.format(Locale.getDefault(), "%.2f", imc)
        val couleurResolue = ContextCompat.getColor(this, couleur)

        textViewImc.text = getString(R.string.resultat_imc, imcTexte)
        textViewCategorie.text = getString(categorie)
        textViewImc.setTextColor(couleurResolue)
        textViewCategorie.setTextColor(couleurResolue)
    }

    private fun effacer() {
        editTextPoids.text.clear()
        editTextTaille.text.clear()
        editTextPoids.error = null
        editTextTaille.error = null
        viderResultat()
        editTextPoids.requestFocus()
    }

    private fun viderResultat() {
        textViewImc.text = ""
        textViewCategorie.text = ""
    }
}

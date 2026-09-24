package com.example.model


/**
 * Représente les catégories obligatoires pour la classification des dépenses dans EcoBudget.
 */


enum class Category(val emoji: String) {
    TRANSPORT("🚌"),
    ALIMENTATION("🍱"),
    LOISIRS("🎾"),
    LOGEMENT("🏠")
}


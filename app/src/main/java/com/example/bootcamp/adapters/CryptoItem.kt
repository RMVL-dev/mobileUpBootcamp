package com.example.bootcamp.adapters

data class CryptoItem (
    val symbol: String,
    val name: String,
    val currentPrice: String,
    val priceChangePercent: String,
    val imageUrl: String
        )
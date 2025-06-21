package com.uaike.randalf.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "wishes")
data class Wish(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val title: String,

    @Column
    val description: String? = null,

    @Column(nullable = false)
    val price: Double,

    @Column(name = "is_fulfilled", nullable = false)
    val isFulfilled: Boolean = false,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
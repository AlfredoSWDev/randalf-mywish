package com.uaike.randalf.service

import com.uaike.randalf.model.Wish
import com.uaike.randalf.repository.WishRepository
import org.springframework.stereotype.Service

@Service
class WishService(private val wishRepository: WishRepository) {

    fun getAllWishes(): List<Wish> = wishRepository.findAll()

    fun getWishById(id: Long): Wish? = wishRepository.findById(id).orElse(null)

    fun createWish(wish: Wish): Wish = wishRepository.save(wish)

    fun updateWish(id: Long, wish: Wish): Wish? {
        return if (wishRepository.existsById(id)) {
            wishRepository.save(wish.copy(id = id, updatedAt = java.time.LocalDateTime.now()))
        } else {
            null
        }
    }

    fun deleteWish(id: Long): Boolean {
        return if (wishRepository.existsById(id)) {
            wishRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}
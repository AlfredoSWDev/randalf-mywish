package com.uaike.randalf.controller

import com.uaike.randalf.model.Wish
import com.uaike.randalf.service.WishService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/wishes")
class WishController(private val wishService: WishService) {

    @GetMapping
    fun getAllWishes(): ResponseEntity<List<Wish>> {
        return ResponseEntity.ok(wishService.getAllWishes())
    }

    @GetMapping("/{id}")
    fun getWishById(@PathVariable id: Long): ResponseEntity<Wish> {
        return wishService.getWishById(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping
    fun createWish(@RequestBody wish: Wish): ResponseEntity<Wish> {
        return ResponseEntity.status(HttpStatus.CREATED).body(wishService.createWish(wish))
    }

    @PutMapping("/{id}")
    fun updateWish(@PathVariable id: Long, @RequestBody wish: Wish): ResponseEntity<Wish> {
        return wishService.updateWish(id, wish)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteWish(@PathVariable id: Long): ResponseEntity<Void> {
        return if (wishService.deleteWish(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
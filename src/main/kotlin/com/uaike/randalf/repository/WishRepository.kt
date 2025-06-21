package com.uaike.randalf.repository

import com.uaike.randalf.model.Wish
import org.springframework.data.jpa.repository.JpaRepository

interface WishRepository : JpaRepository<Wish, Long>
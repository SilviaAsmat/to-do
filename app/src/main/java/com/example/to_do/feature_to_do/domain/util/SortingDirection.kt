package com.example.to_do.feature_to_do.domain.util

sealed class SortingDirection {
    object Up: SortingDirection()
    object Down: SortingDirection()
}
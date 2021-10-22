package com.example.jetnote.domain.model

import com.example.jetnote.data.database.model.ColorDbModel

/**
 * Model class for one Color
 */
data class ColorModel(
  val id: Long,
  val name: String,
  val hex: String
) {

  companion object {

    val DEFAULT = with(ColorDbModel.DEFAULT_COLOR) { ColorModel(id, name, hex) }
  }
}

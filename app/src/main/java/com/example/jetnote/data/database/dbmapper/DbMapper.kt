package com.example.jetnote.data.database.dbmapper

import com.example.jetnote.data.database.model.ColorDbModel
import com.example.jetnote.data.database.model.NoteDbModel
import com.example.jetnote.data.repository.Repository
import com.example.jetnote.domain.model.ColorModel
import com.example.jetnote.domain.model.NoteModel

interface DbMapper {

  // NoteDbModel -> NoteModel

  fun mapNotes(
    noteDbModels: List<NoteDbModel>,
    colorDbModels: Map<Long, ColorDbModel>
  ): List<NoteModel>

  fun mapNote(noteDbModel: NoteDbModel, colorDbModel: ColorDbModel): NoteModel

  // ColorDbModel -> ColorModel

  fun mapColors(colorDbModels: List<ColorDbModel>): List<ColorModel>

  fun mapColor(colorDbModel: ColorDbModel): ColorModel

  // NoteModel -> NoteDbModel

  fun mapDbNote(note: NoteModel): NoteDbModel

  // ColorModel -> ColorDbModel

  fun mapDbColors(colors: List<ColorModel>): List<ColorDbModel>

  fun mapDbColor(color: ColorModel): ColorDbModel
}

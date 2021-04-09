package by.aermakova.habitat.model.useCase.weekdaysStrategy

import by.aermakova.habitat.R

enum class WeekdaysStrategy(val position: Int, val titleId: Int) {

    EVERYDAY(0, R.string.spinner_every_day),
    CHOOSE_MANUALLY(1, R.string.spinner_select_manually)
}
package by.aermakova.habitat.model.utilenums

import by.aermakova.habitat.R

enum class CardColor(val id: Int, val cardColorId: Int, val textColorId: Int) {
    RED(1, R.color.color_category_card_0, R.color.color_category_card_text_0),
    YELLOW(2, R.color.color_category_card_1, R.color.color_category_card_text_1),
    GREEN_LIGHT(3, R.color.color_category_card_2, R.color.color_category_card_text_2),
    GREEN(4, R.color.color_category_card_3, R.color.color_category_card_text_3),
    BLUE_LIGHT(5, R.color.color_category_card_4, R.color.color_category_card_text_4),
    BLUE(6, R.color.color_category_card_5, R.color.color_category_card_text_5),
    PURPLE(7, R.color.color_category_card_6, R.color.color_category_card_text_6);

    companion object {
        fun getColorById(id: Int): CardColor {
            return if (id < 1 || id > 7) {
                throw IllegalArgumentException("Color ID must be in the range of 1 to 7")
            } else {
                cardColors[id - 1]
            }
        }

        val cardColors: Array<CardColor> = values()
    }
}
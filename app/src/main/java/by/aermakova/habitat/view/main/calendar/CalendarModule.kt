package by.aermakova.habitat.view.main.calendar

import by.aermakova.habitat.model.di.module.ViewModelKey
import by.aermakova.habitat.model.useCase.calendar.ShowWeekCalendarUseCase
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class CalendarModule {

    @Provides
    fun provideShowWeekCalendarUseCase() = ShowWeekCalendarUseCase()

    @Provides
    @IntoMap
    @ViewModelKey(CalendarViewModel::class)
    fun provideViewModel(viewModel: CalendarViewModel) = viewModel
}
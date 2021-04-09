package by.aermakova.habitat.model.di.module

import by.aermakova.habitat.view.app.AppActivity
import by.aermakova.habitat.view.app.AppModule
import by.aermakova.habitat.view.custom.dialog.TimePickerFragment
import by.aermakova.habitat.view.custom.dialog.TimePickerModule
import by.aermakova.habitat.view.main.calendar.CalendarFragment
import by.aermakova.habitat.view.main.calendar.CalendarModule
import by.aermakova.habitat.view.main.category.addNew.AddNewCategoryFragment
import by.aermakova.habitat.view.main.category.addNew.AddNewCategoryModule
import by.aermakova.habitat.view.main.category.detail.CategoryItemFragment
import by.aermakova.habitat.view.main.category.detail.CategoryItemModule
import by.aermakova.habitat.view.main.dashboard.DashboardFragment
import by.aermakova.habitat.view.main.dashboard.DashboardModule
import by.aermakova.habitat.view.main.habit.AddNewHabitFragment
import by.aermakova.habitat.view.main.habit.AddNewHabitModule
import by.aermakova.habitat.view.onboarding.signup.SignUpFragment
import by.aermakova.habitat.view.onboarding.signup.SignUpModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector(modules = [AppModule::class])
    abstract fun provideAppActivity(): AppActivity

    @ContributesAndroidInjector(modules = [AddNewCategoryModule::class])
    abstract fun provideAddNewCategoryFragment(): AddNewCategoryFragment

    @ContributesAndroidInjector(modules = [AddNewHabitModule::class])
    abstract fun provideAddNewHabitFragment(): AddNewHabitFragment

    @ContributesAndroidInjector(modules = [DashboardModule::class])
    abstract fun provideDashboardFragment(): DashboardFragment

    @ContributesAndroidInjector(modules = [CalendarModule::class])
    abstract fun provideCalendarFragment(): CalendarFragment

    @ContributesAndroidInjector(modules = [CategoryItemModule::class])
    abstract fun provideCategoryItemFragment(): CategoryItemFragment

    @ContributesAndroidInjector(modules = [SignUpModule::class])
    abstract fun provideSignUpFragment(): SignUpFragment

    @ContributesAndroidInjector(modules = [TimePickerModule::class])
    abstract fun provideTimePickerDialogFragment(): TimePickerFragment
}
package com.picpay.desafio.android.main

import com.picpay.desafio.android.R
import com.picpay.desafio.android.robot.ActionRobot
import com.picpay.desafio.android.robot.ArrangeRobot
import com.picpay.desafio.android.robot.AssertRobot
import com.picpay.desafio.domain.entity.EmptyResult
import com.picpay.desafio.domain.usecase.UserUseCase
import io.mockk.coEvery
import org.mockito.ArgumentMatchers.anyString

fun arrange(
    userUseCase: UserUseCase,
    block: MainActivityRobot.() -> Unit
) {
    MainActivityRobot(userUseCase).block()
}

fun assert(
    assertObject: MainActivityAssertRobot = MainActivityAssertRobot(),
    block: MainActivityAssertRobot.() -> Unit
) {
    assertObject.block()
}

fun act(
    assertObject: MainActivityActionRobot = MainActivityActionRobot(),
    block: MainActivityActionRobot.() -> Unit
) {

}

class MainActivityRobot(
    private val userUseCase: UserUseCase
) : ArrangeRobot() {

    fun mockUsersList() {
        coEvery {
            userUseCase.getUser()
        } returns UserStub.getUserListSuccessResult()
    }

    fun mockEmpty() {
        coEvery {
            userUseCase.getUser()
        } returns EmptyResult(anyString(), anyString())
    }
}

class MainActivityAssertRobot {
    private val robot = AssertRobot()

    fun textIsVisible(text: String) = robot.textIsVisible(text = text)

    fun nameTextIs(position: Int, text: String) =
        robot.checkTextAtRecyclerViewItem(position, R.id.name, text, R.id.recyclerView)

}

class MainActivityActionRobot {
    private val robot = ActionRobot()
}
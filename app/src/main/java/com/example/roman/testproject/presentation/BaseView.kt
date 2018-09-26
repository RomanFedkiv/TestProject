package com.example.roman.testproject.presentation

interface BaseView<P : BasePresenter> {

    var presenter: P
}
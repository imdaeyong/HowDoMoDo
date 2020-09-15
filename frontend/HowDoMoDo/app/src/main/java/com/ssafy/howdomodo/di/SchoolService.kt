package com.ssafy.howdomodo.di

import org.koin.dsl.module

class SchoolService {
    var schoolName = "대구소프트웨어고등학교"

    fun moveSchool(newSchoolName : String){
        this.schoolName = newSchoolName
    }
}

class StudentController(val schoolService: SchoolService) {
    fun print(){
        println("현재 재학 중인 학교 : ${schoolService.schoolName}")
    }
}

val appModule = module {
    single { SchoolService() }
    single { StudentController(get()) }
}
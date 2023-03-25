package com.example.base.exceptions

class NoResetPasswordRequestedException extends BaseException{

    NoResetPasswordRequestedException(){
        super()
    }

    NoResetPasswordRequestedException(String message){
        super(message)
    }
}
